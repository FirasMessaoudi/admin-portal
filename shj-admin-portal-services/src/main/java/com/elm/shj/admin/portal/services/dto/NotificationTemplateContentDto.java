/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the notification template content domain.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class NotificationTemplateContentDto implements Serializable {

    private static final long serialVersionUID = -5368515528390490032L;

    private long id;
    @JsonBackReference
    private NotificationTemplateDto notificationTemplate;
    private String lang;
    private String title;
    private String body;
    private String actionLabel;
    private Date creationDate;
    private Date updateDate;
}
