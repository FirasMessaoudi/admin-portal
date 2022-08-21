/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantBasic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository for applicant basic.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
public interface ApplicantBasicRepository extends JpaRepository<JpaApplicantBasic, Long> {

    @Query("SELECT a FROM JpaApplicantBasic a LEFT JOIN JpaApplicantDigitalId ad on a.id = ad.applicantId WHERE ad.id IS NULL and a.deleted = FALSE ")
    Page<JpaApplicantBasic> findAllApplicantsWithoutDigitalId(Pageable pageable);

    @Query("select a from JpaApplicantBasic a where " +
            "(a.idNumber = :idNumber) or " +
            "(a.passportNumber = :passportNumber and a.nationalityCode = :nationalityCode) and a.deleted = false")
    Optional<JpaApplicantBasic> findByBasicInfo(@Param("idNumber") String idNumber, @Param("passportNumber") String passportNumber, @Param("nationalityCode") String nationalityCode);

    @Query("select a.id from JpaApplicantBasic a where a.idNumber = :idNumber or (a.passportNumber = :passportNumber and a.nationalityCode = :nationalityCode) and a.deleted = false")
    Long findIdByBasicInfo(@Param("idNumber") String idNumber, @Param("passportNumber") String passportNumber, @Param("nationalityCode") String nationalityCode);

    @Query("select a.packageReferenceNumber from JpaApplicantBasic a where a.id = :applicantId and a.deleted = false")
    String findPackageReferenceNumberById(@Param("applicantId") Long applicantId);

}