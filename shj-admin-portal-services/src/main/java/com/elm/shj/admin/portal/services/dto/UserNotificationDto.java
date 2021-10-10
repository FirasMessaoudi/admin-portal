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
 * Dto class for the user notification domain.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class UserNotificationDto implements Serializable {

    private static final long serialVersionUID = 5517356491343788168L;

    private long id;
    private long notificationTemplateId;
    private long userId;
    private String resolvedBody;
    private String statusCode;
    private Date creationDate;
    private Date updateDate;
}
