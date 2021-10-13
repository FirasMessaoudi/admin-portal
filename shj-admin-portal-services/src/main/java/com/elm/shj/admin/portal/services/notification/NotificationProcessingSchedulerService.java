/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;


import com.elm.shj.admin.portal.orm.entity.*;
import com.elm.shj.admin.portal.orm.repository.NotificationRequestRepository;
import com.elm.shj.admin.portal.orm.repository.UserNotificationRepository;
import com.elm.shj.admin.portal.services.dto.ENotificationProcessingStatus;
import com.elm.shj.admin.portal.services.dto.EUserNotificationStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

/**
 * Service handling Applicant Notifications
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class NotificationProcessingSchedulerService {

    private final NotificationRequestRepository notificationRequestRepository;
    private final UserNotificationRepository userNotificationRepository;

    @Value("${notification.processing.batch.size}")
    private int notificationProcessingBatchSize;
    private static final String PROCESSING_STATUS_CODE = ENotificationProcessingStatus.NEW.name();

    @PostConstruct
    @Scheduled(cron = "${scheduler.notification.processing.cron}")
    @SchedulerLock(name = "notification-processing-task")
    void sendUserNotifications() {
        log.debug("send notification scheduler started ...");
        Page<JpaNotificationRequest> notificationRequests = notificationRequestRepository.findNotificationRequests(PageRequest.ofSize(notificationProcessingBatchSize), PROCESSING_STATUS_CODE, new Date());
        notificationRequests.stream().parallel().forEach(
                notificationRequest -> processNotificationRequest(notificationRequest)
        );

    }


    @Transactional
    void processNotificationRequest(JpaNotificationRequest notificationRequest) {
        log.debug("Start processing notification request with ID   {} ", notificationRequest.getId());

        JpaUserNotification userNotification = new JpaUserNotification();
        userNotification.setNotificationTemplate(notificationRequest.getNotificationTemplate());
        userNotification.setStatusCode(EUserNotificationStatus.NEW.name());
        userNotification.setUserId(notificationRequest.getUserId());
        userNotification.setUserLang(notificationRequest.getUserLang());
        Optional<JpaNotificationTemplateContent> notificationTemplateContent = notificationRequest.getNotificationTemplate().getNotificationTemplateContents().stream().filter(content -> content.getLang().equalsIgnoreCase(notificationRequest.getUserLang())).findAny();
        String resolvedBody = resolveNotificationRequestBody(notificationRequest.getNotificationTemplate(), notificationRequest.getNotificationRequestParameterValues(), notificationTemplateContent);
        userNotification.setResolvedBody(resolvedBody);
        userNotificationRepository.save(userNotification);
        notificationRequestRepository.delete(notificationRequest);

        log.debug("End processing notification request with ID   {} ", notificationRequest.getId());

    }

    private String resolveNotificationRequestBody(JpaNotificationTemplate notificationTemplate, Set<JpaNotificationRequestParameterValue> notificationRequestParameterValues, Optional<JpaNotificationTemplateContent> notificationContent) {
        String emptyBody = "no content";
        if (!notificationContent.isPresent()) {
            return emptyBody;
        }
        log.debug("Start resolving notification request body the unresolved body is {}", notificationContent.get().getBody());
        String resolvedBody = notificationContent.get().getBody();
        if (!resolvedBody.isEmpty()) {
            for (JpaNotificationRequestParameterValue param : notificationRequestParameterValues) {
                Optional<JpaNotificationTemplateParameter> templateParameter = notificationTemplate.getNotificationTemplateParameters().stream().filter(tparam -> tparam.getId() == param.getNotificationTemplateParameterId()).findFirst();
                if (!templateParameter.isPresent()) {
                    //throw custom Exception that this param is not in the template parameter list
                }
                resolvedBody = resolvedBody.replaceAll("<" + templateParameter.get().getParameterName() + ">", param.getNotificationTemplateParameterValue());
            }

            log.debug("End resolving notification request body the resolved body is {}", resolvedBody);
            return resolvedBody;
        }
        log.debug("End resolving notification request body is {empty}");
        return emptyBody;
    }

}
