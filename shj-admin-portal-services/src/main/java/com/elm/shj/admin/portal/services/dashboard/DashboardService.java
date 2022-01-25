/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dashboard;

import com.elm.shj.admin.portal.orm.entity.CountVo;
import com.elm.shj.admin.portal.orm.repository.ApplicantIncidentRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantRepository;
import com.elm.shj.admin.portal.orm.repository.RoleRepository;
import com.elm.shj.admin.portal.orm.repository.UserRepository;
import com.elm.shj.admin.portal.services.dto.EGender;
import com.elm.shj.admin.portal.services.dto.ERitualType;
import com.elm.shj.admin.portal.services.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

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

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ApplicantRepository applicantRepository;
    private final ApplicantIncidentRepository applicantIncidentRepository;

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
                .countAllApplicantBySeasonAndRitualType(hijriSeason, List.of(ERitualType.INTERNAL_HAJJ.name()));
        long totalNumberOfExternalApplicants = applicantRepository
                .countAllApplicantBySeasonAndRitualType(hijriSeason, List.of(ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name()));

        return DashboardGeneralNumbersVo.builder()
                .totalNumberOfApplicants(totalNumberOfApplicants)
                .totalNumberOfMaleApplicants(totalNumberOfMaleApplicants)
                .totalNumberOfFemaleApplicants(totalNumberOfFemaleApplicants)
                .totalNumberOfInternalApplicants(totalNumberOfInternalApplicants)
                .totalNumberOfExternalApplicants(totalNumberOfExternalApplicants)
                .build();
    }

    public DashboardIncidentNumbersVo loadDashboardIncidentNumbers() {
        long totalNumberOfRegisteredIncidents = applicantIncidentRepository.count();
        long totalNumberOfResolvedIncidents = applicantIncidentRepository.countAllResolvedIncidents();
        long totalNumberOfUnResolvedIncidents = applicantIncidentRepository.countAllUnResolvedIncidents();
        List<CountVo> countIncidentByCompany = applicantIncidentRepository.countIncidentByCompany();

        return DashboardIncidentNumbersVo.builder()
                .totalNumberOfRegisteredIncidents(totalNumberOfRegisteredIncidents)
                .totalNumberOfResolvedIncidents(totalNumberOfResolvedIncidents)
                .totalNumberOfUnResolvedIncidents(totalNumberOfUnResolvedIncidents)
                .countIncidentByCompany(countIncidentByCompany)
                .build();
    }

    private Date getDateFromAge(Integer age) {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -age);
        return c.getTime();
    }

    public List<CountVo> pilgrimsCountListsByAgesRange() {
        List<CountVo> countVoList = new ArrayList<CountVo>();
        String[] arrOfRanges = this.agesRange.split(",");
        long totalApplicants = applicantRepository.countTotalApplicantsFromCurrentSeason((int) DateUtils.getCurrentHijriYear(), new ArrayList<>(Arrays.asList(ERitualType.INTERNAL_HAJJ.name(), ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name())));
        for (String range : arrOfRanges) {
            CountVo countVo = new CountVo();
            String[] ages = range.split("-");
            Date from = new Timestamp(getDateFromAge(Integer.valueOf(ages[0])).getTime());
            Date to = new Timestamp(getDateFromAge(Integer.valueOf(ages[1])).getTime());
            long applicantsNumber = applicantRepository
                    .countPilgrimsFromCurrentSeasonByAgeRange(from, to, (int) DateUtils.getCurrentHijriYear(), new ArrayList<>(Arrays.asList(ERitualType.INTERNAL_HAJJ.name(), ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name())));
            countVo.setLabel(range);
            countVo.setCount(applicantsNumber);
            countVo.setPercentage("%" + String.format("%.2f", (double) applicantsNumber / totalApplicants * 100));
            countVoList.add(countVo);
        }
        return countVoList;
    }

}
