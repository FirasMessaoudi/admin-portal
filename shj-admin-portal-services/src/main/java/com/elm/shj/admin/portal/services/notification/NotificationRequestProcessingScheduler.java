/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationRequest;
import com.elm.shj.admin.portal.orm.repository.NotificationRequestRepository;
import com.elm.shj.admin.portal.services.dto.ENotificationProcessingStatus;
import com.elm.shj.admin.portal.services.dto.ENotificationTemplateType;
import com.elm.shj.admin.portal.services.dto.NotificationTemplateDto;
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
import java.util.List;

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
    private final NotificationTemplateService notificationTemplateService;
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

    @PostConstruct
    @Scheduled(cron = "${scheduler.notification.processing.cron}")
    @SchedulerLock(name = "notification-template-processing-task")
    void createNotificationRequests() {
        log.debug("create notification requests scheduler started ...");
        List<NotificationTemplateDto> notificationTemplates = notificationTemplateService.findUnprocessedUserDefinedNotifications(ENotificationTemplateType.USER_DEFINED.name(), new Date(), false, true);
        notificationTemplates.forEach(
                notificationTemplate -> {
                    notificationRequestService.createUserDefinedNotificationRequest(notificationTemplate);
                    notificationTemplate.setIsProcessed(true);
                    notificationTemplateService.save(notificationTemplate);
                }
        );
    }
}
