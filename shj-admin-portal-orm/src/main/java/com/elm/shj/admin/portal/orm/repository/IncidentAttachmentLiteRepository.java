/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaIncidentAttachmentLite;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for incident attachment table.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface IncidentAttachmentLiteRepository extends JpaRepository<JpaIncidentAttachmentLite, Long> {
}
