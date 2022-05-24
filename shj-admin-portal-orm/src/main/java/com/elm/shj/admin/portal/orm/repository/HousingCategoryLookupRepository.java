/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaHousingCategoryLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for housing category lookup table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface HousingCategoryLookupRepository extends JpaRepository<JpaHousingCategoryLookup, Long> {
    boolean existsByCode(String code);
}
