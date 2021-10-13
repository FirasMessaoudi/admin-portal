/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplateContent;
import com.elm.shj.admin.portal.orm.entity.JpaUserNotification;
import com.elm.shj.admin.portal.orm.repository.UserNotificationRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
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

    public final static String PASSWORD_EXPIRATION_TEMPLATE_NAME = "PASSWORD_EXPIRATION";
    public final static String PASSWORD_EXPIRY_TEMPLATE_USER_ID_PARAMETER_NAME = "user_id";
    public final static String PASSWORD_EXPIRY_TEMPLATE_USER_LANG_PARAMETER_NAME = "user_lang";
    public final static String PASSWORD_EXPIRY_TEMPLATE_DAYS_TO_EXPIRY_PARAMETER_NAME = "days_to_expiry";
    private final NotificationRequestService notificationRequestService;
    private final NotificationTemplateService notificationTemplateService;
    private final UserNotificationRepository userNotificationRepository;

    /**
     * Finds user Notifications by user's Id
     *
     * @param userId the userId of the notifications  to find for
     * @return the found Notifications  or empty structure
     */
    //TODO: INITIAL CODE TO BE REFACOTORED
    public List<DetailedUserNotificationDto> findUserNotifications(long userId) {
        List<DetailedUserNotificationDto> detailedUserNotificationDtos = new ArrayList<>();
        List<JpaUserNotification> userNotifications = userNotificationRepository.findByUserId(userId);
        userNotifications.parallelStream().forEach(
                notification -> {
                    Optional<JpaNotificationTemplateContent> notificationTemplateContent = notification.getNotificationTemplate().getNotificationTemplateContents().stream().filter(content -> content.getLang().equals(notification.getUserLang())).findAny();
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


    public void saveNotificationRequest(NotificationRequestDto notificationRequestDto) {
        notificationRequestService.save(notificationRequestDto);
    }

    //TODO: INITIAL CODE TO BE REFACOTORED
    public void savePasswordExpiryNotificationRequest(PasswordExpiryNotificationRequest passwordExpiryNotificationRequest) {
        Optional<NotificationTemplateDto> notificationTemplate = notificationTemplateService.findEnabledNotificationTemplateByNameCode(PASSWORD_EXPIRATION_TEMPLATE_NAME);
        if (!notificationTemplate.isPresent()) {
            //throw custom exception and return specific error code
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

                    notificationRequestParamValue.add(NotificationRequestParameterValueDto.builder()
                            .notificationTemplateParameterId(findNotificationTemplateParameterId(notificationTemplate, PASSWORD_EXPIRY_TEMPLATE_USER_ID_PARAMETER_NAME))
                            .notificationTemplateParameterValue(Long.toString(param.getUserId()))
                            .notificationRequest(notificationRequest).build());
                    notificationRequestParamValue.add(NotificationRequestParameterValueDto.builder()
                            .notificationTemplateParameterId(findNotificationTemplateParameterId(notificationTemplate, PASSWORD_EXPIRY_TEMPLATE_USER_LANG_PARAMETER_NAME))
                            .notificationTemplateParameterValue(param.getUserLang())
                            .notificationRequest(notificationRequest).build());
                    notificationRequestParamValue.add(NotificationRequestParameterValueDto.builder()
                            .notificationTemplateParameterId(findNotificationTemplateParameterId(notificationTemplate, PASSWORD_EXPIRY_TEMPLATE_DAYS_TO_EXPIRY_PARAMETER_NAME))
                            .notificationTemplateParameterValue(Integer.toString(param.getDaysToExpiry()))
                            .notificationRequest(notificationRequest).build());

                    notificationRequest.setNotificationRequestParameterValues(notificationRequestParamValue);
                    saveNotificationRequest(notificationRequest);

                }

        );


    }

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

    //TODO: INITIAL CODE TO BE REFACOTORED
    private long findNotificationTemplateParameterId(Optional<NotificationTemplateDto> notificationTemplate, String parameterName) {
        return notificationTemplate.get().getNotificationTemplateParameters().parallelStream().filter(nt -> nt.getParameterName().equals(parameterName)).findAny().get().getId();
    }
}
