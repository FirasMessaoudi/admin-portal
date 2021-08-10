package com.elm.shj.admin.portal.services.ritual;

import com.elm.dcc.foundation.commons.core.mapper.MapperRegistry;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualDto;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualDtoMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Testing class for service {@link ApplicantRitualService}
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
@ExtendWith(MockitoExtension.class)
public class ApplicantRitualServiceTest {

    private final static String EXIST_USER_UIN = "59737700000059";

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

    @Test
    public void test_find_hijriSeasons_by_uin_success() {

        List<Integer> seasons = new ArrayList<>();
        seasons.add(1442);
        Mockito.when(applicantRitualRepository.findApplicantRitualHijriSeasonsByUin(EXIST_USER_UIN)).thenReturn(seasons);
        List<Integer> outSeasons = applicantRitualService.findHijriSeasonsByUin(EXIST_USER_UIN);

        Assert.assertNotNull(outSeasons);
        Assert.assertEquals(1, outSeasons.size());
        Assert.assertEquals(Optional.of(1442), Optional.of(outSeasons.get(0)));
    }
}
