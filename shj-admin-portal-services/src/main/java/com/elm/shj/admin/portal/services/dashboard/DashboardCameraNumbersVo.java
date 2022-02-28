package com.elm.shj.admin.portal.services.dashboard;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardCameraNumbersVo implements Serializable {


    private long totalNumberOfCameras;
    private long totalNumberOfActiveCameras;
    private long totalNumberOfInactiveCameras;

}
