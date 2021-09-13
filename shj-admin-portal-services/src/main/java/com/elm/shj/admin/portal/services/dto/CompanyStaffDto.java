/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Dto class for the companyStaff.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@NoArgsConstructor
@Data
public class CompanyStaffDto {
    private static final long serialVersionUID = -4462482228404995089L;
    private long id;
    private String fullNameAr;
    private String fullNameEn;
    private int idNumber;
    @JsonBackReference(value = "company")
    private CompanyDto company;
    /* @JsonBackReference(value = "applicantGroups")
     private List<ApplicantGroupDto> applicantGroups;*/
    private String titleCode;
    private String mobileNumber;
    private String email;
    private Date creationDate;
    private Date updateDate;
}
