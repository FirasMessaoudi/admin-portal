/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantLite;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link ApplicantLiteDto} class
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Mapper(componentModel = "spring")
public abstract class ApplicantLiteDtoMapper implements IGenericMapper<ApplicantLiteDto, JpaApplicantLite>, HibernateAwareMapper {
}
