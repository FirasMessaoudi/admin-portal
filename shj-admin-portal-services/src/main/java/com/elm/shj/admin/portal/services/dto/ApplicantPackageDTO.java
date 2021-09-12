package com.elm.shj.admin.portal.services.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Dto class for the applicant package domain.
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@NoArgsConstructor
@Data
public class ApplicantPackageDTO {

    private static final long serialVersionUID = 9128835722212623516L;

    private long id;

    private long applicantUin;

    private Date creationDate;

    private Date updateDate;

//    @JsonBackReference(value = "ritualPackage")
//    private RitualPackageDto ritualPackage;
//
//    @JsonBackReference(value = "applicantPackageCaterings")
//    private List<ApplicantPackageCateringDto> applicantPackageCaterings;
//
//    @JsonBackReference(value = "applicantPackageTransportations")
//    private List<ApplicantPackageTransportationDto> applicantPackageTransportations;
//
//    @JsonBackReference(value = "applicantPackageHousings")
//    private List<ApplicantPackageHousingDto> applicantPackageHousings;
}
