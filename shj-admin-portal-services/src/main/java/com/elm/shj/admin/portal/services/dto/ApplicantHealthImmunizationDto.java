/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.mapper.NestedCells;
import com.elm.shj.admin.portal.services.data.validators.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant health immunization domain.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@WithApplicant
@NoArgsConstructor
@Getter
@Setter
public class ApplicantHealthImmunizationDto implements Serializable {

    private static final long serialVersionUID = -1939250710514581003L;

    private long id;
    @JsonBackReference
    private ApplicantHealthDto applicantHealth;

    @ImmunizationCode
    @CellIndex(index = 4)
    private String immunizationCode;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @GregorianDate(minOffset = -3, maxOffset = 0)
    @CellIndex(index = 5)
    private Date immunizationDate;

    @CellIndex(index = 6)
    @Mandatory
    private boolean mandatory;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @WithRitualPackage
    @OnlyCharacters(min = 1, max = 16, allowNumbers = true, allowEmpty = false)
    @CellIndex(index = 7)
    private String packageReferenceNumber;

    private Date creationDate;
    private Date updateDate;
    private Long dataRequestRecordId;

    // used in data requests either through file upload or integration
    @Valid
    @NestedCells
    private ApplicantBasicInfoDto applicantBasicInfo;
}
