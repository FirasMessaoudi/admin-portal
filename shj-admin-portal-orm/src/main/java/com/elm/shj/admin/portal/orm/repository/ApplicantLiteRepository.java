/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantStaffVO;
import com.elm.shj.admin.portal.orm.entity.ApplicantVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantLite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.Date;
import java.util.List;

/**
 * Repository for applicant lite.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface ApplicantLiteRepository extends JpaRepository<JpaApplicantLite, Long> {

    @Query("SELECT a FROM JpaApplicantLite a JOIN a.digitalIds adi WHERE adi.uin = :uin")
    JpaApplicantLite findByUin(@Param("uin") String uin);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM JpaApplicantLite a INNER JOIN a.digitalIds adi WHERE adi.uin = :uin")
    boolean existsByUin(@Param("uin") String uin);

    @Query("SELECT CASE WHEN COUNT(a)> 0 THEN TRUE ELSE FALSE END " +
            "FROM JpaApplicantLite a WHERE " +
            "(a.idNumber = :idNumber AND a.dateOfBirthHijri = :dateOfBirthHijri) OR " +
            "(a.passportNumber = :passportNumber AND a.dateOfBirthGregorian = :dateOfBirthGregorian)")
    boolean existsByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                              @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian);

    @Query("SELECT a FROM JpaApplicantLite a WHERE a.id NOT IN (SELECT ad.applicantId FROM JpaApplicantDigitalId ad)")
    List<JpaApplicantLite> findAllApplicantsWithoutDigitalId();

    @Query("select a from JpaApplicantLite a where " +
            "(a.idNumber = :idNumber and a.dateOfBirthHijri = :dateOfBirthHijri) or " +
            "(a.passportNumber = :passportNumber and a.dateOfBirthGregorian = :dateOfBirthGregorian)")
    JpaApplicantLite findByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                                 @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantStaffVO ( applicantDigitalId.uin, applicant.fullNameEn, applicant.fullNameAr, " +
            "ritualSeason.ritualTypeCode, card.statusCode, applicant.photo, " +
            "applicantPackage.id, groupLeaderDigitalId.suin, groupLeader.mobileNumber,groupLeader.mobileNumberIntl,company.labelEn,company.labelAr  ) " +
            "FROM JpaApplicantCard card " +
            "INNER JOIN card.applicantRitual ritual  " +
            "INNER JOIN ritual.applicant applicant " +
            "INNER JOIN ritual.applicantPackage applicantPackage " +
            "INNER JOIN applicant.digitalIds applicantDigitalId " +
            "INNER JOIN applicantPackage.ritualPackage ritualPackage " +
            "INNER JOIN ritualPackage.companyRitualSeason companyRitualSeason " +
            "INNER JOIN companyRitualSeason.ritualSeason ritualSeason " +
            "INNER JOIN companyRitualSeason.company company " +
            "LEFT JOIN JpaGroupApplicantList groupApplicantList on groupApplicantList.applicantUin = applicantDigitalId.uin " +
            "LEFT JOIN groupApplicantList.applicantGroup applicantGroup " +
            "LEFT JOIN applicantGroup.groupLeader groupLeader " +
            "LEFT JOIN groupLeader.digitalIds groupLeaderDigitalId " +
            "WHERE ritualSeason.active = true " +
            "AND (applicant.idNumber =:idNumber OR  " +
            "applicant.idNumberOriginal =:idNumber OR " +
            "applicant.passportNumber =:idNumber )" +
            "AND applicantDigitalId.statusCode=:digitalIdStatus " +
            "AND card.statusCode <> :canceledCardStatus "+
            "AND card.statusCode <> :suspendedCardStatus " +
            "order by applicantPackage.startDate desc, applicantPackage.creationDate desc"
    )
    List<ApplicantStaffVO> findApplicantRitualByIdNumber(@Param("idNumber") String idNumber, @Param("digitalIdStatus") String digitalIdStatus, @Param("canceledCardStatus") String canceledCardStatus, @Param("suspendedCardStatus") String suspendedCardStatus);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantStaffVO ( applicantDigitalId.uin, applicant.fullNameEn, applicant.fullNameAr, " +
            "ritualSeason.ritualTypeCode, card.statusCode, applicant.photo, " +
            "applicantPackage.id, groupLeaderDigitalId.suin, groupLeader.mobileNumber,groupLeader.mobileNumberIntl,company.labelEn,company.labelAr  ) " +
            "FROM JpaApplicantCard card " +
            "INNER JOIN card.applicantRitual ritual  " +
            "INNER JOIN ritual.applicant applicant " +
            "INNER JOIN ritual.applicantPackage applicantPackage " +
            "INNER JOIN applicant.digitalIds applicantDigitalId " +
            "INNER JOIN applicantPackage.ritualPackage ritualPackage " +
            "INNER JOIN ritualPackage.companyRitualSeason companyRitualSeason " +
            "INNER JOIN companyRitualSeason.ritualSeason ritualSeason " +
            "INNER JOIN companyRitualSeason.company company " +
            "LEFT JOIN JpaGroupApplicantList groupApplicantList on groupApplicantList.applicantUin = applicantDigitalId.uin " +
            "LEFT JOIN groupApplicantList.applicantGroup applicantGroup " +
            "LEFT JOIN applicantGroup.groupLeader groupLeader " +
            "LEFT JOIN groupLeader.digitalIds groupLeaderDigitalId " +
            "WHERE ritualSeason.active = true " +
            "AND  applicantDigitalId.uin =:uin " +
            "AND applicantDigitalId.statusCode=:digitalIdStatus " +
            "AND card.statusCode <> :canceledCardStatus " +
            "AND card.statusCode <> :suspendedCardStatus " +
            "order by applicantPackage.startDate desc, applicantPackage.creationDate desc "
    )
    List<ApplicantStaffVO> findApplicantRitualByUin(@Param("uin") String uin, @Param("digitalIdStatus") String digitalIdStatus, @Param("canceledCardStatus") String canceledCardStatus, @Param("suspendedCardStatus") String suspendedCardStatus);

    @Query(value = "SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantVo(a.fullNameAr, a.fullNameEn, adi.uin, a.photo, l.latitude , l.longitude,a.idNumber,a.passportNumber) From JpaApplicant a INNER JOIN JpaApplicantDigitalId adi ON adi.applicantId = a.id LEFT JOIN JpaUserLocation l ON l.userId = adi.uin WHERE adi.uin = :uin order by l.creationDate desc")
    List<ApplicantVo> findApplicantDetailsWithLocationByUin(@Param("uin") String uin);

}
