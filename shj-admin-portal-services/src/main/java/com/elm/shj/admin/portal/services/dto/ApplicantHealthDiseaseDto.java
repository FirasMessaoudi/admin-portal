/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.mapper.NestedCells;
import com.elm.shj.admin.portal.services.data.validators.OnlyCharacters;
import com.elm.shj.admin.portal.services.data.validators.WithApplicant;
import com.elm.shj.admin.portal.services.data.validators.WithRitualPackage;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant health disease domain.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@WithApplicant
@NoArgsConstructor
@Getter
@Setter
public class ApplicantHealthDiseaseDto implements Serializable {

    private static final long serialVersionUID = 5702724312215614750L;

    private long id;
    @JsonBackReference
    private ApplicantHealthDto applicantHealth;

    @OnlyCharacters(min = 3, max = 650, allowNumbers = true)
    @CellIndex(index = 5)
    private String labelAr;

    @OnlyCharacters(min = 3, max = 650, allowEmpty = false, allowNumbers = true)
    @CellIndex(index = 4)
    private String labelEn;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @WithRitualPackage
    @OnlyCharacters(min = 1, max = 50, allowNumbers = true, allowEmpty = false, allowSpecialChars = true)
    @CellIndex(index = 6)
    private String packageReferenceNumber;

    private Date creationDate;
    private Date updateDate;
    private Long dataRequestRecordId;

    // used in data requests either through file upload or integration
    @Valid
    @NestedCells
    private ApplicantBasicInfoDto applicantBasicInfo;
}
