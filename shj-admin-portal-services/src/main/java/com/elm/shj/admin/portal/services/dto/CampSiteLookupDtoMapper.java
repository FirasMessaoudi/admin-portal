/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaCampCategoryLookup;
import com.elm.shj.admin.portal.orm.entity.JpaCampSiteLookup;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link CampSiteLookupDto} class
 *
 * @author salzoubi
 * @since 1.1.0
 */

@Mapper(componentModel = "spring")
public abstract class CampSiteLookupDtoMapper implements IGenericMapper<CampSiteLookupDto, JpaCampSiteLookup> {
}
