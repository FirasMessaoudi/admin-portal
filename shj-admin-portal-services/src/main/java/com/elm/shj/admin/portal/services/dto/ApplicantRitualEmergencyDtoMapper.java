package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ApplicantRitualEmergencyDtoMapper implements IGenericMapper<ApplicantRitualEmergencyDto, JpaApplicantRitual> {

}
