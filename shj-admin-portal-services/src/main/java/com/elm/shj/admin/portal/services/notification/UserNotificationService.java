package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplateContent;
import com.elm.shj.admin.portal.orm.entity.JpaUserNotification;
import com.elm.shj.admin.portal.orm.repository.NotificationRequestParameterValueRepository;
import com.elm.shj.admin.portal.orm.repository.NotificationRequestRepository;
import com.elm.shj.admin.portal.orm.repository.NotificationTemplateRepository;
import com.elm.shj.admin.portal.orm.repository.UserNotificationRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    private final NotificationTemplateRepository notificationTemplateRepository;
    private final NotificationRequestRepository notificationRequestRepository;
    private final NotificationRequestParameterValueRepository notificationRequestParameterValueRepo;
    public final static String TEMPLATE_NAME = "PASSWORD_EXPIRATION";
    private final NotificationTemplateService notificationTemplateService;

    /**
     * Finds user Notification   By userId
     *
     * @param userId the userId of the notifications  to find for
     * @return the found Notifications  or empty structure
     */
    public List<DetailedUserNotificationDto> findUserNotifications(long userId) {
        List<DetailedUserNotificationDto> detailedUserNotificationDtos = new ArrayList<>();
        List<JpaUserNotification> userNotifications = userNotificationRepository.findByUserId(userId);
        userNotifications.parallelStream().forEach(
                notification -> {

                    Optional<JpaNotificationTemplateContent> notificationTemplateContent = notification.getNotificationTemplate().getNotificationTemplateContents().stream().filter(content -> content.getLang().equals(notification.getUserLang())).findAny();
                    detailedUserNotificationDtos.add(DetailedUserNotificationDto.builder()
                            .resolvedBody(notification.getResolvedBody())
                            .statusCode(notification.getStatusCode())
                            .important(notification.getNotificationTemplate().isImportant())
                            .actionRequired(notification.getNotificationTemplate().isActionRequired())
                            .userSpecific(notification.getNotificationTemplate().isUserSpecific())
                            .categoryCode(notification.getNotificationTemplate().getCategoryCode())
                            .title(notificationTemplateContent.isPresent() ? notificationTemplateContent.get().getTitle() : null)
                            .actionLabel(notificationTemplateContent.isPresent() ? notificationTemplateContent.get().getActionLabel() : null)
                            .build());
                });
        return detailedUserNotificationDtos;
    }

    public Object savePasswordExpiryNotificationRequest(PasswordExpiryNotificationRequest passwordExpiryNotificationRequest) {
        Optional<NotificationTemplateDto> notificationTemplate = notificationTemplateService.findNotificationTemplateByNameCode(TEMPLATE_NAME);
        if (!notificationTemplate.isPresent()) {
            //throw custom exception and return specific error code
        }

        passwordExpiryNotificationRequest.getParameterValueList().parallelStream().forEach(
                param -> {
                    NotificationRequestDto notificationRequest = new NotificationRequestDto();
                    notificationRequest.setNotificationTemplate(notificationTemplate.get());
                    notificationRequest.setUserLang(param.getUserLang());
                    notificationRequest.setUserId(param.getUserId());
                    notificationRequest.setProcessingStatus(NotificationProcessingStatusLookupDto.builder().id(ENotificationProcessingStatus.NEW.getId()).build());
                    notificationRequest.setSendingDate(new Date());
                    /**get list of parameters for each template
                     * check if incoming parameter is found in this template parameters list or not
                     * if yes insert it with its value if not ignore the incoming one
                     *
                     * */
//                    notificationRequest.setNotificationRequestParameterValues();


                }

        );
//        notificationRequest.setUserLang(passwordExpiryNotificationRequest.getParameterValueList().parallelStream().filter(param-> param.getUserLang().equals()));
//      notificationRequestRepository.save()
        return new Object();
    }
}
