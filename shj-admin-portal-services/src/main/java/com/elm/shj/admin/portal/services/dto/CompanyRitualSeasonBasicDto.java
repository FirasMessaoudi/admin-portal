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
    private Long seasonStart;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private Long seasonEnd;

    private Integer totalQuota;
    private Integer airQuota;
    private Integer seaQuota;
    private Integer landQuota;
    private CompanyLiteDto company;
    private Date creationDate;
    private Date updateDate;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private Boolean isActive;
}
