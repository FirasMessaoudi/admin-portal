package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.web.AbstractControllerTestSuite;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IntegrationWsControllerTest extends AbstractControllerTestSuite {
    private final static String EXIST_USER_UIN = "50208700000027";
    private final static String FAKE_USER_UIN = "1234567893";
    private final static String TEST_MOBILE_NUMBER = "0555359268";
    private final static String TEST_EMAIL = "app@elm.sa";
    private static final int TEST_HIJRI_DATE = 14051016;
    private final static String EXIST_RITUAL_ID = "36";
    private static final int TEST_CARD_DETAILS_NOT_FOUND_RESPONSE_CODE = 561;
    private static final String UIN= "1010101040";
    private static final long COMPANY_RITUAL_ID = 1;
    private final static long EXIST_APPLICANT_RITUAL_ID = 1;
    private final static String TEST_APPLICANT_UIN = "111";
    private final static String TEST_CONTACT_UIN = "222";

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

    @Test
    void test_verify_success() throws Exception {
        String url = Navigation.API_INTEGRATION + "/verify";
        ApplicantLiteDto applicantLiteDto = new ApplicantLiteDto();
        applicantLiteDto.setDateOfBirthHijri(Long.valueOf(TEST_HIJRI_DATE));
        UpdateApplicantCmd command = new UpdateApplicantCmd();
        command.setMobileNumber(TEST_MOBILE_NUMBER);
        command.setUin(EXIST_USER_UIN);
        command.setEmail(TEST_EMAIL);
        command.setDateOfBirthHijri(14051016);
        when(applicantLiteService.findByUin(anyString())).thenReturn(Optional.of(applicantLiteDto));
        mockMvc.perform(post(url).cookie(tokenCookie).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectToJson(command)).with(csrf())).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status", is("SUCCESS"))).andExpect(jsonPath("$.body.dateOfBirthHijri", is(TEST_HIJRI_DATE)));
    }

    @Test
    void test_verify_not_found_uin() throws Exception {
        String url = Navigation.API_INTEGRATION + "/verify";
        UpdateApplicantCmd command = new UpdateApplicantCmd();
        command.setMobileNumber(TEST_MOBILE_NUMBER);
        command.setUin(FAKE_USER_UIN);
        when(applicantService.findByUin(anyString())).thenReturn(Optional.empty());
        mockMvc.perform(post(url).cookie(tokenCookie).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectToJson(command)).with(csrf())).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status", is("FAILURE")));
    }

    @Test
    void test_update_uin_not_found() throws Exception {
        String url = Navigation.API_INTEGRATION + "/update";
        UpdateApplicantCmd command = new UpdateApplicantCmd();
        command.setMobileNumber(TEST_MOBILE_NUMBER);
        command.setUin(FAKE_USER_UIN);
        when(applicantService.findByUin(anyString())).thenReturn(Optional.empty());
        mockMvc.perform(post(url).cookie(tokenCookie).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectToJson(command)).with(csrf())).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status", is("FAILURE")));
    }

    @Test
    void test_update_successfully() throws Exception {
        String url = Navigation.API_INTEGRATION + "/update";
        ApplicantDto applicantDto = new ApplicantDto();
        applicantDto.setDateOfBirthHijri(Long.valueOf(TEST_HIJRI_DATE));
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
        doNothing().when(applicantService).updateApplicantContacts(anyLong(), any());
        mockMvc.perform(post(url).cookie(tokenCookie).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectToJson(command)).with(csrf())).andDo(print()).andExpect(status().isOk());
        verify(applicantService, times(1)).updateApplicantContacts(anyLong(), any());


    }

    @Test
    public void test_find_applicant_card_details_success() throws Exception {
        String url = Navigation.API_INTEGRATION + "/details/" + EXIST_USER_UIN + "/" + EXIST_RITUAL_ID;
        ApplicantRitualCardLiteDto applicantRituals = new ApplicantRitualCardLiteDto();
        when(applicantRitualCardLiteService.findCardDetailsByUinAndRitualId(EXIST_USER_UIN, Long.parseLong(EXIST_RITUAL_ID))).thenReturn(Optional.of(applicantRituals));
        mockMvc.perform(get(url).cookie(tokenCookie).with(csrf())).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void test_find_applicant_card_details_fail() throws Exception {
        Map<String, String> errors = new HashMap<>();
        String url = Navigation.API_INTEGRATION + "/details/" + FAKE_USER_UIN + "/" + EXIST_RITUAL_ID;
        when(applicantRitualCardLiteService.findCardDetailsByUinAndRitualId(any(), any())).thenReturn(Optional.empty());
        mockMvc.perform(get(url).cookie(tokenCookie).with(csrf())).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status", is("FAILURE")));
    }

    @Test
    public void test_find_company_ritual_step_success() throws Exception {
        String url = Navigation.API_INTEGRATION + "/company-ritual-step/" + UIN + "/" + COMPANY_RITUAL_ID;
        List<CompanyRitualStepDto> companyRitualSteps = new ArrayList<>();
        when(companyRitualStepService.findCompanyRitualStepsByApplicantUin(UIN)).thenReturn(companyRitualSteps);
        mockMvc.perform(get(url).cookie(tokenCookie).with(csrf())).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void test_list_languages_success() throws Exception {
        String url = Navigation.API_INTEGRATION + "/language/list";
        when(languageLookupService.findAll()).thenReturn(new ArrayList<>());
        mockMvc.perform(get(url).cookie(tokenCookie).with(csrf())).andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void test_list_languages_fail() throws Exception {
        String url = Navigation.API_INTEGRATION + "/language/list";
        when(languageLookupService.findAll()).thenReturn(null);
        mockMvc.perform(get(url).cookie(tokenCookie).with(csrf())).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.body").isEmpty());
    }

    @Test
    public void test_find_company_ritual_step_fail() throws Exception {
        String url = Navigation.API_INTEGRATION + "/company-ritual-step/" + FAKE_USER_UIN + "/" + COMPANY_RITUAL_ID;
        when(companyRitualStepService.findCompanyRitualStepsByApplicantUin(any())).thenReturn(null);
        mockMvc.perform(get(url).cookie(tokenCookie).with(csrf())).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status", is("FAILURE")));
    }

    @Test
    public void test_find_company_employees_by_uin_success() throws Exception {
        String url = Navigation.API_INTEGRATION + "/find/company-employees/" + EXIST_USER_UIN + "/" + COMPANY_RITUAL_ID;
        List<CompanyStaffDto> groupLeaders = new ArrayList<CompanyStaffDto>();
        when(companyStaffService.findRelatedEmployeesByApplicantUinAndSeasonId(EXIST_USER_UIN, COMPANY_RITUAL_ID)).thenReturn(null);
        mockMvc.perform(get(url).cookie(tokenCookie).contentType(MediaType.APPLICATION_JSON).content(objectToJson(groupLeaders)).with(csrf())).andDo(print()).andExpect(status().isOk());
        verify(companyStaffService, times(1)).findRelatedEmployeesByApplicantUinAndSeasonId(anyString(), anyLong());

    }

    @Test
    public void test_find_company_employees_by_uin_fail() throws Exception {
        String url = Navigation.API_INTEGRATION + "/find/company-employees/" + EXIST_USER_UIN + "/" + COMPANY_RITUAL_ID;
        List<CompanyStaffDto> groupLeaders = new ArrayList<CompanyStaffDto>();
        when(companyStaffService.findRelatedEmployeesByApplicantUinAndSeasonId(EXIST_USER_UIN, COMPANY_RITUAL_ID)).thenReturn(groupLeaders);
        mockMvc.perform(get(url).cookie(tokenCookie).contentType(MediaType.APPLICATION_JSON).content(objectToJson(groupLeaders)).with(csrf())).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status", is("FAILURE")));
    }
    @Test
    public void test_find_program_table_success() throws Exception {
        String url = Navigation.API_INTEGRATION + "/program-time-table/" + UIN + "/" + COMPANY_RITUAL_ID;
        List<CompanyRitualStepDto> companyRitualSteps = new ArrayList<>();
        when(companyRitualStepService.findCompanyRitualStepsByApplicantUin(UIN)).thenReturn(companyRitualSteps);
        mockMvc.perform(get(url).cookie(tokenCookie).with(csrf())).andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void test_find_program_table_fail() throws Exception {
        String url = Navigation.API_INTEGRATION + "/program-time-table/" + FAKE_USER_UIN + "/" + COMPANY_RITUAL_ID;
        when(companyRitualStepService.findCompanyRitualStepsByApplicantUin(any())).thenReturn(null);
        mockMvc.perform(get(url).cookie(tokenCookie).with(csrf())).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status", is("FAILURE")));
    }

    @Test
    public void test_list_applicant_related_incidents_fail() throws Exception {
        String url = Navigation.API_INTEGRATION + "/incident/list/" + EXIST_APPLICANT_RITUAL_ID;
        when(applicantIncidentService.listApplicantRelatedIncidents(anyLong())).thenReturn(null);
        mockMvc.perform(get(url).cookie(tokenCookie).with(csrf())).andDo(print()).andExpect(status().isNoContent()).andExpect(jsonPath("$.status", is("FAILURE")));
    }

    @Test
    public void test_list_applicant_related_incidents_success() throws Exception {

        List<ApplicantIncidentDto> applicantIncidents= new ArrayList<>();
        ApplicantIncidentDto applicantIncidentDto  = new ApplicantIncidentDto();
        applicantIncidentDto.setId(1);
        applicantIncidentDto.setDescription("hello");
        applicantIncidents.add(applicantIncidentDto);
        String url = Navigation.API_INTEGRATION + "/incident/list/" + EXIST_APPLICANT_RITUAL_ID;
        when(applicantIncidentService.listApplicantRelatedIncidents(anyLong())).thenReturn(applicantIncidents);
        mockMvc.perform(get(url).cookie(tokenCookie).with(csrf())).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status", is("SUCCESS")));
    }

    @Test
    void test_delete_applicant_chat_contact() throws Exception {
        String url = Navigation.API_INTEGRATION + "/chat-contact/"+TEST_APPLICANT_UIN+"/"+TEST_CONTACT_UIN;
        when(applicantChatContactService.deleteApplicantChatContact(anyString(),anyString())).thenReturn(1);
        mockMvc.perform(post(url).cookie(tokenCookie).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectToJson(null)).with(csrf())).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status", is("SUCCESS"))).andExpect(jsonPath("$.body", is("number of affected rows : 1")));
    }
 }
