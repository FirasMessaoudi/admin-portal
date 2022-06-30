/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.incident;

import com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo;
import com.elm.shj.admin.portal.orm.repository.ApplicantIncidentLiteRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantIncidentRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.integration.CrmAuthResponse;
import com.elm.shj.admin.portal.services.integration.IntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Scheduler to generate automatically Incident for new added Incident to CRM after 24 hours without action
 *
 * @author othman alamoud
 * @since 1.2.6
 * */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicantIncidentScheduler {


    @Value("${generate.applicant.incident.scheduler.active.nodes}")
    private String schedulerActiveNodes;

    @Value("${crm.complaint.update.url}")
    private String crmUpdateComplaintUrl;

    private final IntegrationService integrationService;

    private final ApplicantIncidentRepository applicantIncidentRepository;
    private final ApplicantIncidentLiteRepository applicantIncidentLiteRepository;


    /**
     * Scheduled job to create incidents for new applicant records in CRM
     */
    @Scheduled(fixedDelayString = "${scheduler.generate.applicant.incident.delay.milliseconds}")
    @SchedulerLock(name = "generate-applicant-incident-task")
    public void sendIncidentToCRM() {
        String runningIpAddress;
        try {
            runningIpAddress = InetAddress.getLocalHost().getHostAddress();
            log.info("running IP address for potential applicant incident scheduler is: {}", runningIpAddress);
        } catch (UnknownHostException e) {
            log.error("Error while getting the running ip address. Applicant incident scheduler will not run.", e);
            return;
        }
        if (schedulerActiveNodes == null || schedulerActiveNodes.isEmpty()) {
            log.warn("Applicant incident scheduler will not run, no active nodes are configured in database.");
            return;
        }
        if (!schedulerActiveNodes.contains(runningIpAddress)) {
            log.warn("Applicant incident scheduler will not run, {} ip is not in the configured active nodes list.", runningIpAddress);
            return;
        }

        log.debug("Generate applicants incidents scheduler started...");
        LockAssert.assertLocked();
        List<ApplicantComplaintVo> incidents = applicantIncidentRepository.findAllCrm(EIncidentStatus.UNDER_PROCESSING.name());

        incidents.forEach(incident -> {
            try {
                CrmAuthResponse accessTokenWsResponse = integrationService.callCrmAuth();
                if (CrmAuthResponse.ECrmResponseStatus.SUCCESS.getCode() != accessTokenWsResponse.getResponseCode()) {
                    // cannot authenticate, throw an exception
                    // TODO: handle failure of authentication .
                }
                if (incident.getCrmTicketNumber() == null) {
                    integrationService.callCRMCreateProfile(incident.getApplicantRitual(), incident.getMobileNumber(), accessTokenWsResponse);

                    ComplaintUpdateCRMDto updateCRMDto = integrationService.callCRMCreateComplaint(incident, accessTokenWsResponse);


                    applicantIncidentLiteRepository.updateCRMTicketNumber(incident.getId(), updateCRMDto.getCrmTicketNumber());
                    incident.setCrmTicketNumber(updateCRMDto.getCrmTicketNumber());
                }
                if (incident.getCrmTicketNumber() != null  && !incident.getStatusCode().equals(EIncidentStatus.UNDER_PROCESSING.name()) && incident.getCrmStatusUpdated() == null && !incident.getCrmStatusUpdated()){
                    ApplicantIncidentComplaintVoCRM applicantComplaintVoCRM = new ApplicantIncidentComplaintVoCRM();
                    applicantComplaintVoCRM.setCrmTicketNumber(incident.getCrmTicketNumber());
                    applicantComplaintVoCRM.setStatus(EIncidentResolutionType.valueOf(incident.getStatusCode()).getCrmCode());
                    applicantComplaintVoCRM.setSmartIDTicketNumber(incident.getReferenceNumber());
                    applicantComplaintVoCRM.setResolutionComment(incident.getResolutionComment());

                    integrationService.callCRM(crmUpdateComplaintUrl, HttpMethod.POST, applicantComplaintVoCRM, accessTokenWsResponse.getToken(),
                                new ParameterizedTypeReference<ComplaintUpdateCRMDto>() {
                                });

                    applicantIncidentLiteRepository.updateCRMUpdateStatus(incident.getId());

                }
                log.info("incident successfully created #{}", incident.getId());
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Failed create incident of #{}, exception {}", incident.getId(), e.getMessage());

            }
        });
        log.debug("Generate applicants incidents scheduler finished...");
    }

}
