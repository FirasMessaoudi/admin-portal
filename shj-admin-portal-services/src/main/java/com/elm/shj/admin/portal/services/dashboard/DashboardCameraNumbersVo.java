/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dashboard;

import lombok.*;

import java.io.Serializable;

/**
 * Dashboard camera value object
 *
 * @author r.chebbi
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardCameraNumbersVo implements Serializable {

    private static final long serialVersionUID = -182172602430591013L;

    private long totalNumberOfCameras;
    private long totalNumberOfActiveCameras;
    private long totalNumberOfInactiveCameras;
}
