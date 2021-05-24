/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestApplicant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for print request applicant table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
public interface PrintRequestApplicantRepository extends JpaRepository<JpaPrintRequestApplicant, Long> {
}
