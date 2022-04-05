/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaBatchMainCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for batch main collection table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public interface BatchMainCollectionRepository extends JpaRepository<JpaBatchMainCollection, Long> {

    @Query("SELECT b FROM JpaBatchMainCollection b WHERE b.referenceNumber LIKE :referenceNumber || '_' ||'%'")
    List<JpaBatchMainCollection> findBatchStatusByReference(@Param("referenceNumber") String referenceNumber);

    Optional<JpaBatchMainCollection> findTopByReferenceNumberOrderByCreationDateDesc(String referenceNumber);
}
