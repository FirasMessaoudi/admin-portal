/*
 * Copyright (c) 2022 ELM. All rights reserved.
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
 * Dto class for the Package Housing Basic.
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageHousingBasicDto implements Serializable {

    private long id;
    private String typeCode;
    private String siteCode;
    private RitualPackageBasicDto ritualPackage;
    private HousingZoneBasicDto housingZone;

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
    private Double lat;
    private Double lng;

    @JsonBackReference("packageCatering")
    private List<PackageCateringBasicDto> packageCatering;

    private Date creationDate;
    private Date updateDate;

}
