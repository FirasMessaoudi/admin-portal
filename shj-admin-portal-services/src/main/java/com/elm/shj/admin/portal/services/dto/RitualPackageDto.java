/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.validators.CompanyTypeCode;
import com.elm.shj.admin.portal.services.data.validators.RitualTypeCode;
import com.elm.shj.admin.portal.services.data.validators.SeasonYear;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the  Ritual Package .
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RitualPackageDto implements Serializable {

    private static final long serialVersionUID = 811079397522626616L;

    private long id;


    private Float price;
    private String departureCity;
    private Integer countryId;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @Pattern(regexp = "([0-9]+)"
            , message = "validation.data.constraints.msg.20003")
    private String referenceNumber;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @Pattern(regexp = "([0-9]+)"
            , message = "validation.data.constraints.msg.20003")
    private String companyRefCode;

    @Pattern(regexp = "(^[a-zA-Z0-9]*)"
            , message = "validation.data.constraints.msg.20003")
    private String hajjOfficeMakkah;
    @Pattern(regexp = "(^[a-zA-Z0-9]*)"
            , message = "validation.data.constraints.msg.20003")
    private String hajjOfficeMadina;
    @Pattern(regexp = "(^[a-zA-Z0-9]*)"
            , message = "validation.data.constraints.msg.20003")
    private String packageNameAr;
    @Pattern(regexp = "(^[a-zA-Z0-9]*)"
            , message = "validation.data.constraints.msg.20003")
    private String packageNameEn;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private String packageTypeCode;
    @CompanyTypeCode
    private Integer companyTypeCode;

    private Date startDate;
    private Date endDate;

    @RitualTypeCode
    private String ritualTypeCode;
    @SeasonYear
    private int seasonYear;

    @JsonBackReference(value = "ritualSeason")
    private CompanyRitualSeasonDto companyRitualSeason;

    @JsonBackReference("applicantPackages")
    private List<ApplicantPackageDto> applicantPackages;
    @JsonBackReference("packageHousings")
    private List<PackageHousingDto> packageHousings;
    @JsonBackReference("packageTransportations")
    private List<PackageTransportationDto> packageTransportations;

    private Date creationDate;
    private Date updateDate;
}
