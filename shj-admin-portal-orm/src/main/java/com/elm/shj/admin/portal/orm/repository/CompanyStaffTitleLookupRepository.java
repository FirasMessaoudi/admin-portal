/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffTitleLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for company staff title lookup table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface CompanyStaffTitleLookupRepository extends JpaRepository<JpaCompanyStaffTitleLookup, Long> {
    boolean existsByCode(String jobTitleCode);
}
