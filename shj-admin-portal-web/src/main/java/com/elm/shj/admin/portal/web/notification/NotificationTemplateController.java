/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.notification;

import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.dto.NotificationSearchCriteriaDto;
import com.elm.shj.admin.portal.services.dto.NotificationTemplateContentDto;
import com.elm.shj.admin.portal.services.dto.NotificationTemplateDto;
import com.elm.shj.admin.portal.services.notification.NotificationTemplateService;
import com.elm.shj.admin.portal.services.notification.UserNotificationService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Main controller for notification template management pages
 *
 * @author ahmed elsayed
 * @since 1.0.0
 */
@RestController
@RequestMapping(Navigation.API_NOTIFICATION_TEMPLATE)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class NotificationTemplateController {

    private final NotificationTemplateService notificationTemplateService;
    private final UserNotificationService userNotificationService;

    private final static String SYSTEM_DEFINED = "SYSTEM_DEFINED";
    private final static String USER_DEFINED = "USER_DEFINED";
    private static final int INVALID_TEMPLATE_CONTENT_PARAMS_RESPONSE_CODE = 558;

    @PostMapping("/system-defined/list")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.NOTIFICATION_MANAGEMENT + "')")
    public Page<NotificationTemplateDto> searchSystemDefinedNotificationTemplate(@RequestBody NotificationSearchCriteriaDto notificationSearchCriteria,
                                                                                 Pageable pageable, Authentication authentication) throws IOException {
        return notificationTemplateService.findByFilter(notificationSearchCriteria, SYSTEM_DEFINED, pageable);
    }

    @PostMapping("/user-defined/list")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.NOTIFICATION_MANAGEMENT + "')")
    public Page<NotificationTemplateDto> searchUserDefinedNotificationTemplate(@RequestBody NotificationSearchCriteriaDto notificationSearchCriteria,
                                                                               Pageable pageable, Authentication authentication) {
        return notificationTemplateService.findByFilter(notificationSearchCriteria, USER_DEFINED, pageable);
    }

    @PostMapping("/user-defined/create")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.NOTIFICATION_MANAGEMENT + "')")
    public NotificationTemplateDto createUserDefinedNotificationTemplate(@RequestBody NotificationTemplateDto notificationTemplate, Authentication authentication) {
        return notificationTemplateService.create(notificationTemplate);
    }


    @GetMapping("/{templateId}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.SYSTEM_DEFINED_NOTIFICATION_DETAILS + "')")
    public NotificationTemplateDto findNotificationTemplateById(@PathVariable long templateId,
                                                                Authentication authentication) {
        return notificationTemplateService.findOne(templateId);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.SYSTEM_DEFINED_NOTIFICATION_DETAILS + "')")
    public ResponseEntity<NotificationTemplateDto> updateNotificationTemplate(@RequestBody @Validated NotificationTemplateDto notificationTemplate) {
        if (!validateTemplateContentParams(notificationTemplate))
            return ResponseEntity.status(INVALID_TEMPLATE_CONTENT_PARAMS_RESPONSE_CODE).body(null);
        NotificationTemplateDto updatedNotificationTemplate = notificationTemplateService.updateNotificationTemplate(notificationTemplate);
        return ResponseEntity.ok(updatedNotificationTemplate);
    }

    @GetMapping("/send-to-all/{templateId}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.NOTIFICATION_MANAGEMENT + "')")
    public void sendToAllApplicants(@PathVariable long templateId,
                                    Authentication authentication) {
        userNotificationService.sendToAllApplicants(templateId);
    }

    private boolean validateTemplateContentParams(NotificationTemplateDto notificationTemplate) {
        boolean allParamsValid = true;
        Pattern pattern = Pattern.compile("\\<(.*?)\\>");
        List templateParams = notificationTemplate.getNotificationTemplateParameters().parallelStream().map(param -> param.getParameterName()).collect(Collectors.toList());
        for (NotificationTemplateContentDto notificationTemplateContentDto : notificationTemplate.getNotificationTemplateContents()) {
            Matcher matcher = pattern.matcher(notificationTemplateContentDto.getBody());
            while (matcher.find()) {
                if (!templateParams.contains(matcher.group(1).trim())) {
                    allParamsValid = false;
                    break;
                }
            }
        }
        return allParamsValid;
    }
}