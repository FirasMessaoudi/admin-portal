/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffTitleLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for company staff title lookup table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface CompanyStaffTitleLookupRepository extends JpaRepository<JpaCompanyStaffTitleLookup, Long> {
    boolean existsByCode(String jobTitleCode);

    @Query("select r.label from  JpaCompanyStaffTitleLookup r where r.code = :code and r.lang = :lang")
    String findLabelByCodeAndLanguage(@Param("code") String code, @Param("lang") String lang);
}
