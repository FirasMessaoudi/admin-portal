/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffDigitalIdBasic;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link CompanyStaffDigitalIdBasicDto} class
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@Mapper(componentModel = "spring")
public abstract class CompanyStaffDigitalIdBasicDtoMapper implements IGenericMapper<CompanyStaffDigitalIdBasicDto, JpaCompanyStaffDigitalIdBasic> , HibernateAwareMapper {

}
