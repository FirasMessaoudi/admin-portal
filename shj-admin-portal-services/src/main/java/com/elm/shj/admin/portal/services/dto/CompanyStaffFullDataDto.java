/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.validators.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the companyStaffRitual.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@NoArgsConstructor
@Getter
@Setter
@UniqueCompanyStaffFullData
@FieldDependency.List({
        @FieldDependency(first = "idNumber", second = "passportNumber"),
        @FieldDependency(first = "dateOfBirthGregorian", second = "dateOfBirthHijri")
})
public class CompanyStaffFullDataDto implements Serializable {
    private long id;

    @UniquePerRequest
    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    @CellIndex(index = 0)
    private String idNumber;

    @UniquePerRequest
    @PassportNumber
    @CellIndex(index = 1)
    private String passportNumber;

    @CellIndex(index = 2)
    @GregorianDate(minOffset = -120, maxOffset = -10, allowNull = true)
    private Date dateOfBirthGregorian;

    @CellIndex(index = 3)
    @HijriDate(minOffset = -140, maxOffset = -11, allowEmpty = true)
    private Long dateOfBirthHijri;

    @OnlyCharacters(min = 0, max = 150, allowEmpty = true)
    @CellIndex(index = 4)
    private String fullNameEn;

    @CellIndex(index = 5)
    @OnlyCharacters(min = 6, max = 150, arabic = true)
    private String fullNameAr;

    @NullOrNotBlank(min = 0, max = 150)
    @CellIndex(index = 6)
    private String fullNameOrigin;

    @NullOrNotBlank(min = 1, max = 15)
    @CellIndex(index = 7)
    @JsonIgnore
    private String genderType;

    @Gender
    @CellIndex(index = 8)
    private String gender;

    @NullOrNotBlank(min = 1, max = 30)
    @CellIndex(index = 9)
    @JsonIgnore
    private String nationality;

    @NationalityCode
    @CellIndex(index = 10)
    private String nationalityCode;

    @IdNumber(minLength = 5, maxLength = 30)
    @CellIndex(index = 11)
    private String idNumberOriginal;

    @NullOrNotBlank(min = 1, max = 30)
    @CellIndex(index = 12)
    @JsonIgnore
    private String jobTitle;

    @NullOrNotBlank(min = 1, max = 30)
    @JobTitleCode
    @CellIndex(index = 13)
    private String titleCode;

    @NullOrNotBlank(min = 1, max = 30)
    @OnlyCharacters(min = 0, max = 150, arabic = true, allowEmpty=false)
    @CellIndex(index = 14)
    private String customJobTitle;

    @NullOrNotBlank(min = 5, max = 50)
    @Email(message = "validation.data.constraints.msg.20003")
    @CellIndex(index = 15)
    private String email;

    @NullOrNotBlank(min = 5, max = 16)
    @CellIndex(index =16)
    private String mobileNumber;

    @NullOrNotBlank(min = 5, max = 30)
    @CellIndex(index =17)
    private String mobileNumberIntl;

    @CellIndex(index = 18)
    @NullOrNotBlank(min = 1, max = 30)
    @JsonIgnore
    private String ritualType;

    @CellIndex(index = 19)
    @RitualTypeCode
    private String typeCode;

    private String photo;

    private Long dataRequestRecordId;

}
