/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the user notification request parameter value domain.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestParameterValueDto implements Serializable {

    private static final long serialVersionUID = 5399553423919052752L;

    private long id;
    private NotificationRequestDto notificationRequest;
    private long notificationTemplateParameterId;
    private String notificationTemplateParameterValue;
    private Date creationDate;
}
