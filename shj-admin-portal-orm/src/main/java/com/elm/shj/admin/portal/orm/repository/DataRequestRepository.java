/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaDataRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

}
