/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.entity.*;
import com.elm.shj.admin.portal.orm.repository.NotificationRequestRepository;
import com.elm.shj.admin.portal.orm.repository.UserNotificationRepository;
import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service handling Notification Request
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class NotificationRequestService extends GenericService<JpaNotificationRequest, NotificationRequestDto, Long> {

    public static final String PASSWORD_EXPIRATION_TEMPLATE_NAME = "PASSWORD_EXPIRATION";
    public static final String PASSWORD_EXPIRY_TEMPLATE_DAYS_TO_EXPIRY_PARAMETER_NAME = "days_to_expiry";
    private static final String NOTIFICATION_DEFAULT_LANGUAGE = "ar";

    private final NotificationTemplateService notificationTemplateService;
    private final NotificationRequestRepository notificationRequestRepository;
    private final UserNotificationRepository userNotificationRepository;
    private final ApplicantService applicantService;
    private final CompanyStaffService companyStaffService;

    /**
     * will handle processing of user Notifications in notification processing scheduler
     *
     * @param notificationRequest the   notification request to be processed
     */
    @Transactional
    public void processNotificationRequest(JpaNotificationRequest notificationRequest) {
        log.debug("Start processing notification request with ID {} ", notificationRequest.getId());
        JpaUserNotification userNotification = new JpaUserNotification();
        userNotification.setNotificationTemplate(notificationRequest.getNotificationTemplate());
        userNotification.setStatusCode(EUserNotificationStatus.NEW.name());
        userNotification.setUserId(notificationRequest.getUserId());
        userNotification.setUserLang(notificationRequest.getUserLang());
        Optional<JpaNotificationTemplateContent> notificationTemplateContent = notificationRequest.getNotificationTemplate().getNotificationTemplateContents().stream().filter(content -> content.getLang().equalsIgnoreCase(notificationRequest.getUserLang())).findAny();
        String resolvedBody = resolveNotificationRequestBody(notificationRequest.getNotificationTemplate(), notificationRequest.getNotificationRequestParameterValues(), notificationTemplateContent);
        if (resolvedBody.equals("no content")) {
            log.error("no Template content found for template ID  " + notificationRequest.getNotificationTemplate().getId() + "\t  for user language code " + notificationRequest.getUserLang());
        }

        userNotification.setResolvedBody(resolvedBody);
        userNotificationRepository.save(userNotification);
        notificationRequestRepository.delete(notificationRequest);
        log.debug("End processing notification request with ID   {} ", notificationRequest.getId());
    }

    //private method to resolve notification request body by replacing every parameter by it's value
    private String resolveNotificationRequestBody(JpaNotificationTemplate notificationTemplate, Set<JpaNotificationRequestParameterValue> notificationRequestParameterValues, Optional<JpaNotificationTemplateContent> notificationContent) {
        String emptyBody = "no content";
        if (!notificationContent.isPresent()) {
            return emptyBody;
        }
        log.debug("Start resolving notification request body the unresolved body is {}", notificationContent.get().getBody());
        String resolvedBody = notificationContent.get().getBody();
        if (resolvedBody != null && !resolvedBody.isEmpty()) {
            for (JpaNotificationRequestParameterValue param : notificationRequestParameterValues) {
                Optional<JpaNotificationTemplateParameter> templateParameter = notificationTemplate.getNotificationTemplateParameters().stream().filter(tparam -> tparam.getId() == param.getNotificationTemplateParameterId()).findFirst();
                if (!templateParameter.isPresent()) {
                    log.error("no Template parameters found for template ID  " + notificationTemplate.getId());
                }
                resolvedBody = resolvedBody.replaceAll("<" + templateParameter.get().getParameterName() + ">", param.getNotificationTemplateParameterValue());
            }
            log.debug("End resolving notification request body the resolved body is {}", resolvedBody);
            return resolvedBody;
        }
        log.debug("End resolving notification request body is {empty}");
        return emptyBody;
    }


    /**
     * save Password Expiry Notification Request Details
     *
     * @param passwordExpiryNotificationRequest the Password Expiry Notification Request Details to save
     */
    public void savePasswordExpiryNotificationRequest(PasswordExpiryNotificationRequest passwordExpiryNotificationRequest) throws NotFoundException {
        log.info("Start savePasswordExpiryNotificationRequest");
        Optional<NotificationTemplateDto> notificationTemplate = notificationTemplateService.findEnabledNotificationTemplateByNameCode(PASSWORD_EXPIRATION_TEMPLATE_NAME);

        if (notificationTemplate == null || !notificationTemplate.isPresent()) {
            log.debug("notification template not found");
            throw new NotFoundException("no Template found for  " + PASSWORD_EXPIRATION_TEMPLATE_NAME);
        }

        passwordExpiryNotificationRequest.getUserParametersList().forEach(
                param -> {
                    NotificationRequestDto notificationRequest = new NotificationRequestDto();
                    notificationRequest.setNotificationTemplate(notificationTemplate.get());
                    notificationRequest.setUserLang(param.getUserLang());
                    notificationRequest.setUserId(param.getUserId());
                    notificationRequest.setProcessingStatus(NotificationProcessingStatusLookupDto.builder().id(ENotificationProcessingStatus.NEW.getId()).build());
                    notificationRequest.setSendingDate(new Date());
                    Set<NotificationRequestParameterValueDto> notificationRequestParamValue = new HashSet<>();
                    notificationRequestParamValue.add(buildNotificationRequestParamValue(notificationTemplate, notificationRequest, PASSWORD_EXPIRY_TEMPLATE_DAYS_TO_EXPIRY_PARAMETER_NAME, Integer.toString(param.getDaysToExpiry())));
                    notificationRequest.setNotificationRequestParameterValues(notificationRequestParamValue);
                    save(notificationRequest);
                }
        );
        log.info("end savePasswordExpiryNotificationRequest");
    }

    /**
     * build Notification Request Param Value
     *
     * @param notificationRequest
     * @param notificationTemplate
     * @param paramName            notification Request Parameter name
     * @param paramValue           notification Request Parameter value
     * @return Notification Request Parameter Value instance.
     */
    private NotificationRequestParameterValueDto buildNotificationRequestParamValue(Optional<NotificationTemplateDto> notificationTemplate, NotificationRequestDto notificationRequest, String paramName, String paramValue) {
        return NotificationRequestParameterValueDto.builder()
                .notificationTemplateParameterId(findNotificationTemplateParameterId(notificationTemplate, paramName))
                .notificationTemplateParameterValue(paramValue)
                .notificationRequest(notificationRequest).build();
    }

    /**
     * Retrieve Notification Template Parameter Id
     *
     * @param parameterName        to find id for
     * @param notificationTemplate to find parameter id for
     * @return notification Template parameter id.
     */
    private long findNotificationTemplateParameterId(Optional<NotificationTemplateDto> notificationTemplate, String parameterName) {
        return notificationTemplate.get().getNotificationTemplateParameters().stream().filter(nt -> nt.getParameterName().equalsIgnoreCase(parameterName)).findAny().get().getId();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void createUserDefinedNotificationRequest(NotificationTemplateDto notificationTemplate) {
        log.info("Start createUserDefinedNotificationRequest");
        List<ApplicantDto> applicants;
        List<CompanyStaffDto> companyStaff;
        NotificationTemplateDto savedNotificationTemplate = notificationTemplateService.create(notificationTemplate);
        NotificationTemplateCategorizingDto categorizing = notificationTemplate.getNotificationTemplateCategorizing();
        if(categorizing != null){
            log.info("notification categorization found");
            if(categorizing.getNotificationCategory() == NotificationScope.ALL){

                if(notificationTemplate.getCompanyCode() == null) {
                    log.info("retrieve all aplicants for all registered and have active ritual");
                    applicants = applicantService.findAllRegisteredAndHavingActiveRitual();
                } else {
                    String[] companyCodeArray = notificationTemplate.getCompanyCode().split("_", 2);
                    String companyTypeCode = companyCodeArray[1];
                    long establishmentRefCode = -1L;
                    long missionRefCode = -1L;
                    String companyCode = null;

                    if (companyTypeCode.equals(EOrganizerTypes.ESTABLISHMENT.name())) {
                        establishmentRefCode = Long.parseLong(companyCodeArray[0]);
                    } else if (companyTypeCode.equals(EOrganizerTypes.MISSION.name())) {
                        missionRefCode = Long.parseLong(companyCodeArray[0]);
                    } else if (companyTypeCode.equals(EOrganizerTypes.INTERNAL_HAJ_COMPANY.name()) ||
                            companyTypeCode.equals(EOrganizerTypes.EXTERNAL_HAJ_COMPANY.name())) {
                        companyCode = notificationTemplate.getCompanyCode();
                    }
                    log.info("Retrieve all applicants for company with companyCode: {}", companyCode);
                    applicants = applicantService.findOrganizerApplicants(companyCode, establishmentRefCode, missionRefCode);
                }
                log.info("send notification to applicants", applicants);
                sendNotificationTemplateToApplicants(savedNotificationTemplate, applicants);
            }
            else if(categorizing.getNotificationCategory() == 2){
                log.info("find registered staff for send notification");
                companyStaff = companyStaffService.findRegisteredStaff();
                log.info("registered staff to send notification: {}", companyStaff);
                sendNotificationTemplateToCompanyStaff(savedNotificationTemplate, companyStaff);
            }
            else if(categorizing.getNotificationCategory() == 3){
                log.info("find all applicants by criteria to send notifications");
                applicants = applicantService.findAllByCriteria(notificationTemplate.getNotificationTemplateCategorizing(), null);
                sendNotificationTemplateToApplicants(savedNotificationTemplate, applicants);
            }
            else if(categorizing.getNotificationCategory() == NotificationScope.SELECTED_APPLICANT){

                if (categorizing.getSelectedApplicants() != null ) {
                    List<Long> applicantIds = Arrays.stream(categorizing.getSelectedApplicants().split(",")).map(Long::parseLong).collect(Collectors.toList());
                    log.info("applicantIds for send notificiation to applicants : {}", applicantIds);
                    applicants = applicantService.findAllByIds(applicantIds);
                    sendNotificationTemplateToApplicants(savedNotificationTemplate, applicants);
                }
            }
            else if(categorizing.getNotificationCategory() == 5 ){
                if (categorizing.getSelectedStaff() != null) {
                    List<Long> staffIds = Arrays.stream(categorizing.getSelectedApplicants().split(",")).map(Long::parseLong).collect(Collectors.toList());
                    log.info("staffIds for send notification to staff : {}", staffIds);
                    companyStaff = companyStaffService.findAllByIds(staffIds);
                    sendNotificationTemplateToCompanyStaff(savedNotificationTemplate, companyStaff);
                }
            } else if(categorizing.getNotificationCategory() == NotificationScope.GROUP) {
                    List<ApplicantDto> groupApplicants = applicantService.findApplicantByGroupId(categorizing.getSelectedGroupId());
                    sendNotificationTemplateToApplicants(savedNotificationTemplate, groupApplicants);
            }
        }

    }

    @Transactional
    public void sendIncidentNotification(NotificationTemplateDto notificationTemplate, String uin, String preferredLanguage) {
        NotificationRequestDto notificationRequest = NotificationRequestDto
                .builder()
                .userId(uin)
                .notificationTemplate(notificationTemplate)
                .userLang(notificationTemplate.getNotificationTemplateContents()
                        .stream().filter(c -> preferredLanguage.equalsIgnoreCase(c.getLang()))
                        .findFirst()
                        .map(NotificationTemplateContentDto::getLang)
                        .orElse(NOTIFICATION_DEFAULT_LANGUAGE))
                .sendingDate(new Date())
                .processingStatus(NotificationProcessingStatusLookupDto.builder().id(ENotificationProcessingStatus.NEW.getId()).build())
                .build();
        super.save(notificationRequest);
    }

    @Transactional
    public void sendComplaintNotification(NotificationTemplateDto notificationTemplate, String uin, String preferredLanguage) {
        NotificationRequestDto notificationRequest = NotificationRequestDto
                .builder()
                .userId(uin)
                .notificationTemplate(notificationTemplate)
                .userLang(notificationTemplate.getNotificationTemplateContents()
                        .stream().filter(c -> preferredLanguage.equalsIgnoreCase(c.getLang()))
                        .findFirst()
                        .map(NotificationTemplateContentDto::getLang)
                        .orElse(NOTIFICATION_DEFAULT_LANGUAGE))
                .sendingDate(new Date())
                .processingStatus(NotificationProcessingStatusLookupDto.builder().id(ENotificationProcessingStatus.NEW.getId()).build())
                .build();
        super.save(notificationRequest);
    }

    private void sendNotificationTemplateToApplicants(NotificationTemplateDto notificationTemplate, List<ApplicantDto> applicants) {
        List<NotificationRequestDto> notificationRequests = applicants.stream().map(applicant -> NotificationRequestDto
                        .builder()
                        .userId(applicant.getDigitalIds().get(0).getUin())
                        .notificationTemplate(notificationTemplate)
                        .sendingDate(notificationTemplate.getSendingDate())
                        .userLang(applicant.getPreferredLanguage() != null ? getApplicantNotificationLanguage(notificationTemplate, applicant) : NOTIFICATION_DEFAULT_LANGUAGE)
                        .processingStatus(NotificationProcessingStatusLookupDto.builder().id(ENotificationProcessingStatus.NEW.getId()).build())
                        .build())
                .collect(Collectors.toList());
        super.saveAll(notificationRequests);
    }
    private void sendNotificationTemplateToCompanyStaff(NotificationTemplateDto notificationTemplate, List<CompanyStaffDto> companyStaffDtos) {
        List<NotificationRequestDto> notificationRequests = companyStaffDtos.stream().map(companyStaff -> NotificationRequestDto
                        .builder()
                        .userId(companyStaff.getDigitalIds().stream().findFirst().get().getSuin())
                        .notificationTemplate(notificationTemplate)
                        .sendingDate(notificationTemplate.getSendingDate())
                        .userLang(companyStaff.getPreferredLanguage() != null ? getCompanyNotificationLanguage(notificationTemplate, companyStaff) : NOTIFICATION_DEFAULT_LANGUAGE)
                        .processingStatus(NotificationProcessingStatusLookupDto.builder().id(ENotificationProcessingStatus.NEW.getId()).build())
                        .build())
                .collect(Collectors.toList());
        super.saveAll(notificationRequests);
    }

    private String getApplicantNotificationLanguage(NotificationTemplateDto notificationTemplate, ApplicantDto applicant) {
        return notificationTemplate.getNotificationTemplateContents()
                .stream().filter(c -> applicant.getPreferredLanguage().equalsIgnoreCase(c.getLang()))
                .findFirst()
                .map(NotificationTemplateContentDto::getLang)
                .orElse(NOTIFICATION_DEFAULT_LANGUAGE);
    }
    private String getCompanyNotificationLanguage(NotificationTemplateDto notificationTemplate, CompanyStaffDto companyStaffDto) {
        return notificationTemplate.getNotificationTemplateContents()
                .stream().filter(c -> companyStaffDto.getPreferredLanguage().equalsIgnoreCase(c.getLang()))
                .findFirst()
                .map(NotificationTemplateContentDto::getLang)
                .orElse(NOTIFICATION_DEFAULT_LANGUAGE);
    }

    @Modifying
    public void processNotificationTemplates(int notificationProcessingBatchSize) {
        List<NotificationTemplateDto> notificationTemplates = notificationTemplateService
                .findUnprocessedUserDefinedNotifications(ENotificationTemplateType.USER_DEFINED.name(), new Date(), false, true, notificationProcessingBatchSize);
        notificationTemplates.forEach(
                notificationTemplate -> {
                    createUserDefinedNotificationRequest(notificationTemplate);
                    notificationTemplateService.updatedProcessed(notificationTemplate);
                }
        );
    }
}
