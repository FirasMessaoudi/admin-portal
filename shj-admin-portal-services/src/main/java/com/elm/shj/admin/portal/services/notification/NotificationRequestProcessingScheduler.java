/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.repository.NotificationRequestRepository;
import com.elm.shj.admin.portal.services.dto.ENotificationProcessingStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * Scheduler to process automatically notifications requests
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class NotificationRequestProcessingScheduler {

    private final NotificationRequestRepository notificationRequestRepository;
    private final NotificationRequestService notificationRequestService;

    @Value("${notification.processing.batch.size}")
    private int notificationProcessingBatchSize;

    @Value("${scheduler.notification.processing.active.nodes}")
    private String schedulerActiveNodes;

    @Scheduled(cron = "${scheduler.notification.processing.cron}")
    //@SchedulerLock(name = "notification-processing-task")
    void sendUserNotifications() {
        String runningIpAddress;
        try {
            runningIpAddress = InetAddress.getLocalHost().getHostAddress();
            log.info("running IP address for potential notification processing scheduler is: {}", runningIpAddress);
        } catch (UnknownHostException e) {
            log.error("Error while getting the running ip address. Notification template processing scheduler will not run.", e);
            return;
        }
        if (schedulerActiveNodes == null || schedulerActiveNodes.isEmpty()) {
            log.warn("Notification processing scheduler will not run, no active nodes are configured in database.");
            return;
        }
        if (!schedulerActiveNodes.contains(runningIpAddress)) {
            log.warn("Notification processing scheduler will not run, {} ip is not in the configured active nodes list.");
            return;
        }
        log.debug("send notification scheduler started ...");

        notificationRequestRepository.findNotificationRequests(PageRequest.ofSize(notificationProcessingBatchSize), ENotificationProcessingStatus.NEW.name(), new Date()).forEach(
                notificationRequest -> {
                    notificationRequest.getProcessingStatus().setId(ENotificationProcessingStatus.UNDER_PROCESSING.getId());
                    notificationRequestRepository.save(notificationRequest);
                    try {
                        if (notificationRequest.getNotificationTemplate().isEnabled()) {
                            notificationRequestService.processNotificationRequest(notificationRequest);
                        } else {
                            notificationRequestRepository.delete(notificationRequest);
                        }
                    } catch (Exception e) {
                        notificationRequest.getProcessingStatus().setId(ENotificationProcessingStatus.FAILED.getId());
                        notificationRequestRepository.save(notificationRequest);
                        log.error("Failed to send notification request with id >> {}", notificationRequest.getId());
                    }
                }
        );
    }

}
