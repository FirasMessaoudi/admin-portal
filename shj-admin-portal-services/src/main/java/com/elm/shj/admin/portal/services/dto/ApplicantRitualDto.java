/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.mapper.NestedCells;
import com.elm.shj.admin.portal.services.data.validators.*;
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

    private int hijriSeason;

    @GregorianDate(minOffset = 0, maxOffset = 1)
    @CellIndex(index = 6)
    private Date dateStartGregorian;

    @GregorianDate(minOffset = 0, maxOffset = 1)
    @CellIndex(index = 7)
    private Date dateEndGregorian;

    @HijriDate(minOffset = 0, maxOffset = 1)
    @CellIndex(index = 8)
    private Long dateStartHijri;

    @HijriDate(minOffset = 0, maxOffset = 1)
    @CellIndex(index = 9)
    private Long dateEndHijri;

    @RitualTypeCode
    @CellIndex(index = 10)
    private String typeCode;

    @VisaOrPermitNumber(allowEmpty = false)
    @CellIndex(index = 11)
    private String visaNumber;

    @VisaOrPermitNumber(allowEmpty = false)
    @CellIndex(index = 12)
    private String permitNumber;
    @CellIndex(index = 13)
    private String insuranceNumber;

    @BorderNumber
    @CellIndex(index = 14)
    private String borderNumber;

    @CellIndex(index = 15)
    private String zoneCode;
    @CellIndex(index = 16)
    private String groupCode;
    @CellIndex(index = 17)
    private String unitCode;
    @CellIndex(index = 18)
    private String busNumber;
    @CellIndex(index = 19)
    private String seatNumber;
    private Date creationDate;
    private Date updateDate;
    private DataRequestRecordDto dataRequestRecord;

    // used in data requests either through file upload or integration
    @Valid
    @NestedCells
    private ApplicantBasicInfoDto applicantBasicInfo;
}
