/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class NotificationSearchCriteriaDto implements Serializable {

    private static final long serialVersionUID = -6323946540060068951L;

    private String notificationBody;
    private String notificationTitle;
    private String notificationCategory;
    private String notificationName;
    private Boolean severity;
    private Boolean notificationType;
    private Date creationDateStart;
    private Date creationDateEnd;
}
