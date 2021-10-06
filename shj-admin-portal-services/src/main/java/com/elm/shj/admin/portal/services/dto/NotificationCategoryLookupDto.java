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
 * Dto class for the notification category lookup domain.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Data
public class NotificationCategoryLookupDto implements Serializable {

    private static final long serialVersionUID = -3311259714351332784L;

    private long id;
    private String code;
    private String lang;
    private String label;
    private Date creationDate;
}
