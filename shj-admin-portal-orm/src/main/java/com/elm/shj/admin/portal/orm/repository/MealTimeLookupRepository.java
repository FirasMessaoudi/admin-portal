/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaMealTimeLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for JpaMealTimeLookup.
 *
 * @author salzoubi
 * @since 1.1.0
 */
public interface MealTimeLookupRepository extends JpaRepository<JpaMealTimeLookup, Long> {
    boolean existsByCode(String code);
}
