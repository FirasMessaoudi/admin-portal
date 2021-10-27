/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.dto.DetailedUserNotificationDto;
import com.elm.shj.admin.portal.services.dto.PasswordExpiryNotificationRequest;
import com.elm.shj.admin.portal.services.dto.PasswordExpiryNotificationRequestUserParameters;
import com.elm.shj.admin.portal.services.notification.UserNewNotificationsCountVo;
import com.elm.shj.admin.portal.web.AbstractControllerTestSuite;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testing class for Controller {@link NotificationWsController}
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public class NotificationWsControllerTest extends AbstractControllerTestSuite {

    @Override
    public void setUp() throws Exception {
        mockSuccessfulLogin();
        triggerLogin();
    }

    @Override
    public void tearDown() {

    }

    @Test
    public void test_find_User_Notifications_success() throws Exception {

        long userId = 1;
        DetailedUserNotificationDto detailedUserNotificationDto = new DetailedUserNotificationDto();
        detailedUserNotificationDto.setTitle("Password will expire soon");
        List<DetailedUserNotificationDto> detailedUserNotificationDtos = new ArrayList<>();
        detailedUserNotificationDtos.add(detailedUserNotificationDto);
        String url = Navigation.API_NOTIFICATION_INTEGRATION + "/" + userId;


        when(userNotificationService.findUserNotifications(anyLong())).thenReturn(detailedUserNotificationDtos);

        mockMvc.perform(get(url).cookie(tokenCookie).header(JwtTokenService.CALLER_TYPE_HEADER_NAME, JwtTokenService.WEB_SERVICE_CALLER_TYPE).with(csrf())).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.length()", is(1)))
                .andExpect(jsonPath("$.body[0].title", is("Password will expire soon")));

    }


    @Test
    public void test_count_User_New_Notifications() throws Exception {
        long userId = 3088;
        UserNewNotificationsCountVo notificationsCountVo = UserNewNotificationsCountVo.builder().userSpecificNewNotificationsCount(1).userNotSpecificNewNotificationsCount(2).build();
        String url = Navigation.API_NOTIFICATION_INTEGRATION + "/count-new-notifications/" + userId;
        when(userNotificationService.retrieveUserNewNotificationsCount(anyLong())).thenReturn(notificationsCountVo);
        mockMvc.perform(get(url).cookie(tokenCookie).header(JwtTokenService.CALLER_TYPE_HEADER_NAME, JwtTokenService.WEB_SERVICE_CALLER_TYPE).with(csrf())).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.userSpecificNewNotificationsCount", is(1)))
                .andExpect(jsonPath("$.body.userNotSpecificNewNotificationsCount", is(2)));

    }

    @Test
    public void test_save_Password_Expiry_Notification_Request() throws Exception {
        PasswordExpiryNotificationRequest passwordExpiryNotificationRequest = new PasswordExpiryNotificationRequest();
        PasswordExpiryNotificationRequestUserParameters param = new PasswordExpiryNotificationRequestUserParameters();
        param.setUserId(1);
        param.setDaysToExpiry(5);
        param.setUserLang("EN");
        Set<PasswordExpiryNotificationRequestUserParameters> userParametersList = new HashSet<>();
        userParametersList.add(param);
        passwordExpiryNotificationRequest.setUserParametersList(userParametersList);
        String url = Navigation.API_NOTIFICATION_INTEGRATION + "/password-expiry";
        doNothing().when(notificationRequestService).savePasswordExpiryNotificationRequest(passwordExpiryNotificationRequest);
        mockMvc.perform(post(url).content(objectToJson(passwordExpiryNotificationRequest)).contentType(MediaType.APPLICATION_JSON_UTF8).cookie(tokenCookie).header(JwtTokenService.CALLER_TYPE_HEADER_NAME, JwtTokenService.WEB_SERVICE_CALLER_TYPE).with(csrf())).andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void test_mark_User_Notification_As_Read() throws Exception {
        int numberOfAffectedRows = 0;
        String url = Navigation.API_NOTIFICATION_INTEGRATION + "/mark-as-read";
        when(userNotificationService.markUserNotificationAsRead(anyLong())).thenReturn(numberOfAffectedRows);
        mockMvc.perform(post(url).cookie(tokenCookie).header(JwtTokenService.CALLER_TYPE_HEADER_NAME, JwtTokenService.WEB_SERVICE_CALLER_TYPE).with(csrf())).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body", is(0)));
    }

}
