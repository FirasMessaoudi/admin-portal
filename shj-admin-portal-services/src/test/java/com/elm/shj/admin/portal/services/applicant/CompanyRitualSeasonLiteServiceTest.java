package com.elm.shj.admin.portal.services.applicant;

import com.elm.dcc.foundation.commons.core.mapper.MapperRegistry;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualSeasonLite;
import com.elm.shj.admin.portal.orm.repository.CompanyRitualSeasonLiteRepository;
import com.elm.shj.admin.portal.services.dto.CompanyRitualSeasonLiteDto;
import com.elm.shj.admin.portal.services.dto.CompanyRitualSeasonLiteMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

/**
 * Testing class for service {@link CompanyRitualSeasonLiteService}
 *
 * @author Ahmed fouad
 * @since 1.1.0
 */
@ExtendWith(MockitoExtension.class)
public class CompanyRitualSeasonLiteServiceTest {

    private final static long TEST_UIN = 50208700000027L;

    @Mock
    private MapperRegistry mapperRegistry;
    @Mock
    private CompanyRitualSeasonLiteMapper companyRitualSeasonLiteMapper;

    @Mock
    private CompanyRitualSeasonLiteRepository companyRitualSeasonRepository;

    @InjectMocks
    private CompanyRitualSeasonLiteService companyRitualSeasonLiteService;

    @BeforeEach
    public void setUp() throws IllegalAccessException {
        Mockito.lenient().when(mapperRegistry.mapperOf(CompanyRitualSeasonLiteDto.class, JpaCompanyRitualSeasonLite.class)).thenReturn(companyRitualSeasonLiteMapper);

        Field mapperRegistryField = ReflectionUtils.findField(companyRitualSeasonLiteService.getClass(), "mapperRegistry");
        Field repositoryField = ReflectionUtils.findField(companyRitualSeasonLiteService.getClass(), "repository");

        ReflectionUtils.makeAccessible(mapperRegistryField);
        ReflectionUtils.makeAccessible(repositoryField);

        mapperRegistryField.set(companyRitualSeasonLiteService, mapperRegistry);
        repositoryField.set(companyRitualSeasonLiteService, companyRitualSeasonRepository);
    }

    @Test
    public void test_get_latest_company_ritual_season_by_applicantUin_success() {

        JpaCompanyRitualSeasonLite jpaCompanyRitualSeason = new JpaCompanyRitualSeasonLite();
        CompanyRitualSeasonLiteDto companyRitualSeasonLiteDto = new CompanyRitualSeasonLiteDto();

        Mockito.when(companyRitualSeasonRepository.findTopByRitualPackagesApplicantPackagesApplicantUinOrderBySeasonStartDesc(anyLong())).thenReturn(jpaCompanyRitualSeason);
        Mockito.when(companyRitualSeasonLiteMapper.fromEntity(any(), any())).thenReturn(companyRitualSeasonLiteDto);

        CompanyRitualSeasonLiteDto resultDto = companyRitualSeasonLiteService.getLatestCompanyRitualSeasonByApplicantUin(TEST_UIN);

        assertNotNull(resultDto);
        assertEquals(companyRitualSeasonLiteDto, resultDto);
    }

    @Test
    public void test_get_latest_company_ritual_season_by_applicantUin_notFound() {


        Mockito.when(companyRitualSeasonRepository.findTopByRitualPackagesApplicantPackagesApplicantUinOrderBySeasonStartDesc(anyLong())).thenReturn(null);
        Mockito.when(companyRitualSeasonLiteMapper.fromEntity(any(), any())).thenReturn(null);

        CompanyRitualSeasonLiteDto resultDto = companyRitualSeasonLiteService.getLatestCompanyRitualSeasonByApplicantUin(TEST_UIN);

        assertNull(resultDto);
    }
}
