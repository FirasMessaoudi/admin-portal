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
 * Dto class for the user notification status lookup domain.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class UserNotificationStatusLookupDto implements Serializable {

    private static final long serialVersionUID = -5828241433185126963L;

    private long id;
    private String code;
    private String lang;
    private String label;
    private Date creationDate;
}

