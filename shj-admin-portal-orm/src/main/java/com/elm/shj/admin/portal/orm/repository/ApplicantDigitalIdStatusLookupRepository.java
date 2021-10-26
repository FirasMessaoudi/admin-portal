/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantDigitalIdStatusLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for applicant digital id status lookup table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface ApplicantDigitalIdStatusLookupRepository extends JpaRepository<JpaApplicantDigitalIdStatusLookup, Long> {
}
