/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestLite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repository for print request lite.
 * The JpaSpecificationExecutor interface adds methods which allow the execution of Specifications.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface PrintRequestLiteRepository extends JpaRepository<JpaPrintRequestLite, Long>, JpaSpecificationExecutor<JpaPrintRequestLite> {

    Page<JpaPrintRequestLite> findByTarget(String target, Pageable pageable);

}
