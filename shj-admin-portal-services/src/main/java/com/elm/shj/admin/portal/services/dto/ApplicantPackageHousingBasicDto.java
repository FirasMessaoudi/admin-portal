/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for applicant package housing
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ApplicantPackageHousingBasicDto implements Serializable {
    private static final long serialVersionUID = 4195242056822545138L;

    public ApplicantPackageHousingBasicDto() {
    }

    private long id;
    private String roomNumber;
    private String bedNumber;
    private String campInfo;
    private String siteCampRefCode;
    private String siteTent;
    private String siteFloor;
    private String siteCorridor;
    private String siteRoom;
    private String siteBedNumber;
    private Date creationDate;
    private Date updateDate;
    private Long applicantPackageId;
    private long packageHousingId;
}
