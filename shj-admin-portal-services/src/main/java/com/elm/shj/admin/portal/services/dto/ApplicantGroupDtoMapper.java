/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;


import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantGroup;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link ApplicantGroupDto} class
 *
 * @author jaafer jarray
 * @since 1.1.0
 */

@Mapper(componentModel = "spring")
public abstract class ApplicantGroupDtoMapper implements IGenericMapper<ApplicantGroupDto, JpaApplicantGroup> {
}
