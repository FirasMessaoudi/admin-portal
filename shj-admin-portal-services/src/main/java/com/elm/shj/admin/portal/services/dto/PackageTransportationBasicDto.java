/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the Package Transportation Basic.
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageTransportationBasicDto implements Serializable {

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
    private RitualPackageBasicDto ritualPackage;
    private Date creationDate;
    private Date updateDate;
}
