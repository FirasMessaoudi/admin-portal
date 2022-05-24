/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.validators.WithHousingCategory;
import com.elm.shj.admin.portal.services.data.validators.WithHousingSite;
import com.elm.shj.admin.portal.services.data.validators.WithHousingType;
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
 * Dto class for the Package Housing  .
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class PackageHousingDto implements Serializable {

    private static final long serialVersionUID = -2333926062779667053L;

    public PackageHousingDto(){
    }
    private long id;
    @WithHousingType
    private String typeCode;
    @WithHousingSite
    private String siteCode;
    private RitualPackageDto ritualPackage;
    private HousingZoneDto housingZone;
    @NotNull(message = "validation.data.constraints.msg.20001")
    private String referenceNumber;
    @WithHousingCategory
    private String categoryCode;
    @NotNull(message = "validation.data.constraints.msg.20001")
    private String locationNameAr;
    @NotNull(message = "validation.data.constraints.msg.20001")
    private String locationNameEn;
    private Date validityStart;
    private Date validityEnd;
    private String addressEn;
    private String addressAr;
    private boolean isDefault;
    private double lat;
    private double lng;
    @JsonBackReference("packageCatering")
    private List<PackageCateringDto> packageCatering;
    @JsonBackReference("applicantPackageHousing")
    private List<ApplicantPackageHousingDto> applicantPackageHousing;
    private Date creationDate;
    private Date updateDate;

    private GeoLocationDto geoLocation;
}
