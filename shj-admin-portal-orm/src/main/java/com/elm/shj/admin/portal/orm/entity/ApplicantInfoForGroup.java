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
 * The persistent class for get shc_applicant basic info for group applicant list.
 *
 * @author salzoubi
 * @since 1.1.0
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantInfoForGroup implements Serializable {


    private long id;

    private String idNumber;

    private String passportNumber;

    private String digitalIds;

    private String fullNameAr;

    private String fullNameEn;
}
