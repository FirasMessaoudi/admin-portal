/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.orm.entity.CountVo;
import com.elm.shj.admin.portal.orm.entity.LocationVo;
import com.elm.shj.admin.portal.services.dashboard.*;
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

import javax.annotation.security.RolesAllowed;
import java.util.List;


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

    @GetMapping("/incident-numbers")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.INCIDENT_DASHBOARD + "')")
    public DashboardIncidentNumbersVo loadIncidentNumbers() {
        log.info("Handling loadDashboardGeneralNumbers endpoint.");
        return dashboardService.loadDashboardIncidentNumbers();
    }

    @GetMapping("/incident-numbers/max-companies")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.INCIDENT_DASHBOARD + "')")
    public List<CountVo> loadCompaniesWithMaxIncidentsCount() {
        log.info("Load Companies with max incident count");
        return dashboardService.loadCompaniesWithMaxIncidentsCount();
    }

    @GetMapping("/incident-numbers/min-companies")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.INCIDENT_DASHBOARD + "')")
    public List<CountVo> loadCompaniesWithMinIncidentsCount() {
        log.info("Load Companies with min incident count.");
        return dashboardService.loadCompaniesWithMinIncidentsCount();
    }

    @GetMapping("/general-numbers/applicant/count-per-age")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT)
    public List<CountVo> countPilgrimsFromCurrentSeasonByAgeRanges() {
        log.debug("Count applicants based on age range...");
        return dashboardService.pilgrimsCountListsByAgesRange();
    }

    @GetMapping("/general-numbers/applicant/count-per-nationalities")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<CountVo> loadNumberOfApplicantsByNationalities() {
        log.info("Handling loadDashboardGeneralNumbers endpoint.");
        return dashboardService.listCountApplicantsByNationalities();
    }

    @GetMapping("/general-numbers/max-companies")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<CountVo> loadCompaniesWithMaxApplicantsCount() {
        log.info("Load Companies with max applicants' count for current season.");
        return dashboardService.loadCompaniesWithMaxApplicantsCountByHijriSeason((int) DateUtils.getCurrentHijriYear());
    }

    @GetMapping("/general-numbers/min-companies")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<CountVo> loadCompaniesWithMinApplicantsCount() {
        log.info("Load Companies with min applicants' count for current season.");
        return dashboardService.loadCompaniesWithMinApplicantsCountByHijriSeason((int) DateUtils.getCurrentHijriYear());
    }

    @GetMapping("/general-numbers/max-camps")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<CountVo> loadCampsWithMaxApplicantsCount() {
        log.info("Load Camps with max applicants' count for current season.");
        return dashboardService.loadCampsWithMaxApplicantsCountByHijriSeason((int) DateUtils.getCurrentHijriYear());
    }

    @GetMapping("/general-numbers/min-camps")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<CountVo> loadCampsWithMinApplicantsCount() {
        log.info("Load Camps with min applicants' count for current season.");
        return dashboardService.loadCampsWithMinApplicantsCountByHijriSeason((int) DateUtils.getCurrentHijriYear());
    }

    @GetMapping("/incidents-locations")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<LocationVo> loadIncidentsLocationsFromCurrentSeasons() {
        log.info("Handling loadDashboardGeneralNumbers endpoint.");
        return dashboardService.getIncidentsLocationsFromCurrentSeason();
    }

    @GetMapping("/mobile/app-downloads")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public DashboardMobileNumbersVo loadMobileAppDownloadNumbers() {
        log.info("Handling loadMobileAppDownloadNumbers endpoint.");
        return dashboardService.getMobileAppDownloadsFromCurrentSeason();
    }

    @GetMapping("/applicant-numbers/max-companies")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<CountVo> loadCompaniesWithMaxApplicantsRegisteredCount() {
        log.info("Load Companies with max applicant registered count");
        return dashboardService.loadCompaniesWithMaxApplicantsRegisteredCount();
    }

    @GetMapping("/applicant-numbers/min-companies")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<CountVo> loadCompaniesWithMinApplicantsRegisteredCount() {
        log.info("Load Companies with min applicant registered count.");
        return dashboardService.loadCompaniesWithMinApplicantsRegisteredCount();
    }

}
