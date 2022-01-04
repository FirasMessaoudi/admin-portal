/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRelative;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for applicant relative table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public interface ApplicantRelativeRepository extends JpaRepository<JpaApplicantRelative, Long> {
    Optional<JpaApplicantRelative> findByApplicantIdAndRelativeApplicantId(long applicantId, long relativeApplicantId);
}
