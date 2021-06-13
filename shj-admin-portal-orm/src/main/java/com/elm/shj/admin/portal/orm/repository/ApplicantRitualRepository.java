/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository for applicant ritual table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public interface ApplicantRitualRepository extends JpaRepository<JpaApplicantRitual, Long> {

    @Query("select ar from JpaApplicantRitual ar where ar.id not in (select ac.applicantRitual.id from JpaApplicantCard ac)")
    List<JpaApplicantRitual> findAllApplicantRitualsWithoutCard();
}
