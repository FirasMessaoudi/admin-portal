/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaReadinessSiteCampQuestion;
import com.elm.shj.admin.portal.orm.entity.JpaReadinessSurveyQuestionCategoryLookup;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link ReadinessSiteCampQuestionDto} class
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Mapper(componentModel = "spring")
public abstract class ReadinessSiteCampQuestionDtoMapper implements IGenericMapper<ReadinessSiteCampQuestionDto, JpaReadinessSiteCampQuestion> {
}
