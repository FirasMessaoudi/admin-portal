/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the notification template domain.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class NotificationTemplateDto implements Serializable {

    private static final long serialVersionUID = -3952522883570009978L;

    private long id;
    private String categoryCode;
    private String nameCode;
    private String statusCode;
    private String typeCode;
    private boolean important;
    private boolean actionRequired;
    private boolean enabled;
    private boolean userSpecific;
    private Date creationDate;
    private Date updateDate;
    private List<NotificationTemplateContentDto> notificationTemplateContents;
    private List<NotificationTemplateParameterDto> notificationTemplateParameters;
}
