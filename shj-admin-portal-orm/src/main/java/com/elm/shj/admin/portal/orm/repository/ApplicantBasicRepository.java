/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantBasic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository for applicant basic.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
public interface ApplicantBasicRepository extends JpaRepository<JpaApplicantBasic, Long> {

    @Query("SELECT a FROM JpaApplicantBasic a WHERE a.id NOT IN (SELECT ad.applicantId FROM JpaApplicantDigitalId ad)")
    Page<JpaApplicantBasic> findAllApplicantsWithoutDigitalId(Pageable pageable);

}