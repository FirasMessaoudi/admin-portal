/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantCard;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantCardBasic;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link ApplicantCardBasicDto} class
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Mapper(componentModel = "spring")
public abstract class ApplicantCardBasicDtoMapper implements IGenericMapper<ApplicantCardBasicDto, JpaApplicantCardBasic>, HibernateAwareMapper {
}
