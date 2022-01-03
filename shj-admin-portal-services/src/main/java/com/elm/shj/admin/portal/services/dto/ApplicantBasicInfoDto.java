/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.validators.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant basic info - Used in data requests either through file upload or integration.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@FieldDependency.List({
        @FieldDependency(first = "idNumber", second = "passportNumber", groups = CheckFirst.class),
        @FieldDependency(first = "dateOfBirthGregorian", second = "dateOfBirthHijri", groups = CheckFirst.class)
})
@NoArgsConstructor
@Getter
@Setter
public class ApplicantBasicInfoDto implements Serializable {

    private static final long serialVersionUID = -5830783311245682596L;

    private long rowNum;

    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true, groups = CheckSecond.class)
    @CellIndex(index = 0)
    private String idNumber;

    @PassportNumber(groups = CheckSecond.class)
    @CellIndex(index = 1)
    private String passportNumber;

    @GregorianDate(minOffset = -120, maxOffset = -10, groups = CheckSecond.class)
    @CellIndex(index = 2)
    private Date dateOfBirthGregorian;

    @HijriDate(minOffset = -140, maxOffset = -11, groups = CheckSecond.class)
    @CellIndex(index = 3)
    private Long dateOfBirthHijri;

    public static ApplicantBasicInfoDto fromRelative(ApplicantRelativeDto applicantRelative) {
        ApplicantBasicInfoDto applicantBasicInfo = new ApplicantBasicInfoDto();
        applicantBasicInfo.setIdNumber(applicantRelative.getRelativeIdNumber());
        applicantBasicInfo.setPassportNumber(applicantRelative.getRelativePassportNumber());
        applicantBasicInfo.setDateOfBirthGregorian(applicantRelative.getRelativeDateOfBirthGregorian());
        applicantBasicInfo.setDateOfBirthHijri(applicantRelative.getRelativeDateOfBirthHijri());
        return applicantBasicInfo;
    }

    public static ApplicantBasicInfoDto fromApplicant(ApplicantDto applicant) {
        ApplicantBasicInfoDto applicantBasicInfo = new ApplicantBasicInfoDto();
        applicantBasicInfo.setIdNumber(applicant.getIdNumber());
        applicantBasicInfo.setPassportNumber(applicant.getPassportNumber());
        applicantBasicInfo.setDateOfBirthGregorian(applicant.getDateOfBirthGregorian());
        applicantBasicInfo.setDateOfBirthHijri(applicant.getDateOfBirthHijri());
        return applicantBasicInfo;
    }

    /**
     * Get applicant basic info from applicant emergency.
     *
     * @param applicant
     * @return
     */
    public static ApplicantBasicInfoDto fromApplicantEmergency(ApplicantEmergencyDto applicant) {
        ApplicantBasicInfoDto applicantBasicInfo = new ApplicantBasicInfoDto();
        applicantBasicInfo.setIdNumber(applicant.getIdNumber());
        applicantBasicInfo.setPassportNumber(applicant.getPassportNumber());
        applicantBasicInfo.setDateOfBirthGregorian(applicant.getDateOfBirthGregorian());
        applicantBasicInfo.setDateOfBirthHijri(applicant.getDateOfBirthHijri());
        return applicantBasicInfo;
    }
}
