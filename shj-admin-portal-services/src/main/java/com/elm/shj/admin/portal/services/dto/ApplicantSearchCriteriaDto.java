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
public class ApplicantSearchCriteriaDto implements Serializable {

    private static final long serialVersionUID = 4367068154822439005L;

    private String applicantName;
    private String uin;
    private String idNumber;
    private String groupNumber;
    private String gender;
    private String passportNumber;
}
