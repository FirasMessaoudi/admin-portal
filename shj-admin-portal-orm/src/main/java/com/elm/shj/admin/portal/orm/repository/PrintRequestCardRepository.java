/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestBatchCard;
import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for print request card table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
public interface PrintRequestCardRepository extends JpaRepository<JpaPrintRequestCard, Long> {
    int countAllByPrintRequestId(long printRequestId);

    List<JpaPrintRequestCard> findAllByPrintRequestId(long printRequestId);
}
