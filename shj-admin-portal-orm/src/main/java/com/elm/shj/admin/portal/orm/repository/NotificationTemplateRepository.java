/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;

/**
 * Repository for Notification Template.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface NotificationTemplateRepository extends JpaRepository<JpaNotificationTemplate, Long>, JpaSpecificationExecutor<JpaNotificationTemplate> {
    JpaNotificationTemplate findByNameCodeAndEnabledTrue(String nameCode);

    Page<JpaNotificationTemplate> findByTypeCodeAndSendingDateBeforeAndProcessedAndEnabled(String typeCode, Date date, Boolean isProcessed, boolean enabled, Pageable pageable);
}
