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
 * Incident filter value object.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ComplaintSearchCriteriaDto implements Serializable {

    private static final long serialVersionUID = -8688455019872282414L;

    private String complaintNumber;
    private String applicantId;
    private String applicantName;
    private String complaintType;
    private Date fromDate;
    private Date toDate;
    private String status;
}
