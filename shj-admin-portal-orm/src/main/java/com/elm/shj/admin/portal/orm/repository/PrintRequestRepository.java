/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for print request table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
public interface PrintRequestRepository extends JpaRepository<JpaPrintRequest, Long> {
}
