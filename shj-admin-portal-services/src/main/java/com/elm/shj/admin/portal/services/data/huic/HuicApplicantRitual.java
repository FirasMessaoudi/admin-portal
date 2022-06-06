/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.huic;

import com.elm.shj.admin.portal.services.data.validators.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * class for the applicant ritual huic.
 *
 * @author f.messaoudi
 * @since 11.0
 */

@WithApplicant
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HuicApplicantRitual implements Serializable {

    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    private Long idNumber;
    @PassportNumber
    private String passportNo;
    @CountryCode
    private Long nationality;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @WithRitualPackage
    private String packageRefNumber;
    @RitualTypeCode
    private Long ritualTypeCode;
    @SeasonYear
    private int seasonYear;

    @VisaOrPermitNumber(allowEmpty = true)
    private String visaNumber;

    @VisaOrPermitNumber(allowEmpty = true)
    private String permitNumber;

    @OnlyCharacters(min = 5, max = 50, allowEmpty = true, allowNumbers = true, allowSpecialChars = true)
    private String insuranceNumber;

    @BorderNumber
    private String borderNo;

    @OnlyCharacters(min = 3, max = 20, allowNumbers = true, allowSpecialChars = true)
    private String busNumber;

    @OnlyCharacters(min = 1, max = 6, allowNumbers = true, allowSpecialChars = true)
    private String seatNumber;

    @OnlyCharacters(min = 1, max = 20, allowNumbers = true, allowSpecialChars = true)
    private String roomNumber;

    @OnlyCharacters(min = 1, max = 6, allowNumbers = true, allowSpecialChars = true)
    private String bedNumber;

}
