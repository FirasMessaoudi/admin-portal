/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaff;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Company Staff data.
 *
 * @author salzoubi
 * @since 1.1.0
 **/
public interface CompanyStaffRepository extends JpaRepository<JpaCompanyStaff, Long> {

}
