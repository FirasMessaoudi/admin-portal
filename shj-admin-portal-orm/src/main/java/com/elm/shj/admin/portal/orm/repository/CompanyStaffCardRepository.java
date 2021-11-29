/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffCard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Company Staff Card Table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public interface CompanyStaffCardRepository extends JpaRepository<JpaCompanyStaffCard, Long> {

}
