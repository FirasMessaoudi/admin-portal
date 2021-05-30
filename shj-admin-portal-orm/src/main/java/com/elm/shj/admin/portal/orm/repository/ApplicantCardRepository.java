/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for Applicant Card Table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public interface ApplicantCardRepository extends JpaRepository<JpaApplicantCard, Long> {

    Page<JpaApplicantCard> findByStatusCodeAndIdNotIn(String statusCode,
                                                      List<Long> excludedCardsIds,
                                                      Pageable pageable);

    /**
     * Find applicants cards matching passed status code.
     *
     * @param statusCode
     * @return
     */
    Page<JpaApplicantCard> findByStatusCode(String statusCode, Pageable pageable);
}
