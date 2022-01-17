/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Repository for print request table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
public interface PrintRequestRepository extends JpaRepository<JpaPrintRequest, Long>, JpaSpecificationExecutor<JpaPrintRequest> {
    Page<JpaPrintRequest> findByTarget(String target, Pageable pageable);

    @Query(value = "SELECT distinct pr FROM JpaPrintRequest pr JOIN pr.printRequestBatches prb JOIN pr.printRequestCards prc JOIN JpaApplicantCard ac " +
            "on prc.cardId = ac.id JOIN ac.applicantRitual ar JOIN ar.applicant a JOIN a.digitalIds di WHERE pr.id = prb.printRequest.id AND " +
            "(:statusCode is null or pr.statusCode like %:statusCode%) AND pr.target = 'APPLICANT' AND " +
            "(:description is null or pr.description like %:description%) AND (:referenceNumber is null or pr.referenceNumber like %:referenceNumber%) AND " +
            "(:batchNumber = -1L or prb.sequenceNumber = :batchNumber) AND (:cardNumber is null or ac.referenceNumber like %:cardNumber%) AND " +
            "(:uin is null or di.uin like %:uin%) AND (:endDate is null or :startDate is null or pr.creationDate BETWEEN :startDate AND :endDate)")
    Page<JpaPrintRequest> findByFilters(@Param("statusCode") String statusCode, @Param("description") String description,
                                        @Param("referenceNumber") String referenceNumber, @Param("batchNumber") long batchNumber,
                                        @Param("cardNumber") String cardNumber, @Param("uin") String uin,
                                        @Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);
}
