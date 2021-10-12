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

    public final static String TEMPLATE_NAME = "PASSWORD_EXPIRATION";
    public final static String PASSWORD_EXPIRY_TEMPLATE_PARAMETER_NAME_1 = "user_name";
    public final static String PASSWORD_EXPIRY_TEMPLATE_PARAMETER_NAME_2 = "uin";
    public final static String PASSWORD_EXPIRY_TEMPLATE_PARAMETER_NAME_3 = "user_id";
    public final static String PASSWORD_EXPIRY_TEMPLATE_PARAMETER_NAME_4 = "user_lang";
    public final static String PASSWORD_EXPIRY_TEMPLATE_PARAMETER_NAME_5 = "expiry_day_count";

    private final UserNotificationRepository userNotificationRepository;
    private final NotificationRequestService notificationRequestService;
    private final NotificationTemplateService notificationTemplateService;

    /**
     * Finds user Notification by user's UIN
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
        Optional<NotificationTemplateDto> notificationTemplate = notificationTemplateService.findEnabledNotificationTemplateByNameCode(TEMPLATE_NAME);
        if (!notificationTemplate.isPresent()) {
            //throw custom exception and return specific error code
        }

        passwordExpiryNotificationRequest.getParameterValueList().parallelStream().forEach(
                param -> {
                    NotificationRequestDto notificationRequest = new NotificationRequestDto();
                    notificationRequest.setNotificationTemplate(notificationTemplate.get());
                    notificationRequest.setUserLang(param.getUserLang());
                    notificationRequest.setUserId(param.getUserId());
                    notificationRequest.setProcessingStatus(NotificationProcessingStatusLookupDto.builder().id(ENotificationProcessingStatus.NEW.getId()).build());
                    notificationRequest.setSendingDate(new Date());
                    Set<NotificationRequestParameterValueDto> notificationRequestParamValue = new HashSet<>();
                    notificationRequestParamValue.add(NotificationRequestParameterValueDto.builder()
                            .notificationTemplateParameterId(findTemplateParameterId(notificationTemplate, PASSWORD_EXPIRY_TEMPLATE_PARAMETER_NAME_1))
                            .notificationTemplateParameterValue(param.getUserName())
                            .notificationRequest(notificationRequest).build());
                    notificationRequestParamValue.add(NotificationRequestParameterValueDto.builder()
                            .notificationTemplateParameterId(findTemplateParameterId(notificationTemplate, PASSWORD_EXPIRY_TEMPLATE_PARAMETER_NAME_2))
                            .notificationTemplateParameterValue(Long.toString(param.getUin()))
                            .notificationRequest(notificationRequest).build());
                    notificationRequestParamValue.add(NotificationRequestParameterValueDto.builder()
                            .notificationTemplateParameterId(findTemplateParameterId(notificationTemplate, PASSWORD_EXPIRY_TEMPLATE_PARAMETER_NAME_3))
                            .notificationTemplateParameterValue(Long.toString(param.getUserId()))
                            .notificationRequest(notificationRequest).build());
                    notificationRequestParamValue.add(NotificationRequestParameterValueDto.builder()
                            .notificationTemplateParameterId(findTemplateParameterId(notificationTemplate, PASSWORD_EXPIRY_TEMPLATE_PARAMETER_NAME_4))
                            .notificationTemplateParameterValue(param.getUserLang())
                            .notificationRequest(notificationRequest).build());
                    notificationRequestParamValue.add(NotificationRequestParameterValueDto.builder()
                            .notificationTemplateParameterId(findTemplateParameterId(notificationTemplate, PASSWORD_EXPIRY_TEMPLATE_PARAMETER_NAME_5))
                            .notificationTemplateParameterValue(Integer.toString(param.getDayDiff()))
                            .notificationRequest(notificationRequest).build());

                    notificationRequest.setNotificationRequestParameterValues(notificationRequestParamValue);
                    saveNotificationRequest(notificationRequest);

                }

        );


    }

    public int updateUserNotificationStatus(Long notificationId, String statusCode) {
        return userNotificationRepository.updateUserNotificationStatus(notificationId, statusCode);
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
    private long findTemplateParameterId(Optional<NotificationTemplateDto> notificationTemplate, String parameterName) {
        return notificationTemplate.get().getNotificationTemplateParameters().parallelStream().filter(nt -> nt.getParameterName().equals(parameterName)).findAny().get().getId();
    }
}
