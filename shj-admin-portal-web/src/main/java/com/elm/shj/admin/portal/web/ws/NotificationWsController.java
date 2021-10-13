/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.notification.UserNotificationService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
}
