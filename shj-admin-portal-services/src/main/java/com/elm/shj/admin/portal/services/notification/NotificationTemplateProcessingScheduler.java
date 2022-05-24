/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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

    @PostConstruct
    @Scheduled(cron = "${scheduler.notification.template.processing.cron}")
    @SchedulerLock(name = "notification-template-processing-task")
    public void createNotificationRequests() {
        log.debug("create notification requests scheduler started ...");
       // LockAssert.assertLocked();
        notificationRequestService.processNotificationTemplates();
    }

}
