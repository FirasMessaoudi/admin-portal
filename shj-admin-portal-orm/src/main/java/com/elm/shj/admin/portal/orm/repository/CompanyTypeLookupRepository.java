/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyTypeLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for JpaCompanyType.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public interface CompanyTypeLookupRepository extends JpaRepository<JpaCompanyTypeLookup, Long> {
    boolean existsByCode(String code);
}
