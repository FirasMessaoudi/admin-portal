/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Scheduler to process automatically notification templates
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class NotificationTemplateProcessingScheduler {

    private final NotificationRequestService notificationRequestService;

    @Value("${notification.processing.batch.size}")
    private int notificationProcessingBatchSize;

    @Value("${scheduler.notification.template.processing.active.nodes}")
    private String schedulerActiveNodes;

    @Scheduled(cron = "${scheduler.notification.template.processing.cron}")
    public void createNotificationRequests() {
        String runningIpAddress;
        try {
            runningIpAddress = InetAddress.getLocalHost().getHostAddress();
            log.info("running IP address for potential notification template processing scheduler is: {}", runningIpAddress);
        } catch (UnknownHostException e) {
            log.error("Error while getting the running ip address. Notification template processing scheduler will not run.", e);
            return;
        }
        if (schedulerActiveNodes == null || schedulerActiveNodes.isEmpty()) {
            log.warn("Notification template processing scheduler will not run, no active nodes are configured in database.");
            return;
        }
        if (!schedulerActiveNodes.contains(runningIpAddress)) {
            log.warn("Notification template processing scheduler will not run, {} ip is not in the configured active nodes list.");
            return;
        }
        log.debug("create notification requests scheduler started ...");
       // LockAssert.assertLocked();
        notificationRequestService.processNotificationTemplates(notificationProcessingBatchSize);
    }

}
