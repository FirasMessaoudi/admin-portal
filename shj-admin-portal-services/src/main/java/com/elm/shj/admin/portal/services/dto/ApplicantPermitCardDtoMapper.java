package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantPermitCard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ApplicantPermitCardDtoMapper implements IGenericMapper<ApplicantPermitCardDto, JpaApplicantPermitCard>, HibernateAwareMapper {
}
