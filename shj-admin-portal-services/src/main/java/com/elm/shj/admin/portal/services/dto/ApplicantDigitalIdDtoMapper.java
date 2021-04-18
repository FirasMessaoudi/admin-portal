/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantDigitalId;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link ApplicantDigitalIdDto} class
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Mapper(componentModel = "spring")
public abstract class ApplicantDigitalIdDtoMapper implements IGenericMapper<ApplicantDigitalIdDto, JpaApplicantDigitalId> {
}
