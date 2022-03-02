/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.orm.entity.ApplicantMobileTrackingVo;
import com.elm.shj.admin.portal.orm.entity.CountVo;
import com.elm.shj.admin.portal.orm.entity.LocalizedCountVo;
import com.elm.shj.admin.portal.orm.entity.LocationVo;
import com.elm.shj.admin.portal.services.dashboard.*;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.dto.ECampSite;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/general-numbers/current-season/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public DashboardGeneralNumbersVo loadCurrentSeasonGeneralNumbers(@PathVariable("seasonYear") int seasonYear) {
        log.info("Load Dashboard General Numbers for Current Season.");
        return dashboardService.loadDashboardGeneralNumbersByHijriSeason(seasonYear);
    }

    @GetMapping("/general-numbers/previous-season/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public DashboardGeneralNumbersVo loadPreviousSeasonGeneralNumbers(@PathVariable("seasonYear") int seasonYear) {
        log.info("Load Dashboard General Numbers for Previous Season.");
        return dashboardService.loadDashboardGeneralNumbersByHijriSeason(seasonYear);
    }

    @GetMapping("/incident-numbers/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.INCIDENT_DASHBOARD + "')")
    public DashboardIncidentNumbersVo loadIncidentNumbers(@PathVariable("seasonYear") int seasonYear) {
        log.info("Handling loadDashboardGeneralNumbers endpoint.");
        return dashboardService.loadDashboardIncidentNumbers(seasonYear);
    }

    @GetMapping("/incident-numbers/max-companies/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.INCIDENT_DASHBOARD + "')")
    public List<LocalizedCountVo> loadCompaniesWithMaxIncidentsCount(@PathVariable("seasonYear") int seasonYear) {
        log.info("Load Companies with max incident count");
        return dashboardService.loadCompaniesWithMaxIncidentsCount(seasonYear);
    }

    @GetMapping("/incident-numbers/min-companies/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.INCIDENT_DASHBOARD + "')")
    public List<LocalizedCountVo> loadCompaniesWithMinIncidentsCount(@PathVariable("seasonYear") int seasonYear) {
        log.info("Load Companies with min incident count.");
        return dashboardService.loadCompaniesWithMinIncidentsCount(seasonYear);
    }

    @GetMapping("/general-numbers/applicant/count-per-age/{seasonYear}")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT)
    public List<CountVo> countPilgrimsFromCurrentSeasonByAgeRanges(@PathVariable("seasonYear") int seasonYear) {
        log.debug("Count applicants based on age range...");
        return dashboardService.pilgrimsCountListsByAgesRange(seasonYear);
    }

    @GetMapping("/general-numbers/applicant/count-per-nationalities/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<CountVo> loadNumberOfApplicantsByNationalities(@PathVariable int seasonYear) {
        log.info("Handling loadDashboardGeneralNumbers endpoint.");
        return dashboardService.listCountApplicantsByNationalities(seasonYear);
    }

    @GetMapping("/general-numbers/max-companies/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<LocalizedCountVo> loadCompaniesWithMaxApplicantsCount(@PathVariable("seasonYear") int seasonYear) {
        log.info("Load Companies with max applicants' count for current season.");
        return dashboardService.loadCompaniesWithMaxApplicantsCountByHijriSeason(seasonYear);
    }

    @GetMapping("/general-numbers/min-companies/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<LocalizedCountVo> loadCompaniesWithMinApplicantsCount(@PathVariable("seasonYear") int seasonYear) {
        log.info("Load Companies with min applicants' count for current season.");
        return dashboardService.loadCompaniesWithMinApplicantsCountByHijriSeason(seasonYear);
    }

    @GetMapping("/general-numbers/max-camps/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<LocalizedCountVo> loadCampsWithMaxApplicantsCount(@PathVariable("seasonYear") int seasonYear, @RequestParam ECampSite site) {
        log.info("Load Camps with max applicants' count for current season.");
        return dashboardService.loadCampsWithMaxApplicantsCountByHijriSeason(seasonYear, site);
    }

    @GetMapping("/general-numbers/min-camps/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<LocalizedCountVo> loadCampsWithMinApplicantsCount(@PathVariable("seasonYear") int seasonYear, @RequestParam ECampSite site) {
        log.info("Load Camps with min applicants' count for current season.");
        return dashboardService.loadCampsWithMinApplicantsCountByHijriSeason(seasonYear, site);
    }

    @GetMapping("/incidents-locations/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<LocationVo> loadIncidentsLocationsFromCurrentSeasons(@PathVariable("seasonYear") int seasonYear) {
        log.info("Handling loadDashboardGeneralNumbers endpoint.");
        return dashboardService.getIncidentsLocationsFromCurrentSeason(seasonYear);
    }

    @GetMapping("/mobile/app-downloads/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public DashboardMobileNumbersVo loadMobileAppDownloadNumbers(@PathVariable("seasonYear") int seasonYear) {
        log.info("Handling loadMobileAppDownloadNumbers endpoint.");
        return dashboardService.getMobileAppDownloadsFromCurrentSeason(seasonYear);
    }

    @GetMapping("/applicant-numbers/max-companies/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<CountVo> loadCompaniesWithMaxApplicantsRegisteredCount(@PathVariable("seasonYear") int seasonYear) {
        log.info("Load Companies with max applicant registered count");
        return dashboardService.loadCompaniesWithMaxApplicantsRegisteredCount(seasonYear);
    }

    @GetMapping("/applicant-numbers/min-companies/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<CountVo> loadCompaniesWithMinApplicantsRegisteredCount(@PathVariable("seasonYear") int seasonYear) {
        log.info("Load Companies with min applicant registered count.");
        return dashboardService.loadCompaniesWithMinApplicantsRegisteredCount(seasonYear);
    }

    @GetMapping("/mobile/logged-in-users/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public int[] loadMobileLoggedInUsers(@PathVariable("seasonYear") int seasonYear) {
        log.info("Handling loadMobileLoggedInUsers endpoint.");
        return dashboardService.getMobileLoggedInUsers(seasonYear);
    }

    @GetMapping("/mobile/users/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public int[] loadMobileUsers(@PathVariable("seasonYear") int seasonYear) {
        log.info("Handling loadMobileUsers endpoint.");
        return dashboardService.getMobileUsers(seasonYear);
    }

    @GetMapping("/mobile/active-applicants-locations/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<ApplicantMobileTrackingVo> findActiveApplicantWithLocationBySeason(@PathVariable("seasonYear") int seasonYear) {
        log.info("Handling findActiveApplicantWithLocationBySeason endpoint.");
        return dashboardService.findActiveApplicantWithLocationBySeason(seasonYear);
    }

    @GetMapping("/mobile/usage-by-age-range/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public List<CountVo> loadMobileAppUsersCountByAgeRange(@PathVariable("seasonYear") int seasonYear) {
        log.info("Handling findActiveApplicantWithLocationBySeason endpoint.");
        return dashboardService.loadMobileAppUsersCountByAgeRange(seasonYear);
    }

    @GetMapping("/camera-numbers/{seasonYear}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADMIN_DASHBOARD + "')")
    public DashboardCameraNumbersVo CountTotalCameras(@PathVariable("seasonYear") int seasonYear) {
        log.debug("Count  cameras numbers ...");
        return dashboardService.loadDashboardCamerasNumbers(seasonYear);
    }

}
