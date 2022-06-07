/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.huic;

import com.elm.shj.admin.portal.services.data.validators.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * class for the applicant relative huic.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@WithRelative
@WithApplicant
@NoArgsConstructor
@Getter
@Setter
public class HuicApplicantRelative implements Serializable {


    private static final long serialVersionUID = -8952254188968130392L;

    @RelationshipCode
    private Long relationship;
    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    private Long idNumber;
    @PassportNumber
    private String passportNo;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @NationalityCode
    private Long nationality;
    @NotNull(message = "validation.data.constraints.msg.20001")
    @WithRitualPackage
    @OnlyCharacters(min = 1, max = 16, allowNumbers = true, allowEmpty = false)
    private String packageRefNumber;
    @RitualTypeCode
    private Long ritualTypeCode;
    @SeasonYear
    private int seasonYear;
    ;
    // transient specific mapping for relative applicant as we cannot decorate twice
    // used in data requests either through file upload or integration
    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    private Long relativeIdNumber;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @NationalityCode
    private Long relativeNationality;
    @PassportNumber
    private String relativePassportNo;


}
