/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestLite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository for print request request lite.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface PrintRequestLiteRepository extends JpaRepository<JpaPrintRequestLite, Long> {

    /**
     * Find all print requests with a status code.
     *
     * @param statusCode
     * @param pageable
     * @return Page of print requests
     */
    Page<JpaPrintRequestLite> findByStatusCode(String statusCode, Pageable pageable);

}
