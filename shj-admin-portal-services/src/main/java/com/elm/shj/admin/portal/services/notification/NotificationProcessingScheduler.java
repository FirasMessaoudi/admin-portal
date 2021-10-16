/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;


import com.elm.shj.admin.portal.orm.entity.JpaNotificationRequest;
import com.elm.shj.admin.portal.orm.repository.NotificationRequestRepository;
import com.elm.shj.admin.portal.services.dto.ENotificationProcessingStatus;
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
import java.util.Date;

/**
 * Service handling Applicant Notifications
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class NotificationProcessingScheduler {

    private final NotificationRequestRepository notificationRequestRepository;
    private final NotificationRequestService notificationRequestService;
    @Value("${notification.processing.batch.size}")
    private int notificationProcessingBatchSize;


    @PostConstruct
    @Scheduled(cron = "${scheduler.notification.processing.cron}")
    @SchedulerLock(name = "notification-processing-task")
    void sendUserNotifications() {
        log.debug("send notification scheduler started ...");
        Page<JpaNotificationRequest> notificationRequests = notificationRequestRepository.findNotificationRequests(PageRequest.ofSize(notificationProcessingBatchSize), ENotificationProcessingStatus.NEW.name(), new Date());
        notificationRequests.stream().parallel().forEach(
                notificationRequest -> {
                    notificationRequest.getProcessingStatus().setId(ENotificationProcessingStatus.UNDER_PROCESSING.getId());
                    notificationRequestRepository.save(notificationRequest);
                    try {
                        notificationRequestService.processNotificationRequest(notificationRequest);
                    } catch (Exception e) {
                        notificationRequest.getProcessingStatus().setId(ENotificationProcessingStatus.FAILED.getId());
                        notificationRequestRepository.save(notificationRequest);
                        log.error("Failed to send notification request with id >>  {}", notificationRequest.getId());
                    }
                }
        );
    }
}
