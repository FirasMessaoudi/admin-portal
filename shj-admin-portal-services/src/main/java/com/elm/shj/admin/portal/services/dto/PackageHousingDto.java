/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.validators.WithHousingMaster;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

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
@NoArgsConstructor
@Builder
public class PackageHousingDto implements Serializable {

    private static final long serialVersionUID = -2333926062779667053L;

    private long id;
    private String typeCode;
    private String siteCode;
    private RitualPackageDto ritualPackage;
    private HousingZoneDto housingZone;
    @NotNull(message = "validation.data.constraints.msg.20001")
    @WithHousingMaster
    private String referenceNumber;
    private String categoryCode;
    private String locationNameAr;
    private String locationNameEn;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "validation.data.constraints.msg.20001")
    private Date validityStart;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "validation.data.constraints.msg.20001")
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

}
