package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.orm.entity.ApplicantCardDetails;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.ApplicantHealthDto;
import com.elm.shj.admin.portal.services.dto.ApplicantMainDataDto;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualLiteDto;
import com.elm.shj.admin.portal.web.AbstractControllerTestSuite;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
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
    private static final int TEST_CARD_DETAILS_NOT_FOUND_RESPONSE_CODE = 561;

    @Override
    public void setUp() throws Exception {

    }

    @Override
    public void tearDown() {

    }

    @Test
    public void test_find_applicant_card_details_success() throws Exception {
        String url = Navigation.API_APPLICANT_CARDS + "/details/" + EXIST_USER_UIN;
        ApplicantCardDetails applicantCardDetails = new ApplicantCardDetails();
        when(this.applicantCardService.findCardDetailsByUin(any())).thenReturn(Optional.of(applicantCardDetails));
        mockMvc.perform(get(url).with(csrf())).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void test_find_applicant_card_details_fail() throws Exception {
        String url = Navigation.API_APPLICANT_CARDS + "/details/" + FAKE_USER_UIN;
        ApplicantCardDetails applicantCardDetails = new ApplicantCardDetails();
        when(this.applicantCardService.findCardDetailsByUin(any())).thenReturn(Optional.of(applicantCardDetails));
        mockMvc.perform(get(url).with(csrf())).andDo(print()).andExpect(status().is(TEST_CARD_DETAILS_NOT_FOUND_RESPONSE_CODE));
    }


}
