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
@WithPackage
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HuicApplicantRitual implements Serializable {

    private static final long serialVersionUID = 3960122257793536522L;
    @IdNumber(minLength = 0, maxLength = 16, ninOrIqama = true)
    private Long idNumber;
    @PassportNumber
    private String passportNo;
    @NotNull(message = "validation.data.constraints.msg.20001")
    @NationalityCode
    private Long nationality;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private String packageRefNumber;
    @RitualTypeCode
    private Integer ritualTypeCode;
    @SeasonYear
    private int seasonYear;

    private String visaNumber;

    private String permitNumber;

    @NullOrNotBlank(min = 0, max = 20)
    private String insuranceNumber;

    @NullOrNotBlank(min = 0, max = 20)
    private String borderNo;

    @NullOrNotBlank(min = 0, max = 20)
    private String busNumber;

    @NullOrNotBlank(min = 0, max = 20)
    private String seatNumber;

    @NullOrNotBlank(min = 0, max = 20)
    private String roomNumber;

    @NullOrNotBlank(min = 0, max = 20)
    private String bedNumber;

}
