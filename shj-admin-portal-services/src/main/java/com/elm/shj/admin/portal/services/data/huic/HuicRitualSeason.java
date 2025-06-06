/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.huic;

import com.elm.shj.admin.portal.services.data.validators.HijriDate;
import com.elm.shj.admin.portal.services.data.validators.RitualTypeCode;
import com.elm.shj.admin.portal.services.data.validators.SeasonYear;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Dto class for the ritual season domain.
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class HuicRitualSeason implements Serializable {


    private static final long serialVersionUID = 6193090010236711143L;

    @SeasonYear
    private int seasonYear;
    @RitualTypeCode
    private Integer ritualTypeCode;
    @NotNull(message = "validation.data.constraints.msg.20001")
    @HijriDate(minOffset = -1, maxOffset = 1)
    private long seasonStart;
    @NotNull(message = "validation.data.constraints.msg.20001")
    @HijriDate(minOffset = -1, maxOffset = 1)
    private long seasonEnd;

}
