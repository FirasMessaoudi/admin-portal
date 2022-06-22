/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.complaint;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantComplaint;
import com.elm.shj.admin.portal.orm.repository.ApplicantComplaintRepository;
import com.elm.shj.admin.portal.services.dto.*;
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
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
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


    @Value("${crm.auth.url}")
    private String crmAuthUrl;
    @Value("${crm.url}")
    private String crmUrl;
    @Value("${crm.create.user.profile.url}")
    private String crmCreateUserProfileUrl;
    @Value("${crm.add.complaint.url}")
    private String crmCreateComplaintUrl;

    @Value("${crm.access.username}")
    private String crmAccessUsername;
    @Value("${crm.access.password}")
    private String crmAccessPassword;

    private final WebClient webClient;

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
        //TODO
        Date date = new Date(System.currentTimeMillis() - 3600 * 1000 * 24);
        List<JpaApplicantComplaint> complaints = applicantComplaintRepository.findTop50ByCreationDateLessThanEqualAndStatusCode(date, EComplaintStatus.UNDER_PROCESSING.getCode());

        complaints.stream().forEach(complaint -> {
            try {
                CrmAuthResponse accessTokenWsResponse = webClient.post().uri(crmUrl + crmAuthUrl)
                        .body(BodyInserters.fromValue(LoginRequestCRM.builder().username(crmAccessUsername).password(crmAccessPassword).build()))
                        .retrieve().bodyToMono(CrmAuthResponse.class).block();
                if (CrmAuthResponse.ECrmResponseStatus.SUCCESS.getCode() != accessTokenWsResponse.getResponseCode()) {
                    // cannot authenticate, throw an exception
                    // TODO: handle failure of authentication .
                }
                ApplicantCreateUserVoCRM user = new ApplicantCreateUserVoCRM();
                user.setCustomerType(1);
                user.setDigitalID(complaint.getApplicantRitual().getApplicant().getDigitalIds().get(0).getUin());
                user.setIdNumber(complaint.getApplicantRitual().getApplicant().getIdNumber());
                user.setPassportNumber(complaint.getApplicantRitual().getApplicant().getPassportNumber());
                user.setDateOfBirthHijri(complaint.getApplicantRitual().getApplicant().getDateOfBirthHijri().toString());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateOfBirth = simpleDateFormat.format(complaint.getApplicantRitual().getApplicant().getDateOfBirthGregorian());
                user.setDateOfBirth(dateOfBirth);
                user.setFullNameEn(complaint.getApplicantRitual().getApplicant().getFullNameEn());
                user.setFullNameAr(complaint.getApplicantRitual().getApplicant().getFullNameAr());
                user.setFullNameOrginalLang(complaint.getApplicantRitual().getApplicant().getFullNameOrigin());
                user.setGender(complaint.getApplicantRitual().getApplicant().getGender());
                user.setNationalityCode(complaint.getApplicantRitual().getApplicant().getNationalityCode());
                if (!complaint.getApplicantRitual().getApplicant().getContacts().isEmpty()) {
                    user.setEmail(complaint.getApplicantRitual().getApplicant().getContacts().get(0).getEmail());
                    if (complaint.getApplicantRitual().getApplicant().getContacts().get(0).getLocalMobileNumber() != null)
                        user.setMobileNumber(complaint.getApplicantRitual().getApplicant().getContacts().get(0).getLocalMobileNumber());
                    else
                        user.setMobileNumber(complaint.getApplicantRitual().getApplicant().getContacts().get(0).getIntlMobileNumber());
                }

                callCRM(crmCreateUserProfileUrl, HttpMethod.POST, user, accessTokenWsResponse.getToken(),
                        new ParameterizedTypeReference<ComplaintUpdateCRMDto>() {
                        });

                ApplicantCreateComplaintVoCRM newComplaint = new ApplicantCreateComplaintVoCRM();
                newComplaint.setDigitalID(complaint.getApplicantRitual().getApplicant().getDigitalIds().get(0).getUin());
                newComplaint.setIdNumber(complaint.getApplicantRitual().getApplicant().getIdNumber());
                newComplaint.setPassportNumber(complaint.getApplicantRitual().getApplicant().getPassportNumber());
                newComplaint.setNationalityCode(complaint.getApplicantRitual().getApplicant().getNationalityCode());
                newComplaint.setMainType(ETicketMainTypeCRM.Complaint.getId());
                newComplaint.setSmartIDTicketNumber(complaint.getReferenceNumber());
                newComplaint.setTicketDetails(complaint.getDescription());
                newComplaint.setTicketSubType(EComplaintType.valueOf(complaint.getTypeCode()).getCrmCode());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                newComplaint.setRegisterDateTime(format.format(complaint.getCreationDate()));
                newComplaint.setLocationLng(complaint.getLocationLng());
                newComplaint.setLocationLat(complaint.getLocationLat());
                newComplaint.setAttachmentId(String.valueOf(complaint.getComplaintAttachment().getId()));
                newComplaint.setCity(ECity.valueOf(complaint.getCity()).getCrmCode());
                newComplaint.setCampNumber(complaint.getCampNumber());

                callCRM(crmCreateComplaintUrl, HttpMethod.POST, newComplaint, accessTokenWsResponse.getToken(),
                        new ParameterizedTypeReference<ComplaintUpdateCRMDto>() {
                        });
                log.info("complaint successfully created #{}", complaint.getId());
            } catch (Exception e) {
                log.error("Failed create complaint of #{}", complaint.getId());

            }
        });
        log.debug("Generate applicants complaints scheduler finished...");
    }

    public <B, R> R callCRM(String serviceRelativeUrl, HttpMethod httpMethod, B bodyToSend, String token,
                            ParameterizedTypeReference<R> responseTypeReference)  {
        // check if no body
        if (bodyToSend == null) {

            return webClient.method(httpMethod).uri(crmUrl + serviceRelativeUrl).headers(header -> header.setBearerAuth(token))
                    .retrieve().bodyToMono(responseTypeReference).block();
        }
        return webClient.method(httpMethod).uri(crmUrl + serviceRelativeUrl).headers(header -> header.setBearerAuth(token))
                .body(BodyInserters.fromValue(bodyToSend)).retrieve().bodyToMono(responseTypeReference).block();
    }

}
