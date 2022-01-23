/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
public class CompanyRitualSeasonDto {
    private static final long serialVersionUID = 4098474781987361976L;

    private long id;

    @NotNull(message = "validation.data.constraints.msg.20001")
    //@JsonBackReference(value = "company")
    private CompanyDto company;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private RitualSeasonDto ritualSeason;

    private List<RitualPackageDto> ritualPackages;

    @JsonManagedReference
    private List<ApplicantGroupDto> applicantGroups;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private long seasonStart;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private long seasonEnd;

    private int totalQuota;
    private int airQuota;
    private int seaQuota;
    private int landQuota;
    private Date creationDate;
    private Date updateDate;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private boolean isActive;

}
