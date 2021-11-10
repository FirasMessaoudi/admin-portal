/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.card;

import com.elm.shj.admin.portal.services.dto.ApplicantCardDto;
import com.elm.shj.admin.portal.services.dto.Constants;
import com.elm.shj.admin.portal.services.dto.ECardStatus;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    private final ApplicantRitualService applicantRitualService;
    private final ApplicantCardService applicantCardService;
    private final UserCardStatusAuditService userCardStatusAuditService;


    /**
     * Scheduled job to create cards for new applicant ritual records
     */
    @Scheduled(cron = "${scheduler.generate.card.applicant.ritual.cron}")
    @SchedulerLock(name = "generate-applicant-ritual-cards-task")
    public void generateIdsForNewApplicants() {
        log.debug("Generate applicants cards scheduler started...");
        LockAssert.assertLocked();
        applicantRitualService.findAllWithoutCards().forEach(applicantRitual -> {
            // generate and save the card
            ApplicantCardDto savedCard = applicantCardService.save(ApplicantCardDto.builder().applicantRitual(applicantRitual).statusCode(ECardStatus.READY_TO_PRINT.name()).build());
            userCardStatusAuditService.saveUserCardStatusAudit(savedCard, Optional.of(Constants.SYSTEM_USER_ID_NUMBER));

        });
    }

    /**
     * Scheduled job to update card status based on ritual end date
     */
    @Scheduled(cron = "${scheduler.update.applicant.card.status.cron}")
    @SchedulerLock(name = "update-applicant-cards-status-task")
    public void updateApplicantCardStatusBasedOnRitualEndDate() {
        log.debug("Update applicants cards status scheduler started...");
        LockAssert.assertLocked();
        List<ApplicantCardDto> cardsList = applicantCardService.findAll();
        cardsList.parallelStream()
                .filter(card -> card.getApplicantRitual().getApplicantPackage() != null)
                .filter(card -> new Date().after(card.getApplicantRitual().getApplicantPackage().getEndDate()))
                .forEach(card -> {
                    card.setStatusCode(ECardStatus.EXPIRED.name());
                    userCardStatusAuditService.saveUserCardStatusAudit(card, Optional.of(Constants.SYSTEM_USER_ID_NUMBER));

                });

        applicantCardService.saveAll(cardsList);


    }


}
