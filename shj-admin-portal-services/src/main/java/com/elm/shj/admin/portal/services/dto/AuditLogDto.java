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
 * Dto class for the AuditLog domain.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@Data
public class AuditLogDto implements Serializable {

    private static final long serialVersionUID = 4778905193540470232L;

    private long id;
    private int userIdNumber;
    private String handler;
    private String action;
    private String params;
    private String host;
    private String origin;
    private String channel;
    private Date startTime;
    private int processingTime;
    private int httpStatus;
    private String errorDetails;
}
