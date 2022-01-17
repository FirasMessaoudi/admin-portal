/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.digitalid;

import com.elm.shj.admin.portal.services.applicant.*;
import com.elm.shj.admin.portal.services.dto.ApplicantDigitalIdDto;
import com.elm.shj.admin.portal.services.dto.ApplicantPackageDto;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualDto;
import com.elm.shj.admin.portal.services.dto.EDigitalIdStatus;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
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
    private final ApplicantPackageService applicantPackageService;
    private final ApplicantRitualService applicantRitualService;
    private final ApplicantContactService applicantContactService;
    private final ApplicantHealthService applicantHealthService;
    private final ApplicantRelativeService applicantRelativeService;

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
            ApplicantDigitalIdDto applicantDigitalId = digitalIdService.save(ApplicantDigitalIdDto.builder()
                    .statusCode(EDigitalIdStatus.VALID.name())
                    .applicantId(applicant.getId())
                    .uin(digitalIdService.generate(applicant))
                    .build());

            // create applicant package
            ApplicantPackageDto savedApplicantPackage = null;

            //create or update applicant ritual;
            ApplicantRitualDto applicantRitual = applicantRitualService.findByApplicantIdAndPackageReferenceNumber(applicant.getId(), applicant.getPackageReferenceNumber());
            if (applicantRitual != null) {
                savedApplicantPackage = applicantPackageService.createApplicantPackage(applicantRitual.getPackageReferenceNumber(), Long.parseLong(applicantDigitalId.getUin()));
                applicantRitual.setApplicantPackage(savedApplicantPackage);
                applicantRitualService.save(applicantRitual);
            } else {
                savedApplicantPackage = applicantPackageService.createApplicantPackage(applicant.getPackageReferenceNumber(), Long.parseLong(applicantDigitalId.getUin()));
                applicantRitual = ApplicantRitualDto.builder().applicant(applicant).applicantPackage(savedApplicantPackage).packageReferenceNumber(applicant.getPackageReferenceNumber()).build();
                applicantRitual = applicantRitualService.save(applicantRitual);
                //set applicant ritual id for applicant contacts, applicant health (if exist) and applicant relatives (if exist)
                applicantContactService.updateContactApplicantRitual(applicantRitual.getId(), applicant.getId());
                applicantHealthService.updateApplicantHealthApplicantRitual(applicantRitual.getId(), applicant.getId(), applicantRitual.getPackageReferenceNumber());
                applicantRelativeService.updateApplicantRelativeApplicantRitual(applicantRitual.getId(), applicant.getId(), applicantRitual.getPackageReferenceNumber());
            }
        });
    }
}
