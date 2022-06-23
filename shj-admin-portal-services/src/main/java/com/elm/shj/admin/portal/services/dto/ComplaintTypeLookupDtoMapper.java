/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaComplaintTypeLookup;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link ComplaintTypeLookupDto} class
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Mapper(componentModel = "spring")
public abstract class ComplaintTypeLookupDtoMapper implements IGenericMapper<ComplaintTypeLookupDto, JpaComplaintTypeLookup> {
}
