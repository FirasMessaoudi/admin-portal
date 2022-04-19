/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.audit;

import com.elm.shj.admin.portal.orm.entity.JpaMobileAuditLog;
import com.elm.shj.admin.portal.orm.repository.MobileAuditLogRepository;
import com.elm.shj.admin.portal.services.dto.MobileAuditLogDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service handling mobile audit logs
 *
 * @author Noor Nawaz
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MobileAuditLogService extends GenericService<JpaMobileAuditLog, MobileAuditLogDto, Long> {

    private final MobileAuditLogRepository mobileAuditLogRepository;

}
