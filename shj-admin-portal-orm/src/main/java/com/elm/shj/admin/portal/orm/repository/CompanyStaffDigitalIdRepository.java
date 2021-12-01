/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffDigitalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository for CompanyStaffDigitalId Table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public interface CompanyStaffDigitalIdRepository extends JpaRepository<JpaCompanyStaffDigitalId, Long> {

    @Query(value = "select c from JpaCompanyStaffDigitalId c where " +
            "(c.companyStaff.idNumber = :idNumber and c.seasonYear = :seasonYear)"
    )
    Optional<JpaCompanyStaffDigitalId> findByBasicInfo(@Param("idNumber") String idNumber, @Param("seasonYear") int seasonYear);


}
