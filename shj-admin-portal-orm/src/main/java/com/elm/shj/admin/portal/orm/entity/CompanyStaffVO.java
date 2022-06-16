/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Value object class for returned staff.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStaffVO implements Serializable {

    private static final long serialVersionUID = 8160457570099912194L;

    private String suin;
    private String fullNameEn;
    private String fullNameAr;
    private String jobTitleCode;
    private String photo;
    private String referenceNumber;
    private String cardStatusCode;
    private String ritualTypeCode;
    private int ritualSeason;
    private String companyLabelEn;
    private String companyLabelAr;
    private String companyCode;
    private String idNumber;
    private String passport;
    private String fullNameOrigin;
    private Date dateOfBirthGregorian;
    private Long dateOfBirthHijri;
    private String gender;
    private String nationalityCode;
    private String cardReferenceNumber;
    private long cardId;
    private long id;
    public CompanyStaffVO(String suin, String fullNameEn, String fullNameAr, String jobTitleCode, String photo, String referenceNumber, String cardStatusCode, String ritualTypeCode, int ritualSeason, String companyLabelEn, String companyLabelAr, String companyCode, String idNumber, String passport, String fullNameOrigin, Date dateOfBirthGregorian, Long dateOfBirthHijri, String gender, String nationalityCode, String cardReferenceNumber, long cardId) {
        this.suin = suin;
        this.fullNameEn = fullNameEn;
        this.fullNameAr = fullNameAr;
        this.jobTitleCode = jobTitleCode;
        this.photo = photo;
        this.referenceNumber = referenceNumber;
        this.cardStatusCode = cardStatusCode;
        this.ritualTypeCode = ritualTypeCode;
        this.ritualSeason = ritualSeason;
        this.companyLabelEn = companyLabelEn;
        this.companyLabelAr = companyLabelAr;
        this.companyCode = companyCode;
        this.idNumber = idNumber;
        this.passport = passport;
        this.fullNameOrigin = fullNameOrigin;
        this.dateOfBirthGregorian = dateOfBirthGregorian;
        this.dateOfBirthHijri = dateOfBirthHijri;
        this.gender = gender;
        this.nationalityCode = nationalityCode;
        this.cardReferenceNumber = cardReferenceNumber;
        this.cardId = cardId;
    }

    public CompanyStaffVO(long id,  String fullNameEn, String fullNameAr) {
        this.id = id;
        this.fullNameEn = fullNameEn;
        this.fullNameAr = fullNameAr;
    }
}
