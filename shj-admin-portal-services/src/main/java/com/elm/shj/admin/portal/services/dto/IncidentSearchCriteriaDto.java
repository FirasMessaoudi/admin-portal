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
public class IncidentSearchCriteriaDto implements Serializable {

    private static final long serialVersionUID = 4619655483279464845L;

    private String applicantId;
    private String applicantName;
    private String incidentType;
    private Date creationDateStart;
    private Date creationDateEnd;
    private String status;
}
