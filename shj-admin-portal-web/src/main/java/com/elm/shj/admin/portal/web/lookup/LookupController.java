/*
 *  Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.lookup;

import com.elm.shj.admin.portal.services.company.CompanyLiteService;
import com.elm.shj.admin.portal.services.dashboard.DashboardService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.lookup.*;
import com.elm.shj.admin.portal.services.ritual.RitualSeasonService;
import com.elm.shj.admin.portal.services.utils.MapUtils;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final AuthorityLookupService authorityLookupService;
    private final RitualTypeLookupService ritualTypeLookupService;
    private final CardStatusLookupService cardStatusLookupService;
    private final RelativeRelationshipLookupService relativeRelationshipLookupService;
    private final MaritalStatusLookupService maritalStatusLookupService;
    private final CountryLookupService countryLookupService;
    private final HealthSpecialNeedsLookupService healthSpecialNeedsLookupService;
    private final PrintRequestStatusLookupService printRequestStatusLookupService;
    private final PrintBatchTypeLookupService printBatchTypeLookupService;
    private final CompanyRitualStepLookupService companyRitualStepLookupService;
    private final CompanyStaffTitleLookupService companyStaffTitleLookupService;
    private final HousingCategoryLookupService housingCategoryLookupService;
    private final HousingTypeLookupService housingTypeLookupService;
    private final PackageTypeLookupService packageTypeLookupService;
    private final HousingSiteLookupService housingSiteLookupService;
    private final TransportationTypeLookupService transportationTypeLookupService;
    private final NotificationCategoryLookupService notificationCategoryLookupService;
    private final NotificationTemplateNameLookupService notificationTemplateNameLookupService;
    private final NotificationTemplateTypeLookupService notificationTemplateTypeLookupService;
    private final UserNotificationStatusLookupService userNotificationStatusLookupService;
    private final NotificationTemplateStatusLookupService notificationTemplateStatusLookupService;
    private final HealthImmunizationLookupService healthImmunizationLookupService;
    private final ApplicantDigitalIdStatusLookupService applicantDigitalIdStatusLookupService;
    private final ReligiousOccasionsDayLookupService religiousOccasionsDayLookupService;
    private final LanguageLookupService languageLookupService;
    private final MealTypeLookupService mealTypeLookupService;
    private final IncidentTypeLookupService incidentTypeLookupService;
    private final IncidentStatusLookupService incidentStatusLookupService;
    private final MapUtils mapUtils;
    private final CompanyLiteService companyLiteService;
    private final RitualSeasonService ritualSeasonService;
    private final DashboardService dashboardService;
    private final AreaLayerLookupService areaLayerLookupService;

    @GetMapping("/authority/list/parent")
    public List<AuthorityLookupDto> listParentAuthorities(Authentication authentication) {
        return authorityLookupService.findAllParentAuthorities();
    }

    @GetMapping("/ritual-type/list")
    public List<RitualTypeLookupDto> listRitualTypes(Authentication authentication) {
        log.debug("list ritual types...");
        return ritualTypeLookupService.findAll();
    }

    @GetMapping("/card-status/list")
    public List<CardStatusLookupDto> listCardStatuses(Authentication authentication) {
        log.debug("list card statuses...");
        return cardStatusLookupService.findAll();
    }

    @GetMapping("/relative-relationship/list")
    public List<RelativeRelationshipLookupDto> listRelativeRelationships(Authentication authentication) {
        log.debug("list relative relationships...");
        return relativeRelationshipLookupService.findAll();
    }

    @GetMapping("/marital-status/list")
    public List<MaritalStatusLookupDto> listMaritalStatuses(Authentication authentication) {
        log.debug("list marital statuses...");
        return maritalStatusLookupService.findAll();
    }

    @GetMapping("/country/list")
    public List<CountryLookupDto> listCountries(Authentication authentication) {
        log.debug("list countries...");
        return countryLookupService.findAll();
    }

    @GetMapping("/health-special-needs/list")
    public List<HealthSpecialNeedsTypeLookupDto> listHealthSpecialNeeds(Authentication authentication) {
        log.debug("list health special needs...");
        return healthSpecialNeedsLookupService.findAll();
    }

    @GetMapping("/print-request-status/list")
    public List<PrintRequestStatusLookupDto> listPrintRequestStatuses(Authentication authentication) {
        log.debug("list print request statuses...");
        return printRequestStatusLookupService.findAll();
    }

    @GetMapping("/print-batch-type/list")
    public List<PrintBatchTypeLookupDto> listPrintRequestBatchTypes(Authentication authentication) {
        log.debug("list print batch types...");
        return printBatchTypeLookupService.findBatchTypeByTarget(EPrintingRequestTarget.APPLICANT.name());
    }

    //TODO: remove "label" word from the method name and replace underscore in URL by dash
    @GetMapping("/company_ritual_step/list")
    public List<CompanyRitualStepLookupDto> listCompanyRitualStepsLabel(Authentication authentication) {
        log.debug("list company ritual step labels...");
        return companyRitualStepLookupService.findAll();
    }

    @GetMapping("/company-staff-title/list")
    public List<CompanyStaffTitleLookupDto> listCompanyStaffTitles(Authentication authentication) {
        log.debug("list company staff title labels...");
        return companyStaffTitleLookupService.findAll();
    }

    @GetMapping("/housing-category/list")
    public List<HousingCategoryLookupDto> listHousingCategories(Authentication authentication) {
        log.debug("list housing category...");
        return housingCategoryLookupService.findAll();
    }

    @GetMapping("/housing-type/list")
    public List<HousingTypeLookupDto> listHousingTypes(Authentication authentication) {
        log.debug("list housing type...");
        return housingTypeLookupService.findAll();
    }

    @GetMapping("/package-type/list")
    public List<PackageTypeLookupDto> listPackageTypes(Authentication authentication) {
        log.debug("list package type...");
        return packageTypeLookupService.findAll();
    }

    @GetMapping("/housing-site/list")
    public List<HousingSiteLookupDto> listHousingSites(Authentication authentication) {
        log.debug("list housing site...");
        return housingSiteLookupService.findAll();
    }

    @GetMapping("/camp-site/list")
    public List<HousingSiteLookupDto> listCampSites(Authentication authentication) {
        log.debug("list camp sites...");
        return housingSiteLookupService.findCampSites();
    }

    @GetMapping("/transportation-type/list")
    public List<TransportationTypeLookupDto> listTransportationTypes(Authentication authentication) {
        log.debug("list transportation type...");
        return transportationTypeLookupService.findAll();
    }

    @GetMapping("/notification-category/list")
    public List<NotificationCategoryLookupDto> listNotificationCategories(Authentication authentication) {
        log.debug("list notification category...");
        return notificationCategoryLookupService.findAll();
    }

    @GetMapping("/notification-name/list")
    public List<NotificationTemplateNameLookupDto> listNotificationTemplateNames(Authentication authentication) {
        log.debug("list notification template name...");
        return notificationTemplateNameLookupService.findAll();
    }

    @GetMapping("/notification-template-type/list")
    public List<NotificationTemplateTypeLookupDto> listNotificationTemplateTypes() {
        log.debug("list notification template types...");
        return notificationTemplateTypeLookupService.findAll();
    }

    @GetMapping("/user-notification-status/list")
    public List<UserNotificationStatusLookupDto> listUserNotificationStatuses() {
        log.debug("list user notification statuses...");
        return userNotificationStatusLookupService.findAll();
    }

    @GetMapping("/notification-template-status/list")
    public List<NotificationTemplateStatusLookupDto> listNotificationTemplateStatuses() {
        log.debug("list notification template statuses...");
        return notificationTemplateStatusLookupService.findAll();
    }

    @GetMapping("/health-immunization/list")
    public List<HealthImmunizationLookupDto> listImmunization() {
        log.debug("list health immunizations...");
        return healthImmunizationLookupService.findAll();
    }

    @GetMapping("/religious-occasions-day/list")
    public List<ReligiousOccasionsDayLookupDto> listReligiousOccasionsDay() {
        log.debug("list religious occasions day...");
        return religiousOccasionsDayLookupService.findAll();
    }

    @GetMapping("/meal-type/list")
    public List<MealTypeLookupDto> listMealTypes() {
        log.debug("list meal types...");
        return mealTypeLookupService.findAll();
    }

    @GetMapping("/digital-id-status/list")
    public List<ApplicantDigitalIdStatusLookupDto> listDigitalIdStatuses() {
        log.debug("list applicant digital ID statuses...");
        return applicantDigitalIdStatusLookupService.findAll();
    }

    @GetMapping("/language/list")
    public List<LanguageLookupDto> listLanguages() {
        log.debug("list languages...");
        return languageLookupService.findAll();
    }

    @GetMapping("/incident-type/list")
    public List<IncidentTypeLookupDto> listIncidentTypes() {
        log.debug("list incident types...");
        return incidentTypeLookupService.findAll();
    }

    @GetMapping("/incident-status/list")
    public List<IncidentStatusLookupDto> listIncidentStatuses() {
        log.debug("list incident statuses...");
        return incidentStatusLookupService.findAll();
    }

    @GetMapping("/google-maps/api-key")
    public String loadGoogleMapsApiKey() {
        log.debug("load google maps api key...");
        return mapUtils.retrieveGoogleMapsApiKey();
    }

    @GetMapping("/company-names/list")
    public List<CompanyLiteDto> listCompanyNames() {
        log.debug("list company names...");
        return companyLiteService.findAll();
    }

    @GetMapping("/ritual-seasons/list")
    public List<RitualSeasonDto> listRitualSeasons() {
        log.debug("list ritual seasons...");
        return ritualSeasonService.findAll();
    }

    @GetMapping("/ritual-seasons-years/list")
    public List<Integer> listRitualSeasonYears() {
        log.debug("list ritual season years...");
        return ritualSeasonService.listRitualSeasonYears();
    }

    @GetMapping("/staff-print-batch-type/list")
    public List<PrintBatchTypeLookupDto> listStaffPrintRequestBatchTypes(Authentication authentication) {
        log.debug("list print batch types...");
        return printBatchTypeLookupService.findBatchTypeByTarget(EPrintingRequestTarget.STAFF.name());
    }

    @GetMapping("/dashboard/refresh-interval")
    public Integer loadDashboardRefreshInterval() {
        log.debug("load dashboard refresh interval...");
        return dashboardService.getRefreshInterval();
    }

    @GetMapping("/area_layers/list")
    public List<AreaLayerLookupDto> listAreaLayers() {
        log.debug("list religious occasions day...");
        return areaLayerLookupService.findAll();
    }

}
