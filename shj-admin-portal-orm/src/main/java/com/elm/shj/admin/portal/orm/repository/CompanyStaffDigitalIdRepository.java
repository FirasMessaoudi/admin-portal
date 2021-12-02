/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffDigitalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for CompanyStaffDigitalId Table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public interface CompanyStaffDigitalIdRepository extends JpaRepository<JpaCompanyStaffDigitalId, Long> {

    @Query(value = "select c from JpaCompanyStaffDigitalId c where " +
            "((c.companyStaff.id = :id) and c.seasonYear = :seasonYear)"
    )
    List<JpaCompanyStaffDigitalId> findByBasicInfo(@Param("id") long id, @Param("seasonYear") long seasonYear);


}
