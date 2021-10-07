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
 * Dto class for the user notification queue domain.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class NotificationQueueDto implements Serializable {

    private static final long serialVersionUID = -2499947945567039208L;

    private long id;
    private long notificationTemplateId;
    private long userId;
    private NotificationProcessingStatusLookupDto processingStatus;
    private Date sendingDate;
    private Date creationDate;
    private Date updateDate;

}
