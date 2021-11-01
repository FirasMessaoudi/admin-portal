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
 * Dto class for the user notification category preference domain.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class UserNotificationCategoryPreferenceDto implements Serializable {

    private static final long serialVersionUID = 6279898162164188847L;

    private long id;
    private int userId;
    private String categoryCode;
    private boolean enabled;
    private Date creationDate;
    private Date updateDate;
}
