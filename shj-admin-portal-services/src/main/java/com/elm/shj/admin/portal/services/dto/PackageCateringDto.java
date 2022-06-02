/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the package catering domain.
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PackageCateringDto implements Serializable {

    private static final long serialVersionUID = 4099330015218595333L;

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

    private PackageHousingDto packageHousing;

    @JsonBackReference(value = "applicantPackageCaterings")
    private List<ApplicantPackageCateringDto> applicantPackageCaterings;

}
