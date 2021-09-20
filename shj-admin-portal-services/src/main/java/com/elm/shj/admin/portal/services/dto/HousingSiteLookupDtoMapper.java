/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaHousingSiteLookup;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link HousingSiteLookupDto} class
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
@Mapper(componentModel = "spring")
public abstract class HousingSiteLookupDtoMapper implements IGenericMapper<HousingSiteLookupDto, JpaHousingSiteLookup> {
}
