/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.digitalid;

import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.dto.ApplicantDigitalIdDto;
import com.elm.shj.admin.portal.services.dto.EDigitalIdStatus;
import com.elm.shj.admin.portal.services.dto.EStaffDigitalIdStatus;
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
    private final CompanyStaffDigitalIdService companyStaffDigitalIdService;


    /**
     * Scheduled job to create digital IDs for new applicants
     */
    @Scheduled(cron = "${scheduler.generate.digital.ids.cron}")
    @SchedulerLock(name = "generate-digital-ids-task")
    public void generateIdsForNewApplicants() {
        log.debug("Generate applicants digital ids scheduler started...");
        LockAssert.assertLocked();
        applicantService.findAllWithoutDigitalId().forEach(applicant -> {
            // generate and save digital id for each applicant
            digitalIdService.save(ApplicantDigitalIdDto.builder().statusCode(EDigitalIdStatus.VALID.name()).applicant(applicant).uin(digitalIdService.generate(applicant)).build());
        });
    }


    /**
     * Scheduled job to create digital IDs for new  Company Staff
     */
    @Scheduled(cron = "${scheduler.generate.staff.digital.ids.cron}")
    @SchedulerLock(name = "generate-staff-digital-ids-task")
    public void generateIdsForNewCompanyStaff() {
        log.debug("Generate companyStaff digital ids scheduler started...");
        LockAssert.assertLocked();
        companyStaffDigitalIdService.findAllWithoutDigitalId().forEach(digitalId -> {
            // generate and save staff digital id for each company staff member
            digitalId.setSuin(companyStaffDigitalIdService.generate(digitalId.getCompanyStaff(),digitalId.getSeasonYear()));
            digitalId.setStatusCode(EStaffDigitalIdStatus.VALID.name());
            companyStaffDigitalIdService.save(digitalId);
        });
    }


}
