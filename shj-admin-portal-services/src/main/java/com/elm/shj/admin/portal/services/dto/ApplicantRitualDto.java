/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.mapper.NestedCells;
import com.elm.shj.admin.portal.services.data.validators.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Dto class for the applicant ritual domain.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@WithApplicant
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApplicantRitualDto implements Serializable {

    private static final long serialVersionUID = 8699536906254699723L;

    public ApplicantRitualDto() {
    }

    private long id;

    private ApplicantDto applicant;

    private ApplicantPackageDto applicantPackage;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @WithRitualPackage
    @OnlyCharacters(min = 1, max = 16, allowNumbers = true, allowEmpty = false)
    @CellIndex(index = 4)
    private String packageReferenceNumber;

    @OnlyCharacters(min = 0, max = 16, allowNumbers = true)
    @CellIndex(index = 5)
    private String groupReferenceNumber;

    @VisaOrPermitNumber(allowEmpty = false)
    @CellIndex(index = 6)
    private String visaNumber;

    @VisaOrPermitNumber(allowEmpty = false)
    @CellIndex(index = 7)
    private String permitNumber;

    @OnlyCharacters(min = 5, max = 50, allowEmpty = false, allowNumbers = true, allowSpecialChars = true)
    @CellIndex(index = 8)
    private String insuranceNumber;

    @BorderNumber
    @CellIndex(index = 9)
    private String borderNumber;

    @OnlyCharacters(min = 3, max = 20, allowNumbers = true, allowSpecialChars = true)
    @CellIndex(index = 10)
    private String busNumber;

    @OnlyCharacters(min = 1, max = 6, allowNumbers = true, allowSpecialChars = true)
    @CellIndex(index = 11)
    private String seatNumber;

    private String roomNumber;

    private String bedNumber;

    private Date creationDate;

    private Date updateDate;

    private Long dataRequestRecordId;

    // used in data requests either through file upload or integration
    @Valid
    @NestedCells
    private ApplicantBasicInfoDto applicantBasicInfo;

    private Set<ApplicantRelativeDto> relatives;

    private Set<ApplicantHealthDto> applicantHealths;

    private String typeCode;
}
