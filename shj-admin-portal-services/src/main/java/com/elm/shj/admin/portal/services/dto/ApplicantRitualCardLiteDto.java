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
    private long cardId;
    private String ritualType;
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
    private String leaderNameAr;
    private String leaderNameEn;
    private String leaderMobile;
    private String nationalityCode;
    private String companyName;
    private String idNumber;
    private String cardNumber;
    private String companyNameAr;
    private String establishmentNameAr;
    private String establishmentNameEn;
    private Integer establishmentId;
    private String passportNumber;
    private String establishmentContactNumber;
    private String serviceNameAr;
    private String serviceNameEn;
}
