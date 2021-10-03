package com.elm.shj.admin.portal.services.ritual;

import com.elm.dcc.foundation.commons.core.mapper.MapperRegistry;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualDto;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualDtoMapper;
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
public class ApplicantRitualServiceTest {

    private final static String EXIST_USER_UIN = "50208700000027";
    private final static String FAKE_USER_UIN = "123456987";

    @Mock
    private MapperRegistry mapperRegistry;

    @Mock
    private ApplicantRitualDtoMapper applicantRitualDtoMapper;

    @Mock
    private ApplicantRitualRepository applicantRitualRepository;

    @InjectMocks
    private ApplicantRitualService applicantRitualService;

    @BeforeEach
    public void setUp() {
        Mockito.lenient().when(mapperRegistry.mapperOf(ApplicantRitualDto.class, JpaApplicantRitual.class)).thenReturn(applicantRitualDtoMapper);
    }



}
