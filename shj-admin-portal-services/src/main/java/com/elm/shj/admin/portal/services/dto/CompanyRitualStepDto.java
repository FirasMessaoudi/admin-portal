/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantGroup;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the company ritual step domain.
 *
 * @author jaafer jarray
 * @since 1.1.0
 */
@NoArgsConstructor
@Data
public class CompanyRitualStepDto implements Serializable {

    private static final long serialVersionUID = -5579020557603388032L;

    private long id;

    private ApplicantGroupDto applicantGroup;

    private String transportationTypeCode;

    private long stepIndex;

    private String stepCode;

    private Date time;

    private double locationLat;

    private double locationLng;

    private String locationNameAr;

    private String locationNameEn;

    private Date creationDate;

    private Date updateDate;
}
