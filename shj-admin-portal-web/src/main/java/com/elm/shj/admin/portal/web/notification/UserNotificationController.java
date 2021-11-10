/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.notification;

import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.dto.NotificationTemplateDto;
import com.elm.shj.admin.portal.services.notification.UserNotificationService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main controller for user notification management pages
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@RestController
@RequestMapping(Navigation.API_USER_NOTIFICATION)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserNotificationController {

    private final UserNotificationService userNotificationService;

    @PostMapping("/send-to-categorized")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.NOTIFICATION_MANAGEMENT + "')")
    public void sendToCategorizedApplicants(@RequestBody NotificationTemplateDto notificationTemplate,
                                           Authentication authentication) {
        userNotificationService.sendToCategorizedApplicants(notificationTemplate);
    }

}
