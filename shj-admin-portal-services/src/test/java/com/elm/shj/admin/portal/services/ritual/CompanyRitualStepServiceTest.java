/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.ritual;

import com.elm.dcc.foundation.commons.core.mapper.MapperRegistry;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStep;
import com.elm.shj.admin.portal.orm.repository.CompanyRitualStepRepository;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;


/**
 * Testing class for service {@link CompanyRitualStepService}
 *
 * @author firas messaoudi
 * @since 1.1.0
 */

@ExtendWith(MockitoExtension.class)
public class CompanyRitualStepServiceTest {

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
    public void test_find_company_ritual_step_by_uin(){
    serviceToTest.findByApplicantUin(anyString());
    verify(companyRitualStepRepository).findByApplicantGroupId(anyLong());
    }
}
