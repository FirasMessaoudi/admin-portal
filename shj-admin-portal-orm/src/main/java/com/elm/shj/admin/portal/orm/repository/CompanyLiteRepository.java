/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyLite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for company data.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 **/
public interface CompanyLiteRepository extends JpaRepository<JpaCompanyLite, Long> {

    boolean existsByCode(String code);

    List<JpaCompanyLite> findByTypeCode(String typeCode);

}
