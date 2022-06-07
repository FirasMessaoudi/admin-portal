/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.huic;

import com.elm.shj.admin.portal.services.data.validators.*;
import com.elm.shj.admin.portal.services.dto.GeoLocationDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Dto class for the  Housing Master .
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HuicHousingMaster implements Serializable {


    private static final long serialVersionUID = 3307698822526943775L;
    @WithHousingType
    private Integer typeCode;
    @WithHousingSite
    private Integer siteCode;
    @NotNull(message = "validation.data.constraints.msg.20001")
    private Long houseRefNumber;
    @WithHousingCategory
    private Integer categoryCode;
    @NotNull(message = "validation.data.constraints.msg.20001")
    private String locationNameAr;
    @NotNull(message = "validation.data.constraints.msg.20001")
    private String locationNameEn;
    private String addressEn;
    private String addressAr;
    private Long zoneCode;
    private GeoLocationDto locationLongLat;
    @RitualTypeCode
    private Integer ritualTypeCode;
    @SeasonYear
    private int seasonYear;
}
