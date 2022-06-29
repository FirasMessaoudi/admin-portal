/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealthLite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Lite repository for applicant health table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
public interface ApplicantHealthLiteRepository extends JpaRepository<JpaApplicantHealthLite, Long> {

    JpaApplicantHealthLite findByApplicantDigitalIdsUinAndApplicantRitualApplicantPackageId(String uin, Long applicantPackageId);

    @Query("select h.applicantRitual.id from JpaApplicantHealthLite h where h.id = :id")
    Long findApplicantRitualId(@Param("id") Long applicantHealthId);

    @Query("select h.applicantRitual.packageReferenceNumber from JpaApplicantHealthLite h where h.id = :id")
    String findApplicantHealthPackage(@Param("id") Long applicantHealthId);
}
