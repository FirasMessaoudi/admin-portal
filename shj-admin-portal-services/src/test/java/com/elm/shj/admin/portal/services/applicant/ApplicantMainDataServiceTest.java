package com.elm.shj.admin.portal.services.applicant;

import com.elm.dcc.foundation.commons.core.mapper.MapperRegistry;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantMainData;
import com.elm.shj.admin.portal.orm.repository.ApplicantMainDataRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantMainDataDto;
import com.elm.shj.admin.portal.services.dto.ApplicantMainDataDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing class for service {@link ApplicantMainDataService}
 *
 * @author Ahmed fouad
 * @since 1.1.0
 */
@ExtendWith(MockitoExtension.class)
public class ApplicantMainDataServiceTest {

    private final static String UIN_USER_EXIST = "59737700000059";

    @Mock
    private MapperRegistry mapperRegistry;

    @Mock
    private ApplicantMainDataDtoMapper applicantMainDataDtoMapper;

    @Mock
    private ApplicantMainDataRepository applicantMainDataRepository;

    @InjectMocks
    private ApplicantMainDataService applicantMainDataService;

    @BeforeEach
    public void setUp() {
        Mockito.lenient().when(mapperRegistry.mapperOf(ApplicantMainDataDto.class, JpaApplicantMainData.class)).thenReturn(applicantMainDataDtoMapper);
    }

    @Test
    public void test_find_by_uin_success() {

        JpaApplicantMainData jpaApplicantMainData = new JpaApplicantMainData();
        Mockito.when(applicantMainDataRepository.findByUin(UIN_USER_EXIST)).thenReturn(jpaApplicantMainData);

        ApplicantMainDataDto applicantMainDataDto = new ApplicantMainDataDto();
        Mockito.when(applicantMainDataDtoMapper.fromEntity(Mockito.any(), Mockito.any())).thenReturn(applicantMainDataDto);

        Optional<ApplicantMainDataDto> optionalDto = applicantMainDataService.findByUin(UIN_USER_EXIST, 2);
        assertTrue(optionalDto.isPresent());
    }
}
