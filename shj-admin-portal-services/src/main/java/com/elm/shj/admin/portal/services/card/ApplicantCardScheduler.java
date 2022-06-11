/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.card;

import com.elm.shj.admin.portal.services.dto.ApplicantCardBasicDto;
import com.elm.shj.admin.portal.services.dto.Constants;
import com.elm.shj.admin.portal.services.dto.ECardStatus;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualBasicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduler to generate automatically Cards for new added applicantRituals
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantCardScheduler {

    private final ApplicantRitualBasicService applicantRitualBasicService;
    private final ApplicantCardBasicService applicantCardBasicService;
    private final ApplicantCardService applicantCardService;
    private final UserCardStatusAuditService userCardStatusAuditService;


    /**
     * Scheduled job to create cards for new applicant ritual records
     */
    @Scheduled(cron = "${scheduler.generate.card.applicant.ritual.cron}")
    //@Scheduled(fixedDelay = 1000)
    @SchedulerLock(name = "generate-applicant-ritual-cards-task")
    public void generateIdsForNewApplicants() {
        log.debug("Generate applicants cards scheduler started...");
        LockAssert.assertLocked();
        applicantRitualBasicService.findAllWithoutCards().getContent().forEach(applicantRitualBasic -> {
            // generate and save the card
            ApplicantCardBasicDto savedCard = applicantCardBasicService.save(ApplicantCardBasicDto.builder().applicantRitual(applicantRitualBasic).statusCode(ECardStatus.READY_TO_PRINT.name()).build());
            userCardStatusAuditService.saveUserBasicCardStatusAudit(savedCard, Constants.SYSTEM_USER_ID_NUMBER);

        });
        log.debug("Generate applicants cards scheduler finished...");
    }

    /**
     * Scheduled job to update card status based on ritual end date
     */
    @Scheduled(cron = "${scheduler.update.applicant.card.status.cron}")
    @SchedulerLock(name = "expire-ritual-applicant-card")
    public void expireRitualApplicantCard() {
        log.debug("Expire ritual applicant card scheduler started...");
        LockAssert.assertLocked();
        applicantCardService.markEligibleCardsAsExpired();
    }
}
