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
 * Dto class for the companyStaffRitual.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@WithCompanyStaff
@NoArgsConstructor
@Getter
@Setter
@FieldDependency.List({
        @FieldDependency(first = "idNumber", second = "passportNumber"),
        @FieldDependency(first = "dateOfBirthGregorian", second = "dateOfBirthHijri")
})
public class CompanyStaffRitualDto implements Serializable {
    private long id;

    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    @NotNull(message = "validation.data.constraints.msg.20001")
    @CellIndex(index = 0)
    private String idNumber;

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

    @CellIndex(index = 4)
    @RitualTypeCode
    String typeCode;

    @CellIndex(index = 5)
    private Long season;

    @CellIndex(index = 6)
    @CompanyCode
    private String companyCode;

}
