/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dashboard;

import lombok.*;

import java.io.Serializable;

/**
 * Dashboard general numbers that will be received
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardGeneralNumbersVo implements Serializable {

    private static final long serialVersionUID = -1117150721646949784L;

    // Pilgrims
    private long totalNumberOfPilgrims;
    private long totalNumberOfMalePilgrims;
    private long totalNumberOfFemalePilgrims;
}
