/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.validation.ArabicCharacters;
import com.elm.dcc.foundation.commons.validation.LatinCharacters;
import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.mapper.NestedCells;
import com.elm.shj.admin.portal.services.data.validators.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the applicant domain.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@FieldDependency.List({
        @FieldDependency(first = "idNumber", second = "passportNumber"),
        @FieldDependency(first = "dateOfBirthGregorian", second = "dateOfBirthHijri")
})
@NoArgsConstructor
@Data
public class ApplicantDto implements Serializable {

    private static final long serialVersionUID = 4276580006724069703L;

    private long id;

    //private long rowNum;
    //@PassportNumber
    //@CellIndex(index = 4)
    //private String borderNumber;
    // @CellIndex(index = 12)
    //private String biometricDataFinger;
    // @CellIndex(index = 13)
    //private String biometricDataFace;
    // @CellIndex(index = 15)
    //private String educationLevel;

    @Gender
    @CellIndex(index = 8)
    private String gender;

    @CountryCode
    @CellIndex(index = 9)
    private String nationalityCode;

    @UniquePerRequest
    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    @CellIndex(index = 0)
    private long idNumber;

    @IdNumber(minLength = 5, maxLength = 30)
    @CellIndex(index = 10)
    private String idNumberOriginal;

    @UniquePerRequest
    @PassportNumber
    @CellIndex(index = 1)
    private String passportNumber;

    @GregorianDate(minOffset = -120, maxOffset = -10)
    @CellIndex(index = 2)
    private Date dateOfBirthGregorian;

    @HijriDate(minOffset = -140, maxOffset = -11)
    @CellIndex(index = 3)
    private long dateOfBirthHijri;

    @OnlyCharacters(min = 6, max = 150, arabic = true)
    @CellIndex(index = 6)
    private String fullNameAr;

    @OnlyCharacters(min = 10, max = 150, allowEmpty = false)
    @CellIndex(index = 5)
    private String fullNameEn;

    @NullOrNotBlank(min = 10, max = 150)
    @CellIndex(index = 7)
    private String fullNameOrigin;

    @MaritalStatusCode
    @CellIndex(index = 14)
    private String maritalStatusCode;

    @NotNull(message = "validation.data.constraints.msg.119")
    @CellIndex(index = 11)
    private String photo;

    private long requestId;
    private List<ApplicantDigitalIdDto> digitalIds;
    private List<ApplicantRelativeDto> relatives;
    @JsonBackReference
    private List<ApplicantRitualDto> rituals;
    @NestedCells
    @Valid
    private List<ApplicantContactDto> contacts;
    private List<ApplicantHealthDto> healths;
    private long status;
    private Date creationDate;
    private Date updateDate;
}
