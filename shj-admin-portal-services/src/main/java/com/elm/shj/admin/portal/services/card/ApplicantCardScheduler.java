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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

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

    @Value("${generate.applicant.card.scheduler.active.nodes}")
    private String schedulerActiveNodes;


    /**
     * Scheduled job to create cards for new applicant ritual records
     */
    @Scheduled(fixedDelayString = "${scheduler.generate.card.applicant.ritual.delay.milliseconds}")
    public void generateIdsForNewApplicants() {
        String runningIpAddress;
        try {
            runningIpAddress = InetAddress.getLocalHost().getHostAddress();
            log.info("running IP address for potential applicant card scheduler is: {}", runningIpAddress);
        } catch (UnknownHostException e) {
            log.error("Error while getting the running ip address. Applicant card scheduler will not run.", e);
            return;
        }
        if (schedulerActiveNodes == null || schedulerActiveNodes.isEmpty()) {
            log.warn("Applicant card scheduler will not run, no active nodes are configured in database.");
            return;
        }
        if (!schedulerActiveNodes.contains(runningIpAddress)) {
            log.warn("Applicant card scheduler will not run, {} ip is not in the configured active nodes list.");
            return;
        }
        log.debug("Generate applicants cards scheduler started...");
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
//    @Scheduled(cron = "${scheduler.update.applicant.card.status.cron}")
//    @SchedulerLock(name = "expire-ritual-applicant-card")
    public void expireRitualApplicantCard() {
        log.debug("Expire ritual applicant card scheduler started...");
        LockAssert.assertLocked();
        applicantCardService.markEligibleCardsAsExpired();
    }
}
