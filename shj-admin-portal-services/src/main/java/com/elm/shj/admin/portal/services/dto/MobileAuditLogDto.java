/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the MobileAuditLog domain.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MobileAuditLogDto implements Serializable {

    private static final long serialVersionUID = 8697903054574299840L;

    private long id;
    private String userId;
    private String event;
    private Date eventDate;
}
