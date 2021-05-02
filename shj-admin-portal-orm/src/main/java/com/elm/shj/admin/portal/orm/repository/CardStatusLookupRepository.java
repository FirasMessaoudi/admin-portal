/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCardStatusLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for JpaCardStatusLookup.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public interface CardStatusLookupRepository extends JpaRepository<JpaCardStatusLookup, Long> {
}
