/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Applicant filter value object.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ApplicantSearchCriteriaDto implements Serializable {

    private static final long serialVersionUID = -954603126098503761L;

    private Long campId;
    private Long companyId;
    private String nationalityCode;
    private Integer minAge;
    private Integer maxAge;
    private String gender;
    private String idNumber;
    private String uin;
    private String passportNumber;
}
