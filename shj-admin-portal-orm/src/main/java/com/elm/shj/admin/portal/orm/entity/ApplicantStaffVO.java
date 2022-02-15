/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Value object class for returned Applicant or staff.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ApplicantStaffVO implements Serializable {

    private static final long serialVersionUID = -1154179829470241614L;

    private String uin;
    private String fullNameEn;
    private String fullNameAr;
    private String ritualTypeCode;
    private String cardStatusCode;
    private String photo;
    private long applicantPackageId;
    private String groupLeaderSuin;
    private String groupLeaderMobileNumber;
    private String groupLeaderMobileNumberInt;
    private String companyLabelEn;
    private String companyLabelAr;
    private int userType;
    private boolean isSameStaffGroup;

    public ApplicantStaffVO(String uin, String fullNameEn, String fullNameAr, String ritualTypeCode, String cardStatusCode, String photo, long applicantPackageId, String groupLeaderSuin, String groupLeaderMobileNumber, String groupLeaderMobileNumberInt, String companyLabelEn, String companyLabelAr) {
        this.uin = uin;
        this.fullNameEn = fullNameEn;
        this.fullNameAr = fullNameAr;
        this.ritualTypeCode = ritualTypeCode;
        this.cardStatusCode = cardStatusCode;
        this.photo = photo;
        this.applicantPackageId = applicantPackageId;
        this.groupLeaderSuin = groupLeaderSuin;
        this.groupLeaderMobileNumber = groupLeaderMobileNumber;
        this.groupLeaderMobileNumberInt = groupLeaderMobileNumberInt;
        this.companyLabelEn = companyLabelEn;
        this.companyLabelAr = companyLabelAr;
    }

    public ApplicantStaffVO(String uin, String fullNameEn, String fullNameAr, String ritualTypeCode, String cardStatusCode, String photo, String companyLabelEn, String companyLabelAr) {
        this.uin = uin;
        this.fullNameEn = fullNameEn;
        this.fullNameAr = fullNameAr;
        this.ritualTypeCode = ritualTypeCode;
        this.cardStatusCode = cardStatusCode;
        this.photo = photo;
        this.companyLabelEn = companyLabelEn;
        this.companyLabelAr = companyLabelAr;
    }
}
