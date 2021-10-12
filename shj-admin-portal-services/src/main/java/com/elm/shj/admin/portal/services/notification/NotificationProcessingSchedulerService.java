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
    private final String PROCESSING_STATUS_CODE = ENotificationProcessingStatus.NEW.name();

    @PostConstruct
    @Scheduled(cron = "${scheduler.notification.processing.cron}")
    @SchedulerLock(name = "notification-processing-task")
    void sendUserNotifications() {

        Page<JpaNotificationRequest> notificationRequests = notificationRequestRepository.findUserNotificationRequests(PageRequest.ofSize(notificationProcessingBatchSize), PROCESSING_STATUS_CODE, new Date());
        notificationRequests.stream().parallel().forEach(
                notificationRequest -> {
                    processUserNotificationRequest(notificationRequest);
                }
        );

    }


    @Transactional
    void processUserNotificationRequest(JpaNotificationRequest notificationRequest) {
        JpaUserNotification userNotification = new JpaUserNotification();
        userNotification.setNotificationTemplate(notificationRequest.getNotificationTemplate());
        userNotification.setStatusCode(EUserNotificationStatus.NEW.name());
        userNotification.setUserId(notificationRequest.getUserId());
        userNotification.setUserLang(notificationRequest.getUserLang());
        Optional<JpaNotificationTemplateContent> notificationTemplateContent = notificationRequest.getNotificationTemplate().getNotificationTemplateContents().stream().filter(content -> content.getLang().equals(notificationRequest.getUserLang())).findAny();
        String resolvedContent = resolveNotificationRequestBody(notificationRequest.getNotificationTemplate(), notificationRequest.getNotificationRequestParameterValues(), notificationTemplateContent);
        userNotification.setResolvedBody(resolvedContent);
        userNotificationRepository.save(userNotification);
        notificationRequestRepository.delete(notificationRequest);
    }

    private String resolveNotificationRequestBody(JpaNotificationTemplate notificationTemplate, Set<JpaNotificationRequestParameterValue> notificationRequestParameterValues, Optional<JpaNotificationTemplateContent> notificationContent) {
        String resolvedBody = "no content";
        String emptyBody = "no content";
        String unResolvedBody = notificationContent.get().getBody();


        if (notificationContent.isPresent() && !unResolvedBody.isEmpty()) {
            for (JpaNotificationRequestParameterValue param : notificationRequestParameterValues) {
                Optional<JpaNotificationTemplateParameter> templateParameter = notificationTemplate.getNotificationTemplateParameters().stream().filter(tparam -> tparam.getId() == param.getNotificationTemplateParameterId()).findFirst();
                if (!templateParameter.isPresent()) {
                    //throw custom Exception that this param is not in the template parameter list
                }
                resolvedBody = unResolvedBody.replaceAll("<" + templateParameter.get().getParameterName() + ">", param.getNotificationTemplateParameterValue());

            }
            return resolvedBody;
        }
        return emptyBody;
    }

}
