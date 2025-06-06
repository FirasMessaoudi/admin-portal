/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantEmergencyContactDto;
import com.elm.shj.admin.portal.orm.entity.ApplicantStaffVO;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantLite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Repository for applicant lite.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface ApplicantLiteRepository extends JpaRepository<JpaApplicantLite, Long> {

    @Query("SELECT a FROM JpaApplicantLite a JOIN a.digitalIds adi WHERE adi.uin = :uin and a.deleted = false")
    JpaApplicantLite findByUin(@Param("uin") String uin);

    @Query("SELECT a FROM JpaApplicantLite a WHERE a.passportNumber = :passportNumber AND a.nationalityCode = :nationalityCode and a.deleted = false")
    JpaApplicantLite findByPassportNumberAndCountryCode(@Param("passportNumber") String passportNumber, @Param("nationalityCode") String nationalityCode);

    @Query("SELECT a FROM JpaApplicantLite a WHERE a.idNumber = :idNumber and a.deleted = false")
    JpaApplicantLite findByIdNumber(@Param("idNumber") String passportNumber);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM JpaApplicantLite a INNER JOIN a.digitalIds adi WHERE adi.uin = :uin and a.deleted = false")
    boolean existsByUin(@Param("uin") String uin);

    @Query("SELECT CASE WHEN COUNT(a)> 0 THEN TRUE ELSE FALSE END " +
            "FROM JpaApplicantLite a WHERE " +
            "(a.idNumber = :idNumber AND a.dateOfBirthHijri = :dateOfBirthHijri) OR " +
            "(a.passportNumber = :passportNumber AND a.dateOfBirthGregorian = :dateOfBirthGregorian) and a.deleted = false")
    boolean existsByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                              @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian);

    @Query("SELECT CASE WHEN COUNT(a)> 0 THEN TRUE ELSE FALSE END " +
            "FROM JpaApplicantLite a WHERE a.deleted = false AND " +
            "(a.idNumber = :idNumber) OR " +
            "(a.passportNumber = :passportNumber AND a.nationalityCode = :nationalityCode) and a.deleted = false")
    boolean existsByBasicInfo(@Param("idNumber") String idNumber,
                              @Param("passportNumber") String passportNumber, @Param("nationalityCode") String nationalityCode);

    @Query("select a from JpaApplicantLite a where " +
            "(a.idNumber = :idNumber and a.dateOfBirthHijri = :dateOfBirthHijri) or " +
            "(a.passportNumber = :passportNumber and a.dateOfBirthGregorian = :dateOfBirthGregorian) and a.deleted = false")
    List<JpaApplicantLite> findByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                                           @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian);

    @Query("select a from JpaApplicantLite a where " +
            "(a.idNumber = :idNumber) or " +
            "(a.passportNumber = :passportNumber and a.nationalityCode = :nationalityCode) and a.deleted = false")
    List<JpaApplicantLite> findByBasicInfo(@Param("idNumber") String idNumber, @Param("passportNumber") String passportNumber, @Param("nationalityCode") String nationalityCode);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantStaffVO ( applicantDigitalId.uin, applicant.fullNameEn, applicant.fullNameAr, " +
            "ritualSeason.ritualTypeCode, card.statusCode, applicant.photo, " +
            "applicantPackage.id, groupLeaderDigitalId.suin, groupLeader.mobileNumber,groupLeader.mobileNumberIntl,company.labelEn,company.labelAr,applicant.emergencyContactName, applicant.emergencyContactMobileNumber  ) " +
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
            "AND applicant.deleted = false " +
            "AND applicantDigitalId.statusCode=:digitalIdStatus " +
            "AND card.statusCode <> :canceledCardStatus " +
            "AND card.statusCode <> :suspendedCardStatus " +
            "AND card.statusCode <> 'REISSUED' " +
            "AND card.statusCode <> 'EXPIRED'  " +
            "order by applicantPackage.startDate desc, applicantPackage.creationDate desc"
    )
    List<ApplicantStaffVO> findApplicantRitualByIdNumber(@Param("idNumber") String idNumber, @Param("digitalIdStatus") String digitalIdStatus, @Param("canceledCardStatus") String canceledCardStatus, @Param("suspendedCardStatus") String suspendedCardStatus);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantStaffVO ( applicantDigitalId.uin, applicant.fullNameEn, applicant.fullNameAr, " +
            "ritualSeason.ritualTypeCode, card.statusCode, applicant.photo, " +
            "applicantPackage.id, groupLeaderDigitalId.suin, groupLeader.mobileNumber,groupLeader.mobileNumberIntl,company.labelEn,company.labelAr,applicant.emergencyContactName, applicant.emergencyContactMobileNumber  ) " +
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
            "AND applicant.deleted = false " +
            "AND applicantDigitalId.uin =:uin " +
            "AND applicantDigitalId.statusCode=:digitalIdStatus " +
            "AND card.statusCode <> :canceledCardStatus " +
            "AND card.statusCode <> :suspendedCardStatus " +
            "AND card.statusCode <> 'REISSUED' " +
            "AND card.statusCode <> 'EXPIRED'  " +
            "order by applicantPackage.startDate desc, applicantPackage.creationDate desc "
    )
    List<ApplicantStaffVO> findApplicantRitualByUin(@Param("uin") String uin, @Param("digitalIdStatus") String digitalIdStatus, @Param("canceledCardStatus") String canceledCardStatus, @Param("suspendedCardStatus") String suspendedCardStatus);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantEmergencyContactDto(a.emergencyContactName,a.emergencyContactMobileNumber) from JpaApplicant a " +
            "join JpaApplicantDigitalId digitalId on digitalId.applicantId = a.id where digitalId.uin = :applicantUin and a.deleted = false")
    ApplicantEmergencyContactDto findApplicantEmergencyContactByApplicantId(@Param("applicantUin") String applicantUin);

    @Modifying
    @Query("update JpaApplicant set emergencyContactName= :emergencyContactName, emergencyContactMobileNumber= :emergencyContactMobileNumber where id= :applicantId and deleted =false")
    int updateApplicantEmergencyContactByApplicantId(@Param("applicantId") long applicantId,@Param("emergencyContactName") String emergencyContactName,@Param("emergencyContactMobileNumber") String emergencyContactMobileNumber);
}
