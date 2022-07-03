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

@WithPackage
@WithApplicant
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HuicApplicantRitual implements Serializable {

    private static final long serialVersionUID = 3960122257793536522L;
    private Long idNumber;
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

    private String insuranceNumber;

    private String borderNo;

    private String busNumber;

    private String seatNumber;

    private String roomNumber;

    private String bedNumber;

}
