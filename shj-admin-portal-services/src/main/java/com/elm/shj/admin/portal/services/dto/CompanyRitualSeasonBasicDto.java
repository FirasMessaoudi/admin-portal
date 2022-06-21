/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Dto class for the CompanyRitualSeason.
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@NoArgsConstructor
@Getter
@Setter
public class CompanyRitualSeasonBasicDto {
    private static final long serialVersionUID = 4098474781987361976L;

    private long id;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private RitualSeasonDto ritualSeason;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private long seasonStart;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private long seasonEnd;

    private int totalQuota;
    private int airQuota;
    private int seaQuota;
    private int landQuota;
    private CompanyLiteDto company;
    private Date creationDate;
    private Date updateDate;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private boolean isActive;
}
