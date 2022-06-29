/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealthSpecialNeedsBasic;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link ApplicantHealthSpecialNeedsBasicDto} class
 *
 * @author f.messaoudi
 * @since 1.3.0
 */
@Mapper(componentModel = "spring")
public abstract class ApplicantHealthSpecialNeedsBasicDtoMapper implements IGenericMapper<ApplicantHealthSpecialNeedsBasicDto, JpaApplicantHealthSpecialNeedsBasic> {
}
