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
 * Dto class for the CompanyRitualSeason.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@NoArgsConstructor
@Data
public class CompanyRitualSeasonDto {
    private static final long serialVersionUID = 4098474781987361976L;
    private long id;
    @JsonBackReference(value = "company")
    private CompanyDto company;
    @JsonBackReference(value = "ritualSeason")
    private RitualSeasonDto ritualSeason;
    @JsonBackReference(value = "companySeasonPackages")
    private Set<CompanySeasonPackageDto> companySeasonPackages;
    /*@JsonBackReference(value = "applicantGroups")
    private Set<ApplicantGroupDto> applicantGroups;*/
    private Date seasonStart;
    private Date seasonEnd;
    private int totalQuota;
    private int airQuota;
    private int seaQuota;
    private int landQuota;
    private Date creationDate;
    private Date updateDate;
}
