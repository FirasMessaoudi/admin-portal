/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaSurveyQuestionLookup;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link SurveyQuestionLookupDto} class
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Mapper(componentModel = "spring")
public abstract class SurveyQuestionLookupDtoMapper implements IGenericMapper<SurveyQuestionLookupDto, JpaSurveyQuestionLookup> {
}
