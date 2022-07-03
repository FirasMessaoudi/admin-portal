/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCampCategoryLookup;
import com.elm.shj.admin.portal.orm.entity.JpaCampSiteLookup;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository for Camp Category Lookup table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
public interface CampCategoryLookupRepository extends JpaRepository<JpaCampCategoryLookup, Long> {



}