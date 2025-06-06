/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaPrintBatchTypeLookup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for print batch type lookup table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
public interface PrintBatchTypeRepository extends JpaRepository<JpaPrintBatchTypeLookup, Long> {
    List<JpaPrintBatchTypeLookup> findAllByTarget(String target);
}
