/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Dto class for the package catering domain.
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PackageCateringBasicDto implements Serializable {

    private long id;
    private String mealCode;
    private String mealTimeCode;
    private String mealTypeCode;
    private Time mealTime;
    private String mealDescription;
    private String descriptionAr;
    private String descriptionEn;
    private boolean isDefault;
    private Date creationDate;
    private Date updateDate;
    private PackageHousingBasicDto packageHousing;
}
