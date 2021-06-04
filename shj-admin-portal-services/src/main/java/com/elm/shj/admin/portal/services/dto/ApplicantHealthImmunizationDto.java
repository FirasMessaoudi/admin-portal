/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.mapper.NestedCells;
import com.elm.shj.admin.portal.services.data.validators.GregorianDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant health immunization domain.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@NoArgsConstructor
@Data
public class ApplicantHealthImmunizationDto implements Serializable {

    private static final long serialVersionUID = -1939250710514581003L;

    private long id;
    @JsonBackReference
    private ApplicantHealthDto applicantHealth;

    @CellIndex(index = 4)
    private String immunizationCode;

    @GregorianDate(minOffset = -3, maxOffset = 0)
    @CellIndex(index = 6)
    private Date immunizationDate;

    @CellIndex(index = 5)
    private boolean mandatory;

    private Date creationDate;
    private Date updateDate;
    private DataRequestRecordDto dataRequestRecord;

    // used in data requests either through file upload or integration
    @Valid
    @NestedCells
    private ApplicantBasicInfoDto applicantBasicInfo;
}
