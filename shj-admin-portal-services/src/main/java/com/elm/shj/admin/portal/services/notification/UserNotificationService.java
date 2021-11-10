/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplateContent;
import com.elm.shj.admin.portal.orm.entity.JpaUserNotification;
import com.elm.shj.admin.portal.orm.repository.UserNotificationRepository;
import com.elm.shj.admin.portal.services.dto.DetailedUserNotificationDto;
import com.elm.shj.admin.portal.services.dto.EUserNotificationStatus;
import com.elm.shj.admin.portal.services.dto.NotificationTemplateDto;
import com.elm.shj.admin.portal.services.dto.UserNotificationDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

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

    private final static String DRAFT = "DRAFT";
    private final static String CONFIRMED = "CONFIRMED";

    private final UserNotificationRepository userNotificationRepository;

    /**
     * Finds user Notifications by user Id
     *
     * @param userId the userId of the notifications  to find for
     * @return the found Notifications  or empty structure
     */
    public List<DetailedUserNotificationDto> findUserNotifications(long userId) {
        List<DetailedUserNotificationDto> detailedUserNotificationDtos = new ArrayList<>();
        List<JpaUserNotification> userNotifications = userNotificationRepository.findByUserIdAndStatusCodeNot(userId, EUserNotificationStatus.EXPIRED.name());

        if (userNotifications.isEmpty())
            return Collections.emptyList();

        userNotifications.parallelStream().forEach(
                notification -> {
                    Optional<JpaNotificationTemplateContent> notificationTemplateContent = notification.getNotificationTemplate().getNotificationTemplateContents().stream().filter(content -> content.getLang().equalsIgnoreCase(notification.getUserLang())).findAny();
                    detailedUserNotificationDtos.add(DetailedUserNotificationDto.builder()
                            .id(notification.getId())
                            .resolvedBody(notification.getResolvedBody())
                            .statusCode(notification.getStatusCode())
                            .nameCode(notification.getNotificationTemplate().getNameCode())
                            .important(notification.getNotificationTemplate().isImportant())
                            .actionRequired(notification.getNotificationTemplate().isActionRequired())
                            .userSpecific(notification.getNotificationTemplate().isUserSpecific())
                            .categoryCode(notification.getNotificationTemplate().getCategoryCode())
                            .title(notificationTemplateContent.isPresent() ? notificationTemplateContent.get().getTitle() : null)
                            .actionLabel(notificationTemplateContent.isPresent() ? notificationTemplateContent.get().getActionLabel() : null)
                            .creationDate(notification.getCreationDate())
                            .build());
                });
        return detailedUserNotificationDtos.stream().sorted(Comparator.comparing(DetailedUserNotificationDto::getCreationDate).reversed()).collect(Collectors.toList());
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
    public UserNewNotificationsCountVo retrieveUserNewNotificationsCount(long userId) {
        int userSpecificNewNotificationsCount = userNotificationRepository.countByUserIdAndStatusCodeAndNotificationTemplateUserSpecific(userId, EUserNotificationStatus.NEW.name(), true);
        int userNotSpecificNewNotificationsCount = userNotificationRepository.countByUserIdAndStatusCodeAndNotificationTemplateUserSpecific(userId, EUserNotificationStatus.NEW.name(), false);
        UserNewNotificationsCountVo userNewNotificationsCountVo = UserNewNotificationsCountVo
                .builder().userSpecificNewNotificationsCount(userSpecificNewNotificationsCount)
                .userNotSpecificNewNotificationsCount(userNotSpecificNewNotificationsCount).build();

        return userNewNotificationsCountVo;
    }

    public void sendToCategorizedApplicants(NotificationTemplateDto notificationTemplate) {
    }

    private int calculateAge(Date birthDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate convertedBirthDate = convertToLocalDateViaInstant(birthDate);
        if (convertedBirthDate != null) {
            return Period.between(convertedBirthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
