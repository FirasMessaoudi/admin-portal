/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.dcc.foundation.commons.core.mapper.CycleAvoidingMappingContext;
import com.elm.dcc.foundation.commons.core.mapper.IGenericMapper;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantComplaint;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for {@link ApplicantComplaintDtoMapper} class
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Mapper(componentModel = "spring")
public abstract class ApplicantComplaintDtoMapper implements IGenericMapper<ApplicantComplaintDto, JpaApplicantComplaint>{
    @Override
    @Mapping(target = "applicantRitual.relatives", ignore = true)
    @Mapping(target = "applicantRitual.applicantHealths", ignore = true)
    @Mapping(target = "applicantRitual.applicant.relatives", ignore = true)
    @Mapping(target = "applicantRitual.applicant.rituals", ignore = true)
    @Mapping(target = "applicantRitual.applicant.contacts", ignore = true)
    public abstract ApplicantComplaintDto fromEntity(JpaApplicantComplaint entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

}
