/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.entity.*;
import com.elm.shj.admin.portal.orm.repository.ApplicantRepository;
import com.elm.shj.admin.portal.orm.repository.NotificationRequestRepository;
import com.elm.shj.admin.portal.orm.repository.UserNotificationRepository;
import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    private final NotificationTemplateService notificationTemplateService;
    private final NotificationRequestRepository notificationRequestRepository;
    private final UserNotificationRepository userNotificationRepository;
    private final ApplicantService applicantService;

    /**
     * will handle processing of  user Notifications in notification processing scheduler
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
        Optional<NotificationTemplateDto> notificationTemplate = notificationTemplateService.findEnabledNotificationTemplateByNameCode(PASSWORD_EXPIRATION_TEMPLATE_NAME);

        if (notificationTemplate == null || !notificationTemplate.isPresent()) {
            throw new NotFoundException("no Template found for  " + PASSWORD_EXPIRATION_TEMPLATE_NAME);
        }

        passwordExpiryNotificationRequest.getUserParametersList().parallelStream().forEach(
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
        return notificationTemplate.get().getNotificationTemplateParameters().parallelStream().filter(nt -> nt.getParameterName().equalsIgnoreCase(parameterName)).findAny().get().getId();
    }

    @Transactional
    public NotificationTemplateDto sendToAllApplicants(NotificationTemplateDto notificationTemplate) {
        NotificationTemplateDto savedNotificationTemplate = notificationTemplateService.create(notificationTemplate);
        List<ApplicantDto> applicants = applicantService.findAllHavingActiveRitual(new Date());
        List<NotificationRequestDto> notificationRequests = applicants.parallelStream().map(applicant -> NotificationRequestDto
                        .builder()
                        .userId(applicant.getDigitalIds().get(0).getUin())
                        .notificationTemplate(savedNotificationTemplate)
                        .sendingDate(savedNotificationTemplate.getSendingDate())
                        .userLang(notificationTemplate.getNotificationTemplateContents()
                                .stream().filter(c -> applicant.getPreferredLanguage().equalsIgnoreCase(c.getLang()))
                                .findFirst()
                                .map(NotificationTemplateContentDto::getLang)
                                .orElse("en"))
                        .processingStatus(NotificationProcessingStatusLookupDto.builder().id(ENotificationProcessingStatus.NEW.getId()).build())
                        .build())
                .collect(Collectors.toList());
        super.saveAll(notificationRequests);
        return savedNotificationTemplate;
    }

    @Transactional
    public NotificationTemplateDto sendToCategorizedApplicants(CategorizedNotificationVo categorizedNotificationVo) {
        NotificationTemplateDto savedNotificationTemplate = notificationTemplateService.create(categorizedNotificationVo.getNotificationTemplate());

        List<ApplicantDto> applicants = applicantService.findAllByCriteria(categorizedNotificationVo.getApplicantSearchCriteria(), null);

        List<NotificationRequestDto> notificationRequests = applicants.parallelStream().map(applicant -> NotificationRequestDto
                        .builder()
                        .userId(applicant.getDigitalIds().get(0).getUin())
                        .notificationTemplate(savedNotificationTemplate)
                        .sendingDate(savedNotificationTemplate.getSendingDate())
                        .userLang(savedNotificationTemplate.getNotificationTemplateContents()
                                .stream().filter(c -> applicant.getPreferredLanguage().equalsIgnoreCase(c.getLang()))
                                .findFirst()
                                .map(NotificationTemplateContentDto::getLang)
                                .orElse("en"))
                        .processingStatus(NotificationProcessingStatusLookupDto.builder().id(ENotificationProcessingStatus.NEW.getId()).build())
                        .build())
                .collect(Collectors.toList());
        super.saveAll(notificationRequests);
        return savedNotificationTemplate;
    }

    @Transactional
    public NotificationTemplateDto sendToSelectedApplicants(NotificationTemplateDto notificationTemplate, List<Long> selectedApplicants) {
        NotificationTemplateDto savedNotificationTemplate = notificationTemplateService.create(notificationTemplate);

        List<ApplicantDto> applicants = applicantService.findByIds(selectedApplicants);

        List<NotificationRequestDto> notificationRequests = applicants.parallelStream().map(applicant -> NotificationRequestDto
                        .builder()
                        .userId(applicant.getDigitalIds().get(0).getUin())
                        .notificationTemplate(savedNotificationTemplate)
                        .sendingDate(savedNotificationTemplate.getSendingDate())
                        .userLang(notificationTemplate.getNotificationTemplateContents()
                                .stream().filter(c -> applicant.getPreferredLanguage().equalsIgnoreCase(c.getLang()))
                                .findFirst()
                                .map(NotificationTemplateContentDto::getLang)
                                .orElse(null))
                        .processingStatus(NotificationProcessingStatusLookupDto.builder().id(ENotificationProcessingStatus.NEW.getId()).build())
                        .build())
                .collect(Collectors.toList());
        super.saveAll(notificationRequests);
        return savedNotificationTemplate;
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
                        .orElse("en"))
                .sendingDate(new Date())
                .processingStatus(NotificationProcessingStatusLookupDto.builder().id(ENotificationProcessingStatus.NEW.getId()).build())
                .build();
        super.save(notificationRequest);
    }
}
