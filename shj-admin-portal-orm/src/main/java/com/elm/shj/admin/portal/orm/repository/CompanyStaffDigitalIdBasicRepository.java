/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffDigitalIdBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository for CompanyStaffDigitalId Table.
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
public interface CompanyStaffDigitalIdBasicRepository extends JpaRepository<JpaCompanyStaffDigitalIdBasic, Long> {

    @Query(value = "select c from JpaCompanyStaffDigitalIdBasic c where c.companyStaffId = :staffId and c.seasonYear = :seasonYear and c.statusCode=:statusCode")
    Optional<JpaCompanyStaffDigitalIdBasic> findByBasicInfo(@Param("staffId") long staffId, @Param("seasonYear") int seasonYear, @Param("statusCode") String statusCode);
}
