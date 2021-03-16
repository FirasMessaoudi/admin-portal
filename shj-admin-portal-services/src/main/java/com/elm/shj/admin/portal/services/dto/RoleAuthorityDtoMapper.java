/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaRoleAuthority;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link RoleAuthorityDto} class
 *
 * @author ahmad flaifel
 * @since 1.8.0
 */
@Mapper(componentModel = "spring")
public abstract class RoleAuthorityDtoMapper implements IGenericMapper<RoleAuthorityDto, JpaRoleAuthority> {
}
