/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.validators.NullOrNotBlank;
import com.elm.shj.admin.portal.services.data.validators.SpecialNeedsCodeList;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant health special needs domain.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantHealthSpecialNeedsDto implements Serializable {

    private static final long serialVersionUID = -2130894375076782343L;

    private long id;
    @JsonBackReference
    private ApplicantHealthDto applicantHealth;

    @NullOrNotBlank(min = 3, max = 128)
    @SpecialNeedsCodeList
    @CellIndex(index = 7)
    private String specialNeedTypeCode;

    private Date creationDate;
}
