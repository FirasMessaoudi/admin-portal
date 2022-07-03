/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealthDiseaseBasic;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link ApplicantHealthDiseaseBasicDto} class
 *
 * @author f.messaoudi
 * @since 1.3.0
 */
@Mapper(componentModel = "spring")
public abstract class ApplicantHealthDiseaseBasicDtoMapper implements IGenericMapper<ApplicantHealthDiseaseBasicDto, JpaApplicantHealthDiseaseBasic> {
}
