/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaDataRequest;
import com.elm.shj.admin.portal.orm.entity.JpaDataSegment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for DataRequest Table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public interface DataRequestRepository extends JpaRepository<JpaDataRequest, Long> {

    @Modifying
    @Query("update JpaDataRequest dr set dr.status.id = :dataRequestStatusId, dr.updateDate = CURRENT_TIMESTAMP where dr.id = :dataRequestId")
    void updateStatus(@Param("dataRequestId") long dataRequestId, @Param("dataRequestStatusId") long dataRequestStatusId);

    @Modifying
    @Query("update JpaDataRequest dr set dr.status.id = :dataRequestStatusId, dr.errorFilePath = :errorFilePath, dr.errorCount = :errorCount, dr.updateDate = CURRENT_TIMESTAMP where dr.id = :dataRequestId")
    void updateProcessingStatus(@Param("dataRequestId") long dataRequestId, @Param("dataRequestStatusId") long dataRequestStatusId, @Param("errorFilePath") String errorFilePath, @Param("errorCount") long errorCount);

    @Query("SELECT dr FROM JpaDataRequest dr WHERE dr.dataSegment.id in :ids")
    Page<JpaDataRequest> finDataRequests(@Param("ids") List<Long> ids, Pageable pageable);

    @Query("SELECT dr FROM JpaDataRequest dr WHERE dr.dataSegment.id in :ids order by dr.creationDate desc ")
    Page<JpaDataRequest> finOrganizerDataRequests(@Param("ids") List<Long> ids, Pageable pageable);

}
