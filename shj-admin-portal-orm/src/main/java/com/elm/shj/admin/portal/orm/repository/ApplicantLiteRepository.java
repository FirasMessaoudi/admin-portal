/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantStaffVO;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantLite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for applicant lite.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface ApplicantLiteRepository extends JpaRepository<JpaApplicantLite, Long> {

    @Query(value = "SELECT a FROM JpaApplicantLite a INNER JOIN JpaApplicantDigitalId adi ON adi.applicantId = a.id WHERE adi.uin = :uin")
    JpaApplicantLite findByUin(@Param("uin") String uin);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM JpaApplicantLite a INNER JOIN JpaApplicantDigitalId adi ON adi.applicantId = a.id WHERE adi.uin = :uin")
    boolean existsByUin(@Param("uin") String uin);

    @Query(value = "SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantStaffVO ( applicantDigitalId.uin, applicant.fullNameEn, applicant.fullNameAr, " +
            "ritualSeason.ritualTypeCode, card.statusCode, applicant.photo, " +
            "applicantPackage.id, groupLeaderDigitalId.suin, groupLeader.mobileNumber,groupLeader.mobileNumberIntl,company.labelEn,company.labelAr  ) " +
            "FROM JpaApplicantCard card " +
            "INNER JOIN card.applicantRitual ritual  " +
            "INNER JOIN ritual.applicant applicant " +
            "INNER JOIN ritual.applicantPackage applicantPackage "+
            "INNER JOIN applicant.digitalIds applicantDigitalId "+
            "INNER JOIN JpaGroupApplicantList groupApplicantList on groupApplicantList.applicantUin = applicantDigitalId.uin " +
            "INNER JOIN groupApplicantList.applicantGroup applicantGroup " +
            "INNER JOIN applicantGroup.groupLeader groupLeader " +
            "INNER JOIN groupLeader.digitalIds groupLeaderDigitalId " +
            "INNER JOIN applicantGroup.companyRitualSeason companyRitualSeason " +
            "INNER JOIN companyRitualSeason.ritualSeason ritualSeason " +
            "INNER JOIN companyRitualSeason.company company " +
            "WHERE applicant.idNumberOriginal =:idNumber OR " +
            "applicant.passportNumber =:idNumber"
            )
    ApplicantStaffVO findApplicantRitualByIdNumber(@Param("idNumber") String idNumber);

    @Query(value = "SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantStaffVO ( applicantDigitalId.uin, applicant.fullNameEn, applicant.fullNameAr, " +
            "ritualSeason.ritualTypeCode, card.statusCode, applicant.photo, " +
            "applicantPackage.id, groupLeaderDigitalId.suin, groupLeader.mobileNumber,groupLeader.mobileNumberIntl,company.labelEn,company.labelAr  ) " +
            "FROM JpaApplicantCard card " +
            "INNER JOIN card.applicantRitual ritual  " +
            "INNER JOIN ritual.applicant applicant " +
            "INNER JOIN ritual.applicantPackage applicantPackage "+
            "INNER JOIN applicant.digitalIds applicantDigitalId "+
            "INNER JOIN JpaGroupApplicantList groupApplicantList on groupApplicantList.applicantUin = applicantDigitalId.uin " +
            "INNER JOIN groupApplicantList.applicantGroup applicantGroup " +
            "INNER JOIN applicantGroup.groupLeader groupLeader " +
            "INNER JOIN groupLeader.digitalIds groupLeaderDigitalId " +
            "INNER JOIN applicantGroup.companyRitualSeason companyRitualSeason " +
            "INNER JOIN companyRitualSeason.ritualSeason ritualSeason " +
            "INNER JOIN companyRitualSeason.company company " +
            "WHERE applicantDigitalId.uin =:uin "
    )
    ApplicantStaffVO findApplicantRitualByUin(@Param("uin") String uin);
}
