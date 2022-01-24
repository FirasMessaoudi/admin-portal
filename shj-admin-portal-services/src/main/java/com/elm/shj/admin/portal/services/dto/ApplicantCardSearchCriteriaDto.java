/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Applicant card filter value object.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApplicantCardSearchCriteriaDto implements Serializable {

    private static final long serialVersionUID = 4367068154822439005L;

    private String ritualSeason;
    private String ritualTypeCode;
    private String uin;
    private String idNumber;
    private String referenceNumber;
    private String statusCode;
    private String digitalIdStatus;
    private String gender;
    private String nationalityCode;
    private String passportNumber;
}
