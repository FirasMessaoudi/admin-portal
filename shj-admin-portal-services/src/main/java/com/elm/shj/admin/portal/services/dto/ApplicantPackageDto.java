/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the applicant package domain.
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApplicantPackageDto implements Serializable {

    private static final long serialVersionUID = 9128835722212623516L;

    public ApplicantPackageDto() {
    }

    private long id;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private long applicantUin;

    private Date startDate;

    private Date endDate;

    private Date creationDate;

    private Date updateDate;

    @JsonBackReference(value = "ritualPackage")
    private RitualPackageDto ritualPackage;

    @JsonBackReference(value = "applicantPackageCaterings")
    private List<ApplicantPackageCateringDto> applicantPackageCaterings;

    @JsonBackReference(value = "applicantPackageTransportations")
    private List<ApplicantPackageTransportationDto> applicantPackageTransportations;

    @JsonBackReference(value = "applicantPackageHousings")
    private List<ApplicantPackageHousingDto> applicantPackageHousings;

    @JsonBackReference(value = "applicantRituals")
    private List<ApplicantRitualDto> applicantRituals;
}
