/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Notification filter value object.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class NotificationSearchCriteriaDto implements Serializable {

    private static final long serialVersionUID = -6323946540060068951L;

    private String notificationBody;
    private String notificationTitle;
    private String notificationCategory;
    private String notificationName;
    private String description;
    private Boolean severity;
    private Boolean notificationType;
    private Date creationDateStart;
    private Date creationDateEnd;
    private Date sendingDateStart;
    private Date sendingDateEnd;
    private String companyCode;
}
