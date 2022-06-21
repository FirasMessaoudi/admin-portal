/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaComplaintAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for complaint attachment table.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
public interface ComplaintAttachmentRepository extends JpaRepository<JpaComplaintAttachment, Long> {
}
