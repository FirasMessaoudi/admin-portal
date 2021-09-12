/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

/**
 * Dto class for the company.
 *
 * @author salzoubi
 * @since 1.1.0
 */

@NoArgsConstructor
@Data
public class CompanyDto {

    private static final long serialVersionUID = 436091561114554168L;
    private long id;
    private String code;
    private String labelAr;
    private String labelEn;
    private int missionId;
    private String contactNumber;
    private String website;
    private String accreditationOrganization;
    private String accreditationNumber;
    private Date accreditationDate;
    private Date accreditationExpiry;
    private String email;
    private Date creationDate;
    private Date updateDate;

    @JsonBackReference(value = "companyRitualSeasons")
    private Set<CompanyRitualSeasonDto> companyRitualSeasons;

    @JsonBackReference(value = "companyStaff")
    private Set<CompanyStaffDto> companyStaff;

}
