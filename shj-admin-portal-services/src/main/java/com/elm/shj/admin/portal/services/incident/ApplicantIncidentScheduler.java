/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.incident;

import com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo;
import com.elm.shj.admin.portal.orm.repository.ApplicantIncidentLiteRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantIncidentRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantIncidentComplaintVoCRM;
import com.elm.shj.admin.portal.services.dto.ComplaintUpdateCRMDto;
import com.elm.shj.admin.portal.services.dto.EIncidentStatus;
import com.elm.shj.admin.portal.services.dto.ETicketMainTypeCRM;
import com.elm.shj.admin.portal.services.integration.CrmAuthResponse;
import com.elm.shj.admin.portal.services.integration.IntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        List<ApplicantComplaintVo> incidents = applicantIncidentRepository.findAllCrm();
        log.info("Number of retrieved incidents is {}", (incidents == null || incidents.isEmpty()) ? 0 : incidents.size() );

        incidents.forEach(incident -> {
            try {
                CrmAuthResponse accessTokenWsResponse = integrationService.callCrmAuth();
                if (CrmAuthResponse.ECrmResponseStatus.SUCCESS.getCode() != accessTokenWsResponse.getResponseCode()) {
                    log.error("Authentication failed, cannot push incidents.");
                    return;
                }
                if (incident.getCrmTicketNumber() == null) {
                    try{
                        integrationService.callCRMCreateProfile(incident.getApplicantRitual(), incident.getMobileNumber(), accessTokenWsResponse);
                    } catch (Exception e){
                        log.error("Error creating user profile {}", e);
                    }
                    log.info("Start creating the CRM Ticket for Incident. {}", ETicketMainTypeCRM.Incident.getId());
                    ComplaintUpdateCRMDto updateCRMDto = integrationService.callCRMCreateTicket(incident, ETicketMainTypeCRM.Incident.getId(), accessTokenWsResponse);


                    applicantIncidentLiteRepository.updateCRMTicketNumber(incident.getId(), updateCRMDto.getCrmTicketNumber());
                    incident.setCrmTicketNumber(updateCRMDto.getCrmTicketNumber());
                }
                if (incident.getCrmTicketNumber() != null  && !incident.getStatusCode().equals(EIncidentStatus.UNDER_PROCESSING.name()) && (incident.getCrmStatusUpdated() == null || !incident.getCrmStatusUpdated())){
                    ApplicantIncidentComplaintVoCRM applicantComplaintVoCRM = new ApplicantIncidentComplaintVoCRM();
                    applicantComplaintVoCRM.setCrmTicketNumber(incident.getCrmTicketNumber());
                    applicantComplaintVoCRM.setStatus(EIncidentStatus.valueOf(incident.getStatusCode()).getCrmCode());
                    applicantComplaintVoCRM.setSmartIDTicketNumber(incident.getReferenceNumber());
                    applicantComplaintVoCRM.setResolutionComment(incident.getResolutionComment());

                    integrationService.callCRM(crmUpdateComplaintUrl, HttpMethod.POST, applicantComplaintVoCRM, accessTokenWsResponse.getToken(),
                                new ParameterizedTypeReference<ComplaintUpdateCRMDto>() {
                                });

                    applicantIncidentLiteRepository.updateCRMUpdateStatus(incident.getId());

                }
                log.info("incident successfully created #{}", incident.getId());
            } catch (Exception e) {
                log.error("Error while creating incident with {} id.", incident.getId(), e);
                log.error("Failed create incident of #{}, exception {}", incident.getId(), e.getMessage());

            }
        });
        log.debug("Generate applicants incidents scheduler finished...");
    }

}
