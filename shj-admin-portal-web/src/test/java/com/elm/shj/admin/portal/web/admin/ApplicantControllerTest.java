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
    private final static String FAKE_USER_UIN = "1234567893";
    private final static String TEST_MOBILE_NUMBER = "0555359268";
    private final static String TEST_EMAIL = "app@elm.sa";
    private static final int TEST_APPLICANT_NOT_FOUND_RESPONSE_CODE = 561;

    @Override
    public void setUp() throws Exception {

    }

    @Override
    public void tearDown() {

    }

    @Test
    public void test_find_applicant_health_details_success() throws Exception {
        String url = Navigation.API_APPLICANTS + "/health/" + EXIST_USER_UIN;
        ApplicantHealthDto applicantHealthDto = new ApplicantHealthDto();
        when(this.applicantHealthService.findByUinAndRitualId(any(), any())).thenReturn(Optional.of(applicantHealthDto));
        mockMvc.perform(get(url).with(csrf())).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void test_find_applicant_main_data_success() throws Exception {
        String url = Navigation.API_APPLICANTS + "/find/main-data/" + EXIST_USER_UIN;
        ApplicantMainDataDto applicantMainDataDto = new ApplicantMainDataDto();
        when(this.applicantMainDataService.findByUin(any())).thenReturn(Optional.of(applicantMainDataDto));
        mockMvc.perform(get(url).with(csrf())).andDo(print()).andExpect(status().isOk());

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

    @Test
    void test_update_uin_not_found() throws Exception {
        String url = Navigation.API_APPLICANTS + "/update";
        UpdateApplicantCmd command = new UpdateApplicantCmd();
        command.setMobileNumber(TEST_MOBILE_NUMBER);
        command.setUin(FAKE_USER_UIN);
        when(applicantService.findByUin(anyString())).thenReturn(Optional.empty());
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectToJson(command)).with(csrf())).andDo(print()).andExpect(status().is(TEST_APPLICANT_NOT_FOUND_RESPONSE_CODE));
    }

    @Test
    void test_update_successfully() throws Exception {
        String url = Navigation.API_APPLICANTS + "/update";
        ApplicantDto applicantDto = new ApplicantDto();
        applicantDto.setDateOfBirthHijri(14051016L);
        ApplicantContactDto applicantContactDto = new ApplicantContactDto();
        List<ApplicantContactDto> listOfContacts = new ArrayList<ApplicantContactDto>();
        listOfContacts.add(applicantContactDto);
        applicantDto.setContacts(listOfContacts);
        UpdateApplicantCmd command = new UpdateApplicantCmd();
        command.setMobileNumber(TEST_MOBILE_NUMBER);
        command.setUin(EXIST_USER_UIN);
        command.setEmail(TEST_EMAIL);
        command.setDateOfBirthHijri(14051016);
        when(applicantService.findByUin(anyString())).thenReturn(Optional.of(applicantDto));
        when(applicantLiteService.findByUin(anyString())).thenReturn(Optional.of(new ApplicantLiteDto()));
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectToJson(command)).with(csrf())).andDo(print()).andExpect(status().isOk());
        verify(applicantService, times(1)).save(any());


    }
}
