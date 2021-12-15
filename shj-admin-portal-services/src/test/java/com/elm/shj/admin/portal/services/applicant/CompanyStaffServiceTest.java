package com.elm.shj.admin.portal.services.applicant;


import com.elm.dcc.foundation.commons.core.mapper.MapperRegistry;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaff;
import com.elm.shj.admin.portal.orm.repository.CompanyStaffRepository;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.CompanyStaffDto;
import com.elm.shj.admin.portal.services.dto.CompanyStaffMapper;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CompanyStaffServiceTest {

    private final static String TEST_UIN = "30208700000014";
    private final static int TEST_SEASON = 1443;


    @Mock
    private MapperRegistry mapperRegistry;
    @Mock
    private CompanyStaffMapper companyStaffMapper;

    @Mock
    private CompanyStaffRepository companyStaffRepository;

    @InjectMocks
    private CompanyStaffService companyStaffService;



    @BeforeEach
    public void setUp() throws IllegalAccessException {
        Mockito.lenient().when(mapperRegistry.mapperOf(CompanyStaffDto.class, JpaCompanyStaff.class)).thenReturn(companyStaffMapper);

        Field mapperRegistryField = ReflectionUtils.findField(companyStaffService.getClass(), "mapperRegistry");
        Field repositoryField = ReflectionUtils.findField(companyStaffService.getClass(), "repository");

        ReflectionUtils.makeAccessible(mapperRegistryField);
        ReflectionUtils.makeAccessible(repositoryField);

        mapperRegistryField.set(companyStaffService, mapperRegistry);
        repositoryField.set(companyStaffService, companyStaffRepository);
    }

    @Test
    public void test_find_related_employees_byApplicantUin_success() {

        List<JpaCompanyStaff> jpaCompanyStaff = new ArrayList<JpaCompanyStaff>();
        List<CompanyStaffDto> companyStaffDto = new ArrayList<CompanyStaffDto>();

        Mockito.when(companyStaffRepository.findByApplicantGroupsGroupApplicantListsApplicantUinAndApplicantGroupsCompanyRitualSeasonId(anyString(), anyLong())).thenReturn(jpaCompanyStaff);
        Mockito.lenient().when(companyStaffMapper.fromEntityList(any(), any())).thenReturn(companyStaffDto);

        List<CompanyStaffDto> resultDto = companyStaffService.findRelatedEmployeesByApplicantUinAndSeasonId(TEST_UIN, TEST_SEASON);

        assertNotNull(resultDto);
        assertEquals(companyStaffDto, resultDto);
    }

    @Test
    public void test_find_related_employees_byApplicantUin_not_found(){
        List<JpaCompanyStaff> jpaCompanyStaff = new ArrayList<JpaCompanyStaff>();
        jpaCompanyStaff.clear();
        Mockito.when(companyStaffRepository.findByApplicantGroupsGroupApplicantListsApplicantUinAndApplicantGroupsCompanyRitualSeasonId(anyString(), anyLong())).thenReturn(jpaCompanyStaff);

        List<CompanyStaffDto> resultDto = companyStaffService.findRelatedEmployeesByApplicantUinAndSeasonId(TEST_UIN, TEST_SEASON);
        assertTrue(resultDto.isEmpty());

    }

}
