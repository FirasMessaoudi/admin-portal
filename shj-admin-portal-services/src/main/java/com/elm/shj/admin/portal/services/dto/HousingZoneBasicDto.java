/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Dto class for the Housing Zone Basic.
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HousingZoneBasicDto implements Serializable {

    private long id;
    private String labelAr;
    private String labelEn;
    private String color;

}
