/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.notification;

import com.elm.shj.admin.portal.services.applicant.CompanyLiteService;
import com.elm.shj.admin.portal.services.applicant.PackageHousingService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.notification.NotificationRequestService;
import com.elm.shj.admin.portal.services.notification.NotificationTemplateService;
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
    private final NotificationRequestService notificationRequestService;
    private final CompanyLiteService companyLiteService;
    private final PackageHousingService packageHousingService;

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
    @PreAuthorize("hasAuthority('" + AuthorityConstants.USER_DEFINED_NOTIFICATION_MANAGEMENT + "')")
    public Page<NotificationTemplateDto> searchUserDefinedNotificationTemplate(@RequestBody NotificationSearchCriteriaDto notificationSearchCriteria,
                                                                               Pageable pageable, Authentication authentication) {
        return notificationTemplateService.findByFilter(notificationSearchCriteria, USER_DEFINED, pageable);
    }

    @PostMapping("/user-defined/create")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.USER_DEFINED_NOTIFICATION_MANAGEMENT + "')")
    public NotificationTemplateDto createUserDefinedNotificationTemplate(@RequestBody NotificationTemplateDto notificationTemplate, Authentication authentication) {
        return notificationTemplateService.create(notificationTemplate);
    }


    @GetMapping("/{templateId}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.SYSTEM_DEFINED_NOTIFICATION_DETAILS + "')")
    public NotificationTemplateDto findNotificationTemplateById(@PathVariable long templateId,
                                                                Authentication authentication) {
        return notificationTemplateService.findOne(templateId);
    }

    @GetMapping("/user-defined/{templateId}")
    //TODO Change the authorization to user defined notification
    @PreAuthorize("hasAuthority('" + AuthorityConstants.USER_DEFINED_NOTIFICATION_MANAGEMENT + "')")
    public NotificationTemplateDto findUserDefinedNotificationTemplateById(@PathVariable long templateId, Authentication authentication) {
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

    @PostMapping("/send-to-all")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.USER_DEFINED_NOTIFICATION_MANAGEMENT + "')")
    public NotificationTemplateDto sendToAllApplicants(@RequestBody NotificationTemplateDto notificationTemplateDto, Authentication authentication) {
        return notificationRequestService.sendToAllApplicants(notificationTemplateDto);
    }

    @PostMapping("/send-to-categorized")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.USER_DEFINED_NOTIFICATION_MANAGEMENT + "')")
    public NotificationTemplateDto sendToCategorizedApplicants(@RequestBody CategorizedNotificationVo categorizedNotificationVo,
                                                               Authentication authentication) {
        return notificationRequestService.sendToCategorizedApplicants(categorizedNotificationVo);
    }

    @GetMapping("/company/list")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.USER_DEFINED_NOTIFICATION_MANAGEMENT + "')")
    public List<CompanyLiteDto> listCompanies(Authentication authentication) {
        log.debug("List companies...");
        return companyLiteService.findAll();
    }

    @GetMapping("/camp/list")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.USER_DEFINED_NOTIFICATION_MANAGEMENT + "')")
    public List<PackageHousingDto> listCamps(Authentication authentication) {
        log.debug("List camps...");
        return packageHousingService.findAllCamps();
    }

    private boolean validateTemplateContentParams(NotificationTemplateDto notificationTemplate) {
        boolean allParamsValid = true;
        Pattern pattern = Pattern.compile("\\<(.*?)\\>");
        List templateParams = notificationTemplate.getNotificationTemplateParameters().parallelStream().map(param -> param.getParameterName()).collect(Collectors.toList());
        if (notificationTemplate.getNotificationTemplateContents() != null && notificationTemplate.getNotificationTemplateContents().size() > 0) {
            for (NotificationTemplateContentDto notificationTemplateContentDto : notificationTemplate.getNotificationTemplateContents()) {

                if (notificationTemplateContentDto != null && notificationTemplateContentDto.getBody() != null) {
                    Matcher matcher = pattern.matcher(notificationTemplateContentDto.getBody());
                    while (matcher.find()) {
                        if (!templateParams.contains(matcher.group(1).trim())) {
                            allParamsValid = false;
                            break;
                        }
                    }
                }

            }

        }
        return allParamsValid;
    }
}