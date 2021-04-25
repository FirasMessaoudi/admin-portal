/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant contact domain.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@NoArgsConstructor
@Data
public class ApplicantContactDto implements Serializable {


    private static final long serialVersionUID = -5662001048401642743L;

    private long id;
    private ApplicantDto applicant;
    private String languageList;
    private String email;
    private int localMobileNumber;
    private long intlMobileNumber;
    private String countryCode;
    private String streetName;
    private String districtName;
    private String cityName;
    private int buildingNumber;
    private int postalCode;
    private Date creationDate;
    private Date updateDate;
}
