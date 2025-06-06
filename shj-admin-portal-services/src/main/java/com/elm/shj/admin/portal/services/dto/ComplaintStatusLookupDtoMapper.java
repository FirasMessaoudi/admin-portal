
/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaComplaintStatusLookup;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link ComplaintStatusLookupDto} class
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Mapper(componentModel = "spring")
public abstract class ComplaintStatusLookupDtoMapper implements IGenericMapper<ComplaintStatusLookupDto, JpaComplaintStatusLookup> {
}
