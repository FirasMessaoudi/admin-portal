package com.elm.shj.admin.portal.services.applicant;


import com.elm.dcc.foundation.commons.core.mapper.MapperRegistry;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaff;
import com.elm.shj.admin.portal.orm.repository.CompanyStaffRepository;
import com.elm.shj.admin.portal.services.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CompanyStaffServiceTest {

    private final static String TEST_UIN = "50208700000027";
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

    }

    @Test
    public void test_find_related_employees_byApplicantUin_success() {

        List<JpaCompanyStaff> jpaCompanyStaff = new ArrayList<JpaCompanyStaff>();
        List<CompanyStaffDto> companyStaffDto = new ArrayList<CompanyStaffDto>();

        Mockito.when(companyStaffRepository.findRelatedEmployeesByUinAndSeasonId(anyString(), anyLong())).thenReturn(jpaCompanyStaff);
        List<CompanyStaffDto> resultDto = companyStaffService.findRelatedEmployeesByApplicantUinAndSeasonId(TEST_UIN, TEST_SEASON);

        assertNotNull(resultDto);
        assertEquals(companyStaffDto, resultDto);
    }

}
