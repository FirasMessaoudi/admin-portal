/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the notification template domain.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Data
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
}
