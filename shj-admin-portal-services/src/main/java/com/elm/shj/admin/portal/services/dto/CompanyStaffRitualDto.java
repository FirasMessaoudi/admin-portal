/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.validators.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the companyStaffRitual.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@WithCompanyStaff
@WithCompanyRitualSeason
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDependency.List({
        @FieldDependency(first = "idNumber", second = "passportNumber"),
        @FieldDependency(first = "dateOfBirthGregorian", second = "dateOfBirthHijri")
})
public class CompanyStaffRitualDto implements Serializable {
    private long id;

    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    @UniquePerRequest
    @CellIndex(index = 0)
    private String idNumber;

    @CellIndex(index = 1)
    @UniquePerRequest
    @PassportNumber
    private String passportNumber;

    @CellIndex(index = 2)
    @GregorianDate(minOffset = -120, maxOffset = -10)
    private Date dateOfBirthGregorian;

    @CellIndex(index = 3)
    @HijriDate(minOffset = -140, maxOffset = -11)
    private Long dateOfBirthHijri;

    @CellIndex(index = 4)
    @RitualTypeCode
    private String typeCode;

    @CellIndex(index = 5)
    @SamePerRequest
    @SeasonYear
    private int season;

    @CellIndex(index = 6)
    @CompanyCode
    private String companyCode;

}
