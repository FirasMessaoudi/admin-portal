package com.elm.shj.admin.portal.services.ritual;

import com.elm.dcc.foundation.commons.core.mapper.MapperRegistry;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualCardLiteDto;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualCardLiteDtoMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

/**
 * Testing class for service {@link ApplicantRitualService}
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
@ExtendWith(MockitoExtension.class)
public class ApplicantRitualCardLiteServiceTest {

    private final static String EXIST_USER_UIN = "50208700000027";
    private final static String FAKE_USER_UIN = "50208700000000";
    private final static String EXIST_RITUAL_ID = "36";
    @Mock
    private MapperRegistry mapperRegistry;

    @Mock
    private ApplicantRitualCardLiteDtoMapper applicantRitualCardLiteDtoMapper;

    @Mock
    private ApplicantRitualRepository applicantRitualRepository;

    @InjectMocks
    private ApplicantRitualCardLiteService applicantRitualCardLiteService;

    @BeforeEach
    public void setUp() {
        Mockito.lenient().when(mapperRegistry.mapperOf(ApplicantRitualCardLiteDto.class, JpaApplicantRitual.class)).thenReturn(applicantRitualCardLiteDtoMapper);
    }

    @Test
    public void test_find_applicant_card_details_by_uin_success() {
        JpaApplicantRitual applicantRituals = new JpaApplicantRitual();
        applicantRituals.setBorderNumber("2");
        Mockito.when(applicantRitualRepository.findCardDetailsByUinAndRitualId(EXIST_USER_UIN, Long.parseLong(EXIST_RITUAL_ID))).thenReturn(applicantRituals);
        Optional<ApplicantRitualCardLiteDto> applicantRitualLiteDtos = applicantRitualCardLiteService.findCardDetailsByUinAndRitualId(EXIST_USER_UIN, EXIST_RITUAL_ID);
        Assert.assertNotNull(applicantRitualLiteDtos);
    }


    @Test
    public void test_find_applicant_card_details_by_uin_not_found() {
        Mockito.when(applicantRitualRepository.findCardDetailsByUinAndRitualId(FAKE_USER_UIN, Long.parseLong(EXIST_RITUAL_ID))).thenReturn(null);
        Optional<ApplicantRitualCardLiteDto> returnedCard = applicantRitualCardLiteService.findCardDetailsByUinAndRitualId(FAKE_USER_UIN, EXIST_RITUAL_ID);
        Assert.assertEquals(returnedCard, Optional.empty());
    }
}
