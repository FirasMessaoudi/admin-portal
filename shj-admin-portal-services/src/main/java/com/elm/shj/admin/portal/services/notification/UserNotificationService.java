/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplateContent;
import com.elm.shj.admin.portal.orm.entity.JpaUserNotification;
import com.elm.shj.admin.portal.orm.repository.UserNotificationRepository;
import com.elm.shj.admin.portal.services.dto.DetailedUserNotificationDto;
import com.elm.shj.admin.portal.services.dto.EUserNotificationStatus;
import com.elm.shj.admin.portal.services.dto.EUserNotificationType;
import com.elm.shj.admin.portal.services.dto.UserNotificationDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final UserNotificationRepository userNotificationRepository;

    /**
     * Finds user Notifications by user Id
     *
     * @param userId the userId of the notifications  to find for
     * @return the found Notifications  or empty structure
     */
    public List<DetailedUserNotificationDto> findUserNotifications(String userId) {
        List<DetailedUserNotificationDto> detailedUserNotifications = new ArrayList<>();
        List<JpaUserNotification> userNotifications = userNotificationRepository
                .findByUserIdAndStatusCodeNotOrderByCreationDateDesc(userId, EUserNotificationStatus.EXPIRED.name());

        if (userNotifications.isEmpty())
            return Collections.emptyList();

        userNotifications.forEach(
                notification -> {
                    Optional<JpaNotificationTemplateContent> notificationTemplateContent = notification.getNotificationTemplate().getNotificationTemplateContents().stream().filter(content -> content.getLang().equalsIgnoreCase(notification.getUserLang())).findAny();
                    detailedUserNotifications.add(DetailedUserNotificationDto.builder()
                            .id(notification.getId())
                            .resolvedBody(notification.getResolvedBody())
                            .statusCode(notification.getStatusCode())
                            .nameCode(notification.getNotificationTemplate().getNameCode())
                            .important(notification.getNotificationTemplate().isImportant())
                            .actionRequired(notification.getNotificationTemplate().isActionRequired())
                            .userSpecific(notification.getNotificationTemplate().isUserSpecific())
                            .categoryCode(notification.getNotificationTemplate().getCategoryCode())
                            .title(notificationTemplateContent.map(JpaNotificationTemplateContent::getTitle).orElse(null))
                            .actionLabel(notificationTemplateContent.map(JpaNotificationTemplateContent::getActionLabel).orElse(null))
                            .creationDate(notification.getCreationDate())
                            .build());
                });
        return detailedUserNotifications;
    }

    /**
     * mark User Notification As Read
     *
     * @param notificationId is the id for the notification
     * @return number of affected rows
     */
    @Transactional
    public int markUserNotificationAsRead(Long notificationId) {
        return userNotificationRepository.updateUserNotificationStatus(notificationId, EUserNotificationStatus.READ.name());
    }


    /**
     * mark User Notification As Expird
     *
     * @param notificationId is the id for the notification
     * @return number of affected rows
     */
    @Transactional
    public int markUserNotificationsAsExpired(Long notificationId) {
        return userNotificationRepository.updateUserNotificationStatus(notificationId, EUserNotificationStatus.EXPIRED.name());
    }


    /**
     * Retrieve user new notifications counts.
     *
     * @param userId user id
     * @return number of un-read user notifications.
     */
    public UserNewNotificationsCountVo retrieveUserNewNotificationsCount(String userId) {
        int userSpecificNewNotificationsCount = userNotificationRepository.countByUserIdAndStatusCodeAndNotificationTemplateUserSpecific(userId, EUserNotificationStatus.NEW.name(), true);
        int userNotSpecificNewNotificationsCount = userNotificationRepository.countByUserIdAndStatusCodeAndNotificationTemplateUserSpecific(userId, EUserNotificationStatus.NEW.name(), false);
        UserNewNotificationsCountVo userNewNotificationsCountVo = UserNewNotificationsCountVo
                .builder().userSpecificNewNotificationsCount(userSpecificNewNotificationsCount)
                .userNotSpecificNewNotificationsCount(userNotSpecificNewNotificationsCount).build();

        return userNewNotificationsCountVo;
    }

    public Page<DetailedUserNotificationDto> findTypedUserNotifications(String userId, EUserNotificationType type, Pageable pageable) {
        List<DetailedUserNotificationDto> detailedUserNotifications = new ArrayList<>();

        Page<JpaUserNotification> userNotifications;

        if (EUserNotificationType.ALL.equals(type)) {
            userNotifications = userNotificationRepository
                    .findByUserIdAndStatusCodeNotOrderByCreationDateDesc(userId, EUserNotificationStatus.EXPIRED.name(), pageable);
        } else {
            userNotifications = userNotificationRepository
                    .findByUserIdAndNotificationTemplateUserSpecificAndStatusCodeNotOrderByCreationDateDesc(
                            userId, EUserNotificationType.USER_SPECIFIC.equals(type), EUserNotificationStatus.EXPIRED.name(), pageable);
        }

        userNotifications.stream().forEach(
                notification -> {
                    Optional<JpaNotificationTemplateContent> notificationTemplateContent = notification.getNotificationTemplate()
                            .getNotificationTemplateContents().stream().filter(content -> content.getLang().equalsIgnoreCase(notification.getUserLang())).findAny();
                    detailedUserNotifications.add(DetailedUserNotificationDto.builder()
                            .id(notification.getId())
                            .resolvedBody(notification.getResolvedBody())
                            .statusCode(notification.getStatusCode())
                            .nameCode(notification.getNotificationTemplate().getNameCode())
                            .important(notification.getNotificationTemplate().isImportant())
                            .actionRequired(notification.getNotificationTemplate().isActionRequired())
                            .userSpecific(notification.getNotificationTemplate().isUserSpecific())
                            .categoryCode(notification.getNotificationTemplate().getCategoryCode())
                            .title(notificationTemplateContent.map(JpaNotificationTemplateContent::getTitle).orElse(null))
                            .actionLabel(notificationTemplateContent.map(JpaNotificationTemplateContent::getActionLabel).orElse(null))
                            .creationDate(notification.getCreationDate())
                            .build());
                });
        return new PageImpl<>(detailedUserNotifications, pageable, userNotifications.getTotalElements());
    }

}
