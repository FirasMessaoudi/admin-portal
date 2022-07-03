/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.validators.CountryCode;
import com.elm.shj.admin.portal.services.data.validators.LanguageCodeList;
import com.elm.shj.admin.portal.services.data.validators.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant contact lite domain.
 *
 * @author f.messaoudi
 * @since 1.3.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApplicantContactBasicDto implements Serializable {


    private static final long serialVersionUID = 7775986200662613686L;
    private long id;

    @JsonBackReference(value = "applicant")
    private ApplicantBasicDto applicant;

    @LanguageCodeList
    @CellIndex(index = 15)
    private String languageList;

    @NullOrNotBlank(min = 5, max = 50)
    @Email(message = "validation.data.constraints.msg.20003", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
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
