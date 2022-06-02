package com.elm.shj.admin.portal.services.data.huic;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.validators.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.Email;
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
@FieldDependency.List({
        @FieldDependency(first = "idNumber", second = "passportNumber"),
        @FieldDependency(first = "dateOfBirthGregorian", second = "dateOfBirthHijri")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HuicApplicantMainData implements Serializable {
    private static final long serialVersionUID = 1068726443812588470L;

    @Gender
    private String gender;
    @CountryCode
    private String nationality;

    @UniquePerRequest
    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    @CellIndex(index = 0)
    private String idNumber;

    @IdNumber(minLength = 5, maxLength = 50)
    private String nationalIdOriginalCountry;

    @PassportNumber
    private String passportNo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @GregorianDate(minOffset = -120, maxOffset = -10, allowNull = true)
    private Date dateOfBirth;

    @HijriDate(minOffset = -140, maxOffset = -11)
    private Long dateOfBirthHijri;

    @OnlyCharacters(min = 6, max = 150, arabic = true)
    private String fullNameAr;

    @OnlyCharacters(min = 10, max = 150, allowEmpty = false)
    private String fullNameEn;

    @NullOrNotBlank(min = 10, max = 150)
    private String fullNameOriginalLang;

    @MaritalStatusCode
    private String maritalStatus;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private String photo;

    private String biometricDataFP;

    private String biometricDataFace;

    @Pattern(regexp = "[\\w ]*", message = "validation.data.constraints.msg.20003")
    @NullOrNotBlank(min = 0, max = 100)
    private String qualification;

    @LanguageCodeList
    private String languageList;

    @NullOrNotBlank(min = 5, max = 50)
    @Email(message = "validation.data.constraints.msg.20003", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;

    @NullOrNotBlank(min = 5, max = 16)
    @CellIndex(index = 17)
    private String mobileNumber;

    @NullOrNotBlank(min = 5, max = 30)
    @CellIndex(index = 18)
    private String mobileNumberIntl;

    @CountryCode
    private String country;

    @Pattern(regexp = "[\\w ]*", message = "validation.data.constraints.msg.20003")
    @NullOrNotBlank(min = 3, max = 100)
    @CellIndex(index = 22)
    private String street;

    @Pattern(regexp = "[\\w ]*", message = "validation.data.constraints.msg.20003")
    @NullOrNotBlank(min = 3, max = 50)
    private String district;

    @Pattern(regexp = "[\\w ]*", message = "validation.data.constraints.msg.20003")
    @NullOrNotBlank(min = 3, max = 50)
    private String city;

    @Pattern(regexp = "[\\w ]*", message = "validation.data.constraints.msg.20003")
    @NullOrNotBlank(min = 5, max = 30)
    private String buildingNo;

    @Pattern(regexp = "[\\w ]*", message = "validation.data.constraints.msg.20003")
    @NullOrNotBlank(min = 3, max = 30)
    private String postalCode;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @WithRitualPackage
    private String packageRefNumber;

    @ApplicantStatus
    private Integer status;
    @RitualTypeCode
    private String ritualTypeCode;
    @SeasonYear
    private int seasonYear;

    private String establishmentId;
    private String serviceGroupMakkahId;
    private String serviceGroupMadinaId;
}
