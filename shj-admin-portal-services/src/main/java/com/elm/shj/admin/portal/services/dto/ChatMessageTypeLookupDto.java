/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the chat message type lookup domain.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageTypeLookupDto implements Serializable {

    private static final long serialVersionUID = 3876307347224022563L;

    private long id;
    private String code;
    private Date creationDate;
}
