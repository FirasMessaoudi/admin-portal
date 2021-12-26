/*
 *  Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantPackageVo {
    private Long id;
    private long applicantUin;
    private Date startDate;
    private Date endDate;
    private String typeCode;
    private int seasonYear;
    private long companyRitualSeasonId;
}
