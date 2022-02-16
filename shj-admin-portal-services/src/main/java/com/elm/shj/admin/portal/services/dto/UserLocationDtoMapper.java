/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaUserLocation;
import org.mapstruct.Mapper;

/**
 * Mapper class for user location domain.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Mapper(componentModel = "spring")
public abstract class UserLocationDtoMapper implements IGenericMapper<UserLocationDto, JpaUserLocation> {
}
