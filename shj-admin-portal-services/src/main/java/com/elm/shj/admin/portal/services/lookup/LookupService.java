package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.services.company.CompanyLiteService;
import com.elm.shj.admin.portal.services.company.CompanyService;
import com.elm.shj.admin.portal.services.dashboard.DashboardService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.ritual.RitualSeasonService;
import com.elm.shj.admin.portal.services.utils.MapUtils;
import com.elm.shj.admin.portal.services.zone.AreaLayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;
/**
 * Service handling lookups, data will be loaded at the application startup and will be refreshed at the scheduled time
 *
 * @author omar adel
 * @since 1.0.0
 */

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class LookupService {

    private final AuthorityLookupService authorityLookupService;
    private final RitualTypeLookupService ritualTypeLookupService;
    private final CardStatusLookupService cardStatusLookupService;
    private final RelativeRelationshipLookupService relativeRelationshipLookupService;
    private final MaritalStatusLookupService maritalStatusLookupService;
    private final NationalityLookupService nationalityLookupService;
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
    private final CompanyService companyService;
    private final RitualSeasonService ritualSeasonService;
    private final DashboardService dashboardService;
    private final AreaLayerLookupService areaLayerLookupService;
    private final AreaLayerService areaLayerService;


    private List<AuthorityLookupDto> authorities;
    private List<RitualTypeLookupDto> ritualTypes;
    private List<CardStatusLookupDto> cardStatuses;
    private List<RelativeRelationshipLookupDto> relativeRelationships;
    private List<MaritalStatusLookupDto> maritalStatuses;
    private List<NationalityLookupDto> countries;
    private List<HealthSpecialNeedsTypeLookupDto> healthSpecialNeeds;
    private List<PrintRequestStatusLookupDto> printRequestStatuses;
    private List<PrintBatchTypeLookupDto> printBatchTypes;
    private List<HousingCategoryLookupDto> housingCategories;
    private List<HousingTypeLookupDto> housingTypes;
    private List<PackageTypeLookupDto> packageTypes;
    private List<CompanyRitualStepLookupDto> ritualSteps;
    private List<CompanyStaffTitleLookupDto> staffTitles;
    private List<HousingSiteLookupDto> housingSites;
    private List<HousingSiteLookupDto> campingSites;
    private List<TransportationTypeLookupDto> transportationTypes;
    private List<NotificationCategoryLookupDto> notificationCategories;
    private List<NotificationTemplateNameLookupDto> notificationNames;
    private List<UserNotificationStatusLookupDto> userNotificationStatuses;
    private List<NotificationTemplateTypeLookupDto> notificationTypes;
    private List<NotificationTemplateStatusLookupDto> notificationStatuses;
    private List<HealthImmunizationLookupDto> healthImmunizations;
    private List<ApplicantDigitalIdStatusLookupDto> applicantDigitalIdStatuses;
    private List<ReligiousOccasionsDayLookupDto> religiousOccasionsDay;
    private List<MealTypeLookupDto> mealTypes;
    private List<LanguageLookupDto> supportedLanguages;
    private List<IncidentTypeLookupDto> incidentTypes;
    private List<IncidentStatusLookupDto> incidentStatus;
    private List<CompanyLiteDto> companyNames;
    private List<RitualSeasonDto> ritualSeasons;
    private List<Integer> ritualSeasonYears;
    private List<PrintBatchTypeLookupDto> StaffPrintBatchTypes;
    private List<AreaLayerDto> areaLayers;
    private List<AreaLayerLookupDto> areaLayersLabel;
    private int seasonYear;
    private int dashboardRefreshInterval;
    private String googleMapKey;


    @PostConstruct
    @Scheduled(cron = "${scheduler.load.lookups.cron}")
    @SchedulerLock(name = "load-admin-lookups-task")
    void loadLookups() {
        log.info("loading lookups...");
        this.authorities = authorityLookupService.findAllParentAuthorities();
        this.ritualTypes = ritualTypeLookupService.findAll();
        this.cardStatuses = cardStatusLookupService.findAll();
        this.relativeRelationships = relativeRelationshipLookupService.findAll();
        this.maritalStatuses = maritalStatusLookupService.findAll();
        this.countries = nationalityLookupService.findAll();
        this.healthSpecialNeeds = healthSpecialNeedsLookupService.findAll();
        this.printRequestStatuses = printRequestStatusLookupService.findAll();
        this.printBatchTypes = printBatchTypeLookupService.findBatchTypeByTarget(EPrintingRequestTarget.APPLICANT.name());
        this.ritualSteps = companyRitualStepLookupService.findAll();
        this.staffTitles = companyStaffTitleLookupService.findAll();
        this.housingCategories = housingCategoryLookupService.findAll();
        this.housingTypes = housingTypeLookupService.findAll();
        this.packageTypes = packageTypeLookupService.findAll();
        this.housingSites = housingSiteLookupService.findAll();
        this.campingSites = housingSiteLookupService.findCampSites();
        this.transportationTypes = transportationTypeLookupService.findAll();
        this.notificationCategories = notificationCategoryLookupService.findAll();
        this.notificationNames = notificationTemplateNameLookupService.findAll();
        this.notificationTypes = notificationTemplateTypeLookupService.findAll();
        this.userNotificationStatuses = userNotificationStatusLookupService.findAll();
        this.notificationStatuses = notificationTemplateStatusLookupService.findAll();
        this.healthImmunizations = healthImmunizationLookupService.findAll();
        this.religiousOccasionsDay = religiousOccasionsDayLookupService.findAll();
        this.mealTypes = mealTypeLookupService.findAll();
        this.applicantDigitalIdStatuses = applicantDigitalIdStatusLookupService.findAll();
        this.supportedLanguages = languageLookupService.findAll();
        this.incidentTypes = incidentTypeLookupService.findAll();
        this.incidentStatus = incidentStatusLookupService.findAll();
        this.companyNames = companyLiteService.findAll();
        this.ritualSeasons = ritualSeasonService.findAll();
        this.ritualSeasonYears = ritualSeasonService.listRitualSeasonYears();
        this.StaffPrintBatchTypes = printBatchTypeLookupService.findBatchTypeByTarget(EPrintingRequestTarget.STAFF.name());
        this.areaLayers = areaLayerService.findAll();
        this.dashboardRefreshInterval = dashboardService.getRefreshInterval();
        this.areaLayersLabel = areaLayerLookupService.findAll();
        this.googleMapKey = mapUtils.retrieveGoogleMapsApiKey();
    }


    public List<AuthorityLookupDto> listParentAuthorities() { return this.authorities; }
    public List<RitualTypeLookupDto> listRitualTypes() { return this.ritualTypes; }
    public List<CardStatusLookupDto> listCardStatuses() { return this.cardStatuses; }
    public List<RelativeRelationshipLookupDto> listRelativeRelationships() { return this.relativeRelationships; }
    public List<MaritalStatusLookupDto> listMaritalStatuses() { return this.maritalStatuses; }
    public List<NationalityLookupDto> listCountries() { return this.countries; }
    public List<HealthSpecialNeedsTypeLookupDto> listHealthSpecialNeeds() { return this.healthSpecialNeeds; }
    public List<PrintRequestStatusLookupDto> listPrintRequestStatuses() { return this.printRequestStatuses; }
    public List<PrintBatchTypeLookupDto> listPrintRequestBatchTypes() { return this.printBatchTypes; }
    public List<CompanyRitualStepLookupDto> listCompanyRitualStepsLabel() { return this.ritualSteps; }
    public List<CompanyStaffTitleLookupDto> listCompanyStaffTitles() { return this.staffTitles; }
    public List<HousingCategoryLookupDto> listHousingCategories() { return housingCategories; }
    public List<HousingTypeLookupDto> listHousingTypes() { return this.housingTypes; }
    public List<PackageTypeLookupDto> listPackageTypes() { return this.packageTypes; }
    public List<HousingSiteLookupDto> listHousingSites() { return this.housingSites; }
    public List<HousingSiteLookupDto> listCampSites() {return this.campingSites; }
    public List<TransportationTypeLookupDto> listTransportationTypes() { return this.transportationTypes; }
    public List<NotificationCategoryLookupDto> listNotificationCategories() { return this.notificationCategories; }
    public List<NotificationTemplateNameLookupDto> listNotificationTemplateNames() { return this.notificationNames; }
    public List<NotificationTemplateTypeLookupDto> listNotificationTemplateTypes() {return this.notificationTypes; }
    public List<UserNotificationStatusLookupDto> listUserNotificationStatuses() { return this.userNotificationStatuses; }
    public List<NotificationTemplateStatusLookupDto> listNotificationTemplateStatuses() { return this.notificationStatuses; }
    public List<HealthImmunizationLookupDto> listImmunization() { return healthImmunizations; }
    public List<ReligiousOccasionsDayLookupDto> listReligiousOccasionsDay() { return religiousOccasionsDay; }
    public List<MealTypeLookupDto> listMealTypes() { return mealTypes;}
    public List<ApplicantDigitalIdStatusLookupDto> listDigitalIdStatuses() { return applicantDigitalIdStatuses; }
    public List<LanguageLookupDto> listLanguages() { return supportedLanguages;}
    public List<IncidentTypeLookupDto> listIncidentTypes() { return incidentTypes; }
    public List<IncidentStatusLookupDto> listIncidentStatuses() { return incidentStatus; }
    public String loadGoogleMapsApiKey() { return this.googleMapKey; }
    public List<CompanyLiteDto> listCompanyNameBySeasonYear(int seasonYear) { return companyService.findCompaniesBySeason(seasonYear);}
    public List<CompanyLiteDto> listCompanyNames() { return this.companyNames; }
    public List<RitualSeasonDto> listRitualSeasons() { return ritualSeasons; }
    public List<Integer> listRitualSeasonYears() {return ritualSeasonYears; }
    public List<PrintBatchTypeLookupDto> listStaffPrintRequestBatchTypes() { return StaffPrintBatchTypes; }
    public Integer loadDashboardRefreshInterval() { return this.dashboardRefreshInterval; }
    public List<AreaLayerDto> listAreaLayers() { return this.areaLayers; }
    public List<AreaLayerLookupDto> AreaLayersLabel() {return this.areaLayersLabel; }
}
