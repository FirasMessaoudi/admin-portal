/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the applicant health domain.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@NoArgsConstructor
@Data
public class ApplicantHealthDto implements Serializable {

    private static final long serialVersionUID = 2731031329221419001L;

    private long id;
    private String bloodType;
    private Date creationDate;
    private List<ApplicantHealthDiseaseDto> diseases;
    private List<ApplicantHealthSpecialNeedsDto> specialNeeds;
    private List<ApplicantHealthImmunizationDto> immunizations;
}
