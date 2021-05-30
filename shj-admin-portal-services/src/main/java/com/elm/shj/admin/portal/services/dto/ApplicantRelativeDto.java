/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.mapper.NestedCells;
import com.elm.shj.admin.portal.services.data.validators.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant relative domain.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@NoArgsConstructor
@Data
public class ApplicantRelativeDto implements Serializable {

    private static final long serialVersionUID = 1502487798507783275L;

    private long id;

    @RelationshipCode
    @CellIndex(index = 8)
    private String relationshipCode;

    @JsonBackReference
    private ApplicantDto applicant;
    private ApplicantDto relativeApplicant;
    private Date creationDate;
    // used in data requests either through file upload or integration
    @Valid
    @NestedCells
    private ApplicantBasicInfoDto applicantBasicInfo;
    // transient specific mapping for relative applicant as we cannot decorate twice
    // used in data requests either through file upload or integration
    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    @CellIndex(index = 4)
    private long relativeIdNumber;

    @UniquePerRequest
    @PassportNumber
    @CellIndex(index = 5)
    private String relativePassportNumber;

    @GregorianDate(minOffset = -120, maxOffset = -10)
    @CellIndex(index = 6)
    private Date relativeDateOfBirthGregorian;

    @HijriDate(minOffset = -140, maxOffset = -11)
    @CellIndex(index = 7)
    private int relativeDateOfBirthHijri;
}
