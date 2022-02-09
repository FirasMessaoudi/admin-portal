/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dashboard;

import lombok.*;

import java.io.Serializable;

/**
 * Dashboard mobile app numbers that will be received
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardMobileNumbersVo implements Serializable {

    private static final long serialVersionUID = -8104946004308613206L;

    private long totalNumberOfLoggedInUsers;
    private long totalNumberOfLoggedOutUsers;
    private long totalNumberOfMobileAppDownloads;
}
