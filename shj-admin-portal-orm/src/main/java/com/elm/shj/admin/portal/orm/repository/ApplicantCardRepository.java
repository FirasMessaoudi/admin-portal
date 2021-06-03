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

    //TODO Convert sql statement to jpql statement
    @Query(nativeQuery = true, value = "SELECT * FROM shj_portal.sha_applicant_card card " +
            "LEFT JOIN shj_portal.sha_applicant_ritual ar ON card.applicant_ritual_id = ar.id " +
            "LEFT JOIN shj_portal.sha_applicant a ON ar.applicant_id = a.id WHERE card.id NOT IN " +
            "(SELECT c.id FROM shj_portal.sha_applicant_card c LEFT JOIN shj_portal.sha_print_request_card prc ON " +
            "c.id = prc.card_id LEFT JOIN shj_portal.sha_print_request pr ON prc.print_request_id = pr.id WHERE " +
            "pr.status_code != :printRequestStatus OR c.status_code != :cardStatus) " +
            "AND card.id NOT IN :excludedCardsIds AND (a.id_number LIKE :idNumber OR :idNumber IS NULL) " +
            "AND (a.passport_number = :passportNumber OR :passportNumber IS NULL) AND (a.nationality_code = :nationalityCode OR :nationalityCode IS NULL)")
    Page<JpaApplicantCard> findPrintingCards(@Param("cardStatus") String cardStatus, @Param("printRequestStatus") String printRequestStatus,
                                             @Param("idNumber") String idNumber, @Param("passportNumber") String passportNumber,
                                             @Param("nationalityCode") String nationalityCode,
                                             @Param("excludedCardsIds") List<Long> excludedCardsIds, Pageable pageable);
}
