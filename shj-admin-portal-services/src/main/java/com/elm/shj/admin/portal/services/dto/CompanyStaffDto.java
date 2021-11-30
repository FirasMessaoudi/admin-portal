/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffDigitalId;
import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.validators.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Dto class for the companyStaff.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@NoArgsConstructor
@Getter
@Setter
@FieldDependency.List({
        @FieldDependency(first = "idNumber", second = "passportNumber"),
        @FieldDependency(first = "dateOfBirthGregorian", second = "dateOfBirthHijri")
})
public class CompanyStaffDto {
    private static final long serialVersionUID = -4462482228404995089L;
    private long id;

    @CellIndex(index = 5)
    @NullOrNotBlank(min = 5, max = 255)
    private String fullNameAr;

    @NullOrNotBlank(min = 5, max = 255)
    @CellIndex(index = 4)
    private String fullNameEn;

    @IdNumber(minLength = 5, maxLength = 30)
    @CellIndex(index = 9)
    private String idNumberOriginal;

    @UniquePerRequest
    @PassportNumber
    @CellIndex(index = 1)
    private String passportNumber;

    @GregorianDate(minOffset = -120, maxOffset = -10)
    @CellIndex(index = 2)
    private Date dateOfBirthGregorian;

    @HijriDate(minOffset = -140, maxOffset = -11)
    @CellIndex(index = 3)
    private Long dateOfBirthHijri;
    @Gender
    @CellIndex(index = 7)
    private String gender;

    @CountryCode
    @CellIndex(index = 8)
    private String nationalityCode;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @CellIndex(index = 10)
    private String photo;

    @Min(1)
    @Max(15)
    @NotNull(message = "validation.data.constraints.msg.20001")
    private int idNumber;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @JsonBackReference(value = "company")
    private CompanyDto company;

    @JsonBackReference(value = "applicantGroups")
    private List<ApplicantGroupDto> applicantGroups;

    @NullOrNotBlank(min = 3, max = 45)
    @CellIndex(index = 11)
    private String titleCode;
    @CellIndex(index =15)
    private int season;

    @NullOrNotBlank(min = 10, max = 150)
    @CellIndex(index = 6)
    private String fullNameOrigin;

    @Max(20)
    @Min(10)
    @NotNull(message = "validation.data.constraints.msg.20001")
    @CellIndex(index =13)
    private String mobileNumber;

    @Max(20)
    @Min(10)
    @NotNull(message = "validation.data.constraints.msg.20001")
    @CellIndex(index =14)
    private String mobileNumberIntl;

    @NullOrNotBlank(min = 5, max = 255)
    @Email(message = "validation.data.constraints.msg.20003")
    @CellIndex(index =12)
    private String email;

    private DataRequestRecordDto dataRequestRecord;
     private List<CompanyStaffDigitalIdDto> digitalIds;

    private Date creationDate;
    private Date updateDate;
}
