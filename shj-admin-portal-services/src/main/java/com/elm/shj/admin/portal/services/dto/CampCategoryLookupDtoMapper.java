/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaCampCategoryLookup;
import com.elm.shj.admin.portal.orm.entity.JpaInspectorReadinessSurvey;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link CampCategoryLookupDto} class
 *
 * @author salzoubi
 * @since 1.1.0
 */

@Mapper(componentModel = "spring")
public abstract class CampCategoryLookupDtoMapper implements IGenericMapper<CampCategoryLookupDto, JpaCampCategoryLookup> {
}
