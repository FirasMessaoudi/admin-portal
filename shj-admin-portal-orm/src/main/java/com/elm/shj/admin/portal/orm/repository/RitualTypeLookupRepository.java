/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaRitualTypeLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for JpaRitualTypeLookup.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public interface RitualTypeLookupRepository extends JpaRepository<JpaRitualTypeLookup, Long> {

    boolean existsByCode(String ritualTypeCode);

    @Query("select r.label from  JpaRitualTypeLookup r where r.code = :code and r.lang = :lang")
    String findLabelByCodeAndLanguage(@Param("code") String code, @Param("lang") String lang);
}
