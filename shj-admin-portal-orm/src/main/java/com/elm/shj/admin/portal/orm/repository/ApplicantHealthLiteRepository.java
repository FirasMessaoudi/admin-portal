/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealthLite;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Lite repository for applicant health table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
public interface ApplicantHealthLiteRepository extends JpaRepository<JpaApplicantHealthLite, Long> {

    JpaApplicantHealthLite findByApplicantDigitalIdsUinAndApplicantRitualApplicantPackageId(String uin, Long applicantPackageId);
}
