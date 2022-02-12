/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.digitalid;

import com.elm.shj.admin.portal.services.applicant.*;
import com.elm.shj.admin.portal.services.dto.*;
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
    private final ApplicantEmergencyDataUploadService applicantEmergencyDataUploadService;

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
            ApplicantPackageDto savedApplicantPackage;

            // check if there is a record for applicant emergency data upload to get transportation details.
            ApplicantEmergencyDataUploadDto applicantEmergencyDataUpload = applicantEmergencyDataUploadService.findByBasicInfoAndPackageCode(ApplicantBasicInfoDto.fromApplicant(applicant),
                    applicant.getPackageReferenceNumber());
            String busNumber = applicantEmergencyDataUpload != null ? applicantEmergencyDataUpload.getBusNumber() : null;
            String seatNumber = applicantEmergencyDataUpload != null ? applicantEmergencyDataUpload.getSeatNumber() : null;

            //create or update applicant ritual;
            Long savedApplicantRitualId = applicantRitualService.findIdByApplicantIdAndPackageReferenceNumber(applicant.getId(), applicant.getPackageReferenceNumber());
            if (savedApplicantRitualId != null) {
                savedApplicantPackage = applicantPackageService.createApplicantPackage(applicant.getPackageReferenceNumber(), Long.parseLong(applicantDigitalId.getUin()), busNumber, seatNumber);
                if (savedApplicantPackage != null) {
                    applicantRitualService.updateApplicantRitualApplicantPackage(savedApplicantPackage.getId(), savedApplicantRitualId);
                }
            } else {
                savedApplicantPackage = applicantPackageService.createApplicantPackage(applicant.getPackageReferenceNumber(), Long.parseLong(applicantDigitalId.getUin()), null, null);
                if (savedApplicantPackage != null) {
                    ApplicantRitualDto applicantRitual = ApplicantRitualDto.builder().applicant(applicant).applicantPackage(savedApplicantPackage).packageReferenceNumber(applicant.getPackageReferenceNumber()).build();
                    applicantRitual = applicantRitualService.save(applicantRitual);
                    savedApplicantRitualId = applicantRitual.getId();
                }
            }
            //set applicant ritual id for applicant contacts, applicant health (if exist) and applicant relatives (if exist)
            applicantContactService.updateContactApplicantRitual(savedApplicantRitualId, applicant.getId());
            applicantHealthService.updateApplicantHealthApplicantRitual(savedApplicantRitualId, applicant.getId(), applicant.getPackageReferenceNumber());
            applicantRelativeService.updateApplicantRelativeApplicantRitual(savedApplicantRitualId, applicant.getId(), applicant.getPackageReferenceNumber());
        });
    }
}
