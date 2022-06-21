/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.validators.HijriDate;
import com.elm.shj.admin.portal.services.data.validators.RitualTypeCode;
import com.elm.shj.admin.portal.services.data.validators.SeasonYear;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Dto class for the ritual season domain.
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RitualSeasonBasicDto implements Serializable {

    private long id;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @SeasonYear
    private int seasonYear;

    @RitualTypeCode
    private String ritualTypeCode;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @HijriDate(minOffset = -1, maxOffset = 1)
    private long seasonStart;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @HijriDate(minOffset = -1, maxOffset = 1)
    private long seasonEnd;

    private boolean activated;
}
