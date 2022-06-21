/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.mapper.NestedCells;
import com.elm.shj.admin.portal.services.data.validators.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the applicant health domain.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@WithApplicant
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ApplicantHealthDto implements Serializable {

    private static final long serialVersionUID = 2731031329221419001L;

    public ApplicantHealthDto() {
    }

    private long id;
    @JsonBackReference(value = "applicant")
    private ApplicantDto applicant;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @BloodType
    @CellIndex(index = 4)
    private String bloodType;

    @NullOrNotBlank(min = 3, max = 50)
    @CellIndex(index = 5)
    private String insurancePolicyNumber;

    @CellIndex(index = 6)
    private Boolean hasSpecialNeeds;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @WithRitualPackage
//    //@OnlyCharacters(min = 1, max = 50, allowNumbers = true, allowEmpty = false, allowSpecialChars = true)
    @CellIndex(index = 8)
    private String packageReferenceNumber;

    @JsonBackReference(value = "applicantRitual")
    private ApplicantRitualDto applicantRitual;

    private Long dataRequestRecordId;

    private List<ApplicantHealthDiseaseDto> diseases;

    @Valid
    @NestedCells
    private List<ApplicantHealthSpecialNeedsDto> specialNeeds;

    private List<ApplicantHealthImmunizationDto> immunizations;

    // used in data requests either through file upload or integration
    @Valid
    @NestedCells
    private ApplicantBasicInfoDto applicantBasicInfo;

    private Date creationDate;
    private Date updateDate;
}
