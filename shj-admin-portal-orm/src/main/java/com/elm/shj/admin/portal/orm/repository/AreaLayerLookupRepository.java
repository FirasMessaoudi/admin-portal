/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaAreaLayerLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for area layer lookup table.
 *
 * @author Jaafer Jarray
 * @since 1.1.0
 */
public interface AreaLayerLookupRepository extends JpaRepository<JpaAreaLayerLookup, Long> {
}
