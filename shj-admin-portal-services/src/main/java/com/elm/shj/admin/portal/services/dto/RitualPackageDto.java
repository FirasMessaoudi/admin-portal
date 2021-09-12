/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.orm.entity.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the  Ritual Package .
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RitualPackageDto implements Serializable {


    private static final long serialVersionUID = 811079397522626616L;
    private long id;
    private String typeCode;
    private float price;
    private String departureCity;
    private int countryId;

//    @JsonBackReference
//    private List<JpaCompanySeasonPackageDto> companySeasonPackages;
//     @JsonBackReference
//     private List<JpaApplicantPackageDto> applicantPackages;
//     @JsonBackReference
//     private List<JpaPackageHousingDto> packageHousings;
//     @JsonBackReference
//     private List<JpaPackageTransportationDto> packageTransportations;
//


    private Date creationDate;
    private Date updateDate;
}
