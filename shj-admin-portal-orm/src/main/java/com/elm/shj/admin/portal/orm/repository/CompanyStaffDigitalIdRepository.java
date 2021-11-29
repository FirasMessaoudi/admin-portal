/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffDigitalId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for CompanyStaffDigitalId Table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public interface CompanyStaffDigitalIdRepository extends JpaRepository<JpaCompanyStaffDigitalId, Long> {


}
