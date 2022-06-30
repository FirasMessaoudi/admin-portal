/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.integration;

import com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo;
import com.elm.shj.admin.portal.orm.entity.ApplicantRitualVo;
import com.elm.shj.admin.portal.orm.repository.ApplicantIncidentLiteRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualBasicRepository;
import com.elm.shj.admin.portal.services.complaint.CrmAuthResponse;
import com.elm.shj.admin.portal.services.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.SimpleDateFormat;


/**
 * Service handling dashboard related operations
 *
 * @author othman alamoud
 * @since 1.3.2
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IntegrationService {

    private final WebClient webClient;

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

    private final ApplicantRitualBasicRepository applicantRitualBasicRepository;
    private final ApplicantIncidentLiteRepository applicantIncidentLiteRepository ;


    public void callCRMCreateProfile(ApplicantRitualVo ritual, String mobileNumber, CrmAuthResponse accessTokenWsResponse) {
        ApplicantCreateUserVoCRM user = new ApplicantCreateUserVoCRM();
        user.setCustomerType(ECustomerTypeCRM.PILGRIM.getCrmCode());
        user.setDigitalID(ritual.getApplicant().getUin());
        if (ritual.getApplicant().getIdNumber() != null)
            user.setIdNumber(ritual.getApplicant().getIdNumber());
        else
            user.setIdNumber(StringUtils.EMPTY);
        if (ritual.getApplicant().getPassportNumber() != null )
            user.setPassportNumber(ritual.getApplicant().getPassportNumber());
        else
            user.setPassportNumber(StringUtils.EMPTY);
        if (ritual.getApplicant().getDateOfBirthHijri() != null)
            user.setDateOfBirthHijri(ritual.getApplicant().getDateOfBirthHijri().toString());
        else
            user.setDateOfBirthHijri(StringUtils.EMPTY);
        if (ritual.getApplicant().getDateOfBirthGregorian() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateOfBirth = simpleDateFormat.format(ritual.getApplicant().getDateOfBirthGregorian());
            user.setDateOfBirth(dateOfBirth);
        } else
            user.setDateOfBirth(StringUtils.EMPTY);
        if (ritual.getApplicant().getFullNameEn() != null )
            user.setFullNameEn(ritual.getApplicant().getFullNameEn());
        else
            user.setFullNameEn(StringUtils.EMPTY);
        if (ritual.getApplicant().getFullNameAr() != null )
            user.setFullNameAr(ritual.getApplicant().getFullNameAr());
        else
            user.setFullNameAr(StringUtils.EMPTY);
        if (ritual.getApplicant().getFullNameOrigin() != null )
            user.setFullNameOrginalLang(ritual.getApplicant().getFullNameOrigin());
        else
            user.setFullNameOrginalLang(StringUtils.EMPTY);
        user.setGender(ritual.getApplicant().getGender());
        user.setNationalityCode(ritual.getApplicant().getNationalityCode());

        if (ritual.getApplicant().getEmail() != null )
            user.setEmail(ritual.getApplicant().getEmail());
        else
            user.setEmail(StringUtils.EMPTY);
        user.setMobileNumber(mobileNumber);


        CreateUserCRMDto createUserCRMDto = callCRM(crmCreateUserProfileUrl, HttpMethod.POST, user, accessTokenWsResponse.getToken(),
                new ParameterizedTypeReference<CreateUserCRMDto>() {
                });
    }

    public ComplaintUpdateCRMDto callCRMCreateComplaint(ApplicantComplaintVo complaint, CrmAuthResponse accessTokenWsResponse) {
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

        return callCRM(crmCreateComplaintUrl, HttpMethod.POST, newComplaint, accessTokenWsResponse.getToken(),
                new ParameterizedTypeReference<ComplaintUpdateCRMDto>() {
                });
    }

    public ComplaintUpdateCRMDto callCRMCreateIncident(ApplicantIncidentLiteDto incident, ApplicantRitualVo ritual, CrmAuthResponse accessTokenWsResponse) {
        ApplicantCreateComplaintVoCRM newComplaint = new ApplicantCreateComplaintVoCRM();
        newComplaint.setDigitalID(ritual.getApplicant().getUin());
        if (ritual.getApplicant().getIdNumber() != null )
            newComplaint.setIdNumber(ritual.getApplicant().getIdNumber());
        else
            newComplaint.setIdNumber(StringUtils.EMPTY);
        if (ritual.getApplicant().getPassportNumber() != null )
            newComplaint.setPassportNumber(ritual.getApplicant().getPassportNumber());
        else
            newComplaint.setPassportNumber(StringUtils.EMPTY);
        newComplaint.setNationalityCode(ritual.getApplicant().getNationalityCode());
        newComplaint.setMainType(ETicketMainTypeCRM.Incident.getId());
        newComplaint.setSmartIDTicketNumber(incident.getReferenceNumber());
        newComplaint.setTicketDetails(incident.getDescription());
        newComplaint.setTicketSubType(EComplaintType.valueOf(incident.getTypeCode()).getCrmCode());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        newComplaint.setRegisterDateTime(format.format(incident.getCreationDate()));
        if (incident.getLocationLng() != null )
            newComplaint.setLocationLng(incident.getLocationLng());
        else
            newComplaint.setLocationLng(0D);
        if (incident.getLocationLat() != null )
            newComplaint.setLocationLat(incident.getLocationLat());
        else
            newComplaint.setLocationLat(0D);
        if (incident.getIncidentAttachment() != null && incident.getIncidentAttachment().getId() > 0)
            newComplaint.setAttachmentId(String.valueOf(incident.getIncidentAttachment().getId()));
        else
            newComplaint.setAttachmentId(StringUtils.EMPTY);
        if (incident.getCity() != null)
            newComplaint.setCity(ECity.valueOf(incident.getCity()).getCrmCode());
        else
            newComplaint.setCity(ECity.OTHERS.getCrmCode());
        if (incident.getCampNumber() != null)
            newComplaint.setCampNumber(incident.getCampNumber());
        else
            newComplaint.setCampNumber(StringUtils.EMPTY);
        newComplaint.setAttachmentType(StringUtils.EMPTY);

        ComplaintUpdateCRMDto updateCRMDto = callCRM(crmCreateComplaintUrl, HttpMethod.POST, newComplaint, accessTokenWsResponse.getToken(),
                new ParameterizedTypeReference<ComplaintUpdateCRMDto>() {
                });
        return updateCRMDto;
    }

    public CrmAuthResponse callCrmAuth() {
        CrmAuthResponse accessTokenWsResponse = webClient.post().uri(crmUrl + crmAuthUrl)
                .body(BodyInserters.fromValue(LoginRequestCRM.builder().username(crmAccessUsername).password(crmAccessPassword).build()))
                .retrieve().bodyToMono(CrmAuthResponse.class).block();
        return accessTokenWsResponse;
    }

    private  <B, R> R callCRM(String serviceRelativeUrl, HttpMethod httpMethod, B bodyToSend, String token,
                            ParameterizedTypeReference<R> responseTypeReference)  {
        // check if no body
        if (bodyToSend == null) {

            return webClient.method(httpMethod).uri(crmUrl + serviceRelativeUrl).headers(header -> header.setBearerAuth(token))
                    .retrieve().bodyToMono(responseTypeReference).block();
        }
        return webClient.method(httpMethod).uri(crmUrl + serviceRelativeUrl).headers(header -> header.setBearerAuth(token))
                .body(BodyInserters.fromValue(bodyToSend)).retrieve().bodyToMono(responseTypeReference).block();
    }

    @Async
    public void callCRMIncident(ApplicantIncidentLiteDto incident) {
        try {
            CrmAuthResponse accessTokenWsResponse = callCrmAuth();
            if (CrmAuthResponse.ECrmResponseStatus.SUCCESS.getCode() != accessTokenWsResponse.getResponseCode()) {
                // cannot authenticate, throw an exception
                // TODO: handle failure of authentication .
            }
            ApplicantRitualVo ritual = applicantRitualBasicRepository.findByIdForCrm(incident.getApplicantRitualId());

            callCRMCreateProfile(ritual, incident.getMobileNumber(), accessTokenWsResponse);

            ComplaintUpdateCRMDto updateCRMDto = callCRMCreateIncident(incident, ritual, accessTokenWsResponse);


            applicantIncidentLiteRepository.updateCRMTicketNumber(incident.getId(), updateCRMDto.getCrmTicketNumber());
            log.info("complaint successfully created #{}", incident.getId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed create complaint of #{}, exception {}", incident.getId(), e.getMessage());

        }
    }
}
