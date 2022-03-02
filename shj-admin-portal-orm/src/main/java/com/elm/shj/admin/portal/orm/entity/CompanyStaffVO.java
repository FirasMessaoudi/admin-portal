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
}
