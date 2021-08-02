package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.dto.ApplicantMainDataDto;
import com.elm.shj.admin.portal.web.AbstractControllerTestSuite;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for controller {@link UserManagementController}
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public class ApplicantControllerTest extends AbstractControllerTestSuite {

    private final static String UIN_USER_EXIST = "59737700000059";

    @Override
    public void setUp() throws Exception {

    }

    @Override
    public void tearDown() {

    }


    @Test
    public void test_find_applicant_main_data_success() throws Exception {
        String url = Navigation.API_APPLICANTS + "/find/main-data/" + UIN_USER_EXIST;
        ApplicantMainDataDto applicantMainDataDto = new ApplicantMainDataDto();
        Mockito.when(this.applicantMainDataService.findByUin(Mockito.any())).thenReturn(Optional.of(applicantMainDataDto));
        mockMvc.perform(get(url).with(csrf())).andDo(print()).andExpect(status().isOk());

    }
}
