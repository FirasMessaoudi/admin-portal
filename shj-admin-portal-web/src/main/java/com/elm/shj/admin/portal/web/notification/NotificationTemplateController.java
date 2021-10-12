/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.notification;

import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.dto.NotificationSearchCriteriaDto;
import com.elm.shj.admin.portal.services.dto.NotificationTemplateDto;
import com.elm.shj.admin.portal.services.notification.NotificationTemplateService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Main controller for notification template management pages
 *
 * @author ahmed elsayed
 * @since 1.0.0
 */
@RestController
@RequestMapping(Navigation.API_NOTIFICATION)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class NotificationTemplateController {

    private final NotificationTemplateService notificationTemplateService;

    @PostMapping("/template/list")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.NOTIFICATION_MANAGEMENT + "')")
    public Page<NotificationTemplateDto> searchNotificationTemplate(@RequestBody NotificationSearchCriteriaDto notificationSearchCriteria,
                                                                    Pageable pageable, Authentication authentication) throws IOException {

        return notificationTemplateService.findByFilter(notificationSearchCriteria, pageable);

    }
}
