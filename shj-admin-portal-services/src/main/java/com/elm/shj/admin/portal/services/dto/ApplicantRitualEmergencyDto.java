/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.validators.BorderNumber;
import com.elm.shj.admin.portal.services.data.validators.OnlyCharacters;
import com.elm.shj.admin.portal.services.data.validators.VisaOrPermitNumber;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant ritual emergency data.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantRitualEmergencyDto implements Serializable {
    private static final long serialVersionUID = 5356872922618503948L;

    private Long id;

    @OnlyCharacters(min = 1, max = 50, allowNumbers = true, allowEmpty = false)
    @CellIndex(index = 26)
    private String groupReferenceNumber;


    @VisaOrPermitNumber(allowEmpty = false)
    @CellIndex(index = 27)
    private String visaNumber;

    @VisaOrPermitNumber(allowEmpty = false)
    @CellIndex(index = 28)
    private String permitNumber;

    @OnlyCharacters(min = 5, max = 50, allowEmpty = false, allowNumbers = true, allowSpecialChars = true)
    @CellIndex(index = 29)
    private String insuranceNumber;

    @BorderNumber
    @CellIndex(index = 30)
    private String borderNumber;

    @OnlyCharacters(allowEmpty = false, min = 3, max = 20, allowNumbers = true, allowSpecialChars = true)
    @CellIndex(index = 31)
    private String busNumber;

    @OnlyCharacters(allowEmpty = false, min = 1, max = 6, allowNumbers = true, allowSpecialChars = true)
    @CellIndex(index = 32)
    private String seatNumber;

    private ApplicantDto applicant;
    private ApplicantPackageDto applicantPackage;
    private Date creationDate;


}
