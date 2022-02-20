/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantMobileTrackingVo;
import com.elm.shj.admin.portal.orm.entity.CountVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Repository for Applicant Table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public interface ApplicantRepository extends JpaRepository<JpaApplicant, Long>, JpaSpecificationExecutor<JpaApplicant> {

    @Query(value = "select a from JpaApplicant a where " +
            "(a.idNumber = :idNumber and a.dateOfBirthHijri = :dateOfBirthHijri) or " +
            "(a.passportNumber = :passportNumber and a.dateOfBirthGregorian = :dateOfBirthGregorian)")
    JpaApplicant findByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                                 @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian);

    @Query(value = "select a.id from JpaApplicant a where " +
            "(a.idNumber = :idNumber and a.dateOfBirthHijri = :dateOfBirthHijri) or " +
            "(a.passportNumber = :passportNumber and a.dateOfBirthGregorian = :dateOfBirthGregorian)")
    Long findIdByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                                 @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian);

    @Query(value = "SELECT CASE WHEN COUNT(a)> 0 THEN TRUE ELSE FALSE END " +
            "FROM JpaApplicant a WHERE " +
            "(a.idNumber = :idNumber AND a.dateOfBirthHijri = :dateOfBirthHijri) OR " +
            "(a.passportNumber = :passportNumber AND a.dateOfBirthGregorian = :dateOfBirthGregorian)")
    boolean existsByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                              @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian);

    @Query(value = "SELECT a FROM JpaApplicant a JOIN a.digitalIds adi WHERE adi.uin = :uin")
    JpaApplicant findByUin(@Param("uin") String uin);

    @Query("SELECT a FROM JpaApplicant a LEFT JOIN a.rituals ar JOIN ar.applicantPackage ap WHERE (:today >= ap.startDate AND :today <= ap.endDate AND a.registered = TRUE)")
    List<JpaApplicant> findAllApplicantsRegisteredAndHavingActiveRitual(@Param("today") Date today);

    @Query("SELECT COUNT(a) FROM JpaApplicant a LEFT JOIN a.rituals ar JOIN ar.applicantPackage ap WHERE (:today >= ap.startDate AND :today <= ap.endDate AND a.registered = TRUE)")
    long countHavingActiveRitual(@Param("today") Date today);

    //TODO: To be deleted
    @Query("SELECT CASE WHEN COUNT(a)> 0 THEN TRUE ELSE FALSE END FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp WHERE " +
            "((a.idNumber = :idNumber AND a.dateOfBirthHijri = :dateOfBirthHijri) OR " +
            "(a.passportNumber = :passportNumber AND a.dateOfBirthGregorian = :dateOfBirthGregorian)) AND rp.referenceNumber = :packageCode")
    boolean findByBasicInfoAndPackageCode(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                                          @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian, @Param("packageCode") String packageCode);

    @Query("SELECT a FROM JpaApplicant a WHERE a.id IN :selectedApplicants")
    List<JpaApplicant> findAllByIds(@Param("selectedApplicants") List<Long> selectedApplicants);

    @Query("SELECT a FROM JpaApplicant a WHERE a.id IN :selectedApplicants")
    Page<JpaApplicant> findByIds(@Param("selectedApplicants") List<Long> selectedApplicants, Pageable pageable);

    @Modifying
    @Query("UPDATE JpaApplicant a SET a.preferredLanguage = :lang, a.updateDate = CURRENT_TIMESTAMP WHERE a.id = :applicantId")
    void updatePreferredLanguage(@Param("applicantId") long applicantId, @Param("lang") String lang);

    @Modifying
    @Query("UPDATE JpaApplicant a SET a.registered = TRUE, a.updateDate = CURRENT_TIMESTAMP WHERE a.id = :applicantId")
    int markAsRegistered(@Param("applicantId") long applicantId);

    @Query("SELECT COUNT(a) FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :hijriSeason " +
            "AND rs.ritualTypeCode IN ('INTERNAL_HAJJ', 'EXTERNAL_HAJJ', 'COURTESY_HAJJ')")
    long countAllApplicantsByHijriSeason(@Param("hijriSeason") int hijriSeason);

    @Query("SELECT COUNT(a) FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :hijriSeason " +
            "AND rs.ritualTypeCode IN ('INTERNAL_HAJJ', 'EXTERNAL_HAJJ', 'COURTESY_HAJJ') AND a.gender = :gender")
    long countAllApplicantsByGenderByHijriSeason(@Param("gender") String gender, @Param("hijriSeason") int hijriSeason);

    @Query("SELECT COUNT(a) FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :seasonYear " +
            "AND (a.dateOfBirthGregorian BETWEEN :to AND :from) AND rs.ritualTypeCode IN (:ritualTypeCodeList)")
    long countPilgrimsFromCurrentSeasonByAgeRange(@Param("from") Date from, @Param("to") Date to, @Param("seasonYear") int seasonYear, @Param("ritualTypeCodeList") List<String> ritualTypeCodeList);

    @Query("SELECT COUNT(a) FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :seasonYear AND rs.ritualTypeCode IN (:ritualTypeCodeList)")
    long countTotalApplicantsFromCurrentSeason(@Param("seasonYear") int seasonYear, @Param("ritualTypeCodeList") List<String> ritualTypeCodeList);

    @Query("SELECT COUNT(a) FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :seasonYear " +
            "AND rs.ritualTypeCode IN (:ritualTypeCodeList) ")
    long countAllApplicantBySeasonAndRitualType(@Param("seasonYear") int seasonYear, @Param("ritualTypeCodeList") List<String> ritualTypeCodeList);

    @Query("SELECT COUNT(a) FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :seasonYear and rs.ritualTypeCode IN ('INTERNAL_HAJJ', 'EXTERNAL_HAJJ', 'COURTESY_HAJJ') and a.nationalityCode =:nationalityCode")
    long countTotalApplicantsFromCurrentSeasonByNationality(@Param("nationalityCode") String nationalityCode, @Param("seasonYear") int seasonYear);

    @Query("select distinct (a.nationalityCode) from JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs where rs.ritualTypeCode IN ('INTERNAL_HAJJ', 'EXTERNAL_HAJJ', 'COURTESY_HAJJ')")
    List<String> findAllNationalities();

    @Query("SELECT COUNT(a) FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :seasonYear AND rs.ritualTypeCode IN (:ritualTypeCodeList) AND a.mobileLoggedIn IS NOT NULL")
    long countAllByMobileLoggedInIsNotNull(@Param("seasonYear") int seasonYear, @Param("ritualTypeCodeList") List<String> ritualTypeCodeList);

    @Query("SELECT COUNT(a) FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :seasonYear AND rs.ritualTypeCode IN (:ritualTypeCodeList) AND a.mobileLoggedIn = TRUE")
    long countAllByMobileLoggedInTrue(@Param("seasonYear") int seasonYear, @Param("ritualTypeCodeList") List<String> ritualTypeCodeList);

    @Modifying
    @Query("UPDATE JpaApplicant a SET a.mobileLoggedIn = :mobileLoggedIn, a.updateDate = CURRENT_TIMESTAMP WHERE a.id = :applicantId")
    void updateLoggedInFromMobileAppFlag(@Param("applicantId") long applicantId, @Param("mobileLoggedIn") boolean mobileLoggedIn);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.CountVo(c.labelAr, 0, COUNT(a),'') " +
            "FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp " +
            "JOIN rp.companyRitualSeason crs JOIN crs.company c JOIN crs.ritualSeason rs " +
            "WHERE  rs.ritualTypeCode IN ('INTERNAL_HAJJ', 'EXTERNAL_HAJJ', 'COURTESY_HAJJ') AND " +
            "c.labelAr is NOT NULL AND a.mobileLoggedIn IS NOT NULL AND rs.seasonYear= :seasonYear GROUP BY c.labelAr ORDER BY COUNT(c.labelAr) DESC")
    Page<CountVo> loadCompaniesWithMaxApplicantsRegisteredCount(@Param("seasonYear") int seasonYear, Pageable pageable);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.CountVo(c.labelAr, 0, COUNT(a),'') " +
            "FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp " +
            "JOIN rp.companyRitualSeason crs JOIN crs.company c JOIN crs.ritualSeason rs " +
            "WHERE rs.ritualTypeCode IN ('INTERNAL_HAJJ', 'EXTERNAL_HAJJ', 'COURTESY_HAJJ') AND " +
            "c.labelAr is NOT NULL AND a.mobileLoggedIn IS NOT NULL AND rs.seasonYear= :seasonYear GROUP BY c.labelAr ORDER BY COUNT(c.labelAr)")
    Page<CountVo> loadCompaniesWithMinApplicantsRegisteredCount(@Param("seasonYear") int seasonYear, Pageable pageable);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantMobileTrackingVo(crs.company.code, a.nationalityCode, ul.gpsTime, adi.uin, ul.latitude, ul.longitude) " +
            "FROM JpaApplicant a INNER JOIN JpaApplicantDigitalId adi ON adi.applicantId = a.id JOIN JpaUserLocation ul ON ul.userId = adi.uin " +
            "JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :seasonYear AND ul.userType= 'APPLICANT' ")
    List<ApplicantMobileTrackingVo> findActiveApplicantWithLocationBySeason(@Param("seasonYear") int seasonYear);
}
