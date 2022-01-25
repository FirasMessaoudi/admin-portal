/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dashboard;

import com.elm.shj.admin.portal.orm.entity.CountVo;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Dashboard general numbers that will be received
 *
 * @author Rameez Imtiaz
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardIncidentNumbersVo implements Serializable {

    private static final long serialVersionUID = -1117150721646949784L;

    // incidents
    private long totalNumberOfRegisteredIncidents;
    private long totalNumberOfResolvedIncidents;
    private long totalNumberOfUnResolvedIncidents;
    private List<CountVo> countIncidentByCompany;
}
