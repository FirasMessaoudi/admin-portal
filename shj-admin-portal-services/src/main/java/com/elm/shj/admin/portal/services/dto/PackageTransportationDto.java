/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.validation.ArabicCharacters;
import com.elm.shj.admin.portal.services.data.validators.OnlyCharacters;
import com.elm.shj.admin.portal.services.data.validators.WithTransportationType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.validation.constraints.Pattern;
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
    @WithTransportationType
    private String typeCode;
    @Pattern(regexp = "(^[a-zA-Z0-9]*)"
            , message = "validation.data.constraints.msg.20003")
    private String locationFromNameAr;
    @Pattern(regexp = "(^[a-zA-Z0-9]*)"
            , message = "validation.data.constraints.msg.20003")
    private String locationFromNameEn;
    @ArabicCharacters(lettersOnly = true, numbersOnly = false)
    private String locationToNameAr;
    @Pattern(regexp = "(^[a-zA-Z0-9]*)"
            , message = "validation.data.constraints.msg.20003")
    private String locationToNameEn;
    private String ritualStepCode;
    private Date validityStart;
    private Date validityEnd;
    @OnlyCharacters(min = 0, max = 256)
    private String routeDetails;
    @JsonBackReference("applicantPackageTransportations")
    private List<ApplicantPackageTransportationDto> applicantPackageTransportations;
    private RitualPackageDto ritualPackage;
    private Date creationDate;
    private Date updateDate;
}
