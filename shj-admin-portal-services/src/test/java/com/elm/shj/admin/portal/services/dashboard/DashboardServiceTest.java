/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dashboard;

import com.elm.shj.admin.portal.orm.repository.ApplicantRepository;
import com.elm.shj.admin.portal.orm.repository.RoleRepository;
import com.elm.shj.admin.portal.orm.repository.UserRepository;
import com.elm.shj.admin.portal.services.dto.EGender;
import com.elm.shj.admin.portal.services.dto.ERitualType;
import com.elm.shj.admin.portal.services.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Testing class for service {@link DashboardService}
 *
 * @author Aymen Dhaoui
 * @since 1.8.0
 */
@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

    @InjectMocks
    private DashboardService serviceToTest;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;
    @Mock
    private ApplicantRepository applicantRepository;

    @Test
    void test_load_dashboard_data() {
        serviceToTest.loadDashboardData();
        verify(roleRepository).countDistinctByDeletedFalse();
        verify(roleRepository).countDistinctByDeletedFalseAndActivated(eq(true));
        verify(roleRepository).countDistinctByDeletedFalseAndActivated(eq(false));

        verify(userRepository).countDistinctByDeletedFalse();
        verify(userRepository).countDistinctByDeletedFalseAndActivated(eq(true));
        verify(userRepository).countDistinctByDeletedFalseAndActivated(eq(false));
        verify(userRepository).countDistinctByDeletedFalseAndActivatedTrueAndNinIsLike("1%");
        verify(userRepository).countDistinctByDeletedFalseAndActivatedTrueAndNinIsLike("2%");
        verify(userRepository).countDistinctByDeletedTrue();
        verify(userRepository).countUsersByParentAuthority();
    }

    @Test
    void test_daily_dashboard_data() {
        Date startOfDayDate = Date.from(Instant.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault())));
        serviceToTest.loadDailyDashboardData();
        verify(userRepository).countHourlyCreatedUsers(startOfDayDate);
        verify(userRepository).countHourlyActiveInactiveUsers(startOfDayDate, true);
        verify(userRepository).countHourlyActiveInactiveUsers(startOfDayDate, false);
        verify(userRepository).countHourlyDeletedUsers(startOfDayDate);
    }

    @Test
    void test_weekly_dashboard_data() {
        Date startOfWeekDate = Date.from(Instant.from(LocalDate.now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
                .atStartOfDay(ZoneId.systemDefault())));

        serviceToTest.loadWeeklyDashboardData();
        verify(userRepository).countWeekDayCreatedUsers(startOfWeekDate);
        verify(userRepository).countWeekDayActiveInactiveUsers(startOfWeekDate, true);
        verify(userRepository).countWeekDayActiveInactiveUsers(startOfWeekDate, false);
        verify(userRepository).countWeekDayDeletedUsers(startOfWeekDate);
    }

    @Test
    void test_monthly_dashboard_data() {
        Date startOfMonthDate = Date.from(Instant.from(LocalDate.now().
                with(TemporalAdjusters.firstDayOfMonth()).
                atStartOfDay(ZoneId.systemDefault())));

        serviceToTest.loadMonthlyDashboardData();
        verify(userRepository).countMonthDayCreatedUsers(startOfMonthDate);
        verify(userRepository).countMonthDayActiveInactiveUsers(startOfMonthDate, true);
        verify(userRepository).countMonthDayActiveInactiveUsers(startOfMonthDate, false);
        verify(userRepository).countMonthDayDeletedUsers(startOfMonthDate);
    }


    @Test
    void test_loadDashboardGeneralNumbersByHijriSeason() {
        int hijriSeason = (int) DateUtils.getCurrentHijriYear();
        serviceToTest.loadDashboardGeneralNumbersByHijriSeason(hijriSeason);
        verify(applicantRepository).countAllApplicantsByGenderByHijriSeason(EGender.MALE.getCode(), hijriSeason);
        verify(applicantRepository).countAllApplicantsByGenderByHijriSeason(EGender.FEMALE.getCode(), hijriSeason);
        verify(applicantRepository).countAllApplicantBySeasonAndRitualType(hijriSeason, new ArrayList<>(Arrays.asList(ERitualType.INTERNAL_HAJJ.name())));
        verify(applicantRepository).countAllApplicantBySeasonAndRitualType(hijriSeason, new ArrayList<>(Arrays.asList(ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name())));
    }
}
