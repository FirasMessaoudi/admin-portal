/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaAuditLog;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link MobileAuditLogDto} class
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Mapper(componentModel = "spring")
public abstract class MobileAuditLogDtoMapper implements IGenericMapper<MobileAuditLogDto, JpaAuditLog> {
}
