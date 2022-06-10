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

    private final ApplicantLiteService applicantLiteService;
    private final DigitalIdService digitalIdService;
    private final ApplicantPackageService applicantPackageService;
    private final ApplicantRitualService applicantRitualService;
    private final ApplicantHealthService applicantHealthService;
    private final ApplicantRelativeService applicantRelativeService;
    private final ApplicantEmergencyDataUploadService applicantEmergencyDataUploadService;

    /**
     * Scheduled job to create digital IDs for new applicants
     */
//    @Scheduled(fixedDelay = 5000)
//    @SchedulerLock(name = "generate-digital-ids-task")
    public void generateIdsForNewApplicants() {
        log.debug("Generate applicants digital ids scheduler started...");
        LockAssert.assertLocked();
        applicantLiteService.findAllWithoutDigitalId().getContent().forEach(applicantLiteDto -> {
            // generate and save digital id for each applicant
            ApplicantDigitalIdDto applicantDigitalId = digitalIdService.save(ApplicantDigitalIdDto.builder()
                    .statusCode(EDigitalIdStatus.VALID.name())
                    .applicantId(applicantLiteDto.getId())
                    .uin(digitalIdService.generate(applicantLiteDto))
                    .build());

            // check if there is a record for applicant emergency data upload to get transportation details.
//            ApplicantEmergencyDataUploadDto applicantEmergencyDataUpload = applicantEmergencyDataUploadService.findByBasicInfoAndPackageCode(
//                    ApplicantBasicInfoDto.fromApplicantDigitalIdInfo(applicantLiteDto), applicantLiteDto.getPackageReferenceNumber());
//
//            String busNumber = applicantEmergencyDataUpload != null ? applicantEmergencyDataUpload.getBusNumber() : null;
//            String seatNumber = applicantEmergencyDataUpload != null ? applicantEmergencyDataUpload.getSeatNumber() : null;

            // create applicant package
            ApplicantPackageDto savedApplicantPackage = applicantPackageService.createApplicantPackage(applicantLiteDto.getPackageReferenceNumber(),
                    Long.parseLong(applicantDigitalId.getUin()), null, null);

            //create or update applicant ritual;
            Long savedApplicantRitualId = applicantRitualService.findAndUpdate(applicantLiteDto.getId(), applicantLiteDto.getPackageReferenceNumber(), savedApplicantPackage, true);
            //set applicant ritual id for applicant contacts, applicant health (if exist) and applicant relatives (if exist)
//            applicantHealthService.updateApplicantHealthApplicantRitual(savedApplicantRitualId, applicantLiteDto.getId(), applicantLiteDto.getPackageReferenceNumber());
//            applicantRelativeService.updateApplicantRelativeApplicantRitual(savedApplicantRitualId, applicantLiteDto.getId(), applicantLiteDto.getPackageReferenceNumber());
        });
        log.debug("Generate applicants digital ids scheduler finished...");
    }
}
