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
 * Dto class for the notification template parameter domain.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class NotificationTemplateParameterDto implements Serializable {

    private static final long serialVersionUID = 782484154477969188L;

    private long id;
    @JsonBackReference
    private NotificationTemplateDto notificationTemplate;
    private String parameterName;
    private Date creationDate;
}
