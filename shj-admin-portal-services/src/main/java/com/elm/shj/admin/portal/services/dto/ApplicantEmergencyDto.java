/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.mapper.NestedCells;
import com.elm.shj.admin.portal.services.data.validators.*;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the applicant emergency data.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@UniqueApplicantEmergency
@FieldDependency.List({
        @FieldDependency(first = "idNumber", second = "passportNumber"),
        @FieldDependency(first = "dateOfBirthGregorian", second = "dateOfBirthHijri")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantEmergencyDto implements Serializable {

    private static final long serialVersionUID = -2027005588974249665L;

    @NestedCells
    @Valid
    private ApplicantRitualEmergencyDto applicantRitualEmergencyDto;

    @Gender
    @CellIndex(index = 7)
    private String gender;

    @NationalityCode
    @CellIndex(index = 8)
    private String nationalityCode;

    @UniquePerRequest
    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    @CellIndex(index = 0)
    private String idNumber;

    @IdNumber(minLength = 5, maxLength = 30)
    @CellIndex(index = 9)
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
    private Long dateOfBirthHijri;

    @OnlyCharacters(min = 6, max = 150, arabic = true)
    @CellIndex(index = 5)
    private String fullNameAr;

    @OnlyCharacters(min = 10, max = 150, allowEmpty = false)
    @CellIndex(index = 4)
    private String fullNameEn;

    @NullOrNotBlank(min = 10, max = 150)
    @CellIndex(index = 6)
    private String fullNameOrigin;

    @MaritalStatusCode
    @CellIndex(index = 13)
    private String maritalStatusCode;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @CellIndex(index = 10)
    private String photo;

    @CellIndex(index = 11)
    private String biometricDataFinger;

    @CellIndex(index = 12)
    private String biometricDataFace;

    @Pattern(regexp = "[\\w ]*", message = "validation.data.constraints.msg.20003")
    @CellIndex(index = 14)
    private String educationLevelCode;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @WithRitualPackage
    @CellIndex(index = 25)
    private String packageReferenceNumber;
    @NestedCells
    @Valid
    private List<ApplicantContactDto> contacts;


}
