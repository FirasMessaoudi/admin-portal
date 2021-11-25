/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.dcc.foundation.commons.core.mapper.CycleAvoidingMappingContext;
import com.elm.dcc.foundation.commons.core.mapper.MapperRegistry;
import com.elm.shj.admin.portal.orm.entity.JpaApplicant;
import com.elm.shj.admin.portal.orm.repository.ApplicantContactRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantIncidentRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.ApplicantDtoMapper;
import com.elm.shj.admin.portal.services.dto.UpdateApplicantCmd;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.incident.ApplicantIncidentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Testing class for {@link ApplicantIncidentService}
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@ExtendWith(MockitoExtension.class)
public class ApplicantIncidentServiceTest {
    private final static long EXIST_APPLICANT_RITUAL_ID = 1;

    @Mock
    private MapperRegistry mapperRegistry;
    @Mock
    private ApplicantDtoMapper applicantDtoMapper;

    @Mock
    private ApplicantIncidentRepository applicantIncidentRepository;
    @Mock
    private ApplicantContactRepository applicantContactRepository;
    @Mock
    private ApplicantRepository applicantRepository;
    @InjectMocks
    private ApplicantIncidentService serviceToTest;

    @Mock
    private GenericService genericService;
    @Mock
    private CycleAvoidingMappingContext mappingContext;


    @Test
    public void test_list_applicant_related_incidents() {

        serviceToTest.listApplicantRelatedIncidents(EXIST_APPLICANT_RITUAL_ID);
        verify(applicantIncidentRepository, times(1)).findByApplicantRitualId(anyLong());
    }

}
