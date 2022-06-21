/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCityLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for city lookup repository.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
public interface CityLookupRepository extends JpaRepository<JpaCityLookup, Long> {
}
