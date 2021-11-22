/*
 * Copyright (c) 2017 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web;

import com.elm.dcc.foundation.commons.validation.UniqueValidator;
import com.elm.dcc.foundation.providers.email.config.EmailConfig;
import com.elm.dcc.foundation.providers.filescan.config.FileScanConfig;
import com.elm.dcc.foundation.providers.filescan.service.FileScanService;
import com.elm.dcc.foundation.providers.recaptcha.service.RecaptchaService;
import com.elm.dcc.foundation.providers.sms.config.SmsGatewayConfig;
import com.elm.shj.admin.portal.orm.repository.ApplicantHealthRepository;
import com.elm.shj.admin.portal.orm.repository.DataRequestRecordRepository;
import com.elm.shj.admin.portal.orm.repository.UserNotificationRepository;
import com.elm.shj.admin.portal.services.applicant.*;
import com.elm.shj.admin.portal.services.audit.AuditLogService;
import com.elm.shj.admin.portal.services.card.ApplicantCardScheduler;
import com.elm.shj.admin.portal.services.card.ApplicantCardService;
import com.elm.shj.admin.portal.services.card.UserCardStatusAuditService;
import com.elm.shj.admin.portal.services.dashboard.DashboardService;
import com.elm.shj.admin.portal.services.data.request.DataRequestService;
import com.elm.shj.admin.portal.services.data.segment.DataSegmentService;
import com.elm.shj.admin.portal.services.data.writer.ItemWriter;
import com.elm.shj.admin.portal.services.digitalid.DigitalIdService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.group.RitualGroupService;
import com.elm.shj.admin.portal.services.incident.ApplicantIncidentService;
import com.elm.shj.admin.portal.services.lookup.*;
import com.elm.shj.admin.portal.services.notification.*;
import com.elm.shj.admin.portal.services.otp.OtpService;
import com.elm.shj.admin.portal.services.prinitng.PrintRequestLiteService;
import com.elm.shj.admin.portal.services.prinitng.PrintRequestService;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualCardLiteService;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualLiteService;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
import com.elm.shj.admin.portal.services.role.RoleService;
import com.elm.shj.admin.portal.services.rule.RuleService;
import com.elm.shj.admin.portal.services.unit.RitualUnitService;
import com.elm.shj.admin.portal.services.user.PasswordHistoryService;
import com.elm.shj.admin.portal.services.user.UserService;
import com.elm.shj.admin.portal.services.zone.RitualZoneService;
import com.elm.shj.admin.portal.web.boot.BootApplication;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static java.time.ZoneOffset.UTC;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Abstract class for all controller tests
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = BootApplication.class)
@WebMvcTest
@ActiveProfiles("test")
public abstract class AbstractControllerTestSuite {

    protected static final String TEST_USER_PASSWORD = "test-password";
    protected static final String TEST_USER_NIN = "1234567897";
    protected static final long NEW_USER_ID = 1001L;

    protected final Random random = new Random();

    @MockBean
    protected EntityManagerFactory emf;

    @Autowired
    protected MockMvc mockMvc;

    protected ObjectWriter jsonObjectWriter;

    protected ObjectMapper mapper = new ObjectMapper();

    @MockBean
    protected UserService userService;

    @MockBean
    protected PlatformTransactionManager transactionManager;

    @MockBean
    protected AuthorityLookupService authorityLookupService;

    @MockBean
    protected RoleService roleService;

    @MockBean
    protected ApplicantRitualLiteService applicantRitualLiteService;

    @MockBean
    protected ApplicantHealthLiteService applicantHealthLiteService;

    @MockBean
    protected DashboardService dashboardService;

    @Autowired
    protected BCryptPasswordEncoder passwordEncoder;

    @MockBean
    protected PasswordHistoryService passwordHistoryService;

    @Autowired
    protected JwtTokenService jwtTokenService;

    @MockBean
    protected FileScanService fileScanService;

    @MockBean
    protected RecaptchaService recaptchaService;

    @MockBean
    protected ApplicantService applicantService;

    @MockBean
    protected ApplicantLiteService applicantLiteService;

    @MockBean
    protected ApplicantMainDataService applicantMainDataService;

    @MockBean
    protected ApplicantRitualService applicantRitualService;

    @MockBean
    protected ApplicantCardService applicantCardService;

    @MockBean
    protected DataRequestService dataRequestService;

    @MockBean
    protected DataSegmentService dataSegmentService;

    @MockBean
    protected PrintRequestService printRequestService;

    @MockBean
    protected PrintRequestLiteService printRequestLiteService;

    @MockBean
    protected RitualTypeLookupService ritualTypeLookupService;

    @MockBean
    protected CardStatusLookupService cardStatusLookupService;

    @MockBean
    protected RelativeRelationshipLookupService relativeRelationshipLookupService;

    @MockBean
    protected MaritalStatusLookupService maritalStatusLookupService;

    @MockBean
    protected CountryLookupService countryLookupService;

    @MockBean
    protected HealthSpecialNeedsLookupService healthSpecialNeedsLookupService;

    @MockBean
    protected PrintRequestStatusLookupService printRequestStatusLookupService;

    @MockBean
    protected PrintBatchTypeLookupService printBatchTypeLookupService;

    @MockBean
    protected AuditLogService auditLogService;

    @MockBean
    protected DigitalIdService digitalIdService;

    @MockBean
    protected RitualGroupService ritualGroupService;

    @MockBean
    protected HealthImmunizationLookupService healthImmunizationLookupService;

    @MockBean
    protected LanguageLookupService languageLookupService;

    @MockBean
    protected OtpService otpService;

    @MockBean
    protected RitualUnitService ritualUnitService;

    @MockBean
    protected RitualZoneService ritualZoneService;

    @MockBean
    protected EmailConfig emailConfig;

    @MockBean
    protected SmsGatewayConfig smsGatewayConfig;

    @MockBean
    protected FileScanConfig fileScanConfig;

    @MockBean
    protected ApplicantHealthRepository applicantHealthRepository;

    @MockBean
    protected DataRequestRecordRepository dataRequestRecordRepository;

    @MockBean
    protected RuleService ruleService;

    @MockBean
    protected ApplicantRitualCardLiteService applicantRitualCardLiteService;

    protected Cookie tokenCookie;

    protected UserDto loggedInUser;

    @MockBean
    protected CompanyRitualStepService companyRitualStepService;

    @MockBean
    protected ApplicantIncidentService applicantIncidentService;

    @MockBean
    protected CompanyStaffService companyStaffService;

    @MockBean
    protected UserNotificationService userNotificationService;

    @MockBean
    protected CompanyRitualSeasonLiteService companyRitualSeasonLiteService;

    @MockBean
    protected ApplicantPackageHousingService applicantPackageHousingService;

    @MockBean
    protected ApplicantPackageCateringService applicantPackageCateringService;

    @MockBean
    protected ApplicantPackageTransportationService applicantPackageTransportationService;

    @MockBean
    protected CompanyLiteService companyLiteService;

    @MockBean
    protected CompanyRitualStepLookupService companyRitualStepLookupService;

    @MockBean
    protected CompanyStaffLookupService companyStaffLookupService;

    @MockBean
    protected HousingCategoryLookupService housingCategoryLookupService;

    @MockBean
    protected HousingTypeLookupService housingTypeLookupService;

    @MockBean
    protected PackageTypeLookupService packageTypeLookupService;

    @MockBean
    protected HousingSiteLookupService housingSiteLookupService;

    @MockBean
    protected TransportationTypeLookupService transportationTypeLookupService;

    @MockBean
    protected NotificationCategoryLookupService notificationCategoryLookupService;

    @MockBean
    protected NotificationTemplateNameLookupService notificationTemplateNameLookupService;

    @MockBean
    protected NotificationTemplateTypeLookupService notificationTemplateTypeLookupService;

    @MockBean
    protected UserNotificationStatusLookupService userNotificationStatusLookupService;

    @MockBean
    protected NotificationTemplateStatusLookupService notificationTemplateStatusLookupService;

    @MockBean
    protected NotificationTemplateService notificationTemplateService;

    @MockBean
    protected NotificationRequestService notificationRequestService;

    @MockBean
    protected ApplicantGroupService applicantGroupService;

    @MockBean
    protected ApplicantPackageService applicantPackageService;

    @MockBean
    protected GroupApplicantListService groupApplicantListService;

    @MockBean
    protected RitualPackageService ritualPackageService;

    @MockBean
    protected ItemWriter itemWriter;

    @MockBean
    protected NotificationProcessingScheduler notificationProcessingScheduler;

    @MockBean
    protected UserNotificationRepository userNotificationRepository;

    @MockBean
    protected ApplicantDigitalIdStatusLookupService applicantDigitalIdStatusLookupService;

    @MockBean
    protected UserNotificationExpirationScheduler userNotificationExpirationScheduler;

    @MockBean
    protected ReligiousOccasionsDayLookupService religiousOccasionsDayLookupService;

    @MockBean
    protected MealTypeLookupService mealTypeLookupService;

    @MockBean
    protected PackageHousingService packageHousingService;


    @MockBean
    protected UserCardStatusAuditService userCardStatusAuditService;

    @MockBean
    protected UserNotificationCategoryPreferenceService notificationCategoryPreferenceService;

    @MockBean
    protected ApplicantCardScheduler applicantCardScheduler;

    @MockBean
    protected ApplicantContactService applicantContactService ;

    @MockBean
    protected ApplicantChatContactService applicantChatContactService;

    /**
     * Method which is executed before each test
     */
    @SuppressWarnings("unchecked")
    @BeforeEach
    public void beforeEach() throws Exception {

        // skip heavy validation
        System.setProperty(UniqueValidator.UNIQUE_VALIDATION_SKIP, "true");
        System.setProperty(RecaptchaService.RECAPTCHA_VALIDATION_SKIP, "true");

        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        jsonObjectWriter = mapper.writer().withDefaultPrettyPrinter();

        setUp();
    }

    protected String objectToJson(Object o) {
        try {
            return jsonObjectWriter.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    protected <T> T jsonToObject(String s, Class<T> clazz) {
        try {
            return mapper.readerFor(clazz).readValue(s);
        } catch (IOException e) {
            return null;
        }
    }

    protected void triggerLogin() throws Exception {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("idNumber", TEST_USER_NIN);
        credentials.put("password", TEST_USER_PASSWORD);

        Map<String, String> otpCredentials = new HashMap<>();
        otpCredentials.put("idNumber", TEST_USER_NIN);
        otpCredentials.put("otp", "1111");

        mockMvc.perform(post(Navigation.API_AUTH + "/login").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectToJson(credentials)).with(csrf())).andExpect(status().isOk()).andDo(result -> {

            mockMvc.perform(post(Navigation.API_AUTH + "/otp").contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(objectToJson(otpCredentials)).with(csrf())).andExpect(status().isOk()).andDo(result2 -> {
                Cookie tokenCookie = result2.getResponse().getCookie(JwtTokenService.TOKEN_COOKIE_NAME);
                assertNotNull(tokenCookie);
                this.tokenCookie = tokenCookie;
            });
        });
    }

    protected void mockSuccessfulLogin() {
        loggedInUser = new UserDto();

        RoleDto role = new RoleDto();
        RoleAuthorityDto roleAuthority = new RoleAuthorityDto();

        AuthorityLookupDto authority = new AuthorityLookupDto();
        roleAuthority.setAuthority(authority);
        authority.setCode(AuthorityConstants.USER_MANAGEMENT);

        RoleAuthorityDto roleAuthorityDelete = new RoleAuthorityDto();
        AuthorityLookupDto authorityDelete = new AuthorityLookupDto();
        roleAuthorityDelete.setAuthority(authorityDelete);
        authorityDelete.setCode(AuthorityConstants.DELETE_USER);

        RoleAuthorityDto roleAuthorityEdit = new RoleAuthorityDto();
        AuthorityLookupDto authorityEdit = new AuthorityLookupDto();
        roleAuthorityEdit.setAuthority(authorityEdit);
        authorityEdit.setCode(AuthorityConstants.EDIT_USER);

        RoleAuthorityDto roleAuthorityResetPassword = new RoleAuthorityDto();
        AuthorityLookupDto authorityResetPassword = new AuthorityLookupDto();
        roleAuthorityResetPassword.setAuthority(authorityResetPassword);
        authorityResetPassword.setCode(AuthorityConstants.RESET_PASSWORD);

        RoleAuthorityDto roleAuthorityResetUserPassword = new RoleAuthorityDto();
        AuthorityLookupDto authorityResetUserPassword = new AuthorityLookupDto();
        roleAuthorityResetUserPassword.setAuthority(authorityResetUserPassword);
        authorityResetUserPassword.setCode(AuthorityConstants.RESET_USER_PASSWORD);

        RoleAuthorityDto roleAuthorityAdd = new RoleAuthorityDto();
        AuthorityLookupDto authorityAdd = new AuthorityLookupDto();
        roleAuthorityAdd.setAuthority(authorityAdd);
        authorityAdd.setCode(AuthorityConstants.ADD_USER);

        RoleAuthorityDto roleAuthorityUserStatus = new RoleAuthorityDto();
        AuthorityLookupDto authorityUserStatus = new AuthorityLookupDto();
        roleAuthorityUserStatus.setAuthority(authorityUserStatus);
        authorityUserStatus.setCode(AuthorityConstants.CHANGE_USER_STATUS);

        RoleAuthorityDto roleAuthorityIntegration = new RoleAuthorityDto();
        AuthorityLookupDto authorityIntegration = new AuthorityLookupDto();
        roleAuthorityIntegration.setAuthority(authorityIntegration);
        authorityIntegration.setCode(AuthorityConstants.INTEGRATION_WEB_SERVICE_CALL);

        role.setRoleAuthorities(new HashSet<>((Arrays.asList(roleAuthority, roleAuthorityDelete, roleAuthorityEdit, roleAuthorityResetPassword, roleAuthorityResetUserPassword, roleAuthorityAdd, roleAuthorityUserStatus, roleAuthorityIntegration))));
        UserRoleDto userRole = new UserRoleDto();
        userRole.setUser(loggedInUser);
        userRole.setRole(role);
        userRole.setMainRole(true);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        loggedInUser.setPasswordHash(encoder.encode(TEST_USER_PASSWORD));
        loggedInUser.setPassword(TEST_USER_PASSWORD);
        loggedInUser.setNin(new Long(TEST_USER_NIN));
        loggedInUser.setUserRoles(Collections.singleton(userRole));
        loggedInUser.setActivated(true);
        Mockito.when(userService.findByNin(new Long(TEST_USER_NIN))).thenReturn(Optional.of(loggedInUser));
        Mockito.when(userService.hasToken(new Long(TEST_USER_NIN))).thenReturn(true);

        UserPasswordHistoryDto userPasswordHistoryDto = new UserPasswordHistoryDto();
        userPasswordHistoryDto.setCreationDate(Date.from(LocalDateTime.now(UTC).minusSeconds(10).toInstant(UTC)));
        Mockito.when(passwordHistoryService.findLastByUserId(anyLong())).thenReturn(Optional.of(userPasswordHistoryDto));

        Mockito.when(otpService.validateOtp(any(), any())).thenReturn(true);

        doAnswer((Answer<Void>) invocation -> {
            // do nothing
            return null;
        }).when(userService).updateUserLoginInfo(any(Long.class), any(Date.class));
    }

    /**
     * Method which is executed after each test
     */
    @AfterEach
    public void afterEach() {
        tearDown();
    }

    /**
     * Additional set up for the test
     * <p>
     * To be defined in each test
     * </p>
     */
    public abstract void setUp() throws Exception;

    /**
     * Additional closure for the test
     * <p>
     * To be defined in each test
     * </p>
     */
    public abstract void tearDown();
}
