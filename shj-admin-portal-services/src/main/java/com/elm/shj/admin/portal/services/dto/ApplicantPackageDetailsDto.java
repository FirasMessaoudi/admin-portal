/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Dto class for the applicant package details domain.
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class ApplicantPackageDetailsDto implements Serializable {

    private static final long serialVersionUID = 52707732635351247L;

    private List<ApplicantPackageCateringDto> applicantPackageCaterings;
    private List<ApplicantPackageHousingDto> applicantPackageHousings;
    private List<ApplicantPackageTransportationDto> applicantPackageTransportations;
    private CompanyLiteDto companyLite;
}
