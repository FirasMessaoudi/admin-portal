package com.elm.shj.admin.portal.services.ritual;

import com.elm.dcc.foundation.commons.core.mapper.MapperRegistry;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualLiteDto;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualLiteDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Testing class for service {@link ApplicantRitualService}
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
@ExtendWith(MockitoExtension.class)
public class ApplicantRitualLiteServiceTest {

    private final static String EXIST_USER_UIN = "59737700000059";

    @Mock
    private MapperRegistry mapperRegistry;

    @Mock
    private ApplicantRitualLiteDtoMapper applicantRitualLiteDtoMapper;

    @Mock
    private ApplicantRitualRepository applicantRitualRepository;

    @InjectMocks
    private ApplicantRitualLiteService applicantRitualLiteService;

    @BeforeEach
    public void setUp() {
        Mockito.lenient().when(mapperRegistry.mapperOf(ApplicantRitualLiteDto.class, JpaApplicantRitual.class)).thenReturn(applicantRitualLiteDtoMapper);
    }


}
