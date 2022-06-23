/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Value object class for returned applicant Basic Info.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantBasicInfoVo implements Serializable {

    private static final long serialVersionUID = -7269254967366204060L;
    private String uin;
    private String fullNameAr;
    private String fullNameEn;
    private String serialNumber;
    private String preferredLanguage;


    public ApplicantBasicInfoVo(String fullNameAr, String fullNameEn, String uin){
        this.fullNameAr = fullNameAr;
        this.fullNameEn = fullNameEn;
        this.uin = uin;
    }

    public ApplicantBasicInfoVo(String fullNameAr, String fullNameEn, String uin, String preferredLanguage){
        this.fullNameAr = fullNameAr;
        this.fullNameEn = fullNameEn;
        this.uin = uin;
        this.preferredLanguage = preferredLanguage;
    }
}
