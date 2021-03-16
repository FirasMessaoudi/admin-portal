/*
 * Copyright (c) 2019 ELM. All rights reserved.
 */

package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaUser;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link UserDto} class
 *
 * @author Aymen DHAOUI
 * @since 1.5.0
 */
@Mapper(componentModel = "spring")
public abstract class UserDtoMapper implements IGenericMapper<UserDto, JpaUser> {
}
