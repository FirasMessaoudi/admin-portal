/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantCardBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for Applicant Card Table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public interface ApplicantCardBasicRepository extends JpaRepository<JpaApplicantCardBasic, Long> {

    @Modifying
    @Query("UPDATE JpaApplicantCardBasic ac SET ac.deleted = true, ac.statusCode = :statusCode WHERE ac.applicantRitual.applicant.id = :applicantId")
    void deleteAllApplicantCards(@Param("applicantId") Long applicantId, @Param("status") String statusCode);

}
