/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

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
public class RitualSeasonDto implements Serializable {

    private static final long serialVersionUID = 5471593790584285097L;

    private long id;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private int seasonYear;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private String ritualTypeCode;

    private int seasonStart;

    private int seasonEnd;

    private boolean activated;
}
