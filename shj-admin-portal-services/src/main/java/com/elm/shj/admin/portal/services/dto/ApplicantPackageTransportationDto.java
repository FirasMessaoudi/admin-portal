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
 * Dto class for applicant package transportation
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ApplicantPackageTransportationDto implements Serializable {
    private static final long serialVersionUID = -8137564418002673291L;

    public ApplicantPackageTransportationDto() {
    }
    private long id;
    private String seatNumber;
    private String wagonNumber;
    private String vehicleNumber;
    private String vehicleInfo;
    private Date creationDate;
    private Date updateDate;
    private ApplicantPackageDto applicantPackage;
    private PackageTransportationDto packageTransportation;
}
