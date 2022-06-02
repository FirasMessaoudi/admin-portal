/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaApplicant;
import com.elm.shj.admin.portal.orm.entity.JpaInspectorReadinessSurvey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;

import java.io.Serializable;
import java.util.Date;

/**
 * Mapper for {@link InspectorReadinessSurveyDto} class
 *
 * @author salzoubi
 * @since 1.1.0
 */

@Mapper(componentModel = "spring")
public abstract class InspectorReadinessSurveyDtoMapper  implements IGenericMapper<InspectorReadinessSurveyDto, JpaInspectorReadinessSurvey> {
}
