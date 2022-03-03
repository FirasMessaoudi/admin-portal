/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.CountVo;
import com.elm.shj.admin.portal.orm.entity.JpaMobileAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Repository for Mobile Audit Log Table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface MobileAuditLogRepository extends JpaRepository<JpaMobileAuditLog, Long> {

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.CountVo('', FUNCTION('DAY',mal.eventDate),COUNT(DISTINCT a),'') " +
            "FROM JpaApplicant a JOIN JpaApplicantDigitalId adi ON adi.applicantId = a.id " +
            "JOIN JpaMobileAuditLog mal ON mal.userId = adi.uin JOIN a.rituals ar JOIN ar.applicantPackage ap " +
            "JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs JOIN crs.ritualSeason rs " +
            "WHERE rs.seasonYear= :seasonYear AND rs.ritualTypeCode IN (:ritualTypeCodeList) AND mal.eventDate >= :currentDate " +
            "AND mal.event = 'login' GROUP BY FUNCTION('DAY', mal.eventDate) ORDER BY FUNCTION('DAY', mal.eventDate)")
    List<CountVo> getMobileLoggedInUsers(@Param("seasonYear") int seasonYear,
                                         @Param("ritualTypeCodeList") List<String> ritualTypeCodeList,
                                         @Param("currentDate") Date currentDate);

    @Query("SELECT COUNT(mal) FROM JpaApplicant a JOIN JpaApplicantDigitalId adi ON adi.applicantId = a.id " +
            "JOIN JpaMobileAuditLog mal ON mal.userId = adi.uin JOIN a.rituals ar JOIN ar.applicantPackage ap " +
            "JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs JOIN crs.ritualSeason rs " +
            "WHERE rs.seasonYear= :seasonYear AND rs.ritualTypeCode IN (:ritualTypeCodeList) AND mal.eventDate < :currentDate " +
            "AND mal.event = 'register'")
    int countPreviousMobileUsers(@Param("seasonYear") int seasonYear,
                                  @Param("ritualTypeCodeList") List<String> ritualTypeCodeList,
                                  @Param("currentDate") Date currentDate);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.CountVo('', FUNCTION('DAY',mal.eventDate),COUNT(DISTINCT a),'') " +
            "FROM JpaApplicant a JOIN JpaApplicantDigitalId adi ON adi.applicantId = a.id " +
            "JOIN JpaMobileAuditLog mal ON mal.userId = adi.uin JOIN a.rituals ar JOIN ar.applicantPackage ap " +
            "JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs JOIN crs.ritualSeason rs " +
            "WHERE rs.seasonYear= :seasonYear AND rs.ritualTypeCode IN (:ritualTypeCodeList) AND mal.eventDate >= :currentDate " +
            "AND mal.event = 'register' GROUP BY FUNCTION('DAY', mal.eventDate) ORDER BY FUNCTION('DAY', mal.eventDate)")
    List<CountVo> getMobileUsers(@Param("seasonYear") int seasonYear,
                                 @Param("ritualTypeCodeList") List<String> ritualTypeCodeList,
                                 @Param("currentDate") Date currentDate);

    @Query("SELECT COUNT(DISTINCT a) FROM JpaApplicant a JOIN JpaApplicantDigitalId adi ON adi.applicantId = a.id " +
            "JOIN JpaMobileAuditLog mal ON mal.userId = adi.uin JOIN a.rituals ar JOIN ar.applicantPackage ap " +
            "JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs JOIN crs.ritualSeason rs " +
            "WHERE rs.seasonYear = :seasonYear AND (a.dateOfBirthGregorian BETWEEN :to AND :from) AND rs.ritualTypeCode IN (:ritualTypeCodeList)")
    long countMobileAppUsersByAgeRange(@Param("from") Date from,
                                       @Param("to") Date to,
                                       @Param("seasonYear") int seasonYear,
                                       @Param("ritualTypeCodeList") List<String> ritualTypeCodeList);
}