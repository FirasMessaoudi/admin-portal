/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the notification processing status lookup domain.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationProcessingStatusLookupDto implements Serializable {

    private static final long serialVersionUID = -1358810971203198326L;

    private long id;
    private String code;
    private String description;
    private Date creationDate;
}
