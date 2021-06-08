/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.mapper.NestedCells;
import com.elm.shj.admin.portal.services.data.validators.WithApplicant;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant ritual domain.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@WithApplicant
@NoArgsConstructor
@Data
public class ApplicantRitualDto implements Serializable {

    private static final long serialVersionUID = 8699536906254699723L;

    private long id;
    private ApplicantDto applicant;
    @CellIndex(index = 4)
    private String hamlahPackageCode;
    @CellIndex(index = 5)
    private String tafweejCode;
    @CellIndex(index = 6)
    private int hijriSeason;
    @CellIndex(index = 7)
    private Date dateStartGregorian;
    @CellIndex(index = 8)
    private Date dateEndGregorian;
    @CellIndex(index = 8)
    private int dateStartHijri;
    @CellIndex(index = 10)
    private int dateEndHijri;
    @CellIndex(index = 11)
    private String typeCode;
    @CellIndex(index = 12)
    private String visaNumber;
    @CellIndex(index = 13)
    private String permitNumber;
    @CellIndex(index = 14)
    private String insuranceNumber;
    @CellIndex(index = 15)
    private String borderNumber;
    @CellIndex(index = 16)
    private String zoneCode;
    @CellIndex(index = 17)
    private String groupCode;
    @CellIndex(index = 18)
    private String unitCode;
    private Date creationDate;
    private Date updateDate;
    private DataRequestRecordDto dataRequestRecord;

    // used in data requests either through file upload or integration
    @Valid
    @NestedCells
    private ApplicantBasicInfoDto applicantBasicInfo;
}
