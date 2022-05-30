/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.validators.WithHousingCategory;
import com.elm.shj.admin.portal.services.data.validators.WithHousingSite;
import com.elm.shj.admin.portal.services.data.validators.WithHousingType;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

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
public class HousingMasterDto implements Serializable {


    private static final long serialVersionUID = -2674840473618482955L;

    private long id;
    @WithHousingType
    private String typeCode;
    @WithHousingSite
    private String siteCode;
    @NotNull(message = "validation.data.constraints.msg.20001")
    @Pattern(regexp = "([0-9]+)"
            , message = "validation.data.constraints.msg.20003")
    private String housingReferenceCode;
    @WithHousingCategory
    private String categoryCode;
    @NotNull(message = "validation.data.constraints.msg.20001")
    private String locationNameAr;
    @NotNull(message = "validation.data.constraints.msg.20001")
    private String locationNameEn;
    private String addressEn;
    private String addressAr;
    private Double lat;
    private Double lng;
    private String zoneCode;
    private Date creationDate;
    private Date updateDate;
    private GeoLocationDto geoLocation;
}
