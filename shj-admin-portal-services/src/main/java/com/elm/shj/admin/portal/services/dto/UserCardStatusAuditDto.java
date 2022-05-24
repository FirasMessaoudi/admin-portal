/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for UserCardStatusAudit domain
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCardStatusAuditDto implements Serializable {

    private static final long serialVersionUID = -752004467273695076L;
    private long id;
    String uin;
    private long userId;
    private long cardId;
    String statusCode;
    Date creationDate;
}
