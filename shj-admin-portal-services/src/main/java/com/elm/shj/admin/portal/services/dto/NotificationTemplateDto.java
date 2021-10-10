/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationRequest;
import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplateContent;
import com.elm.shj.admin.portal.orm.entity.JpaUserNotification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
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
    private Set<JpaNotificationTemplateContent> notificationTemplateContents;
    private Set<JpaNotificationRequest> notificationRequests;
    private Set<JpaUserNotification> userNotifications;
    private List<NotificationTemplateParameterDto> notificationTemplateParameters;

    private Date creationDate;
    private Date updateDate;
}
