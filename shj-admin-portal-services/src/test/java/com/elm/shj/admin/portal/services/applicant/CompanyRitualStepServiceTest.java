/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.dcc.foundation.commons.core.mapper.MapperRegistry;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStep;
import com.elm.shj.admin.portal.orm.repository.CompanyRitualStepRepository;
import com.elm.shj.admin.portal.services.company.CompanyRitualStepService;
import com.elm.shj.admin.portal.services.dto.CompanyRitualStepDto;
import com.elm.shj.admin.portal.services.dto.CompanyRitualStepDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;


/**
 * Testing class for service {@link CompanyRitualStepService}
 *
 * @author firas messaoudi
 * @since 1.1.0
 */

@ExtendWith(MockitoExtension.class)
public class CompanyRitualStepServiceTest {
    private final static String UIN = "59737700000059";
    private final static long RITUAL_SEASON_ID = 1;
    @InjectMocks
    private CompanyRitualStepService serviceToTest;
    @Mock
    private CompanyRitualStepRepository companyRitualStepRepository;
    @Mock
    private MapperRegistry mapperRegistry;
    @Mock
    private CompanyRitualStepDtoMapper companyRitualStepDtoMapper;
    @BeforeEach
    public void setUp() throws IllegalAccessException {
        Mockito.lenient().when(mapperRegistry.mapperOf(CompanyRitualStepDto.class, JpaCompanyRitualStep.class)).thenReturn(companyRitualStepDtoMapper);

        Field mapperRegistryField = ReflectionUtils.findField(serviceToTest.getClass(), "mapperRegistry");
        Field repositoryField = ReflectionUtils.findField(serviceToTest.getClass(), "repository");

        ReflectionUtils.makeAccessible(mapperRegistryField);
        ReflectionUtils.makeAccessible(repositoryField);

        mapperRegistryField.set(serviceToTest, mapperRegistry);
        repositoryField.set(serviceToTest, companyRitualStepRepository);
    }

    @Test
    public void test_find_company_ritual_step_by_uin_success(){
        List<JpaCompanyRitualStep> jpaCompanyRitualStepList = new ArrayList<>();
        List<CompanyRitualStepDto> companyRitualStepDtos = new ArrayList<>();
        Mockito.when(companyRitualStepRepository.findByApplicantGroupIdOrderByStepIndexAsc(anyLong())).thenReturn(jpaCompanyRitualStepList);
        Mockito.when(companyRitualStepDtoMapper.fromEntityList(any(), any())).thenReturn(companyRitualStepDtos);

        List<CompanyRitualStepDto> result = serviceToTest.findCompanyRitualStepsByApplicantUin(UIN);
        assertEquals(1,result.size());
    }
    @Test
    public void test_find_company_ritual_step_by_uin_fail(){
        Mockito.when(companyRitualStepRepository.findByApplicantGroupIdOrderByStepIndexAsc(anyLong())).thenReturn(null);

        List<CompanyRitualStepDto> result = serviceToTest.findCompanyRitualStepsByApplicantUin(UIN);
        assertNull(result);
    }
}
