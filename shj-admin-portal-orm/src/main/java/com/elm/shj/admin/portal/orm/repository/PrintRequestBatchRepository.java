/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    Optional<JpaPrintRequestBatch> findBySequenceNumberAndPrintRequestReferenceNumber(Integer sequenceNumber, String printRequestReferenceNumber);


}
