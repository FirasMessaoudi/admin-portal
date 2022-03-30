/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.Optional;

/**
 * Repository for print request batch table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
public interface PrintRequestBatchRepository extends JpaRepository<JpaPrintRequestBatch, Long> {
    int countAllByPrintRequestId(long printRequestId);

    @Query("select max (p.sequenceNumber) from JpaPrintRequestBatch p")
    Integer maxSequenceNumber();

    @Query("SELECT distinct prb FROM JpaPrintRequestBatch prb JOIN prb.printRequestBatchCards prc " +
            "JOIN JpaApplicantCard card ON prc.cardId = card.id " +
            "WHERE prb.printRequest.id=:printRequestId")
    List<JpaPrintRequestBatch> findPrintRequestBatches(@Param("printRequestId") long printRequestId);

    @Query("SELECT distinct prb FROM JpaPrintRequestBatch prb JOIN prb.printRequestBatchCards prc " +
            "JOIN JpaCompanyStaffCard sc ON prc.cardId = sc.id " +
            "WHERE prb.printRequest.id=:printRequestId")
    List<JpaPrintRequestBatch> findStaffPrintRequestBatches(@Param("printRequestId") long printRequestId);

    Optional<JpaPrintRequestBatch> findBySequenceNumberAndPrintRequestReferenceNumber(Integer sequenceNumber, String printRequestReferenceNumber);


}
