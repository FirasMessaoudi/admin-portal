/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.mapper.NestedCells;
import com.elm.shj.admin.portal.services.data.validators.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the applicant domain.
 *
 * @author ahmad flaifel
 * @since 1.0.0
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
public class ApplicantDto implements Serializable {

    private static final long serialVersionUID = 4276580006724069703L;

    private long id;

    @Gender
    @CellIndex(index = 7)
    private String gender;

    @CountryCode
    @CellIndex(index = 8)
    private String nationalityCode;

    @UniquePerRequest
    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    @CellIndex(index = 0)
    private String idNumber;

    @IdNumber(minLength = 5, maxLength = 50)
    @CellIndex(index = 9)
    private String idNumberOriginal;

    @UniquePerRequest
    @PassportNumber
    @CellIndex(index = 1)
    private String passportNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @GregorianDate(minOffset = -120, maxOffset = -10, allowNull = true)
    @CellIndex(index = 2)
    private Date dateOfBirthGregorian;

    @HijriDate(minOffset = -140, maxOffset = -11)
    @CellIndex(index = 3)
    private Long dateOfBirthHijri;

    @OnlyCharacters(min = 6, max = 150, arabic = true)
    @CellIndex(index = 5)
    private String fullNameAr;

    @OnlyCharacters(min = 10, max = 150, allowEmpty = false)
    @CellIndex(index = 4)
    private String fullNameEn;

    @NullOrNotBlank(min = 10, max = 150)
    @CellIndex(index = 6)
    private String fullNameOrigin;

    @MaritalStatusCode
    @CellIndex(index = 13)
    private String maritalStatusCode;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @CellIndex(index = 10)
    private String photo;

    @CellIndex(index = 11)
    private String biometricDataFinger;

    @CellIndex(index = 12)
    private String biometricDataFace;

    @Pattern(regexp = "[\\w ]*", message = "validation.data.constraints.msg.20003")
    @NullOrNotBlank(min = 0, max = 100)
    @CellIndex(index = 14)
    private String educationLevelCode;

    private String preferredLanguage;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @WithRitualPackage
    @CellIndex(index = 25)
    private String packageReferenceNumber;

    private Long dataRequestRecordId;
    private List<ApplicantDigitalIdDto> digitalIds;
    private List<ApplicantRelativeDto> relatives;
    @JsonBackReference
    private List<ApplicantRitualDto> rituals;
    @NestedCells
    @Valid
    private List<ApplicantContactDto> contacts;
    private ApplicantHealthDto applicantHealth;
    private boolean registered;
    private Date creationDate;
    private Date updateDate;
    private boolean deleted;

    /**
     * Get applicant object from applicant lite object.
     *
     * @param applicantLiteDto
     * @return
     */
    public static ApplicantDto fromApplicantLite(ApplicantLiteDto applicantLiteDto) {
        return ApplicantDto.builder().id(applicantLiteDto.getId()).nationalityCode(applicantLiteDto.getNationalityCode())
                .idNumber(applicantLiteDto.getIdNumber()).passportNumber(applicantLiteDto.getPassportNumber())
                .packageReferenceNumber(applicantLiteDto.getPackageReferenceNumber()).dateOfBirthGregorian(applicantLiteDto.getDateOfBirthGregorian())
                .dateOfBirthHijri(applicantLiteDto.getDateOfBirthHijri()).fullNameAr(applicantLiteDto.getFullNameAr()).fullNameEn(applicantLiteDto.getFullNameEn())
                .fullNameOrigin(applicantLiteDto.getFullNameOrigin()).preferredLanguage(applicantLiteDto.getPreferredLanguage()).gender(applicantLiteDto.getGender())
                .photo(applicantLiteDto.getPhoto()).contacts(applicantLiteDto.getContacts()).digitalIds(applicantLiteDto.getDigitalIds())
                .build();
    }
}
