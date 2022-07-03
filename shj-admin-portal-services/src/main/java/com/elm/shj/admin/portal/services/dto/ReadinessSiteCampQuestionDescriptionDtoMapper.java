/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaReadinessSiteCampQuestion;
import com.elm.shj.admin.portal.orm.entity.JpaReadinessSiteCampQuestionDescription;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link ReadinessSiteCampQuestionDescriptionDto} class
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Mapper(componentModel = "spring")
public abstract class ReadinessSiteCampQuestionDescriptionDtoMapper implements IGenericMapper<ReadinessSiteCampQuestionDescriptionDto, JpaReadinessSiteCampQuestionDescription> {
}
