/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaIncidentStatusLookup;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link IncidentStatusLookupDto} class
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Mapper(componentModel = "spring")
public abstract class IncidentStatusLookupDtoMapper implements IGenericMapper<IncidentStatusLookupDto, JpaIncidentStatusLookup> {
}
