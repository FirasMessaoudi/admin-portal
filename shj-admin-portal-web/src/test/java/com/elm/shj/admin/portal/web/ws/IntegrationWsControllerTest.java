package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.dto.ApplicantMainDataDto;
import com.elm.shj.admin.portal.web.AbstractControllerTestSuite;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IntegrationWsControllerTest extends AbstractControllerTestSuite {

    @Override
    public void setUp() throws Exception {
        mockSuccessfulLogin();
        triggerLogin();
    }

    @Override
    public void tearDown() {

    }

    @Test
    public void test_find_applicant_main_data_success() throws Exception {

        String uin = "1234";
        String url = Navigation.API_INTEGRATION + "/find/main-data/" + uin + "/2";
        ApplicantMainDataDto applicantMainDataDto = new ApplicantMainDataDto();
        applicantMainDataDto.setUin(uin);

        when(applicantMainDataService.findByUin(any(String.class), any(Long.class))).thenReturn(Optional.of(applicantMainDataDto));

        mockMvc.perform(get(url).cookie(tokenCookie).header(JwtTokenService.CALLER_TYPE_HEADER_NAME, JwtTokenService.WEB_SERVICE_CALLER_TYPE).with(csrf())).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.uin", is(uin)));
    }
}
