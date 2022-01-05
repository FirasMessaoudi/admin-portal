/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaGroupApplicantList;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link GroupApplicantListDto} class
 *
 * @author jaafer jarray
 * @since 1.1.0
 */

@Mapper(componentModel = "spring")
public abstract class GroupApplicantListDtoMapper implements IGenericMapper<GroupApplicantListDto, JpaGroupApplicantList>, HibernateAwareMapper {
}
