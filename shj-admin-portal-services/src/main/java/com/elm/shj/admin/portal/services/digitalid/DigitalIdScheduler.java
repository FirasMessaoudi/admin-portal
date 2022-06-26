/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.digitalid;

import com.elm.shj.admin.portal.services.applicant.ApplicantBasicService;
import com.elm.shj.admin.portal.services.applicant.ApplicantHealthService;
import com.elm.shj.admin.portal.services.applicant.ApplicantPackageService;
import com.elm.shj.admin.portal.services.applicant.ApplicantRelativeService;
import com.elm.shj.admin.portal.services.dto.ApplicantDigitalIdDto;
import com.elm.shj.admin.portal.services.dto.ApplicantPackageDto;
import com.elm.shj.admin.portal.services.dto.EDigitalIdStatus;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
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
 * Scheduler to generate automatically Applicant Digital ID
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DigitalIdScheduler {

    private final ApplicantBasicService applicantBasicService;
    private final DigitalIdService digitalIdService;
    private final ApplicantPackageService applicantPackageService;
    private final ApplicantRitualService applicantRitualService;
    private final ApplicantHealthService applicantHealthService;
    private final ApplicantRelativeService applicantRelativeService;

    @Value("${generate.digital.ids.scheduler.active.nodes}")
    private String schedulerActiveNodes;

    /**
     * Scheduled job to create digital IDs for new applicants
     */
    @Scheduled(fixedDelayString = "${scheduler.generate.digital.ids.delay.milliseconds}")
    @SchedulerLock(name = "generate-digital-ids-task")
    public void generateIdsForNewApplicants() {
        String runningIpAddress;
        try {
            runningIpAddress = InetAddress.getLocalHost().getHostAddress();
            log.info("running IP address for potential digital id scheduler is: {}", runningIpAddress);
        } catch (UnknownHostException e) {
            log.error("Error while getting the running ip address. Digital Id scheduler will not run.", e);
            return;
        }
        if (schedulerActiveNodes == null || schedulerActiveNodes.isEmpty()) {
            log.warn("Digital Id scheduler will not run, no active nodes are configured in database.");
            return;
        }
        if (!schedulerActiveNodes.contains(runningIpAddress)) {
            log.warn("Digital Id scheduler will not run, {} ip is not in the configured active nodes list.");
            return;
        }
        log.debug("Generate applicants digital ids scheduler started...");
        LockAssert.assertLocked();
        applicantBasicService.findAllWithoutDigitalId().getContent().forEach(applicantBasicDto -> {
            // generate and save digital id for each applicant
            ApplicantDigitalIdDto applicantDigitalId = digitalIdService.save(ApplicantDigitalIdDto.builder()
                    .statusCode(EDigitalIdStatus.VALID.name())
                    .applicantId(applicantBasicDto.getId())
                    .uin(digitalIdService.generate(applicantBasicDto))
                    .build());

            // create applicant package
            ApplicantPackageDto savedApplicantPackage = applicantPackageService.createApplicantPackage(applicantBasicDto.getPackageReferenceNumber(),
                    Long.parseLong(applicantDigitalId.getUin()), null, null);

            if (savedApplicantPackage == null) {
                log.warn("no applicant ritual is created for {} uin and {} package reference number.", applicantDigitalId.getUin(), applicantBasicDto.getPackageReferenceNumber());
                return;
            }

            //create or update applicant ritual;
            Long savedApplicantRitualId = applicantRitualService.findAndUpdate(applicantBasicDto.getId(), applicantBasicDto.getPackageReferenceNumber(), savedApplicantPackage, true);
            //set applicant ritual id for applicant contacts, applicant health (if exist) and applicant relatives (if exist)
            applicantHealthService.updateApplicantHealthApplicantRitual(savedApplicantRitualId, applicantBasicDto.getId(), applicantBasicDto.getPackageReferenceNumber());
            applicantRelativeService.updateApplicantRelativeApplicantRitual(savedApplicantRitualId, applicantBasicDto.getId(), applicantBasicDto.getPackageReferenceNumber());
        });
        log.debug("Generate applicants digital ids scheduler finished...");
    }
}
