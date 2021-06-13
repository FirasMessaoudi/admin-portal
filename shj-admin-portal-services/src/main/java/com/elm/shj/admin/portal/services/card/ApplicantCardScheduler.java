/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.card;

import com.elm.shj.admin.portal.services.dto.ApplicantCardDto;
import com.elm.shj.admin.portal.services.dto.ECardStatus;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
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

    private final ApplicantRitualService applicantRitualService;
    private final ApplicantCardService applicantCardService;

    /**
     * Scheduled job to create cards for new applicant ritual records
     */
    @Scheduled(cron = "${scheduler.generate.card.applicant.ritual.cron}")
    @SchedulerLock(name = "generate-applicant-ritual-cards-task")
    public void generateIdsForNewApplicants() {
        LockAssert.assertLocked();
        applicantRitualService.findAllWithoutCards().forEach(applicantRitual -> {
            // generate and save the card
            applicantCardService.save(ApplicantCardDto.builder().applicantRitual(applicantRitual).statusCode(ECardStatus.READY_TO_PRINT.name()).build());
        });
    }


}
