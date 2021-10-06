/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the company ritual step main data
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class CompanyRitualStepMainDataDto implements Serializable {

    private static final long serialVersionUID = -5579020557603388032L;

    private long id;

    private String referenceNumber;

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
