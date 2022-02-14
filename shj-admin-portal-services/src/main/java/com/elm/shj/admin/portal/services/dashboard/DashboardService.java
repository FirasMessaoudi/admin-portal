/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dashboard;

import com.elm.shj.admin.portal.orm.entity.CountVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantIncident;
import com.elm.shj.admin.portal.orm.entity.LocationVo;
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

    @Value("${applicants.counter.ages.range}")
    private String agesRange;

    @Value("${dashboard.incident.company.chart.size}")
    private int maxCompanyChartSize;

    @Value("${dashboard.refresh-interval}")
    private int refreshInterval;

    @Value("${dashboard.mobile.registered.applicant.by.company.size}")
    private int maxApplicantRegistereByCompanySize;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ApplicantRepository applicantRepository;
    private final ApplicantIncidentRepository applicantIncidentRepository;
    private final CompanyRepository companyRepository;
    private final PackageHousingRepository packageHousingRepository;

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

        Date startOfDayDate = Date.from(Instant.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault())));

        List<CountVo> createdUsersCountVoList = userRepository.countHourlyCreatedUsers(startOfDayDate);
        List<CountVo> activeUsersCountVoList = userRepository.countHourlyActiveInactiveUsers(startOfDayDate, true);
        List<CountVo> inactiveUsersCountVoList = userRepository.countHourlyActiveInactiveUsers(startOfDayDate, false);
        List<CountVo> deletedUsersCountVoList = userRepository.countHourlyDeletedUsers(startOfDayDate);

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

        Date startOfWeekDate = Date.from(Instant.from(LocalDate.now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
                .atStartOfDay(ZoneId.systemDefault())));

        List<CountVo> createdUsersCountVoList = userRepository.countWeekDayCreatedUsers(startOfWeekDate);
        List<CountVo> activeUsersCountVoList = userRepository.countWeekDayActiveInactiveUsers(startOfWeekDate, true);
        List<CountVo> inactiveUsersCountVoList = userRepository.countWeekDayActiveInactiveUsers(startOfWeekDate, false);
        List<CountVo> deletedUsersCountVoList = userRepository.countWeekDayDeletedUsers(startOfWeekDate);

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
        Date startOfMonthDate = Date.from(Instant.from(LocalDate.now().
                with(TemporalAdjusters.firstDayOfMonth()).
                atStartOfDay(ZoneId.systemDefault())));

        List<CountVo> createdUsersCountVoList = userRepository.countMonthDayCreatedUsers(startOfMonthDate);
        List<CountVo> activeUsersCountVoList = userRepository.countMonthDayActiveInactiveUsers(startOfMonthDate, true);
        List<CountVo> inactiveUsersCountVoList = userRepository.countMonthDayActiveInactiveUsers(startOfMonthDate, false);
        List<CountVo> deletedUsersCountVoList = userRepository.countMonthDayDeletedUsers(startOfMonthDate);

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

        long totalNumberOfApplicants = applicantRepository.countAllApplicantsByHijriSeason(hijriSeason);
        long totalNumberOfMaleApplicants = applicantRepository.countAllApplicantsByGenderByHijriSeason(EGender.MALE.getCode(), hijriSeason);
        long totalNumberOfFemaleApplicants = applicantRepository.countAllApplicantsByGenderByHijriSeason(EGender.FEMALE.getCode(), hijriSeason);

        long totalNumberOfInternalApplicants = applicantRepository
                .countAllApplicantBySeasonAndRitualType(hijriSeason, new ArrayList<>(Arrays.asList(ERitualType.INTERNAL_HAJJ.name())));
        long totalNumberOfExternalApplicants = applicantRepository
                .countAllApplicantBySeasonAndRitualType(hijriSeason, new ArrayList<>(Arrays.asList(ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name())));

        long totalNumberOfUsersInstalledApp = applicantRepository.countAllByMobileLoggedInIsNotNull(hijriSeason, List.of(ERitualType.INTERNAL_HAJJ.name(), ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name()));
        long totalNumberOfLoggedInUsersFromMobile = applicantRepository.countAllByMobileLoggedInTrue(hijriSeason, List.of(ERitualType.INTERNAL_HAJJ.name(), ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name()));
        long totalNumberOfLoggedOutUsersFromMobile = totalNumberOfUsersInstalledApp - totalNumberOfLoggedInUsersFromMobile;

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

    public DashboardIncidentNumbersVo loadDashboardIncidentNumbers(int seasonYear) {
        List<JpaApplicantIncident> allApplicantIncident = applicantIncidentRepository.findAll();
        long totalNumberOfResolvedIncidents = applicantIncidentRepository.countAllResolvedIncidents();
        long totalNumberOfUnResolvedIncidents = applicantIncidentRepository.countAllUnResolvedIncidents();
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
        Map<String, Long> incidentByArea = allApplicantIncident.stream().collect(Collectors.groupingBy(JpaApplicantIncident::getAreaCode, Collectors.counting()));
        if (!incidentByArea.isEmpty()) {
            if (incidentByArea.entrySet().iterator().hasNext()) {
                Map.Entry<String, Long> incidentByAreaMapEntry = incidentByArea.entrySet().iterator().next();
                mostIncidentsArea = incidentByAreaMapEntry.getKey();
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
        return DashboardIncidentNumbersVo.builder()
                .totalNumberOfRegisteredIncidents(totalNumberOfRegisteredIncidents)
                .totalNumberOfResolvedIncidents(totalNumberOfResolvedIncidents)
                .totalNumberOfUnResolvedIncidents(totalNumberOfUnResolvedIncidents)
                .countIncidentByTypes(countVoList)
                .mostIncidentDate(mostIncidentDate)
                .mostIncidentsArea(mostIncidentsArea)
                .build();
    }

    private Date getDateFromAge(Integer age) {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -age);
        return c.getTime();
    }

    public List<CountVo> pilgrimsCountListsByAgesRange(int hijriYear) {
        List<CountVo> countVoList = new ArrayList<>();
        String[] arrOfRanges = this.agesRange.split(",");
        long totalApplicants = applicantRepository.countTotalApplicantsFromCurrentSeason(hijriYear, new ArrayList<>(Arrays.asList(ERitualType.INTERNAL_HAJJ.name(), ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name())));
        for (String range : arrOfRanges) {
            CountVo countVo = new CountVo();
            String[] ages = range.split("-");
            Date from = new Timestamp(getDateFromAge(Integer.valueOf(ages[0])).getTime());
            Date to = new Timestamp(getDateFromAge(Integer.valueOf(ages[1])).getTime());
            long applicantsNumber = applicantRepository
                    .countPilgrimsFromCurrentSeasonByAgeRange(from, to, (int) DateUtils.getCurrentHijriYear(), new ArrayList<>(Arrays.asList(ERitualType.INTERNAL_HAJJ.name(), ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name())));
            countVo.setLabel(range);
            countVo.setCount(applicantsNumber);
            countVo.setPercentage(String.format("%.2f", (double) applicantsNumber / totalApplicants * 100));
            countVoList.add(countVo);
        }
        return countVoList;
    }

    public List<CountVo> listCountApplicantsByNationalities(int seasonYear) {
        List<CountVo> countVoList = new ArrayList<>();
        List<String> nationalities = applicantRepository.findAllNationalities();
        long totalApplicants = applicantRepository.countTotalApplicantsFromCurrentSeason(seasonYear, new ArrayList<>(Arrays.asList(ERitualType.INTERNAL_HAJJ.name(), ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name())));
        for (String nat : nationalities) {
            CountVo countVo = new CountVo();
            long applicantsNumber = applicantRepository.countTotalApplicantsFromCurrentSeasonByNationality(nat, (int) DateUtils.getCurrentHijriYear());
            countVo.setLabel(nat);
            countVo.setCount(applicantsNumber);
            countVo.setPercentage(String.format("%.2f", (double) applicantsNumber / totalApplicants * 100));
            countVoList.add(countVo);
        }
        countVoList.sort(Comparator.comparing(CountVo::getCount).reversed());
        if (countVoList.size() > 5) {
            return countVoList.subList(0, 4);
        }
        return countVoList;
    }

    public List<CountVo> loadCompaniesWithMaxApplicantsCountByHijriSeason(int currentHijriYear) {
        return companyRepository.findCompaniesWithMaxApplicantsByHijriSeason(currentHijriYear, List.of(ERitualType.INTERNAL_HAJJ.name(), ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name()), PageRequest.of(0, maxCompanyChartSize)).getContent();
    }

    public List<CountVo> loadCompaniesWithMinApplicantsCountByHijriSeason(int currentHijriYear) {
        return companyRepository.findCompaniesWithMinApplicantsByHijriSeason(currentHijriYear, List.of(ERitualType.INTERNAL_HAJJ.name(), ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name()), PageRequest.of(0, maxCompanyChartSize)).getContent();
    }

    public List<CountVo> loadCampsWithMaxApplicantsCountByHijriSeason(int currentHijriYear, ECampSite siteCode) {
        return packageHousingRepository.findCampsWithMaxApplicantsByHijriSeason(
                currentHijriYear,
                List.of(ERitualType.INTERNAL_HAJJ.name(), ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name()),
                siteCode.name(),
                PageRequest.of(0, maxCompanyChartSize)).getContent();
    }

    public List<CountVo> loadCampsWithMinApplicantsCountByHijriSeason(int currentHijriYear, ECampSite siteCode) {
        return packageHousingRepository.findCampsWithMinApplicantsByHijriSeason(
                currentHijriYear,
                List.of(ERitualType.INTERNAL_HAJJ.name(), ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name()),
                siteCode.name(),
                PageRequest.of(0, maxCompanyChartSize)).getContent();
    }

    public List<LocationVo> getIncidentsLocationsFromCurrentSeason(int hijriYear) {
        return applicantIncidentRepository.getIncidentsLocationsBySeasonAndRitualType(hijriYear, List.of(ERitualType.INTERNAL_HAJJ.name(), ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name()));
    }

    public List<CountVo> loadCompaniesWithMaxIncidentsCount(int seasonYear) {
        return applicantIncidentRepository.findCompaniesWithMaxIncidents(seasonYear, PageRequest.of(0, maxCompanyChartSize)).getContent();
    }

    public List<CountVo> loadCompaniesWithMinIncidentsCount(int seasonYear) {
        return applicantIncidentRepository.findCompaniesWithMinIncidents(seasonYear, PageRequest.of(0, maxCompanyChartSize)).getContent();
    }

    public DashboardMobileNumbersVo getMobileAppDownloadsFromCurrentSeason(int seasonYear) {
        long totalNumberOfMobileAppDownloads = applicantRepository.countAllByMobileLoggedInIsNotNull(seasonYear, List.of(ERitualType.INTERNAL_HAJJ.name(), ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name()));
        long totalNumberOfLoggedInUsersFromMobile = applicantRepository.countAllByMobileLoggedInTrue(seasonYear, List.of(ERitualType.INTERNAL_HAJJ.name(), ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name()));
        long totalNumberOfLoggedOutUsersFromMobile = totalNumberOfMobileAppDownloads - totalNumberOfLoggedInUsersFromMobile;
        return DashboardMobileNumbersVo.builder()
                .totalNumberOfMobileAppDownloads(totalNumberOfMobileAppDownloads)
                .totalNumberOfLoggedInUsers(totalNumberOfLoggedInUsersFromMobile)
                .totalNumberOfLoggedOutUsers(totalNumberOfLoggedOutUsersFromMobile)
                .build();
    }

    public int getRefreshInterval() {
        return refreshInterval;
    }

    public List<CountVo> loadCompaniesWithMaxApplicantsRegisteredCount(int seasonYear) {
        return applicantRepository.loadCompaniesWithMaxApplicantsRegisteredCount(PageRequest.of(0, maxApplicantRegistereByCompanySize)).getContent();
    }

    public List<CountVo> loadCompaniesWithMinApplicantsRegisteredCount(int seasonYear) {
        return applicantRepository.loadCompaniesWithMinApplicantsRegisteredCount(PageRequest.of(0, maxApplicantRegistereByCompanySize)).getContent();
    }
}
