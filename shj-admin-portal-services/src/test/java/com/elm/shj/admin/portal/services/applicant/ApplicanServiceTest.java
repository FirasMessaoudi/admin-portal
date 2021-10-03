package com.elm.shj.admin.portal.services.applicant;

import com.elm.dcc.foundation.commons.core.mapper.CycleAvoidingMappingContext;
import com.elm.dcc.foundation.commons.core.mapper.MapperRegistry;
import com.elm.shj.admin.portal.orm.entity.JpaApplicant;
import com.elm.shj.admin.portal.orm.repository.ApplicantContactRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.ApplicantDtoMapper;
import com.elm.shj.admin.portal.services.dto.UpdateApplicantCmd;
import com.elm.shj.admin.portal.services.generic.GenericService;
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
 * Testing class for service {@link ApplicantMainDataService}
 *
 * @author Ahmed fouad
 * @since 1.1.0
 */
@ExtendWith(MockitoExtension.class)
public class ApplicanServiceTest {

    private final static String TEST_UIN = "50208700000027";
    private final static long TEST_APPLICANT_ID = 36;
    private final static String TEST_LOCAL_MOBILE = "0555359285";
    private final static String TEST_INTL_MOBILE = "00201154785699";
    @Mock
    private MapperRegistry mapperRegistry;
    @Mock
    private ApplicantDtoMapper applicantDtoMapper;

    @Mock
    private ApplicantRitualRepository applicantRitualRepository;
    @Mock
    private ApplicantContactRepository applicantContactRepository;
    @Mock
    private ApplicantRepository applicantRepository;
    @InjectMocks
    private ApplicantService applicantService;

    @Mock
    private GenericService genericService;
    @Mock
    private CycleAvoidingMappingContext mappingContext;


    @Test
    public void test_update_applicant_local_contacts() {
        UpdateApplicantCmd command = new UpdateApplicantCmd();
        command.setUin(TEST_UIN);
        command.setMobileNumber(TEST_LOCAL_MOBILE);
        applicantService.updateApplicantContacts(TEST_APPLICANT_ID, command);
        verify(applicantContactRepository, times(1)).updateContactLocalNumber(any(), any(), anyString(), anyLong(), anyLong());
    }

    @Test
    public void test_update_applicant_intl_contacts() {
        UpdateApplicantCmd command = new UpdateApplicantCmd();
        command.setUin(TEST_UIN);
        command.setMobileNumber(TEST_INTL_MOBILE);
        applicantService.updateApplicantContacts(TEST_APPLICANT_ID, command);
        verify(applicantContactRepository, times(1)).updateContactIntlNumber(any(), any(), anyString(), anyLong(), anyLong());
    }

    @Test
    public void test_find_by_uin_success() {
        JpaApplicant jpaApplicant = new JpaApplicant();
        ApplicantDto foundDto = new ApplicantDto();
        Mockito.when(applicantRepository.findByUin(TEST_UIN)).thenReturn(jpaApplicant);
        applicantService.setMapperRegistry(mapperRegistry);
//       Mockito.when(applicantService.getMapperRegistry()).thenReturn(mapperRegistry);
//       Mockito.when(applicantDtoMapper.fromEntity(any(), any())).thenReturn(foundDto);
        Optional<ApplicantDto> optionalDto = applicantService.findByUin(TEST_UIN);
        assertFalse(optionalDto.isPresent());
    }
}
