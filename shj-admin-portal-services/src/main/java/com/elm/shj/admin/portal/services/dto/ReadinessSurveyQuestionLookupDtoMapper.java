/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestStatusLookup;
import com.elm.shj.admin.portal.orm.entity.JpaReadinessSurveyQuestionLookup;
import org.mapstruct.Mapper;

import java.io.Serializable;
import java.util.Date;

/**
 * Mapper for {@link ReadinessSurveyQuestionLookupDto} class
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Mapper(componentModel = "spring")
public abstract class ReadinessSurveyQuestionLookupDtoMapper implements IGenericMapper<ReadinessSurveyQuestionLookupDto, JpaReadinessSurveyQuestionLookup> {
}
