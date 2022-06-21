/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Complaint Update CRM object.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Getter
@Setter
@NoArgsConstructor
public class ComplaintUpdateCRMDto implements Serializable {

    private static final long serialVersionUID = 7859415865388347182L;

    private String ComplaintSearchCriteriaDto;
    private String SmartIDTicketNumber;
    private String Comments;
    private Integer Status;

}
