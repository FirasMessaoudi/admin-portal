/*
 *  Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.lookup;

import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.lookup.LookupService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for lookup data
 *
 * @author ahmad flaifel
 * @since 1.3.0
 */
@Slf4j
@RestController
@RequestMapping(Navigation.API_LOOKUP)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LookupController {

    private final LookupService lookupService;

    /**
     * List Parent Authorities
     *
     * @return List ParentAuthorities
     */
    @GetMapping("/authority/list/parent")
    public List<AuthorityLookupDto> listParentAuthorities(Authentication authentication) {
        return lookupService.listParentAuthorities();
    }

    /**
     * List Ritual Types
     *
     * @return List Ritual Types
     */
    @GetMapping("/ritual-type/list")
    public List<RitualTypeLookupDto> listRitualTypes(Authentication authentication) {
        log.debug("list ritual types...");
        return lookupService.listRitualTypes();
    }

    /**
     * List Card Statuses
     *
     * @return List Card Statuses
     */
    @GetMapping("/card-status/list")
    public List<CardStatusLookupDto> listCardStatuses(Authentication authentication) {
        log.debug("list card statuses...");
        return lookupService.listCardStatuses();
    }

    /**
     * List Relative Relationships
     *
     * @return List Relative Relationships
     */
    @GetMapping("/relative-relationship/list")
    public List<RelativeRelationshipLookupDto> listRelativeRelationships(Authentication authentication) {
        log.debug("list relative relationships...");
        return lookupService.listRelativeRelationships();
    }

    /**
     * List Marital Statuses
     *
     * @return List Marital Statuses
     */
    @GetMapping("/marital-status/list")
    public List<MaritalStatusLookupDto> listMaritalStatuses(Authentication authentication) {
        log.debug("list marital statuses...");
        return lookupService.listMaritalStatuses();
    }

    /**
     * List Countries
     *
     * @return List Countries
     */
    @GetMapping("/country/list")
    public List<NationalityLookupDto> listCountries(Authentication authentication) {
        log.debug("list countries...");
        return lookupService.listCountries();
    }

    /**
     * List Health Special Needs
     *
     * @return List Health Special Needs
     */
    @GetMapping("/health-special-needs/list")
    public List<HealthSpecialNeedsTypeLookupDto> listHealthSpecialNeeds(Authentication authentication) {
        log.debug("list health special needs...");
        return lookupService.listHealthSpecialNeeds();
    }

    /**
     * List Print Request Statuses
     *
     * @return List Print Request Statuses
     */
    @GetMapping("/print-request-status/list")
    public List<PrintRequestStatusLookupDto> listPrintRequestStatuses(Authentication authentication) {
        log.debug("list print request statuses...");
        return lookupService.listPrintRequestStatuses();
    }

    /**
     * List Print Request Batch Types
     *
     * @return List Print Request Batch Types
     */
    @GetMapping("/print-batch-type/list")
    public List<PrintBatchTypeLookupDto> listPrintRequestBatchTypes(Authentication authentication) {
        log.debug("list print batch types...");
        return lookupService.listPrintRequestBatchTypes();
    }

    /**
     * List Company Ritual Steps Label
     *
     * @return List Company Ritual Steps Label
     */
    @GetMapping("/company_ritual_step/list")
    public List<CompanyRitualStepLookupDto> listCompanyRitualStepsLabel(Authentication authentication) {
        log.debug("list company ritual step labels...");
        return lookupService.listCompanyRitualStepsLabel();
    }

    /**
     * List Company Staff Titles
     *
     * @return List Company Staff Titles
     */
    @GetMapping("/company-staff-title/list")
    public List<CompanyStaffTitleLookupDto> listCompanyStaffTitles(Authentication authentication) {
        log.debug("list company staff title labels...");
        return lookupService.listCompanyStaffTitles();
    }

    /**
     * List Housing Categories
     *
     * @return List Housing Categories
     */
    @GetMapping("/housing-category/list")
    public List<HousingCategoryLookupDto> listHousingCategories(Authentication authentication) {
        log.debug("list housing category...");
        return lookupService.listHousingCategories();
    }

    /**
     * List Housing Types
     *
     * @return List Housing Types
     */
    @GetMapping("/housing-type/list")
    public List<HousingTypeLookupDto> listHousingTypes(Authentication authentication) {
        log.debug("list housing type...");
        return lookupService.listHousingTypes();
    }

    /**
     * List Package Types
     *
     * @return List Package Types
     */
    @GetMapping("/package-type/list")
    public List<PackageTypeLookupDto> listPackageTypes(Authentication authentication) {
        log.debug("list package type...");
        return lookupService.listPackageTypes();
    }

    /**
     * List Housing Sites
     *
     * @return List Housing Sites
     */
    @GetMapping("/housing-site/list")
    public List<HousingSiteLookupDto> listHousingSites(Authentication authentication) {
        log.debug("list housing site...");
        return lookupService.listHousingSites();
    }

    /**
     * List Camp Sites
     *
     * @return List Camp Sites
     */
    @GetMapping("/camp-site/list")
    public List<HousingSiteLookupDto> listCampSites(Authentication authentication) {
        log.debug("list camp sites...");
        return lookupService.listCampSites();
    }

    /**
     * List Transportation Types
     *
     * @return List Transportation Types
     */
    @GetMapping("/transportation-type/list")
    public List<TransportationTypeLookupDto> listTransportationTypes(Authentication authentication) {
        log.debug("list transportation type...");
        return lookupService.listTransportationTypes();
    }

    /**
     * List Notification Categories
     *
     * @return List Notification Categories
     */
    @GetMapping("/notification-category/list")
    public List<NotificationCategoryLookupDto> listNotificationCategories(Authentication authentication) {
        log.debug("list notification category...");
        return lookupService.listNotificationCategories();
    }

    /**
     * List Notification Template Names
     *
     * @return List Notification Template Names
     */
    @GetMapping("/notification-name/list")
    public List<NotificationTemplateNameLookupDto> listNotificationTemplateNames(Authentication authentication) {
        log.debug("list notification template name...");
        return lookupService.listNotificationTemplateNames();
    }

    /**
     * List Notification Template Types
     *
     * @return List Notification Template Types
     */
    @GetMapping("/notification-template-type/list")
    public List<NotificationTemplateTypeLookupDto> listNotificationTemplateTypes() {
        log.debug("list notification template types...");
        return lookupService.listNotificationTemplateTypes();
    }

    /**
     * List User Notification Statuses
     *
     * @return List User Notification Statuses
     */
    @GetMapping("/user-notification-status/list")
    public List<UserNotificationStatusLookupDto> listUserNotificationStatuses() {
        log.debug("list user notification statuses...");
        return lookupService.listUserNotificationStatuses();
    }

    /**
     * List Notification Template Statuses
     *
     * @return List Notification Template Statuses
     */
    @GetMapping("/notification-template-status/list")
    public List<NotificationTemplateStatusLookupDto> listNotificationTemplateStatuses() {
        log.debug("list notification template statuses...");
        return lookupService.listNotificationTemplateStatuses();
    }

    /**
     * List Immunization
     *
     * @return List Immunization
     */
    @GetMapping("/health-immunization/list")
    public List<HealthImmunizationLookupDto> listImmunization() {
        log.debug("list health immunizations...");
        return lookupService.listImmunization();
    }

    /**
     * List Religious Occasions Day
     *
     * @return List Religious Occasions Day
     */
    @GetMapping("/religious-occasions-day/list")
    public List<ReligiousOccasionsDayLookupDto> listReligiousOccasionsDay() {
        log.debug("list religious occasions day...");
        return lookupService.listReligiousOccasionsDay();
    }

    /**
     * List Meal Types
     *
     * @return List Meal Types
     */
    @GetMapping("/meal-type/list")
    public List<MealTypeLookupDto> listMealTypes() {
        log.debug("list meal types...");
        return lookupService.listMealTypes();
    }

    /**
     * List Digital Id Statuses
     *
     * @return List Digital Id Statuses
     */
    @GetMapping("/digital-id-status/list")
    public List<ApplicantDigitalIdStatusLookupDto> listDigitalIdStatuses() {
        log.debug("list applicant digital ID statuses...");
        return lookupService.listDigitalIdStatuses();
    }

    /**
     * List Languages
     *
     * @return List Languages
     */
    @GetMapping("/language/list")
    public List<LanguageLookupDto> listLanguages() {
        log.debug("list languages...");
        return lookupService.listLanguages();
    }

    /**
     * List Incident Types
     *
     * @return List Incident Types
     */
    @GetMapping("/incident-type/list")
    public List<IncidentTypeLookupDto> listIncidentTypes() {
        log.debug("list incident types...");
        return lookupService.listIncidentTypes();
    }

    /**
     * List Incident Statuses
     *
     * @return List Incident Statuses
     */
    @GetMapping("/incident-status/list")
    public List<IncidentStatusLookupDto> listIncidentStatuses() {
        log.debug("list incident statuses...");
        return lookupService.listIncidentStatuses();
    }

    /**
     * Load Google Maps ApiKey
     *
     * @return  Google Maps ApiKey
     */
    @GetMapping("/google-maps/api-key")
    public String loadGoogleMapsApiKey() {
        log.debug("load google maps api key...");
        return lookupService.loadGoogleMapsApiKey();
    }

    /**
     * List Company Names By SesonYear
     *
     * @param seasonYear
     * @return List Company Names
     */
    @GetMapping("/company-names/list/{seasonYear}")
    public List<CompanyLiteDto> listCompanyNames(@PathVariable("seasonYear") int seasonYear) {
        log.debug("list company names for current season ...");
        return lookupService.listCompanyNameBySeasonYear(seasonYear);
    }

    /**
     * List Company Names
     *
     * @return List Company Names
     */
    @GetMapping("/company-names/list")
    public List<CompanyLiteDto> listCompanyNames() {
        log.debug("list company names...");
        return lookupService.listCompanyNames();
    }

    /**
     * List Ritual Seasons
     *
     * @return List Ritual Seasons
     */
    @GetMapping("/ritual-seasons/list")
    public List<RitualSeasonDto> listRitualSeasons() {
        log.debug("list ritual seasons...");
        return lookupService.listRitualSeasons();
    }

    /**
     * List Ritual Season Years
     *
     * @return List Ritual Season Years
     */
    @GetMapping("/ritual-seasons-years/list")
    public List<Integer> listRitualSeasonYears() {
        log.debug("list ritual season years...");
        return lookupService.listRitualSeasonYears();
    }

    /**
     * List Staff Print Request Batch Types
     *
     * @return List Staff Print Request Batch Types
     */
    @GetMapping("/staff-print-batch-type/list")
    public List<PrintBatchTypeLookupDto> listStaffPrintRequestBatchTypes(Authentication authentication) {
        log.debug("list print batch types...");
        return lookupService.listStaffPrintRequestBatchTypes();
    }

    /**
     * load Dashboard Refresh Interval
     *
     * @return Dashboard Refresh Interval
     */
    @GetMapping("/dashboard/refresh-interval")
    public Integer loadDashboardRefreshInterval() {
        log.debug("load dashboard refresh interval...");
        return lookupService.loadDashboardRefreshInterval();
    }

    /**
     * List Area Layers
     *
     * @return List Area Layers
     */
    @GetMapping("/area_layers/list")
    public List<AreaLayerDto> listAreaLayers() {
        log.debug("list area Layers...");
        return lookupService.listAreaLayers();
    }

    /**
     * List Area Layers Label
     *
     * @return List Area Layers Label
     */
    @GetMapping("/area_layers_labels/list")
    public List<AreaLayerLookupDto> AreaLayersLabel() {
        log.debug("list area layer label...");
        return lookupService.AreaLayersLabel();
    }

}
