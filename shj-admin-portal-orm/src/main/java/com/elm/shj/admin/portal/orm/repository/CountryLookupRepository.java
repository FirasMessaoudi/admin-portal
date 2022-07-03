/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCountryLookup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for JpaCountryLookup.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
public interface CountryLookupRepository extends JpaRepository<JpaCountryLookup, Long> {

    boolean existsByCode(String code);

    JpaCountryLookup findFirstByCode(String countryCode);

    List<JpaCountryLookup> findAllByCode(String countryCode);
}

