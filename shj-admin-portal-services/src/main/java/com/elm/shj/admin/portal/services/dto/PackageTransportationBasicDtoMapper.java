/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaPackageTransportationBasic;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link PackageTransportationBasicDto} class
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@Mapper(componentModel = "spring")
public abstract class PackageTransportationBasicDtoMapper implements IGenericMapper<PackageTransportationBasicDto, JpaPackageTransportationBasic> {
}

