/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantMobileTrackingVo;
import com.elm.shj.admin.portal.orm.entity.CountVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicant;
import com.elm.shj.admin.portal.orm.entity.LocalizedCountVo;
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
            "(a.passportNumber = :passportNumber and a.dateOfBirthGregorian = :dateOfBirthGregorian) and a.deleted = false")
    JpaApplicant findByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                                 @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian);

    @Query(value = "select a.id from JpaApplicant a where " +
            "(a.idNumber = :idNumber and a.dateOfBirthHijri = :dateOfBirthHijri) or " +
            "(a.passportNumber = :passportNumber and a.dateOfBirthGregorian = :dateOfBirthGregorian) and a.deleted = false")
    Long findIdByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                           @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian);

    @Query(value = "select a.id from JpaApplicant a where " +
            "(a.idNumber = :idNumber) or " +
            "(a.passportNumber = :passportNumber and a.nationalityCode = :nationalityCode) and a.deleted = false ")
    Long findIdByBasicInfo(@Param("idNumber") String idNumber,
                           @Param("passportNumber") String passportNumber, @Param("nationalityCode") String nationalityCode);

    @Query(value = "SELECT CASE WHEN COUNT(a)> 0 THEN TRUE ELSE FALSE END " +
            "FROM JpaApplicant a WHERE " +
            "(a.idNumber = :idNumber AND a.dateOfBirthHijri = :dateOfBirthHijri) OR " +
            "(a.passportNumber = :passportNumber AND a.dateOfBirthGregorian = :dateOfBirthGregorian) and a.deleted = false")
    boolean existsByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                              @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian);

    @Query(value = "SELECT a FROM JpaApplicant a JOIN a.digitalIds adi WHERE adi.uin = :uin and a.deleted = false")
    JpaApplicant findByUin(@Param("uin") String uin);

    @Query("SELECT a FROM JpaApplicant a LEFT JOIN a.rituals ar JOIN ar.applicantPackage ap WHERE (:today >= ap.startDate AND :today <= ap.endDate AND a.registered = TRUE and a.deleted = false)")
    List<JpaApplicant> findAllApplicantsRegisteredAndHavingActiveRitual(@Param("today") Date today);

    @Query("SELECT COUNT(a) FROM JpaApplicant a LEFT JOIN a.rituals ar JOIN ar.applicantPackage ap WHERE (:today >= ap.startDate AND :today <= ap.endDate AND a.registered = TRUE and a.deleted = false)")
    long countHavingActiveRitual(@Param("today") Date today);

    //TODO: To be deleted
    @Query("SELECT CASE WHEN COUNT(a)> 0 THEN TRUE ELSE FALSE END FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp WHERE " +
            "((a.idNumber = :idNumber AND a.dateOfBirthHijri = :dateOfBirthHijri) OR " +
            "(a.passportNumber = :passportNumber AND a.dateOfBirthGregorian = :dateOfBirthGregorian)) AND rp.referenceNumber = :packageCode and a.deleted = false")
    boolean findByBasicInfoAndPackageCode(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                                          @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian, @Param("packageCode") String packageCode);

    @Query("SELECT a FROM JpaApplicant a WHERE a.id IN :selectedApplicants and a.deleted = false")
    List<JpaApplicant> findAllByIds(@Param("selectedApplicants") List<Long> selectedApplicants);

    @Query("SELECT a FROM JpaApplicant a WHERE a.id IN :selectedApplicants and a.deleted = false")
    Page<JpaApplicant> findByIds(@Param("selectedApplicants") List<Long> selectedApplicants, Pageable pageable);

    @Modifying
    @Query("UPDATE JpaApplicant a SET a.preferredLanguage = :lang, a.updateDate = CURRENT_TIMESTAMP WHERE a.id = :applicantId and a.deleted = false")
    void updatePreferredLanguage(@Param("applicantId") long applicantId, @Param("lang") String lang);

    @Modifying
    @Query("UPDATE JpaApplicant a SET a.registered = TRUE, a.updateDate = CURRENT_TIMESTAMP, a.channel = :channel WHERE a.id = :applicantId and a.deleted = false")
    int markAsRegistered(@Param("applicantId") long applicantId, @Param("channel") String channel);

    @Query("SELECT COUNT(a) FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :hijriSeason " +
            "AND rs.ritualTypeCode IN ('INTERNAL_HAJJ', 'EXTERNAL_HAJJ', 'COURTESY_HAJJ') and a.deleted = false")
    long countAllApplicantsByHijriSeason(@Param("hijriSeason") int hijriSeason);

    @Query("SELECT COUNT(a) FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :hijriSeason " +
            "AND rs.ritualTypeCode IN ('INTERNAL_HAJJ', 'EXTERNAL_HAJJ', 'COURTESY_HAJJ') AND a.gender = :gender and a.deleted = false")
    long countAllApplicantsByGenderByHijriSeason(@Param("gender") String gender, @Param("hijriSeason") int hijriSeason);

    @Query("SELECT COUNT(a) FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :seasonYear " +
            "AND (a.dateOfBirthGregorian BETWEEN :to AND :from) AND rs.ritualTypeCode IN (:ritualTypeCodeList) and a.deleted = false")
    long countPilgrimsFromCurrentSeasonByAgeRange(@Param("from") Date from, @Param("to") Date to, @Param("seasonYear") int seasonYear, @Param("ritualTypeCodeList") List<String> ritualTypeCodeList);

    @Query("SELECT COUNT(a) FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :seasonYear AND rs.ritualTypeCode IN (:ritualTypeCodeList) and a.deleted = false")
    long countTotalApplicantsFromCurrentSeason(@Param("seasonYear") int seasonYear, @Param("ritualTypeCodeList") List<String> ritualTypeCodeList);

    @Query("SELECT COUNT(a) FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :seasonYear " +
            "AND rs.ritualTypeCode IN (:ritualTypeCodeList) and a.deleted = false ")
    long countAllApplicantBySeasonAndRitualType(@Param("seasonYear") int seasonYear, @Param("ritualTypeCodeList") List<String> ritualTypeCodeList);

    @Query("SELECT COUNT(a) FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :seasonYear and rs.ritualTypeCode IN ('INTERNAL_HAJJ', 'EXTERNAL_HAJJ', 'COURTESY_HAJJ') and a.nationalityCode =:nationalityCode and a.deleted = false")
    long countTotalApplicantsFromCurrentSeasonByNationality(@Param("nationalityCode") String nationalityCode, @Param("seasonYear") int seasonYear);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.CountVo(a.nationalityCode, 0, COUNT(a),'') FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :seasonYear and rs.ritualTypeCode IN ('INTERNAL_HAJJ', 'EXTERNAL_HAJJ', 'COURTESY_HAJJ') and a.deleted = false GROUP BY a.nationalityCode")
    List<CountVo> countApplicantsByNationality(@Param("seasonYear") int seasonYear);

    @Query("select distinct (a.nationalityCode) from JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs where rs.ritualTypeCode IN ('INTERNAL_HAJJ', 'EXTERNAL_HAJJ', 'COURTESY_HAJJ') and a.deleted = false")
    List<String> findAllNationalities();

    @Query("SELECT COUNT(a) FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :seasonYear AND rs.ritualTypeCode IN (:ritualTypeCodeList) AND (a.mobileLoggedIn IS NOT NULL OR a.channel='MOBILE') and a.deleted = false")
    long countAllByMobileLoggedInIsNotNull(@Param("seasonYear") int seasonYear, @Param("ritualTypeCodeList") List<String> ritualTypeCodeList);

    @Query("SELECT COUNT(a) FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :seasonYear AND rs.ritualTypeCode IN (:ritualTypeCodeList) AND a.mobileLoggedIn = TRUE and a.deleted = false")
    long countAllByMobileLoggedInTrue(@Param("seasonYear") int seasonYear, @Param("ritualTypeCodeList") List<String> ritualTypeCodeList);

    @Modifying
    @Query("UPDATE JpaApplicant a SET a.mobileLoggedIn = :mobileLoggedIn, a.updateDate = CURRENT_TIMESTAMP WHERE a.id = :applicantId and a.deleted = false")
    void updateLoggedInFromMobileAppFlag(@Param("applicantId") long applicantId, @Param("mobileLoggedIn") boolean mobileLoggedIn);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.LocalizedCountVo(c.labelAr,c.labelEn,COUNT(a)) " +
            "FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp " +
            "JOIN rp.companyRitualSeason crs JOIN crs.company c JOIN crs.ritualSeason rs " +
            "WHERE  rs.ritualTypeCode IN ('INTERNAL_HAJJ', 'EXTERNAL_HAJJ', 'COURTESY_HAJJ') AND " +
            "c.labelAr is NOT NULL AND (a.mobileLoggedIn IS NOT NULL OR a.channel='MOBILE') AND rs.seasonYear= :seasonYear and a.deleted = false " +
            "GROUP BY c.labelAr, c.labelEn ORDER BY COUNT(c.labelAr) DESC")
    Page<LocalizedCountVo> loadCompaniesWithMaxApplicantsRegisteredCount(@Param("seasonYear") int seasonYear, Pageable pageable);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.LocalizedCountVo(c.labelAr,c.labelEn,COUNT(a)) " +
            "FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp " +
            "JOIN rp.companyRitualSeason crs JOIN crs.company c JOIN crs.ritualSeason rs " +
            "WHERE rs.ritualTypeCode IN ('INTERNAL_HAJJ', 'EXTERNAL_HAJJ', 'COURTESY_HAJJ') AND " +
            "c.labelAr is NOT NULL AND (a.mobileLoggedIn IS NOT NULL OR a.channel='MOBILE') AND rs.seasonYear= :seasonYear and a.deleted = false " +
            "GROUP BY c.labelAr, c.labelEn ORDER BY COUNT(c.labelAr)")
    Page<LocalizedCountVo> loadCompaniesWithMinApplicantsRegisteredCount(@Param("seasonYear") int seasonYear, Pageable pageable);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantMobileTrackingVo(crs.company.code, a.nationalityCode, ul.gpsTime, adi.uin, ul.latitude, ul.longitude) " +
            "FROM JpaApplicant a INNER JOIN JpaApplicantDigitalId adi ON adi.applicantId = a.id JOIN JpaUserLocation ul ON ul.userId = adi.uin " +
            "JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs " +
            "JOIN crs.ritualSeason rs WHERE rs.seasonYear = :seasonYear AND ul.userType= 'APPLICANT' AND ul.gpsTime = (SELECT MAX(l.gpsTime) FROM JpaUserLocation l WHERE l.userId  = adi.uin) and a.deleted = false ")
    List<ApplicantMobileTrackingVo> findActiveApplicantWithLocationBySeason(@Param("seasonYear") int seasonYear);

    @Modifying
    @Query("UPDATE JpaApplicant a SET a.dataRequestRecordId = :dataRequestRecordId, a.updateDate = CURRENT_TIMESTAMP WHERE a.id = :applicantId")
    void updateDataRequestRecordId(@Param("dataRequestRecordId") long dataRequestRecordId, @Param("applicantId") long applicantId);

    @Query("SELECT COUNT(DISTINCT a) FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap " +
            "JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs JOIN crs.ritualSeason rs " +
            "WHERE (a.mobileLoggedIn IS NOT NULL OR a.channel='MOBILE') AND rs.seasonYear = :seasonYear AND (a.dateOfBirthGregorian BETWEEN :to AND :from) AND rs.ritualTypeCode IN (:ritualTypeCodeList)")
    long countMobileAppUsersByAgeRange(@Param("from") Date from,
                                       @Param("to") Date to,
                                       @Param("seasonYear") int seasonYear,
                                       @Param("ritualTypeCodeList") List<String> ritualTypeCodeList);

    @Query("select a.registered from JpaApplicant  a where a.id = :id")
    boolean findApplicantStatusById(@Param("id") Long id);

    @Query("select a " +
            "FROM JpaApplicant a JOIN a.digitalIds di JOIN JpaGroupApplicantList ga ON di.uin = ga.applicantUin JOIN ga.applicantGroup ap JOIN ap.companyRitualSeason crs JOIN crs.company c where " +
            "a.deleted = FALSE and a.packageReferenceNumber is not null and "+
            "(:idNumber is null OR a.idNumber = :idNumber) and "+
            "(:passportNumber is null OR a.passportNumber = :passportNumber) and " +
            "(:gender is null OR a.gender = :gender) and " +
            "((:applicantName is null OR a.fullNameEn like '%'+:applicantName+'%' OR a.fullNameAr like '%'+:applicantName+'%')) and " +
            "(:uin is null OR di.uin = :uin) and " +
            "((:groupNumber is null OR ap.referenceNumber = :groupNumber) and c.code = :companyFullCode) and" +
            "(:companyCode is '-1' or a.companyCode = :companyCode) and " +
            "(:establishmentRefCode = -1L or a.establishmentRefCode = :establishmentRefCode) and " +
            "(:missionRefCode = -1L or a.missionRefCode = :missionRefCode) and " +
            "((:serviceGroupRefCode = -1L or a.serviceGroupMakkahCode = :serviceGroupRefCode or a.serviceGroupMadinaCode = :serviceGroupRefCode)) ")
    Page<JpaApplicant> findOrganizerApplicantsWithGroupNumberFilter(@Param("idNumber") String idNumber,  @Param("groupNumber") String groupNumber,
                                        @Param("passportNumber") String passportNumber,  @Param("applicantName") String applicantName,
                                        @Param("gender") String gender, @Param("uin") String uin, @Param("companyCode") String companyCode,
                                          @Param("establishmentRefCode") long establishmentRefCode, @Param("missionRefCode") long missionRefCode,
                                          @Param("serviceGroupRefCode") long serviceGroupRefCode,  @Param("companyFullCode") String companyFullCode, Pageable pageable);

    @Query("SELECT a FROM JpaApplicant a JOIN a.digitalIds di JOIN JpaGroupApplicantList ga On di.uin=ga.applicantUin WHERE ga.applicantGroup.id=:groupId and a.deleted = false")
    List<JpaApplicant> findAllApplicantByGroupId(@Param("groupId") Long groupId);

    @Query("SELECT a  " +
            "FROM JpaApplicant a JOIN a.digitalIds di WHERE " +
            "a.deleted = FALSE AND a.packageReferenceNumber IS NOT NULL AND " +
            "(:companyCode IS NULL OR a.companyCode = :companyCode) AND " +
            "(:establishmentRefCode = -1L OR a.establishmentRefCode = :establishmentRefCode) AND " +
            "(:missionRefCode = -1L OR a.missionRefCode = :missionRefCode)")
    List<JpaApplicant> findOrganizerApplicants(@Param("companyCode") String companyCode,
                                               @Param("establishmentRefCode") Long establishmentRefCode,
                                               @Param("missionRefCode") Long missionRefCode);
    @Query("select a  " +
            "FROM JpaApplicant a JOIN a.digitalIds di where " +
            "a.deleted = FALSE and a.packageReferenceNumber is not null and "+
            "(:idNumber is null OR a.idNumber = :idNumber) and "+
            "(:passportNumber is null OR a.passportNumber = :passportNumber) and " +
            "(:gender is null OR a.gender = :gender) and " +
            "((:applicantName is null OR a.fullNameEn like '%'+:applicantName+'%' OR a.fullNameAr like '%'+:applicantName+'%')) and " +
            "(:uin is null OR di.uin = :uin) and " +
            "(:companyCode is '-1' or a.companyCode = :companyCode) and " +
            "(:establishmentRefCode = -1L or a.establishmentRefCode = :establishmentRefCode) and " +
            "(:missionRefCode = -1L or a.missionRefCode = :missionRefCode) and " +
            "((:serviceGroupRefCode = -1L or a.serviceGroupMakkahCode = :serviceGroupRefCode or a.serviceGroupMadinaCode = :serviceGroupRefCode)) ")
    Page<JpaApplicant> findOrganizerApplicants(@Param("idNumber") String idNumber, @Param("passportNumber") String passportNumber,  @Param("applicantName") String applicantName,
                                                                    @Param("gender") String gender, @Param("uin") String uin, @Param("companyCode") String companyCode,
                                                                    @Param("establishmentRefCode") long establishmentRefCode, @Param("missionRefCode") long missionRefCode,
                                                                    @Param("serviceGroupRefCode") long serviceGroupRefCode, Pageable pageable);

    @Query("select a FROM JpaApplicant a where " +
            "a.deleted = FALSE and a.packageReferenceNumber is not null and "+
            "(:companyCode is -1 or a.companyCode = :companyCode) and " +
            "(:establishmentRefCode = -1L or a.establishmentRefCode = :establishmentRefCode) and " +
            "(:missionRefCode = -1L or a.missionRefCode = :missionRefCode) and " +
            "((:serviceGroupRefCode = -1L or a.serviceGroupMakkahCode = :serviceGroupRefCode or a.serviceGroupMadinaCode = :serviceGroupRefCode)) ")
    List<JpaApplicant> findOrganizerApplicantsForExport(@Param("companyCode") String companyCode,
                                                        @Param("establishmentRefCode") long establishmentRefCode, @Param("missionRefCode") long missionRefCode,
                                                        @Param("serviceGroupRefCode") long serviceGroupRefCode);


    @Query("SELECT rs.ritualTypeCode FROM JpaApplicant a JOIN a.rituals ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp " +
            "JOIN rp.companyRitualSeason crs JOIN crs.ritualSeason rs " +
            "WHERE a.id = :applicantId ORDER BY a.creationDate DESC")
    List<String> findRitualTypeByApplicantId(@Param("applicantId") long applicantId);

    @Query("select a.id FROM JpaApplicant a JOIN a.digitalIds di JOIN JpaGroupApplicantList ga ON di.uin = ga.applicantUin JOIN ga.applicantGroup ap where ap.id = :groupId")
    List<Long> findApplicantIdByGroupId(@Param("groupId") Long groupId);

    @Query(value = "SELECT CASE WHEN COUNT(a)> 0 THEN TRUE ELSE FALSE END " +
            "FROM JpaApplicant a WHERE " +
            "a.id = :applicantId AND a.deleted=FALSE AND a.packageReferenceNumber is not null")
    boolean isValidApplicant(@Param("applicantId") Long applicantId);

}
