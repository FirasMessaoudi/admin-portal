/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Categorized notification filter value object.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class CategorizedNotificationVo implements Serializable {

    private static final long serialVersionUID = 3950036351611874479L;

    private NotificationTemplateDto notificationTemplate;
    private ApplicantSearchCriteriaDto applicantSearchCriteria;
}
