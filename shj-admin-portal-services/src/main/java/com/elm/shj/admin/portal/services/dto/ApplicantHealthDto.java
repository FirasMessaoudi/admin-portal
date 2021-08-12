/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.mapper.NestedCells;
import com.elm.shj.admin.portal.services.data.validators.BloodType;
import com.elm.shj.admin.portal.services.data.validators.NullOrNotBlank;
import com.elm.shj.admin.portal.services.data.validators.WithApplicant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
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
@NoArgsConstructor
@Data
public class ApplicantHealthDto implements Serializable {

    private static final long serialVersionUID = 2731031329221419001L;

    private long id;
    @JsonBackReference
    private ApplicantDto applicant;

    @BloodType
    @CellIndex(index = 4)
    private String bloodType;

    @NullOrNotBlank(min = 3, max = 50)
    @CellIndex(index = 5)
    private String insurancePolicyNumber;

    @CellIndex(index = 6)
    private Boolean hasSpecialNeeds;
    private Date creationDate;
    private Date updateDate;
    @JsonBackReference
    private ApplicantRitualDto applicantRitual;
    private DataRequestRecordDto dataRequestRecord;
    private List<ApplicantHealthDiseaseDto> diseases;
    @Valid
    @NestedCells
    private List<ApplicantHealthSpecialNeedsDto> specialNeeds;
    private List<ApplicantHealthImmunizationDto> immunizations;
    // used in data requests either through file upload or integration
    @Valid
    @NestedCells
    private ApplicantBasicInfoDto applicantBasicInfo;
}
