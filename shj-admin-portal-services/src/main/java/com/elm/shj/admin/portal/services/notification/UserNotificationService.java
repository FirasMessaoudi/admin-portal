/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplateContent;
import com.elm.shj.admin.portal.orm.entity.JpaUserNotification;
import com.elm.shj.admin.portal.orm.repository.UserNotificationRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service handling  User Notification
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserNotificationService extends GenericService<JpaUserNotification, UserNotificationDto, Long> {

    public static final String PASSWORD_EXPIRATION_TEMPLATE_NAME = "PASSWORD_EXPIRATION";
    public static final String PASSWORD_EXPIRY_TEMPLATE_USER_ID_PARAMETER_NAME = "user_id";
    public static final String PASSWORD_EXPIRY_TEMPLATE_USER_LANG_PARAMETER_NAME = "user_lang";
    public static final String PASSWORD_EXPIRY_TEMPLATE_DAYS_TO_EXPIRY_PARAMETER_NAME = "days_to_expiry";
    private final NotificationRequestService notificationRequestService;
    private final NotificationTemplateService notificationTemplateService;
    private final UserNotificationRepository userNotificationRepository;

    /**
     * Finds user Notifications by user Id
     *
     * @param userId the userId of the notifications  to find for
     * @return the found Notifications  or empty structure
     */
    public List<DetailedUserNotificationDto> findUserNotifications(long userId) {
        List<DetailedUserNotificationDto> detailedUserNotificationDtos = new ArrayList<>();
        List<JpaUserNotification> userNotifications = userNotificationRepository.findByUserId(userId);

        if (userNotifications.isEmpty())
            return Collections.emptyList();

        userNotifications.parallelStream().forEach(
                notification -> {
                    Optional<JpaNotificationTemplateContent> notificationTemplateContent = notification.getNotificationTemplate().getNotificationTemplateContents().stream().filter(content -> content.getLang().equalsIgnoreCase(notification.getUserLang())).findAny();
                    detailedUserNotificationDtos.add(DetailedUserNotificationDto.builder()
                            .resolvedBody(notification.getResolvedBody())
                            .statusCode(notification.getStatusCode())
                            .important(notification.getNotificationTemplate().isImportant())
                            .actionRequired(notification.getNotificationTemplate().isActionRequired())
                            .userSpecific(notification.getNotificationTemplate().isUserSpecific())
                            .categoryCode(notification.getNotificationTemplate().getCategoryCode())
                            .title(notificationTemplateContent.isPresent() ? notificationTemplateContent.get().getTitle() : null)
                            .actionLabel(notificationTemplateContent.isPresent() ? notificationTemplateContent.get().getActionLabel() : null)
                            .creationDate(notification.getCreationDate())
                            .build());
                });
        return detailedUserNotificationDtos;
    }


    /**
     * save Password Expiry Notification Request Details
     *
     * @param passwordExpiryNotificationRequest the Password Expiry Notification Request Details to save
     */
    public void savePasswordExpiryNotificationRequest(PasswordExpiryNotificationRequest passwordExpiryNotificationRequest) throws NotFoundException {
        Optional<NotificationTemplateDto> notificationTemplate = notificationTemplateService.findEnabledNotificationTemplateByNameCode(PASSWORD_EXPIRATION_TEMPLATE_NAME);

        if (!notificationTemplate.isPresent()) {
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
                    notificationRequestParamValue.add(buildNotificationRequestParamValue(notificationTemplate, notificationRequest, PASSWORD_EXPIRY_TEMPLATE_USER_ID_PARAMETER_NAME, Long.toString(param.getUserId())));
                    notificationRequestParamValue.add(buildNotificationRequestParamValue(notificationTemplate, notificationRequest, PASSWORD_EXPIRY_TEMPLATE_USER_LANG_PARAMETER_NAME, param.getUserLang()));
                    notificationRequestParamValue.add(buildNotificationRequestParamValue(notificationTemplate, notificationRequest, PASSWORD_EXPIRY_TEMPLATE_DAYS_TO_EXPIRY_PARAMETER_NAME, Integer.toString(param.getDaysToExpiry())));
                    notificationRequest.setNotificationRequestParameterValues(notificationRequestParamValue);
                    notificationRequestService.save(notificationRequest);

                }
        );

    }


    /**
     * mark User Notification As Read
     *
     * @param notificationId is the id for the notification
     * @return number of affected rows
     */
    public int markUserNotificationAsRead(Long notificationId) {
        return userNotificationRepository.markUserNotificationAsRead(notificationId, EUserNotificationStatus.READ.name());
    }

    /**
     * Retrieve user new notifications count.
     *
     * @param userId user id
     * @return number of un-read user notifications.
     */
    public int retrieveUserNewNotificationsCount(long userId) {
        return userNotificationRepository.countByUserIdAndStatusCode(userId, EUserNotificationStatus.NEW.name());
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

}
