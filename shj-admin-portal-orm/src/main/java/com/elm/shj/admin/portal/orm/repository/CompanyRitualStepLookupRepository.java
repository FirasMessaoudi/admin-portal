/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStepLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for company ritual step lookup table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface CompanyRitualStepLookupRepository extends JpaRepository<JpaCompanyRitualStepLookup, Long> {
}
