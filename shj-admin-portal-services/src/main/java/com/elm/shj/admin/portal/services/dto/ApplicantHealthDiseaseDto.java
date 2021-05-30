/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.validation.ArabicCharacters;
import com.elm.dcc.foundation.commons.validation.LatinCharacters;
import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.mapper.NestedCells;
import com.elm.shj.admin.portal.services.data.validators.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant health disease domain.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@NoArgsConstructor
@Data
public class ApplicantHealthDiseaseDto implements Serializable {

    private static final long serialVersionUID = 5702724312215614750L;

    private long id;
    @JsonBackReference
    private ApplicantHealthDto applicantHealth;

    @NullOrNotBlank(min = 3, max = 50)
    @ArabicCharacters(lettersOnly = true, numbersOnly = true, message = "validation.data.constraints.msg.018")
    @CellIndex(index = 4)
    private String labelAr;

    @NullOrNotBlank(min = 3, max = 50)
    @LatinCharacters(lettersOnly = true, numbersOnly = true, message = "validation.data.constraints.msg.019")
    @CellIndex(index = 5)
    private String labelEn;

    private Date creationDate;
    // used in data requests either through file upload or integration
    @Valid
    @NestedCells
    private ApplicantBasicInfoDto applicantBasicInfo;
}
