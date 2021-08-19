package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.web.AbstractControllerTestSuite;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for controller {@link ApplicantController}
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public class ApplicantControllerTest extends AbstractControllerTestSuite {

    private final static String EXIST_USER_UIN = "50208700000027";


    @Override
    public void setUp() throws Exception {

    }

    @Override
    public void tearDown() {

    }

    @Test
    public void test_find_applicant_health_details_success() throws Exception {
    }



    @Test
    public void test_find_applicant_ritual_seasons_success() throws Exception {
        String url = Navigation.API_APPLICANTS + "/find/ritual-seasons/" + EXIST_USER_UIN;

        List<Integer> seasons = new ArrayList<>();
        seasons.add(1442);

        ApplicantDto applicantDto = new ApplicantDto();
        when(this.applicantRitualService.findHijriSeasonsByUin(any())).thenReturn(seasons);

        mockMvc.perform(get(url).with(csrf())).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(seasons.size())))
                .andExpect(jsonPath("$[0]", is(1442)));

    }

    @Test
    public void test_find_applicant_ritual_by_uin_and_seasons_success() throws Exception {
        String url = Navigation.API_APPLICANTS + "/find/ritual-lite/" + EXIST_USER_UIN + "/1442";

        ApplicantRitualLiteDto applicantRitualLiteDto = new ApplicantRitualLiteDto();
        List<ApplicantRitualLiteDto> applicantRitualLites = new ArrayList<>();
        applicantRitualLites.add(applicantRitualLiteDto);


        when(this.applicantRitualLiteService.findApplicantRitualByUinAndSeason(EXIST_USER_UIN, 1442)).thenReturn(applicantRitualLites);

        mockMvc.perform(get(url).with(csrf())).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(applicantRitualLites.size())))
                .andExpect(jsonPath("$[0].hijriSeason", is(applicantRitualLiteDto.getHijriSeason())));

    }


}
