/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.validators.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class CompanyStaffDto {
    private static final long serialVersionUID = -4462482228404995089L;
    private long id;

    @NullOrNotBlank(min = 5, max = 255)
    private String fullNameAr;

    @NullOrNotBlank(min = 5, max = 255)
    private String fullNameEn;

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
    private String titleCode;

    @Max(20)
    @Min(10)
    @NotNull(message = "validation.data.constraints.msg.20001")
    private String mobileNumber;

    @NullOrNotBlank(min = 5, max = 255)
    @Email(message = "validation.data.constraints.msg.20003")
    private String email;

    private Date creationDate;
    private Date updateDate;
}
