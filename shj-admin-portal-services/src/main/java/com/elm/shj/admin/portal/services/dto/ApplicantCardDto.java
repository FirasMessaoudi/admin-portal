/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the applicant card domain.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantCardDto implements Serializable, HibernateAwareMapper {

    private static final long serialVersionUID = -5830783313676682718L;

    private long id;
    private ApplicantRitualDto applicantRitual;
    private String referenceNumber;
    private Long batchId;
    @JsonBackReference
    private List<PrintRequestCardDto> printRequestCards;
    private String statusCode;
    private Date creationDate;
    private List<ApplicantPackageCateringDto> applicantPackageCaterings;
    private List<ApplicantPackageHousingDto> applicantPackageHousings;
    private List<ApplicantPackageTransportationDto> applicantPackageTransportations;
    private CompanyLiteDto companyLites;
}
