/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

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

    @Query(value = "SELECT CASE WHEN COUNT(a)> 0 THEN TRUE ELSE FALSE END " +
            "FROM JpaApplicant a WHERE " +
            "(a.idNumber = :idNumber AND a.dateOfBirthHijri = :dateOfBirthHijri) OR " +
            "(a.passportNumber = :passportNumber AND a.dateOfBirthGregorian = :dateOfBirthGregorian)")
    boolean existsByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                              @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian);

    @Query(value = "SELECT a FROM JpaApplicant a JOIN a.digitalIds adi WHERE adi.uin = :uin")
    JpaApplicant findByUin(@Param("uin") String uin);

    @Query("select a from JpaApplicant a where a.id not in (select ad.applicant.id from JpaApplicantDigitalId ad)")
    List<JpaApplicant> findAllApplicantsWithoutDigitalId();

    @Query("SELECT a FROM JpaApplicant a LEFT JOIN a.rituals ar JOIN ar.applicantPackage ap WHERE (:today >= ap.startDate AND :today <= ap.endDate AND a.registered = TRUE)")
    List<JpaApplicant> findAllApplicantsRegisteredAndHavingActiveRitual(@Param("today") Date today);

    @Query("SELECT COUNT(a) FROM JpaApplicant a LEFT JOIN a.rituals ar JOIN ar.applicantPackage ap WHERE (:today >= ap.startDate AND :today <= ap.endDate AND a.registered = TRUE)")
    long countHavingActiveRitual(@Param("today") Date today);

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

    @Query(value = "SELECT DISTINCT applicant.* " +
            "FROM shc_applicant applicant " +
            "         JOIN shc_applicant_digital_id digital_id ON applicant.id = digital_id.applicant_id " +
            "         JOIN shc_applicant_package applicant_package ON digital_id.uin = applicant_package.applicant_uin " +
            "         JOIN shc_group_applicant_list group_applicant ON applicant_package.applicant_uin = group_applicant.applicant_uin " +
            "         LEFT JOIN shc_applicant_chat_contact chat_contact ON digital_id.uin = chat_contact.applicant_uin " +
            "WHERE applicant.registered = 1 " +
            "  AND applicant_package.start_date <= CURRENT_TIMESTAMP " +
            "  AND applicant_package.end_date >= CURRENT_TIMESTAMP " +
            "  AND group_applicant.applicant_uin NOT IN " +
            "      (SELECT chat_contact.applicant_uin " +
            "       FROM shc_applicant_chat_contact chat_contact " +
            "       WHERE chat_contact.staff_title_code = :title " +
            "         AND chat_contact.applicant_ritual_id = " +
            "             (SELECT sar.id " +
            "              FROM shc_portal.shc_company_ritual_season company_ritual_season " +
            "                       JOIN shc_ritual_package ritual_package ON company_ritual_season.id = ritual_package.company_ritual_season_id " +
            "                       JOIN shc_applicant_package applicant_package ON ritual_package.id = applicant_package.ritual_package_id " +
            "                       JOIN shc_applicant_ritual sar ON applicant_package.id = sar.applicant_package_id " +
            "              WHERE applicant_package.applicant_uin = digital_id.uin " +
            "              ORDER BY company_ritual_season.season_start DESC " +
            "              OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY) ) ;", nativeQuery = true)
    List<JpaApplicant> findAllNotHavingChatContactWithTitle(@Param("title") String title);
}
