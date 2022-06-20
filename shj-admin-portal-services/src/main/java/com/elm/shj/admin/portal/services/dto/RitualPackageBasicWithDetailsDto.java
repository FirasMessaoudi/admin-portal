/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.validation.ArabicCharacters;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the  Ritual Package Basic with Details.
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RitualPackageBasicWithDetailsDto implements Serializable {

    private long id;

    private Float price;
    private String departureCity;
    private Integer countryId;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @Pattern(regexp = "([0-9]+)"
            , message = "validation.data.constraints.msg.20003")
    private String referenceNumber;

    @Pattern(regexp = "(^[a-zA-Z0-9]*)", message = "validation.data.constraints.msg.20003")
    private String hajjOfficeMakkah;

    @Pattern(regexp = "(^[a-zA-Z0-9]*)", message = "validation.data.constraints.msg.20003")
    private String hajjOfficeMadina;

    @ArabicCharacters(lettersOnly = true, numbersOnly = false)
    private String packageNameAr;

    @Pattern(regexp = "(^[a-zA-Z0-9]*)", message = "validation.data.constraints.msg.20003")
    private String packageNameEn;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private String packageTypeCode;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @JsonBackReference(value = "ritualSeason")
    private CompanyRitualSeasonBasicDto companyRitualSeason;

    @JsonBackReference("packageHousings")
    private List<PackageHousingBasicDto> packageHousings;

    @JsonBackReference("packageTransportations")
    private List<PackageTransportationBasicDto> packageTransportations;

    private Date creationDate;
    private Date updateDate;
}
