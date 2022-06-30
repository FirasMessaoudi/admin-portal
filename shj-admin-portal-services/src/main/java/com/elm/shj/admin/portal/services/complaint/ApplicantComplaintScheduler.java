/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.complaint;

import com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo;
import com.elm.shj.admin.portal.orm.repository.ApplicantComplaintRepository;
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
import java.util.Date;
import java.util.List;

/**
 * Scheduler to generate automatically Complaint for new added Complaint to CRM after 24 hours without action
 *
 * @author othman alamoud
 * @since 1.2.6
 * */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicantComplaintScheduler {


    @Value("${generate.applicant.complaint.scheduler.active.nodes}")
    private String schedulerActiveNodes;
    @Value("${complaint.period.minutes}")
    private Long complaintPeriodInMinutes;

    @Value("${crm.complaint.update.url}")
    private String crmUpdateComplaintUrl;

    private final IntegrationService integrationService;

    private final ApplicantComplaintRepository applicantComplaintRepository;


    /**
     * Scheduled job to create complaints for new applicant records in CRM
     */
    @Scheduled(fixedDelayString = "${scheduler.generate.applicant.complaint.delay.milliseconds}")
    @SchedulerLock(name = "generate-applicant-complaint-task")
    public void sendComplaintToCRM() {
        String runningIpAddress;
        try {
            runningIpAddress = InetAddress.getLocalHost().getHostAddress();
            log.info("running IP address for potential applicant complaint scheduler is: {}", runningIpAddress);
        } catch (UnknownHostException e) {
            log.error("Error while getting the running ip address. Applicant complaint scheduler will not run.", e);
            return;
        }
        if (schedulerActiveNodes == null || schedulerActiveNodes.isEmpty()) {
            log.warn("Applicant complaint scheduler will not run, no active nodes are configured in database.");
            return;
        }
        if (!schedulerActiveNodes.contains(runningIpAddress)) {
            log.warn("Applicant complaint scheduler will not run, {} ip is not in the configured active nodes list.", runningIpAddress);
            return;
        }

        log.debug("Generate applicants complaints scheduler started...");
        LockAssert.assertLocked();
        Date date = new Date(System.currentTimeMillis() - 60 * 1000 * complaintPeriodInMinutes);
        List<ApplicantComplaintVo> complaints = applicantComplaintRepository.findByCreationDateLessThanEqualAndStatusCode(date, EComplaintStatus.UNDER_PROCESSING.name());

        complaints.forEach(complaint -> {
            try {
                CrmAuthResponse accessTokenWsResponse = integrationService.callCrmAuth();
                if (CrmAuthResponse.ECrmResponseStatus.SUCCESS.getCode() != accessTokenWsResponse.getResponseCode()) {
                    // cannot authenticate, throw an exception
                    // TODO: handle failure of authentication .
                }
                if (complaint.getCrmTicketNumber() == null) {
                    integrationService.callCRMCreateProfile(complaint.getApplicantRitual(), complaint.getMobileNumber(), accessTokenWsResponse);

                    ComplaintUpdateCRMDto updateCRMDto = integrationService.callCRMCreateComplaint(complaint, accessTokenWsResponse);


                    applicantComplaintRepository.updateCRMTicketNumber(complaint.getId(), updateCRMDto.getCrmTicketNumber());
                    complaint.setCrmTicketNumber(updateCRMDto.getCrmTicketNumber());
                }


                if (complaint.getCrmTicketNumber() != null && !complaint.getStatusCode().equals(EIncidentStatus.UNDER_PROCESSING.name())  && (complaint.getCrmStatusUpdated() == null || !complaint.getCrmStatusUpdated())){
                    ApplicantIncidentComplaintVoCRM applicantComplaintVoCRM = new ApplicantIncidentComplaintVoCRM();
                    applicantComplaintVoCRM.setCrmTicketNumber(complaint.getCrmTicketNumber());
                    applicantComplaintVoCRM.setStatus(EComplaintStatus.valueOf(complaint.getStatusCode()).getCode());
                    applicantComplaintVoCRM.setSmartIDTicketNumber(complaint.getReferenceNumber());
                    applicantComplaintVoCRM.setResolutionComment(complaint.getResolutionComment());

                    integrationService.callCRM(crmUpdateComplaintUrl, HttpMethod.POST, applicantComplaintVoCRM, accessTokenWsResponse.getToken(),
                            new ParameterizedTypeReference<ComplaintUpdateCRMDto>() {
                            });

                    applicantComplaintRepository.updateCRMUpdateStatus(complaint.getId());

                }

                log.info("complaint successfully created #{}", complaint.getId());
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Failed create complaint of #{}, exception {}", complaint.getId(), e.getMessage());

            }
        });
        log.debug("Generate applicants complaints scheduler finished...");
    }

}
