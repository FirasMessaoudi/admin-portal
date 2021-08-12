package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualCardLiteDto;
import com.elm.shj.admin.portal.web.AbstractControllerTestSuite;
import com.elm.shj.admin.portal.web.error.CardDetailsNotFoundException;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for controller {@link ApplicantController}
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public class ApplicantCardControllerTest extends AbstractControllerTestSuite {

    private final static String EXIST_USER_UIN = "59737700000059";
    private final static String FAKE_USER_UIN = "1234567893";
    private final static String EXIST_RITUAL_ID = "36";
    private static final int TEST_CARD_DETAILS_NOT_FOUND_RESPONSE_CODE = 561;

    @Override
    public void setUp() throws Exception {

    }

    @Override
    public void tearDown() {

    }

    @Test
    public void test_find_applicant_card_details_success() throws Exception {
        String url = Navigation.API_APPLICANT_CARDS + "/details/" + EXIST_USER_UIN + "/" + EXIST_RITUAL_ID;
        ApplicantRitualCardLiteDto applicantRituals = new ApplicantRitualCardLiteDto();
        when(applicantRitualCardLiteService.findCardDetailsByUinAndRitualId(EXIST_USER_UIN, EXIST_RITUAL_ID)).thenReturn(Optional.of(applicantRituals));
        mockMvc.perform(get(url).with(csrf())).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void test_find_applicant_card_details_fail() throws Exception {
        Map<String, String> errors = new HashMap<>();
        String url = Navigation.API_APPLICANT_CARDS + "/details/" + FAKE_USER_UIN + "/" + EXIST_RITUAL_ID;
        ;
        when(applicantRitualCardLiteService.findCardDetailsByUinAndRitualId(any(), any())).thenThrow(new CardDetailsNotFoundException("No Card Details Found For Applicant with uin " + FAKE_USER_UIN, errors));
        mockMvc.perform(get(url).with(csrf())).andDo(print()).andExpect(status().is(TEST_CARD_DETAILS_NOT_FOUND_RESPONSE_CODE));
    }


}
