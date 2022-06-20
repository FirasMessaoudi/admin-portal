/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaNationalityLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("select r.label from  JpaNationalityLookup r where r.code = :code and r.lang = :lang")
    String findLabelByCodeAndLanguage(@Param("code") String code, @Param("lang") String lang);
}
