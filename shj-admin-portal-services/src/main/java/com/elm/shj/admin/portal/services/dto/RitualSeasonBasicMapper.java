/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaRitualSeasonBasic;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link RitualSeasonBasicDto} class
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@Mapper(componentModel = "spring")
public abstract class RitualSeasonBasicMapper implements IGenericMapper<RitualSeasonBasicDto, JpaRitualSeasonBasic> {
}
