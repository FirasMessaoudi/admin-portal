/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.validators.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the Staff Applicant Group .
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */

@WithCompanyRitualSeason
@WithGroupLeaderCompanyStaff
@WithApplicant
@SameSeason
@SameRitual
@FieldDependency.List({
        @FieldDependency(first = "staffIdNumber", second = "staffPassportNumber"),
        @FieldDependency(first = "staffDateOfBirthGregorian", second = "staffDateOfBirthHijri"),
        @FieldDependency(first = "idNumber", second = "passportNumber"),
        @FieldDependency(first = "dateOfBirthGregorian", second = "dateOfBirthHijri")
})

@NoArgsConstructor
@Getter
@Setter
public class StaffApplicantGroupDto implements Serializable {


    private static final long serialVersionUID = -55631653517681858L;

    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    @CellIndex(index = 0)
    private String staffIdNumber;


    @PassportNumber
    @CellIndex(index = 1)
    private String staffPassportNumber;

    @GregorianDate(minOffset = -120, maxOffset = -10)
    @CellIndex(index = 2)
    private Date staffDateOfBirthGregorian;

    @HijriDate(minOffset = -140, maxOffset = -11)
    @CellIndex(index = 3)
    private Long staffDateOfBirthHijri;

    @UniquePerRequest
    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    @CellIndex(index = 4)
    private String idNumber;

    @UniquePerRequest
    @PassportNumber
    @CellIndex(index = 5)
    private String passportNumber;

    @GregorianDate(minOffset = -120, maxOffset = -10)
    @CellIndex(index = 6)
    private Date dateOfBirthGregorian;

    @HijriDate(minOffset = -140, maxOffset = -11)
    @CellIndex(index = 7)
    private Long dateOfBirthHijri;

    @CellIndex(index = 8)
    @WithGroupReferenceNumber
    private String groupReferenceNumber;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @CellIndex(index = 9)
    @RitualTypeCode
    private String RitualTypeCode;


    @NotNull(message = "validation.data.constraints.msg.20001")
    @CellIndex(index = 10)
    @CompanyCode
    private String companyCode;

    @SamePerRequest
    @SeasonYear
    @CellIndex(index = 11)
    private int season;


}
