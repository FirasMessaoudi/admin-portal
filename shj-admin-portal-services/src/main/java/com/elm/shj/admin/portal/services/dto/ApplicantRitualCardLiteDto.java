/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Dto class for the applicant ritual domain.
 *
 * @author ahmad ali
 * @since 1.1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class ApplicantRitualCardLiteDto implements Serializable {

    private static final long serialVersionUID = -7334824284229748141L;

    private String hamlahPackageCode;
    private String tafweejCode;
    private String zoneCode;
    private String groupCode;
    private String unitCode;
    private String campCode;
    private String seatNumber;
    private String busNumber;
    private int hijriSeason;
    private String fullNameAr;
    private String fullNameEn;
    private String photo;
    private String leaderName;
    private String leaderMobile;
    private String nationalityCode;
}
