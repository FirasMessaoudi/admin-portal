/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.entity.JpaUserNotification;
import com.elm.shj.admin.portal.orm.repository.UserNotificationRepository;
import com.elm.shj.admin.portal.services.dto.ENotificationTemplateType;
import com.elm.shj.admin.portal.services.dto.EUserNotificationStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

/**
 * scheduler to mark user notifications as expired
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserNotificationExpirationScheduler {

    private final UserNotificationRepository userNotificationRepository;
    private final UserNotificationService userNotificationService;
    @Value("${user.notification.expiration.batch.size}")
    private int userNotificationExpirationBatchSize;

//    @PostConstruct
//    @Scheduled(cron = "${scheduler.user.notification.expiration.cron}")
//    @SchedulerLock(name = "user-notification-expiration-task")
    void markUserNotificationsAsExpired() {
        int pageNum = 0;
        log.debug("mark User Notifications As Expired scheduler started ...");
        Page<JpaUserNotification> userNotifications;
        do {
            userNotifications = userNotificationRepository.findByStatusCodeNot(PageRequest.of(pageNum, userNotificationExpirationBatchSize), EUserNotificationStatus.EXPIRED.name());
            if (userNotifications.getContent().isEmpty()) {
                break;
            }
            userNotifications.forEach(
                    notification -> {
                        if (notification.getNotificationTemplate().getTypeCode() == ENotificationTemplateType.SYSTEM_DEFINED.name()) {
                            long diffInMinutes = ChronoUnit.MINUTES.between(LocalDateTime.now(), notification.getCreationDate().toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDateTime().plusMinutes(notification.getNotificationTemplate().getExpirationPeriodInMinutes()));
                            if (diffInMinutes <= 0) {
                                try {
                                    userNotificationService.markUserNotificationsAsExpired(notification.getId());
                                } catch (Exception e) {
                                    log.error("Failed to mark User Notifications As Expired notification Id  >>  {}", notification.getId());
                                }
                            }
                        } else {
                            // TODO: kindly add the logic for the user defined one
                        }

                    }
            );
            pageNum++;
        } while (userNotifications.hasNext());
    }
}
