/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.digitalid;

import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.dto.ApplicantDigitalIdDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduler to generate automatically Applicant Digital ID
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DigitalIdScheduler {

    private final ApplicantService applicantService;
    private final DigitalIdService digitalIdService;

    /**
     * Scheduled job to create digital IDs for new applicants
     */
    @Scheduled(cron = "${scheduler.generate.digital.ids.cron}")
    @SchedulerLock(name = "generate-digital-ids-task")
    public void generateIdsForNewApplicants() {
        LockAssert.assertLocked();
        applicantService.findAllWithoutDigitalId().forEach(applicant -> {
            // generate and save digital id for each applicant
            digitalIdService.save(ApplicantDigitalIdDto.builder().applicant(applicant).uin(digitalIdService.generate(applicant)).build());
        });
    }


}
