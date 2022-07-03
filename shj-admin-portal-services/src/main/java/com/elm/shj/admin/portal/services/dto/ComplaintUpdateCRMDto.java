/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Complaint Update CRM object.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintUpdateCRMDto implements Serializable {

    private static final long serialVersionUID = 7859415865388347182L;

    private String crmTicketNumber;
    private String message;
    private Integer responseCode;

}
