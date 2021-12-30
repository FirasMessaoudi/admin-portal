/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Repository for Notification Request.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface NotificationRequestRepository extends JpaRepository<JpaNotificationRequest, Long> {
    @Query(value = "select nr from JpaNotificationRequest nr where  nr.processingStatus.code = :statusCode  and nr.sendingDate <= :processingDate")
    Page<JpaNotificationRequest> findNotificationRequests(Pageable pageable, @Param("statusCode") String statusCode, @Param("processingDate") Date processingDate);
}
