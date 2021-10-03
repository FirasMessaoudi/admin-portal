/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Dto class for the applicant health lite domain.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class ApplicantHealthLiteDto implements Serializable {

    private static final long serialVersionUID = -6152719368063316734L;

    private Long id;
    private String bloodType;
    @JsonBackReference
    private ApplicantLiteDto applicant;
    @JsonBackReference
    private ApplicantRitualLiteDto applicantRitual;
    private List<ApplicantHealthDiseaseLiteDto> diseases;
    private List<ApplicantHealthSpecialNeedsLiteDto> specialNeeds;
    private List<ApplicantHealthImmunizationLiteDto> immunizations;
    private Boolean hasSpecialNeeds;
    private String insurancePolicyNumber;

}
