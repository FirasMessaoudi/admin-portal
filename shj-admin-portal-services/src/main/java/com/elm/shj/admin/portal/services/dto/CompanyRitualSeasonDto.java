/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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

    @NotNull(message = "validation.data.constraints.msg.20001")
    @JsonBackReference(value = "company")
    private CompanyDto company;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @JsonBackReference(value = "ritualSeason")
    private RitualSeasonDto ritualSeason;


    @JsonBackReference(value = "ritualPackages")
    private List<RitualPackageDto> ritualPackages;

    @JsonBackReference(value = "applicantGroups")
    private List<ApplicantGroupDto> applicantGroups;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private int seasonStart;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private int seasonEnd;

    private int totalQuota;
    private int airQuota;
    private int seaQuota;
    private int landQuota;
    private Date creationDate;
    private Date updateDate;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private boolean isActive;

}
