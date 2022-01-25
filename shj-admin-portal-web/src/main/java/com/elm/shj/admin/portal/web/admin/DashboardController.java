/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.dashboard.DashboardGeneralNumbersVo;
import com.elm.shj.admin.portal.services.dashboard.DashboardService;
import com.elm.shj.admin.portal.services.dashboard.DashboardVo;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.utils.DateUtils;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Main controller for admin dashboard page after login
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@RestController
@RequestMapping(Navigation.API_DASHBOARD)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DashboardController {

    private final DashboardService dashboardService;

    public enum EPeriodType {
        D, // daily
        W, // Weekly
        M, // Monthly
    }

    @GetMapping
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public DashboardVo loadDashboardData() {
        log.info("Handling loadDashboardData endpoint.");
        return dashboardService.loadDashboardData();
    }

    /**
     * Loads dashboard statistics for a specific period
     *
     * @param periodType the period type to load
     * @return dashboard statistics for the selected period
     */
    @GetMapping("/period/{periodType}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public DashboardVo loadPeriodicallyDashboardData(@PathVariable("periodType") EPeriodType periodType) {
        log.info("Handling loadPeriodDashboardData endpoint.");
        switch (periodType) {
            case D:
                return dashboardService.loadDailyDashboardData();
            case W:
                return dashboardService.loadWeeklyDashboardData();
            case M:
                return dashboardService.loadMonthlyDashboardData();
        }
        return null;
    }

    @GetMapping("/general-numbers/current-season")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public DashboardGeneralNumbersVo loadCurrentSeasonGeneralNumbers() {
        log.info("Load Dashboard General Numbers for Current Season.");
        return dashboardService.loadDashboardGeneralNumbersByHijriSeason((int) DateUtils.getCurrentHijriYear());
    }

    @GetMapping("/general-numbers/previous-season")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public DashboardGeneralNumbersVo loadPreviousSeasonGeneralNumbers() {
        log.info("Load Dashboard General Numbers for Previous Season.");
        return dashboardService.loadDashboardGeneralNumbersByHijriSeason((int) DateUtils.getCurrentHijriYear() - 1);
    }

}
