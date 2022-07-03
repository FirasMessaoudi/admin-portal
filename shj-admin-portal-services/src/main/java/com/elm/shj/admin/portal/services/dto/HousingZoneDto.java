/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Dto class for the Housing Zone .
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HousingZoneDto implements Serializable {


    private static final long serialVersionUID = 7575765144322740810L;

    private long id;
    private String labelAr;
    private String labelEn;
    private String color;
    @JsonBackReference("packageHousings")
    private List<PackageHousingDto> packageHousings;

}
