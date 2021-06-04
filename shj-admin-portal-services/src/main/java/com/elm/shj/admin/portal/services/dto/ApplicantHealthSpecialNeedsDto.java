/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.mapper.NestedCells;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant health special needs domain.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@NoArgsConstructor
@Data
public class ApplicantHealthSpecialNeedsDto implements Serializable {

    private static final long serialVersionUID = -2130894375076782343L;

    private long id;
    @JsonBackReference
    private ApplicantHealthDto applicantHealth;

    @CellIndex(index = 7)
    private String specialNeedTypeCode;

    private Date creationDate;
    // used in data requests either through file upload or integration
    @Valid
    @NestedCells
    private ApplicantBasicInfoDto applicantBasicInfo;
}
