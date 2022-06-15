package com.elm.shj.admin.portal.services.data.huic;

import com.elm.shj.admin.portal.services.data.validators.*;
import com.elm.shj.admin.portal.services.dto.ApplicantBasicInfoDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * class for received applicant main data from huic.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@UniqueApplicant
@WithPackage
@FieldDependency.List({
        @FieldDependency(first = "idNumber", second = "passportNo"),
        @FieldDependency(first = "dateOfBirth", second = "dateOfBirthHijri"),

})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HuicApplicantMainData implements Serializable {
    private static final long serialVersionUID = 1068726443812588470L;

    @Gender
    private Integer gender;
    @NotNull(message = "validation.data.constraints.msg.20001")
    @NationalityCode
    private Long nationality;

    @IdNumber(minLength = 0, maxLength = 16, ninOrIqama = true)
    private Long idNumber;

    @NullOrNotBlank(min = 0, max = 150)
    @Pattern(regexp = "(^[a-zA-Z0-9]*)"
            , message = "validation.data.constraints.msg.20003")
    private String nationalIdOriginalCountry;

    @PassportNumber
    private String passportNo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @HijriDate(minOffset = -140, maxOffset = 0)
    private Long dateOfBirthHijri;

    @NullOrNotBlank(min = 0, max = 200)
    private String fullNameAr;

    @NullOrNotBlank(min = 0, max = 200)
    private String fullNameEn;

    @NullOrNotBlank(min = 0, max = 200)
    private String fullNameOriginalLang;

    @MaritalStatusCode
    private Integer maritalStatus;

    private String photo;

    private String biometricDataFP;

    private String biometricDataFace;

    private String qualification;

    @LanguageCodeList
    private String languageList;

    @NullOrNotBlank(min = 0, max = 50)
    private String email;

    @NullOrNotBlank(min = 0, max = 50)
    private String mobileNumber;

    @NullOrNotBlank(min = 0, max = 50)
    private String mobileNumberIntl;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @CountryCode
    private Integer country;

    @NullOrNotBlank(min = 0, max = 100)
    private String street;

    @NullOrNotBlank(min = 0, max = 100)
    private String district;

    @NullOrNotBlank(min = 0, max = 100)
    private String city;

    @NullOrNotBlank(min = 0, max = 100)
    private String buildingNo;

    @NullOrNotBlank(min = 0, max = 30)
    private String postalCode;

    private String packageRefNumber;

    @ApplicantStatus
    private Integer status;
    @RitualTypeCode
    private Integer ritualTypeCode;
    @SeasonYear
    private int seasonYear;

    @EstablishmentCode
    private Integer establishmentId; //establishmentRefCode
    @WithServiceGroup
    private Long serviceGroupMakkahId; //serviceGroupRefCode
    @WithServiceGroup
    private Long serviceGroupMadinaId; //serviceGroupRefCode

    private Long missionRefCode;
    private Long companyCode;



    public static ApplicantBasicInfoDto fromHuicApplicant(HuicApplicantMainData applicant) {
        ApplicantBasicInfoDto applicantBasicInfo = new ApplicantBasicInfoDto();
        applicantBasicInfo.setIdNumber(applicant.getIdNumber().toString());
        applicantBasicInfo.setPassportNumber(applicant.getPassportNo());
        applicantBasicInfo.setDateOfBirthGregorian(applicant.getDateOfBirth());
        applicantBasicInfo.setDateOfBirthHijri(applicant.getDateOfBirthHijri());
        return applicantBasicInfo;
    }
}
