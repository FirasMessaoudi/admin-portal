/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageHousing;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageHousingBasic;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link ApplicantPackageHousingBasicDto } class
 *
 * @author rameez imtiaz
 * @since 1.2.0
 */
@Mapper(componentModel = "spring")
public abstract class ApplicantPackageHousingBasicDtoMapper implements IGenericMapper<ApplicantPackageHousingBasicDto, JpaApplicantPackageHousingBasic>, HibernateAwareMapper {
}
