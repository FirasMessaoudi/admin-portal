/*
 * Copyright (c) 2019 ELM. All rights reserved.
 */

package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaUserPasswordHistory;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link UserPasswordHistoryDto} class
 *
 * @author Aymen DHAOUI
 * @since 1.5.0
 */
@Mapper(componentModel = "spring")
public abstract class UserPasswordHistoryDtoMapper implements IGenericMapper<UserPasswordHistoryDto, JpaUserPasswordHistory> {
}
