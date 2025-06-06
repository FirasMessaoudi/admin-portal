/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dashboard;

import com.elm.shj.admin.portal.orm.entity.*;
import com.elm.shj.admin.portal.orm.repository.*;
import com.elm.shj.admin.portal.services.dto.ECampSite;
import com.elm.shj.admin.portal.services.dto.EGender;
import com.elm.shj.admin.portal.services.dto.ERitualType;
import com.elm.shj.admin.portal.services.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service handling dashboard related operations
 *
 * @author ahmad flaifel
 * @since 1.4.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DashboardService {

    private static int DAYS_RANGE = 7;

    @Value("${applicants.counter.ages.range}")
    private String agesRange;

    @Value("${dashboard.incident.company.chart.size}")
    private int maxCompanyChartSize;

    @Value("${dashboard.refresh-interval}")
    private int refreshInterval;

    @Value("${dashboard.mobile.registered.applicant.by.company.size}")
    private int maxApplicantRegistereByCompanySize;

    @Value("${dashboard.mobile.age.range}")
    private String mobileUsersAgeRange;

    List<String> hajjRituals = List.of(ERitualType.INTERNAL_HAJJ.name(), ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name());

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ApplicantRepository applicantRepository;
    private final ApplicantIncidentRepository applicantIncidentRepository;
    private final CompanyRepository companyRepository;
    private final PackageHousingRepository packageHousingRepository;
    private final MobileAuditLogRepository mobileAuditLogRepository;
    private final CameraRepository cameraRepository;

    public DashboardVo loadDashboardData() {
        log.info("Start loading dashboard data");
        long totalNumberOfUsers = userRepository.countDistinctByDeletedFalse();

        long totalNumberOfActiveUsers = userRepository.countDistinctByDeletedFalseAndActivated(true);
        long totalNumberOfInactiveUsers = userRepository.countDistinctByDeletedFalseAndActivated(false);
        long totalNumberOfCitizenActiveUsers = userRepository.countDistinctByDeletedFalseAndActivatedTrueAndNinIsLike("1%");
        long totalNumberOfResidentActiveUsers = userRepository.countDistinctByDeletedFalseAndActivatedTrueAndNinIsLike("2%");
        long totalNumberOfDeletedUsers = userRepository.countDistinctByDeletedTrue();

        List<CountVo> usersByParentAuthorityCountVoList = userRepository.countUsersByParentAuthority();

        long totalNumberOfRoles = roleRepository.countDistinctByDeletedFalse();
        long totalNumberOfActiveRoles = roleRepository.countDistinctByDeletedFalseAndActivated(true);
        long totalNumberOfInactiveRoles = roleRepository.countDistinctByDeletedFalseAndActivated(false);

        DashboardVo hourlyDashboard = loadDailyDashboardData();
        log.info("Finish loading dashboard data");

        return DashboardVo.builder()
                // users
                .totalNumberOfUsers(totalNumberOfUsers)
                // active users
                .totalNumberOfCitizenActiveUsers(totalNumberOfCitizenActiveUsers)
                .totalNumberOfResidentActiveUsers(totalNumberOfResidentActiveUsers)
                // total users by status
                .totalNumberOfActiveUsers(totalNumberOfActiveUsers)
                .totalNumberOfInactiveUsers(totalNumberOfInactiveUsers)
                .totalNumberOfDeletedUsers(totalNumberOfDeletedUsers)
                // users by authority
                .usersByParentAuthorityCountVoList(usersByParentAuthorityCountVoList)
                // roles
                .totalNumberOfRoles(totalNumberOfRoles)
                .totalNumberOfActiveRoles(totalNumberOfActiveRoles)
                .totalNumberOfInactiveRoles(totalNumberOfInactiveRoles)
                // period counts
                .createdUsersCountVoList(hourlyDashboard.getCreatedUsersCountVoList())
                .activeUsersCountVoList(hourlyDashboard.getActiveUsersCountVoList())
                .inactiveUsersCountVoList(hourlyDashboard.getInactiveUsersCountVoList())
                .deletedUsersCountVoList(hourlyDashboard.getDeletedUsersCountVoList())
                .build();
    }

    /**
     * Load hourly dashboard permits counts starting from 00:00 time.
     *
     * @return
     */
    public DashboardVo loadDailyDashboardData() {
        log.info("Start loadDailyDashboardData");
        Date startOfDayDate = Date.from(Instant.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault())));

        List<CountVo> createdUsersCountVoList = userRepository.countHourlyCreatedUsers(startOfDayDate);
        List<CountVo> activeUsersCountVoList = userRepository.countHourlyActiveInactiveUsers(startOfDayDate, true);
        List<CountVo> inactiveUsersCountVoList = userRepository.countHourlyActiveInactiveUsers(startOfDayDate, false);
        List<CountVo> deletedUsersCountVoList = userRepository.countHourlyDeletedUsers(startOfDayDate);
        log.info("Finish loadDailyDashboardData");

        return DashboardVo.builder()
                .createdUsersCountVoList(createdUsersCountVoList)
                .activeUsersCountVoList(activeUsersCountVoList)
                .inactiveUsersCountVoList(inactiveUsersCountVoList)
                .deletedUsersCountVoList(deletedUsersCountVoList)
                .build();
    }

    /**
     * Load week day dashboard permits counts starting from the beginning of this week (Sunday) till today.
     *
     * @return
     */
    public DashboardVo loadWeeklyDashboardData() {
        log.info("Start loadWeeklyDashboardData");

        Date startOfWeekDate = Date.from(Instant.from(LocalDate.now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
                .atStartOfDay(ZoneId.systemDefault())));

        List<CountVo> createdUsersCountVoList = userRepository.countWeekDayCreatedUsers(startOfWeekDate);
        List<CountVo> activeUsersCountVoList = userRepository.countWeekDayActiveInactiveUsers(startOfWeekDate, true);
        List<CountVo> inactiveUsersCountVoList = userRepository.countWeekDayActiveInactiveUsers(startOfWeekDate, false);
        List<CountVo> deletedUsersCountVoList = userRepository.countWeekDayDeletedUsers(startOfWeekDate);
        log.info("Finish loadWeeklyDashboardData");

        return DashboardVo.builder()
                .createdUsersCountVoList(createdUsersCountVoList)
                .activeUsersCountVoList(activeUsersCountVoList)
                .inactiveUsersCountVoList(inactiveUsersCountVoList)
                .deletedUsersCountVoList(deletedUsersCountVoList)
                .build();
    }

    /**
     * Load daily dashboard permits counts starting from the beginning of this month till today.
     *
     * @return
     */
    public DashboardVo loadMonthlyDashboardData() {
        log.info("Start loadMonthlyDashboardData");

        Date startOfMonthDate = Date.from(Instant.from(LocalDate.now().
                with(TemporalAdjusters.firstDayOfMonth()).
                atStartOfDay(ZoneId.systemDefault())));

        List<CountVo> createdUsersCountVoList = userRepository.countMonthDayCreatedUsers(startOfMonthDate);
        List<CountVo> activeUsersCountVoList = userRepository.countMonthDayActiveInactiveUsers(startOfMonthDate, true);
        List<CountVo> inactiveUsersCountVoList = userRepository.countMonthDayActiveInactiveUsers(startOfMonthDate, false);
        List<CountVo> deletedUsersCountVoList = userRepository.countMonthDayDeletedUsers(startOfMonthDate);
        log.info("Finish loadMonthlyDashboardData");

        return DashboardVo.builder()
                .createdUsersCountVoList(createdUsersCountVoList)
                .activeUsersCountVoList(activeUsersCountVoList)
                .inactiveUsersCountVoList(inactiveUsersCountVoList)
                .deletedUsersCountVoList(deletedUsersCountVoList)
                .build();
    }

    /**
     * Load dashboard general numbers by hijri season.
     *
     * @return
     */
    public DashboardGeneralNumbersVo loadDashboardGeneralNumbersByHijriSeason(int hijriSeason) {
        log.info("Start loadMonthlyDashboardData with hijriSeason: {}", hijriSeason);
        long totalNumberOfApplicants = applicantRepository.countAllApplicantsByHijriSeason(hijriSeason);
        long totalNumberOfMaleApplicants = applicantRepository.countAllApplicantsByGenderByHijriSeason(EGender.MALE.getCode(), hijriSeason);
        long totalNumberOfFemaleApplicants = applicantRepository.countAllApplicantsByGenderByHijriSeason(EGender.FEMALE.getCode(), hijriSeason);

        long totalNumberOfInternalApplicants = applicantRepository
                .countAllApplicantBySeasonAndRitualType(hijriSeason, new ArrayList<>(Arrays.asList(ERitualType.INTERNAL_HAJJ.name())));
        long totalNumberOfExternalApplicants = applicantRepository
                .countAllApplicantBySeasonAndRitualType(hijriSeason, new ArrayList<>(Arrays.asList(ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name())));

        long totalNumberOfUsersInstalledApp = applicantRepository.countAllByMobileLoggedInIsNotNull(hijriSeason, hajjRituals);
        long totalNumberOfLoggedInUsersFromMobile = applicantRepository.countAllByMobileLoggedInTrue(hijriSeason, hajjRituals);
        long totalNumberOfLoggedOutUsersFromMobile = totalNumberOfUsersInstalledApp - totalNumberOfLoggedInUsersFromMobile;
        log.info("Finish loadMonthlyDashboardData with hijriSeason: {}", hijriSeason);

        return DashboardGeneralNumbersVo.builder()
                .totalNumberOfApplicants(totalNumberOfApplicants)
                .totalNumberOfMaleApplicants(totalNumberOfMaleApplicants)
                .totalNumberOfFemaleApplicants(totalNumberOfFemaleApplicants)
                .totalNumberOfInternalApplicants(totalNumberOfInternalApplicants)
                .totalNumberOfExternalApplicants(totalNumberOfExternalApplicants)
                .totalNumberOfMobileAppDownloads(totalNumberOfUsersInstalledApp)
                .totalNumberOfLoggedInUsers(totalNumberOfLoggedInUsersFromMobile)
                .totalNumberOfLoggedOutUsers(totalNumberOfLoggedOutUsersFromMobile)

                .build();
    }

    /**
     * Load dashboard cameras numbers by hijri season.
     *
     * @return
     */
    public DashboardCameraNumbersVo loadDashboardCamerasNumbers(int seasonYear) {
        log.info("Start loadDashboardCamerasNumbers with seasonYear: {}", seasonYear);

        //cameras related data
        long totalNumberOfActiveCameras = cameraRepository.countCameraByStatusAndHijriYear("active", seasonYear);
        long totalNumberOfInactiveCameras = cameraRepository.countCameraByStatusAndHijriYear("inactive", seasonYear);

        log.info("Finish loadDashboardCamerasNumbers with seasonYear: {}", seasonYear);
        return DashboardCameraNumbersVo.builder()
                .totalNumberOfActiveCameras(totalNumberOfActiveCameras)
                .totalNumberOfInactiveCameras(totalNumberOfInactiveCameras)
                .build();
    }

    public DashboardIncidentNumbersVo loadDashboardIncidentNumbers(int seasonYear) {
        log.info("Start loadDashboardIncidentNumbers with seasonYear: {}", seasonYear);

        List<JpaApplicantIncident> allApplicantIncident = applicantIncidentRepository.findAllByCurrentSeason(seasonYear);
        long totalNumberOfResolvedIncidents = applicantIncidentRepository.countAllResolvedIncidents(seasonYear, hajjRituals);
        long totalNumberOfUnResolvedIncidents = applicantIncidentRepository.countAllUnResolvedIncidents(seasonYear, hajjRituals);
        long totalNumberOfRegisteredIncidents = totalNumberOfResolvedIncidents + totalNumberOfUnResolvedIncidents;

        Date mostIncidentDate = new Date();
        Map<Long, Long> mostIncidentDateCount = allApplicantIncident.stream().collect(Collectors.groupingBy(d -> DateUtils.toHijri(d.getCreationDate()), Collectors.counting()));
        if (!mostIncidentDateCount.isEmpty()) {
            if (mostIncidentDateCount.entrySet().iterator().hasNext() && null != mostIncidentDateCount.entrySet().iterator()) {
                Map.Entry<Long, Long> dateEntry = mostIncidentDateCount.entrySet().iterator().next();
                mostIncidentDate = DateUtils.toGregorian(dateEntry.getKey());
            }
        }

        String mostIncidentsArea = "";
        Map<String, Long> incidentByArea = allApplicantIncident.stream().collect(Collectors.groupingBy(incident -> getAreaCode(incident.getAreaCode()), Collectors.counting()));
        if (!incidentByArea.isEmpty()) {
            if (incidentByArea.entrySet().iterator().hasNext()) {

                incidentByArea = incidentByArea.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
                mostIncidentsArea = incidentByArea.entrySet().iterator().next().getKey();
            }
        }
        Map<String, Long> incidentByType = allApplicantIncident.stream().collect(Collectors.groupingBy(JpaApplicantIncident::getTypeCode, Collectors.counting()));
        List<CountVo> countVoList = new ArrayList<CountVo>();
        for (Map.Entry<String, Long> entry : incidentByType.entrySet()) {
            CountVo countVo = new CountVo();
            countVo.setLabel(entry.getKey());
            countVo.setCount(entry.getValue());
            countVo.setPercentage(String.format("%.2f", (double) entry.getValue() / totalNumberOfRegisteredIncidents * 100));
            countVoList.add(countVo);
        }
        log.info("Finish loadDashboardIncidentNumbers with seasonYear: {}", seasonYear);
        return DashboardIncidentNumbersVo.builder()
                .totalNumberOfRegisteredIncidents(totalNumberOfRegisteredIncidents)
                .totalNumberOfResolvedIncidents(totalNumberOfResolvedIncidents)
                .totalNumberOfUnResolvedIncidents(totalNumberOfUnResolvedIncidents)
                .countIncidentByTypes(countVoList)
                .mostIncidentDate(mostIncidentDate)
                .mostIncidentsArea(mostIncidentsArea)
                .build();
    }

    private String getAreaCode(String areaCode){
        if (areaCode == null)
            return "";
        else
            return areaCode;
    }

    private Date getDateFromAge(Integer age) {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -age);
        return c.getTime();
    }

    public List<CountVo> pilgrimsCountListsByAgesRange(int hijriYear) {
        log.info("Start pilgrimsCountListsByAgesRange with hijriYear: {}", hijriYear);
        List<CountVo> countVoList = new ArrayList<>();
        String[] arrOfRanges = this.agesRange.split(",");
        long totalApplicants = applicantRepository.countTotalApplicantsFromCurrentSeason(hijriYear, hajjRituals);
        for (String range : arrOfRanges) {
            CountVo countVo = new CountVo();
            String[] ages = range.split("-");
            Date from = new Timestamp(getDateFromAge(Integer.valueOf(ages[0])).getTime());
            Date to = new Timestamp(getDateFromAge(Integer.valueOf(ages[1])).getTime());
            long applicantsNumber = applicantRepository
                    .countPilgrimsFromCurrentSeasonByAgeRange(from, to, hijriYear, hajjRituals);
            countVo.setLabel(range);
            countVo.setCount(applicantsNumber);
            countVo.setPercentage(String.format("%.2f", (double) applicantsNumber / totalApplicants * 100));
            countVoList.add(countVo);
        }
        log.info("Finish pilgrimsCountListsByAgesRange with hijriYear: {}", hijriYear);
        return countVoList;
    }

    public List<CountVo> listCountApplicantsByNationalities(int seasonYear) {
        log.info("Start listCountApplicantsByNationalities with seasonYear: {}", seasonYear);
        List<CountVo> applicantsByNationality = applicantRepository.countApplicantsByNationality(seasonYear);
        applicantsByNationality.sort(Comparator.comparing(CountVo::getCount).reversed());
        long totalApplicants = applicantRepository.countTotalApplicantsFromCurrentSeason(seasonYear, hajjRituals);
        List<CountVo> countVoList = new ArrayList<>();
        for(CountVo countVo: applicantsByNationality){
            countVo.setPercentage(String.format("%.2f", (double) countVo.getCount() / totalApplicants * 100));
            countVoList.add(countVo);
            if(countVoList.size()>4)
                break;
        }
        log.info("Finish listCountApplicantsByNationalities with seasonYear: {}", seasonYear);
        return countVoList;
    }

    public List<LocalizedCountVo> loadCompaniesWithMaxApplicantsCountByHijriSeason(int currentHijriYear) {
        log.info("Start loadCompaniesWithMaxApplicantsCountByHijriSeason with currentHijriYear: {}", currentHijriYear);
        List<LocalizedCountVo> localizedCountVoList = companyRepository.findCompaniesWithMaxApplicantsByHijriSeason(currentHijriYear, hajjRituals, PageRequest.of(0, maxCompanyChartSize)).getContent();
        log.info("Finish loadCompaniesWithMaxApplicantsCountByHijriSeason with currentHijriYear: {}", currentHijriYear);
        return localizedCountVoList;
    }

    public List<LocalizedCountVo> loadCompaniesWithMinApplicantsCountByHijriSeason(int currentHijriYear) {
        log.info("Start loadCompaniesWithMinApplicantsCountByHijriSeason with currentHijriYear: {}", currentHijriYear);
        List<LocalizedCountVo> localizedCountVoList = companyRepository.findCompaniesWithMinApplicantsByHijriSeason(currentHijriYear, hajjRituals, PageRequest.of(0, maxCompanyChartSize)).getContent();
        log.info("Finish loadCompaniesWithMinApplicantsCountByHijriSeason with currentHijriYear: {}", currentHijriYear);
        return localizedCountVoList;
    }

    public List<LocalizedCountVo> loadCampsWithMaxApplicantsCountByHijriSeason(int currentHijriYear, ECampSite siteCode) {
        log.info("Start loadCampsWithMaxApplicantsCountByHijriSeason with currentHijriYear: {}", currentHijriYear);
        List<LocalizedCountVo> localizedCountVoList = packageHousingRepository.findCampsWithMaxApplicantsByHijriSeason(
                currentHijriYear, hajjRituals, siteCode.name(), PageRequest.of(0, maxCompanyChartSize)).getContent();
        log.info("Finish loadCampsWithMaxApplicantsCountByHijriSeason with currentHijriYear: {}", currentHijriYear);
        return localizedCountVoList;
    }

    public List<LocalizedCountVo> loadCampsWithMinApplicantsCountByHijriSeason(int currentHijriYear, ECampSite siteCode) {
        log.info("Start loadCampsWithMinApplicantsCountByHijriSeason with currentHijriYear: {}", currentHijriYear);
        List<LocalizedCountVo> localizedCountVoList = packageHousingRepository.findCampsWithMinApplicantsByHijriSeason(
                currentHijriYear, hajjRituals, siteCode.name(), PageRequest.of(0, maxCompanyChartSize)).getContent();
        log.info("Finish loadCampsWithMinApplicantsCountByHijriSeason with currentHijriYear: {}", currentHijriYear);
        return localizedCountVoList;
    }

    public List<LocationVo> getIncidentsLocationsFromCurrentSeason(int hijriYear) {
        log.info("Start loadCampsWithMinApplicantsCountByHijriSeason with hijriYear: {}", hijriYear);
        List<LocationVo> locationVoList =  applicantIncidentRepository.getIncidentsLocationsBySeasonAndRitualType(hijriYear, hajjRituals).stream().filter(c -> c.getLat() != null && c.getLng() != null).collect(Collectors.toList());
        log.info("Finish loadCampsWithMinApplicantsCountByHijriSeason with hijriYear: {}", hijriYear);
        return locationVoList;
    }

    public List<LocalizedCountVo> loadCompaniesWithMaxIncidentsCount(int seasonYear) {
        log.info("Start loadCompaniesWithMaxIncidentsCount with seasonYear: {}", seasonYear);
        List<LocalizedCountVo> localizedCountVoList =  applicantIncidentRepository.findCompaniesWithMaxIncidents(seasonYear, hajjRituals, PageRequest.of(0, maxCompanyChartSize)).getContent();
        log.info("Finish loadCompaniesWithMaxIncidentsCount with seasonYear: {}", seasonYear);
        return localizedCountVoList;
    }

    public List<LocalizedCountVo> loadCompaniesWithMinIncidentsCount(int seasonYear) {
        log.info("Start loadCompaniesWithMinIncidentsCount with seasonYear: {}", seasonYear);
        List<LocalizedCountVo> localizedCountVoList = applicantIncidentRepository.findCompaniesWithMinIncidents(seasonYear, hajjRituals, PageRequest.of(0, maxCompanyChartSize)).getContent();
        log.info("Finish loadCompaniesWithMinIncidentsCount with seasonYear: {}", seasonYear);
        return localizedCountVoList;
    }

    public DashboardMobileNumbersVo getMobileAppDownloadsFromCurrentSeason(int seasonYear) {
        log.info("Start getMobileAppDownloadsFromCurrentSeason with seasonYear: {}", seasonYear);
        long totalNumberOfMobileAppDownloads = applicantRepository.countAllByMobileLoggedInIsNotNull(seasonYear, hajjRituals);
        long totalNumberOfLoggedInUsersFromMobile = applicantRepository.countAllByMobileLoggedInTrue(seasonYear, hajjRituals);
        long totalNumberOfLoggedOutUsersFromMobile = totalNumberOfMobileAppDownloads - totalNumberOfLoggedInUsersFromMobile;
        log.info("Finish getMobileAppDownloadsFromCurrentSeason with seasonYear: {}", seasonYear);
        return DashboardMobileNumbersVo.builder()
                .totalNumberOfMobileAppDownloads(totalNumberOfMobileAppDownloads)
                .totalNumberOfLoggedInUsers(totalNumberOfLoggedInUsersFromMobile)
                .totalNumberOfLoggedOutUsers(totalNumberOfLoggedOutUsersFromMobile)
                .build();
    }

    public int getRefreshInterval() {
        return refreshInterval;
    }

    public List<LocalizedCountVo> loadCompaniesWithMaxApplicantsRegisteredCount(int seasonYear) {
        log.info("Start loadCompaniesWithMaxApplicantsRegisteredCount with seasonYear: {}", seasonYear);
        List<LocalizedCountVo> localizedCountVoList = applicantRepository.loadCompaniesWithMaxApplicantsRegisteredCount(seasonYear, PageRequest.of(0, maxApplicantRegistereByCompanySize)).getContent();
        log.info("Finish loadCompaniesWithMaxApplicantsRegisteredCount with seasonYear: {}", seasonYear);
        return localizedCountVoList;
    }

    public List<LocalizedCountVo> loadCompaniesWithMinApplicantsRegisteredCount(int seasonYear) {
        log.info("Start loadCompaniesWithMinApplicantsRegisteredCount with seasonYear: {}", seasonYear);
        List<LocalizedCountVo> localizedCountVoList = applicantRepository.loadCompaniesWithMinApplicantsRegisteredCount(seasonYear, PageRequest.of(0, maxApplicantRegistereByCompanySize)).getContent();
        log.info("Finish loadCompaniesWithMinApplicantsRegisteredCount with seasonYear: {}", seasonYear);
        return localizedCountVoList;
    }

    public int[] getMobileLoggedInUsers(int seasonYear) {
        log.info("Start getMobileLoggedInUsers with seasonYear: {}", seasonYear);
        int[] loggedInUsers = new int[DAYS_RANGE];
        LocalDate now = LocalDate.now();
        Date startDate = Date.from(Instant.from(now.minusDays(DAYS_RANGE - 1).atStartOfDay(ZoneId.systemDefault())));
        List<CountVo> groupedLoggedInUsers = mobileAuditLogRepository.getMobileLoggedInUsers(seasonYear, hajjRituals, startDate);
        for (int i = 0; i < loggedInUsers.length; i++) {
            int finalI = i;
            groupedLoggedInUsers.stream().filter(item -> item.getLabelNumber() == now.minusDays(finalI).getDayOfMonth()).findAny()
                    .ifPresent(item -> loggedInUsers[DAYS_RANGE - finalI - 1] = (int) item.getCount());
        }
        log.info("Finish getMobileLoggedInUsers with seasonYear: {}", seasonYear);
        return loggedInUsers;
    }

    public int[] getMobileUsers(int seasonYear) {
        log.info("Start getMobileUsers with seasonYear: {}", seasonYear);
        LocalDate now = LocalDate.now();
        Date startDate = Date.from(Instant.from(now.minusDays(DAYS_RANGE - 1).atStartOfDay(ZoneId.systemDefault())));
        int previousMobileUsers = mobileAuditLogRepository.countPreviousMobileUsers(seasonYear, hajjRituals, startDate);
        int[] mobileUsers = new int[DAYS_RANGE];
        Arrays.fill(mobileUsers, previousMobileUsers);
        List<CountVo> groupedLoggedInUsers = mobileAuditLogRepository.getMobileUsers(seasonYear, hajjRituals, startDate);
        for (int i = 0; i < mobileUsers.length; i++) {
            int finalI = i;
            groupedLoggedInUsers.stream().filter(item -> item.getLabelNumber() == now.minusDays(finalI).getDayOfMonth()).findAny()
                    .ifPresent(item -> mobileUsers[DAYS_RANGE - finalI - 1] += (int) item.getCount());
        }
        int sum = 0;

        for (int i = 0; i < mobileUsers.length; i++) {
            sum += mobileUsers[i];
            mobileUsers[i] += sum;
        }
        log.info("Finish getMobileUsers with seasonYear: {}", seasonYear);
        return mobileUsers;
    }

    public List<ApplicantMobileTrackingVo> findActiveApplicantWithLocationBySeason(int seasonYear) {
        log.info("Start findActiveApplicantWithLocationBySeason with seasonYear: {}", seasonYear);
        List<ApplicantMobileTrackingVo> applicantMobileTrackingVoList = applicantRepository.findActiveApplicantWithLocationBySeason(seasonYear);
        log.info("Finish findActiveApplicantWithLocationBySeason with seasonYear: {}", seasonYear);
        return applicantMobileTrackingVoList;
    }

    public List<CountVo> loadMobileAppUsersCountByAgeRange(int hijriYear) {
        log.info("Start loadMobileAppUsersCountByAgeRange with hijriYear: {}", hijriYear);
        List<CountVo> countVoList = new ArrayList<>();
        Arrays.stream(this.mobileUsersAgeRange.split(",")).forEach(range -> {
            CountVo countVo = new CountVo();
            String[] ages = range.split("-");
            Date from = new Timestamp(getDateFromAge(Integer.valueOf(ages[0])).getTime());
            Date to = new Timestamp(getDateFromAge(Integer.valueOf(ages[1])).getTime());
            long applicantsNumber = applicantRepository.countMobileAppUsersByAgeRange(from, to, hijriYear, hajjRituals);
            countVo.setLabel(range);
            countVo.setCount(applicantsNumber);
            countVoList.add(countVo);
        });
        log.info("Finish loadMobileAppUsersCountByAgeRange with hijriYear: {}", hijriYear);
        return countVoList;
    }



}
