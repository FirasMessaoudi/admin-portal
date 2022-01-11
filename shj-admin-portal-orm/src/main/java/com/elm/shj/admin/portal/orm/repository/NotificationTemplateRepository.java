/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

/**
 * Repository for Notification Template.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface NotificationTemplateRepository extends JpaRepository<JpaNotificationTemplate, Long>, JpaSpecificationExecutor<JpaNotificationTemplate> {
    JpaNotificationTemplate findByNameCodeAndEnabledTrue(String nameCode);

    List<JpaNotificationTemplate> findByTypeCodeAndSendingDateBeforeAndIsProcessedAndEnabled(String typeCode, Date date, Boolean isProcessed, boolean enabled);
}
