/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaMealTypeLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for JpaMealTypeLookup.
 *
 * @author salzoubi
 * @since 1.1.0
 */
public interface MealTypeLookupRepository extends JpaRepository<JpaMealTypeLookup, Long> {
    boolean existsByCode(String code);
}
