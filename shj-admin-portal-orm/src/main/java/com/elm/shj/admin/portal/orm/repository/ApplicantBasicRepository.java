/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantBasic;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantLite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for applicant basic.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
public interface ApplicantBasicRepository extends JpaRepository<JpaApplicantBasic, Long> {

    @Query("SELECT a FROM JpaApplicantBasic a LEFT JOIN JpaApplicantDigitalId ad on a.id = ad.applicantId WHERE a.packageReferenceNumber IS NOT NULL AND ad.id IS NULL")
    Page<JpaApplicantBasic> findAllApplicantsWithoutDigitalId(Pageable pageable);

    @Query("select a from JpaApplicantLite a where " +
            "(a.idNumber = :idNumber) or " +
            "(a.passportNumber = :passportNumber and a.nationalityCode = :nationalityCode)")
    List<JpaApplicantLite> findByBasicInfo(@Param("idNumber") String idNumber, @Param("passportNumber") String passportNumber, @Param("nationalityCode") String nationalityCode);

    @Query("select a.id from JpaApplicantBasic a where a.idNumber = :idNumber or (a.passportNumber = :passportNumber and a.nationalityCode = :nationalityCode)")
    Long findIdByBasicInfo(@Param("idNumber") String idNumber, @Param("passportNumber") String passportNumber, @Param("nationalityCode") String nationalityCode);

    @Query("select a.packageReferenceNumber from JpaApplicantBasic a where a.id = :applicantId")
    String findPackageReferenceNumberById(@Param("applicantId") Long applicantId);

}