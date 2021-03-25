/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.audit;

import com.elm.shj.admin.portal.orm.entity.JpaAuditLog;
import com.elm.shj.admin.portal.services.dto.AuditLogDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service handling audit logs
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Service
public class AuditLogService extends GenericService<JpaAuditLog, AuditLogDto, Long> {

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public AuditLogDto save(AuditLogDto dto) {
        return super.save(dto);
    }
}
