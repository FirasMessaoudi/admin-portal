/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.validators.CountryCode;
import com.elm.shj.admin.portal.services.data.validators.LanguageCodeList;
import com.elm.shj.admin.portal.services.data.validators.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
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
    @JsonBackReference
    private ApplicantDto applicant;

    @LanguageCodeList
    @CellIndex(index = 15)
    private String languageList;

    @NullOrNotBlank(min = 5, max = 50)
    @Email(message = "validation.data.constraints.msg.20003")
    @CellIndex(index = 16)
    private String email;

    @NullOrNotBlank(min = 5, max = 16)
    @CellIndex(index = 17)
    private String localMobileNumber;

    @NullOrNotBlank(min = 5, max = 30)
    @CellIndex(index = 18)
    private String intlMobileNumber;

    @CountryCode
    @CellIndex(index = 19)
    private String countryCode;

    @Pattern(regexp = "[\\w ]*", message = "validation.data.constraints.msg.20003")
    @NullOrNotBlank(min = 3, max = 100)
    @CellIndex(index = 22)
    private String streetName;

    @Pattern(regexp = "[\\w ]*", message = "validation.data.constraints.msg.20003")
    @NullOrNotBlank(min = 3, max = 50)
    @CellIndex(index = 21)
    private String districtName;

    @Pattern(regexp = "[\\w ]*", message = "validation.data.constraints.msg.20003")
    @NullOrNotBlank(min = 3, max = 50)
    @CellIndex(index = 20)
    private String cityName;

    @Pattern(regexp = "[\\w ]*", message = "validation.data.constraints.msg.20003")
    @NullOrNotBlank(min = 5, max = 30)
    @CellIndex(index = 23)
    private String buildingNumber;

    @Pattern(regexp = "[\\w ]*", message = "validation.data.constraints.msg.20003")
    @NullOrNotBlank(min = 3, max = 30)
    @CellIndex(index = 24)
    private String postalCode;
    private Date creationDate;
    private Date updateDate;
}
