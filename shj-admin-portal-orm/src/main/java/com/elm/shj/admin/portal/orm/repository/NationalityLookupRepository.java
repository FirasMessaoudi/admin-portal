/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaNationalityLookup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for JpaNationalityLookup.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public interface NationalityLookupRepository extends JpaRepository<JpaNationalityLookup, Long> {

    boolean existsByCode(String code);

    JpaNationalityLookup findFirstByCode(String countryCode);

    List<JpaNationalityLookup> findAllByCode(String countryCode);
}
