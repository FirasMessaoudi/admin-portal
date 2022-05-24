/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

/**
 * Dto class for geo location.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GeoLocationDto {
    private double lat;
    private double lng;
}
