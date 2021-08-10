package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.ApplicantMainDataDto;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualLiteDto;
import com.elm.shj.admin.portal.web.AbstractControllerTestSuite;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for controller {@link ApplicantController}
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public class ApplicantControllerTest extends AbstractControllerTestSuite {

    private final static String EXIST_USER_UIN = "59737700000059";

    @Override
    public void setUp() throws Exception {

    }

    @Override
    public void tearDown() {

    }


    @Test
    public void test_find_applicant_main_data_success() throws Exception {
        String url = Navigation.API_APPLICANTS + "/find/main-data/" + EXIST_USER_UIN;
        ApplicantMainDataDto applicantMainDataDto = new ApplicantMainDataDto();
        Mockito.when(this.applicantMainDataService.findByUin(Mockito.any())).thenReturn(Optional.of(applicantMainDataDto));
        mockMvc.perform(get(url).with(csrf())).andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void test_find_applicant_ritual_seasons_success() throws Exception {
        String url = Navigation.API_APPLICANTS + "/find/ritual-seasons";

        List<Integer> seasons = new ArrayList<>();
        seasons.add(1442);

        ApplicantDto applicantDto = new ApplicantDto();
        Mockito.when(this.applicantService.findByUin(Mockito.any())).thenReturn(Optional.of(applicantDto));
        Mockito.when(this.applicantRitualService.findHijriSeasonsByUin(Mockito.any())).thenReturn(seasons);

        mockMvc.perform(get(url).header("uin", EXIST_USER_UIN).with(csrf())).andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void test_find_applicant_ritual_by_uin_and_seasons_success() throws Exception {
        String url = Navigation.API_APPLICANTS + "/find/ritual-lite";

        List<ApplicantRitualLiteDto> applicantRitualLites = new ArrayList<>();
        applicantRitualLites.add(new ApplicantRitualLiteDto());

        ApplicantDto applicantDto = new ApplicantDto();
        Mockito.when(this.applicantService.findByUin(Mockito.any())).thenReturn(Optional.of(applicantDto));
        Mockito.when(this.applicantRitualLiteService.findApplicantRitualByUinAndSeason(EXIST_USER_UIN, 1442)).thenReturn(applicantRitualLites);

        mockMvc.perform(get(url).header("uin", EXIST_USER_UIN).header("season", 1442).with(csrf())).andDo(print()).andExpect(status().isOk());

    }
}
