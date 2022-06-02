/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the Package Transportation .
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageTransportationDto implements Serializable {


    private static final long serialVersionUID = 2025548600075831276L;

    private long id;
    private String typeCode;
    private String locationFromNameAr;
    private String locationFromNameEn;
    private String locationToNameAr;
    private String locationToNameEn;
    private String ritualStepCode;
    private Date validityStart;
    private Date validityEnd;
    private String routeDetails;
    @JsonBackReference("applicantPackageTransportations")
    private List<ApplicantPackageTransportationDto> applicantPackageTransportations;
    private RitualPackageDto ritualPackage;
    private Date creationDate;
    private Date updateDate;
}
