/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the ritual unit domain.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RitualUnitDto implements Serializable {

    private static final long serialVersionUID = -6494898105854025590L;

    private long id;
    private String code;
    private String lang;
    private String label;
    private long seasonId;
    private Date creationDate;
}
