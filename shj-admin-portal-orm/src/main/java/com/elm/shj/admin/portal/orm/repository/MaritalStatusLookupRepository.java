/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaMaritalStatusLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for JpaMaritalStatusLookup.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public interface MaritalStatusLookupRepository extends JpaRepository<JpaMaritalStatusLookup, Long> {

    boolean existsByCode(String maritalStatusCode);
}
