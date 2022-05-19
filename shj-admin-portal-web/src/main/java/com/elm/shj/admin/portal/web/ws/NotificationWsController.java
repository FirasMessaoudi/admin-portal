/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.dto.PasswordExpiryNotificationRequest;
import com.elm.shj.admin.portal.services.dto.UserNotificationCategoryPreferenceDto;
import com.elm.shj.admin.portal.services.dto.EUserNotificationType;
import com.elm.shj.admin.portal.services.notification.NotificationRequestService;
import com.elm.shj.admin.portal.services.notification.UserNewNotificationsCountVo;
import com.elm.shj.admin.portal.services.notification.UserNotificationCategoryPreferenceService;
import com.elm.shj.admin.portal.services.notification.UserNotificationService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for exposing notification web services for external party.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@CrossOrigin(
        originPatterns = "*",
        maxAge = 3600,
        exposedHeaders = {"Authorization", JwtTokenService.CALLER_TYPE_HEADER_NAME, JwtTokenService.TOKEN_COOKIE_NAME},
        allowCredentials = "true"
)
@Slf4j
@RestController
@RequestMapping(Navigation.API_NOTIFICATION_INTEGRATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationWsController {

    private final UserNotificationService userNotificationService;
    private final NotificationRequestService notificationRequestService;
    private final UserNotificationCategoryPreferenceService notificationCategoryPreferenceService;

    /**
     * Count user un-read notifications.
     *
     * @param userId
     * @return number of un-read user specific and user not specific notifications.
     */
    @GetMapping("/count-new-notifications/{userId}")
    public ResponseEntity<WsResponse<?>> countUserNewNotifications(@PathVariable String userId) {
        log.info("Start countUserNewNotifications userId: {}", userId);
        UserNewNotificationsCountVo userNewNotificationsCountVo = userNotificationService.retrieveUserNewNotificationsCount(userId);
        log.info("Finish countUserNewNotifications {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(userNewNotificationsCountVo).build());
    }


    /**
     * finds user notifications by user Id
     *
     * @param userId the userId to find notifications for
     * @return the User Notifications
     */
    @GetMapping("/{userId}")
    public ResponseEntity<WsResponse<?>> findUserNotifications(@PathVariable String userId,
                                                               @RequestParam(required = false) EUserNotificationType type,
                                                               Pageable pageable) {
        log.info("Start findUserNotifications userId: {}, EUserNotificationType: {} , pageable:{}", userId, type, pageable);
        if (type != null) {
            log.info("Finish findUserNotifications findTypedUserNotifications {}", "SUCCESS");
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                    userNotificationService.findTypedUserNotifications(userId, type, pageable)).build());
        }
        log.info("Finish findUserNotifications findUserNotifications {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                userNotificationService.findUserNotifications(userId)).build());
    }

    /**
     * save Password Expiry Notification Request
     *
     * @param passwordExpiryNotificationRequest this is an object holding the details of password Expiry Notification Request
     * @return success message if process ended successfully
     */
    @PostMapping("/password-expiry")
    public ResponseEntity<WsResponse<?>> savePasswordExpiryNotificationRequest(@RequestBody @Validated PasswordExpiryNotificationRequest passwordExpiryNotificationRequest) {
        log.info("Start savePasswordExpiryNotificationRequest");
        try {
            notificationRequestService.savePasswordExpiryNotificationRequest(passwordExpiryNotificationRequest);
        } catch (NotFoundException e) {
            log.info("Finish savePasswordExpiryNotificationRequest {}, {} ", "FAILURE-NO_CONTENT", e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(e.getMessage()).build());
        }
        log.info("Finish savePasswordExpiryNotificationRequest {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(null).build());
    }


    /**
     * mark User Notification As Read
     *
     * @param notificationId is the id for the notification
     * @return success message if process ended successfully
     */
    @PostMapping("/mark-as-read/{notificationId}")
    public ResponseEntity<WsResponse<?>> markUserNotificationAsRead(@PathVariable Long notificationId) {
        log.info("Start markUserNotificationAsRead notificationId: {} ", notificationId);
        int numberOfRowsAffected = 0;
        try {
            numberOfRowsAffected = userNotificationService.markUserNotificationAsRead(notificationId);
        } catch (Exception e) {
            log.info("Finish markUserNotificationAsRead {}, {} ", "FAILURE-NO_CONTENT", e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(e.getMessage()).build());
        }
        log.info("Finish markUserNotificationAsRead {}, numberOfRowsAffected: {}", "SUCCESS", numberOfRowsAffected);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(numberOfRowsAffected).build());
    }

    /**
     * Find user notification category preference.
     *
     * @param userId
     * @return
     */
    @GetMapping("/user-notification-category-preference/{userId}")
    public ResponseEntity<WsResponse<?>> findUserNotificationCategoryPreference(@PathVariable String userId) {
        log.info("Start findUserNotificationCategoryPreference userId: {} ", userId);
        List<UserNotificationCategoryPreferenceDto> userNotificationCategoryPreference = notificationCategoryPreferenceService.findUserNotificationCategoryPreference(userId);
        log.info("Finish findUserNotificationCategoryPreference  {}, userNotificationCategoryPreferenceListSize {} ", "SUCCESS", userNotificationCategoryPreference == null ? null : userNotificationCategoryPreference.size());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(userNotificationCategoryPreference).build());
    }

    /**
     * Update user notification category preference.
     *
     * @param userNotificationCategoryPreference
     * @return updated userNotificationCategoryPreference
     */
    @PostMapping("/update-user-notification-category-preference")
    public ResponseEntity<WsResponse<?>> updateUserNotificationCategoryPreference(@RequestBody UserNotificationCategoryPreferenceDto userNotificationCategoryPreference) {
        log.info("Start updateUserNotificationCategoryPreference userNotificationCategoryPreference UserId: {} ", userNotificationCategoryPreference == null ? null : userNotificationCategoryPreference.getId());
        UserNotificationCategoryPreferenceDto userNotificationCategoryPreferenceDto = notificationCategoryPreferenceService.save(userNotificationCategoryPreference);
        log.info("Finish updateUserNotificationCategoryPreference  {}, updatedUserNotificationCategoryPreferenceId {} ", "SUCCESS", userNotificationCategoryPreference == null ? null : userNotificationCategoryPreference.getId());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(userNotificationCategoryPreferenceDto).build());
    }
}
