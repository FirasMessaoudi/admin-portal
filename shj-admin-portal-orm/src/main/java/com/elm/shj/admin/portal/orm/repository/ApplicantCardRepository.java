/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for Applicant Card Table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public interface ApplicantCardRepository extends JpaRepository<JpaApplicantCard, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM shj_portal.sha_applicant_card card WHERE card.id NOT IN (SELECT c.id FROM shj_portal.sha_applicant_card c LEFT JOIN shj_portal.sha_print_request_card prc ON c.id = prc.card_id LEFT JOIN shj_portal.sha_print_request pr ON prc.print_request_id = pr.id WHERE pr.status_code != 'NEW' OR c.status_code != 'READY_TO_PRINT')")
    Page<JpaApplicantCard> findPrintingCards(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM shj_portal.sha_applicant_card card WHERE card.id NOT IN (SELECT c.id FROM shj_portal.sha_applicant_card c LEFT JOIN shj_portal.sha_print_request_card prc ON c.id = prc.card_id LEFT JOIN shj_portal.sha_print_request pr ON prc.print_request_id = pr.id WHERE pr.status_code != 'NEW' OR c.status_code != 'READY_TO_PRINT') AND card.id NOT IN :excludedCardsIds")
    Page<JpaApplicantCard> findPrintingCards(@Param("excludedCardsIds") List<Long> excludedCardsIds, Pageable pageable);
}
