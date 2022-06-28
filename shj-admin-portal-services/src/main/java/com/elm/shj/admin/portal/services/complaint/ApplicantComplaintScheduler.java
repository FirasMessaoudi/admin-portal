/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.complaint;

import com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo;
import com.elm.shj.admin.portal.orm.repository.ApplicantComplaintLiteRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantComplaintRepository;
import com.elm.shj.admin.portal.services.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.apache.commons.lang3.StringUtils;
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
    @Value("${complaint.period.minutes}")
    private Long complaintPeriodInMinutes;


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
    private final ApplicantComplaintLiteRepository applicantComplaintLiteRepository;


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
        Date date = new Date(System.currentTimeMillis() - 60 * 1000 * complaintPeriodInMinutes);
        List<ApplicantComplaintVo> complaints = applicantComplaintRepository.findByCreationDateLessThanEqualAndStatusCode(date, EComplaintStatus.UNDER_PROCESSING.name());

        complaints.forEach(complaint -> {
            try {
                CrmAuthResponse accessTokenWsResponse = webClient.post().uri(crmUrl + crmAuthUrl)
                        .body(BodyInserters.fromValue(LoginRequestCRM.builder().username(crmAccessUsername).password(crmAccessPassword).build()))
                        .retrieve().bodyToMono(CrmAuthResponse.class).block();
                if (CrmAuthResponse.ECrmResponseStatus.SUCCESS.getCode() != accessTokenWsResponse.getResponseCode()) {
                    // cannot authenticate, throw an exception
                    // TODO: handle failure of authentication .
                }
                ApplicantCreateUserVoCRM user = new ApplicantCreateUserVoCRM();
                user.setCustomerType(ECustomerTypeCRM.PILGRIM.getCrmCode());
                user.setDigitalID(complaint.getApplicantRitual().getApplicant().getUin());
                if (complaint.getApplicantRitual().getApplicant().getIdNumber() != null)
                    user.setIdNumber(complaint.getApplicantRitual().getApplicant().getIdNumber());
                else
                    user.setIdNumber(StringUtils.EMPTY);
                if (complaint.getApplicantRitual().getApplicant().getPassportNumber() != null )
                    user.setPassportNumber(complaint.getApplicantRitual().getApplicant().getPassportNumber());
                else
                    user.setPassportNumber(StringUtils.EMPTY);
                if (complaint.getApplicantRitual().getApplicant().getDateOfBirthHijri() != null)
                    user.setDateOfBirthHijri(complaint.getApplicantRitual().getApplicant().getDateOfBirthHijri().toString());
                else
                    user.setDateOfBirthHijri(StringUtils.EMPTY);
                if (complaint.getApplicantRitual().getApplicant().getDateOfBirthGregorian() != null) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String dateOfBirth = simpleDateFormat.format(complaint.getApplicantRitual().getApplicant().getDateOfBirthGregorian());
                    user.setDateOfBirth(dateOfBirth);
                } else
                    user.setDateOfBirth(StringUtils.EMPTY);
                if (complaint.getApplicantRitual().getApplicant().getFullNameEn() != null )
                    user.setFullNameEn(complaint.getApplicantRitual().getApplicant().getFullNameEn());
                else
                    user.setFullNameEn(StringUtils.EMPTY);
                if (complaint.getApplicantRitual().getApplicant().getFullNameAr() != null )
                    user.setFullNameAr(complaint.getApplicantRitual().getApplicant().getFullNameAr());
                else
                    user.setFullNameAr(StringUtils.EMPTY);
                if (complaint.getApplicantRitual().getApplicant().getFullNameOrigin() != null )
                    user.setFullNameOrginalLang(complaint.getApplicantRitual().getApplicant().getFullNameOrigin());
                else
                    user.setFullNameOrginalLang(StringUtils.EMPTY);
                user.setGender(complaint.getApplicantRitual().getApplicant().getGender());
                user.setNationalityCode(complaint.getApplicantRitual().getApplicant().getNationalityCode());

                if (complaint.getApplicantRitual().getApplicant().getEmail() != null )
                    user.setEmail(complaint.getApplicantRitual().getApplicant().getEmail());
                else
                    user.setEmail(StringUtils.EMPTY);
                user.setMobileNumber(complaint.getMobileNumber());


                CreateUserCRMDto createUserCRMDto = callCRM(crmCreateUserProfileUrl, HttpMethod.POST, user, accessTokenWsResponse.getToken(),
                        new ParameterizedTypeReference<CreateUserCRMDto>() {
                        });

                ApplicantCreateComplaintVoCRM newComplaint = new ApplicantCreateComplaintVoCRM();
                newComplaint.setDigitalID(complaint.getApplicantRitual().getApplicant().getUin());
                if (complaint.getApplicantRitual().getApplicant().getIdNumber() != null )
                    newComplaint.setIdNumber(complaint.getApplicantRitual().getApplicant().getIdNumber());
                else
                    newComplaint.setIdNumber(StringUtils.EMPTY);
                if (complaint.getApplicantRitual().getApplicant().getPassportNumber() != null )
                    newComplaint.setPassportNumber(complaint.getApplicantRitual().getApplicant().getPassportNumber());
                else
                    newComplaint.setPassportNumber(StringUtils.EMPTY);
                newComplaint.setNationalityCode(complaint.getApplicantRitual().getApplicant().getNationalityCode());
                newComplaint.setMainType(ETicketMainTypeCRM.Complaint.getId());
                newComplaint.setSmartIDTicketNumber(complaint.getReferenceNumber());
                newComplaint.setTicketDetails(complaint.getDescription());
                newComplaint.setTicketSubType(EComplaintType.valueOf(complaint.getTypeCode()).getCrmCode());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                newComplaint.setRegisterDateTime(format.format(complaint.getCreationDate()));
                if (complaint.getLocationLng() != null )
                    newComplaint.setLocationLng(complaint.getLocationLng());
                else
                    newComplaint.setLocationLng(0D);
                if (complaint.getLocationLat() != null )
                    newComplaint.setLocationLat(complaint.getLocationLat());
                else
                    newComplaint.setLocationLat(0D);
                if (complaint.getComplaintAttachment() != null && complaint.getComplaintAttachment().getId() > 0)
                    newComplaint.setAttachmentId(String.valueOf(complaint.getComplaintAttachment().getId()));
                else
                    newComplaint.setAttachmentId(StringUtils.EMPTY);
                if (complaint.getCity() != null)
                    newComplaint.setCity(ECity.valueOf(complaint.getCity()).getCrmCode());
                else
                    newComplaint.setCity(ECity.OTHERS.getCrmCode());
                if (complaint.getCampNumber() != null)
                    newComplaint.setCampNumber(complaint.getCampNumber());
                else
                    newComplaint.setCampNumber(StringUtils.EMPTY);
                newComplaint.setAttachmentType(StringUtils.EMPTY);

                ComplaintUpdateCRMDto updateCRMDto = callCRM(crmCreateComplaintUrl, HttpMethod.POST, newComplaint, accessTokenWsResponse.getToken(),
                        new ParameterizedTypeReference<ComplaintUpdateCRMDto>() {
                        });


                applicantComplaintRepository.updateCRMTicketNumber(complaint.getId(), updateCRMDto.getCrmTicketNumber());
                log.info("complaint successfully created #{}", complaint.getId());
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Failed create complaint of #{}, exception {}", complaint.getId(), e.getMessage());

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
