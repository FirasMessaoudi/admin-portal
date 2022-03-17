/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealthImmunization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for applicant health immunization table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
public interface ApplicantHealthImmunizationRepository extends JpaRepository<JpaApplicantHealthImmunization, Long> {
    Optional<JpaApplicantHealthImmunization> findByApplicantHealthApplicantIdAndImmunizationCodeAndApplicantHealthPackageReferenceNumber(long applicantId, String immunizationCode, String packageCode);
}
