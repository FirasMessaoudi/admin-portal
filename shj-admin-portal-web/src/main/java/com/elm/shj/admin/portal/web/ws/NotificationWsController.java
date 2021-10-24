/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.dto.PasswordExpiryNotificationRequest;
import com.elm.shj.admin.portal.services.notification.NotificationRequestService;
import com.elm.shj.admin.portal.services.notification.UserNotificationService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Count user un-read notifications.
     *
     * @param userId
     * @return number of un-read user notifications.
     */
    @GetMapping("/count-new-notifications/{userId}")
    public ResponseEntity<WsResponse<?>> countUserNewNotifications(@PathVariable Long userId) {
        log.debug("Handler for count un-read user notifications.");
        int userNewNotificationsCount = userNotificationService.retrieveUserNewNotificationsCount(userId);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(userNewNotificationsCount).build());
    }


    /**
     * finds user notifications by user Id
     *
     * @param userId the userId to find notifications for
     * @return the User Notifications
     */
    @GetMapping("/{userId}")
    public ResponseEntity<WsResponse<?>> findUserNotifications(@PathVariable long userId) {
        log.debug("Handler for {}", "Find all user notifications by user Id");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(userNotificationService.findUserNotifications(userId)).build());
    }

    /**
     * save Password Expiry Notification Request
     *
     * @param passwordExpiryNotificationRequest this is an object holding the details of password Expiry Notification Request
     * @return success message if process ended successfully
     */
    @PostMapping("/password-expiry")
    public ResponseEntity<WsResponse<?>> savePasswordExpiryNotificationRequest(@RequestBody @Validated PasswordExpiryNotificationRequest passwordExpiryNotificationRequest) {
        log.debug("Handler for {}", "Find all user notifications by user Id");
        try {
            notificationRequestService.savePasswordExpiryNotificationRequest(passwordExpiryNotificationRequest);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE).body(e.getMessage()).build());
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(null).build());
    }


    /**
     * mark User Notification As Read
     *
     * @param notificationId is the id for the notification
     * @return success message if process ended successfully
     */
    @PostMapping("/mark-as-read/{notificationId}")
    public ResponseEntity<WsResponse<?>> markUserNotificationAsRead(@PathVariable Long notificationId) {
        log.debug("Handler for {}", "mark user notification as read");
        int numberOfRowsAffected = 0;
        try {
            numberOfRowsAffected = userNotificationService.markUserNotificationAsRead(notificationId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE).body(e.getMessage()).build());
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(numberOfRowsAffected).build());
    }
}
