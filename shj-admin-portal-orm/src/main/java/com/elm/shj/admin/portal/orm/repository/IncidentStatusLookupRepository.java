/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaIncidentStatusLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for incident status lookup repository.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface IncidentStatusLookupRepository extends JpaRepository<JpaIncidentStatusLookup, Long> {
}
