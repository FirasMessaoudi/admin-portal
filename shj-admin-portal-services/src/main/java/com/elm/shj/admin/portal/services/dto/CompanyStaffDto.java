/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.validators.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the companyStaff.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@NoArgsConstructor
@Getter
@Setter
@UniqueCompanyStaff
@FieldDependency.List({
        @FieldDependency(first = "idNumber", second = "passportNumber"),
        @FieldDependency(first = "dateOfBirthGregorian", second = "dateOfBirthHijri")
})
public class CompanyStaffDto {
    private static final long serialVersionUID = -4462482228404995089L;
    private long id;

    @CellIndex(index = 5)
    @OnlyCharacters(min = 6, max = 150, arabic = true)
    private String fullNameAr;

    @OnlyCharacters(min = 10, max = 150, allowEmpty = false)
    @CellIndex(index = 4)
    private String fullNameEn;

    @IdNumber(minLength = 5, maxLength = 30)
    @CellIndex(index = 9)
    private String idNumberOriginal;

    @UniquePerRequest
    @PassportNumber
    @CellIndex(index = 1)
    private String passportNumber;

    @GregorianDate(minOffset = -120, maxOffset = -10 ,allowNull = false)
    @CellIndex(index = 2)
    private Date dateOfBirthGregorian;

    @HijriDate(minOffset = -140, maxOffset = -11)
    @CellIndex(index = 3)
    private Long dateOfBirthHijri;

    @Gender
    @CellIndex(index = 7)
    private String gender;

    @CountryCode
    @CellIndex(index = 8)
    private String nationalityCode;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @CellIndex(index = 10)
    private String photo;

    @UniquePerRequest
    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    @CellIndex(index = 0)
    private String idNumber;

    @JsonBackReference(value = "applicantGroups")
    private List<ApplicantGroupDto> applicantGroups;

    @JobTitleCode
    @CellIndex(index = 11)
    private String titleCode;

    @SamePerRequest
    @SeasonYear
    @CellIndex(index = 15)
    private int season;

    @NullOrNotBlank(min = 10, max = 150)
    @CellIndex(index = 6)
    private String fullNameOrigin;


    @NullOrNotBlank(min = 5, max = 16)
    @CellIndex(index =13)
    private String mobileNumber;

    @NullOrNotBlank(min = 5, max = 30)
    @CellIndex(index =14)
    private String mobileNumberIntl;

    @NullOrNotBlank(min = 5, max = 50)
    @Email(message = "validation.data.constraints.msg.20003")
    @CellIndex(index = 12)
    private String email;

    private DataRequestRecordDto dataRequestRecord;
    private List<CompanyStaffDigitalIdDto> digitalIds;

    @JsonBackReference(value = "companyRitualSeason")
    private CompanyRitualSeasonDto companyRitualSeason;
    private Date creationDate;
    private Date updateDate;
}
