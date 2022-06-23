/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.validators.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the companyStaff.
 *
 * @author rimtiaz
 * @since 1.2.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDependency.List({
        @FieldDependency(first = "idNumber", second = "passportNumber"),
        @FieldDependency(first = "dateOfBirthGregorian", second = "dateOfBirthHijri"),
        @FieldDependency(first = "fullNameEn", second = "fullNameAr")
})
public class CompanyStaffMainFullDataDto {


    private Long id;
    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    private String idNumber;

    @PassportNumber
    private String passportNumber;

    @GregorianDate(minOffset = -120, maxOffset = -10, allowNull = true)
    private Date dateOfBirthGregorian;

    @HijriDate(minOffset = -140, maxOffset = -11, allowEmpty = true)
    private Long dateOfBirthHijri;

    private String fullNameEn;

    private String fullNameAr;

    @NullOrNotBlank(min = 0, max = 650)
    private String fullNameOrigin;

    @Gender
    private String gender;

    @NationalityCode
    private String nationalityCode;

    @IdNumber(minLength = 5, maxLength = 30)
    private String idNumberOriginal;

    @NullOrNotBlank(min = 1, max = 30)
    @JobTitleCode
    private String jobTitle;

    private String customJobTitle;

    @NullOrNotBlank(min = 5, max = 50)
    @Email(message = "validation.data.constraints.msg.20003")
    private String email;

    @NullOrNotBlank(min = 5, max = 16)
    private String mobileNumber;

    @NullOrNotBlank(min = 5, max = 30)
    private String mobileNumberIntl;

    @RitualTypeCode
    private String ritualTypeCode;

    private String avatar;

    private String companyCode;
    private String countryPhonePrefix;

}
