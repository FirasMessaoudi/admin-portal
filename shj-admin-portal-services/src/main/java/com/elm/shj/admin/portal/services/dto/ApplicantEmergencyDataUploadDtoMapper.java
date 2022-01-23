/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantEmergencyDataUpload;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link ApplicantEmergencyDataUploadDto} class
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Mapper(componentModel = "spring")
public abstract class ApplicantEmergencyDataUploadDtoMapper implements IGenericMapper<ApplicantEmergencyDataUploadDto, JpaApplicantEmergencyDataUpload> {
}
