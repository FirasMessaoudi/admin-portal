/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.dcc.foundation.commons.core.mapper.MapperRegistry;
import com.elm.shj.admin.portal.orm.entity.JpaNotificationRequest;
import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplate;
import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplateContent;
import com.elm.shj.admin.portal.orm.repository.NotificationRequestRepository;
import com.elm.shj.admin.portal.orm.repository.UserNotificationRepository;
import com.elm.shj.admin.portal.services.dto.*;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Testing class for service {@link NotificationRequestService}
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@ExtendWith(MockitoExtension.class)
public class NotificationRequestServiceTest {
 @Mock
 private NotificationRequestRepository notificationRequestRepository;
 @Mock
 private UserNotificationRepository userNotificationRepository;
 @Mock
 private NotificationTemplateService notificationTemplateService;
 @InjectMocks
 private NotificationRequestService notificationRequestService;
 @Mock
 private MapperRegistry mapperRegistry;

 @Mock
 private NotificationRequestDtoMapper notificationRequestDtoMapper;

 @BeforeEach
 public void setUp() {
  Mockito.lenient().when(mapperRegistry.mapperOf(NotificationRequestDto.class, JpaNotificationRequest.class)).thenReturn(notificationRequestDtoMapper);
 }

 @Test
 public void test_Process_Notification_Request() {

  JpaNotificationRequest notificationRequest = buildNotificationRequest();
  notificationRequestService.processNotificationRequest(notificationRequest);
  verify(userNotificationRepository, times(1)).save(any());
  verify(notificationRequestRepository, times(1)).delete(any());

 }

 @Test
 public void test_Save_Password_Expiry_Notification_Request_No_Template() throws NotFoundException {
  String PASSWORD_EXPIRATION_TEMPLATE_NAME = "PASSWORD_EXPIRATION";
  PasswordExpiryNotificationRequest passwordExpiryNotificationRequest = new PasswordExpiryNotificationRequest();
  PasswordExpiryNotificationRequestUserParameters param = new PasswordExpiryNotificationRequestUserParameters();
  param.setUserId(1);
  param.setDaysToExpiry(5);
  param.setUserLang("EN");
  Set<PasswordExpiryNotificationRequestUserParameters> userParametersList = new HashSet<>();
  userParametersList.add(param);
  passwordExpiryNotificationRequest.setUserParametersList(userParametersList);
  when(notificationTemplateService.findEnabledNotificationTemplateByNameCode(any())).thenReturn(null);

  Exception exception = Assertions.assertThrows(NotFoundException.class, () -> {
   notificationRequestService.savePasswordExpiryNotificationRequest(passwordExpiryNotificationRequest);
  });
  String expectedMessage = "no Template found for";
  String actualMessage = exception.getMessage();
  Assert.assertTrue(actualMessage.contains(expectedMessage));

 }

 @Test
 public void test_Save_Password_Expiry_Notification_Request() throws NotFoundException {
  PasswordExpiryNotificationRequest passwordExpiryNotificationRequest = new PasswordExpiryNotificationRequest();
  PasswordExpiryNotificationRequestUserParameters param = new PasswordExpiryNotificationRequestUserParameters();
  param.setUserId(1);
  param.setDaysToExpiry(5);
  param.setUserLang("EN");
  Set<PasswordExpiryNotificationRequestUserParameters> userParametersList = new HashSet<>();
  userParametersList.add(param);
  passwordExpiryNotificationRequest.setUserParametersList(userParametersList);
  Set<NotificationTemplateParameterDto> notificationTemplateParameters = new HashSet<>();
  NotificationTemplateParameterDto notificationTemplateParameterDto = new NotificationTemplateParameterDto();
  NotificationTemplateParameterDto notificationTemplateParameterDto2 = new NotificationTemplateParameterDto();
  NotificationTemplateParameterDto notificationTemplateParameterDto3 = new NotificationTemplateParameterDto();
  Optional<NotificationTemplateDto> notificationTemplateDto = Optional.of(new NotificationTemplateDto());
  notificationTemplateParameterDto.setId(1);
  notificationTemplateParameterDto.setParameterName("user_lang");
  notificationTemplateParameterDto2.setParameterName("user_id");
  notificationTemplateParameterDto3.setParameterName("days_to_expiry");
  notificationTemplateParameters.add(notificationTemplateParameterDto);
  notificationTemplateParameters.add(notificationTemplateParameterDto2);
  notificationTemplateParameters.add(notificationTemplateParameterDto3);
  notificationTemplateDto.get().setNotificationTemplateParameters(notificationTemplateParameters);
  when(notificationTemplateService.findEnabledNotificationTemplateByNameCode(any())).thenReturn(notificationTemplateDto);
  notificationRequestService.savePasswordExpiryNotificationRequest(passwordExpiryNotificationRequest);
  verify(notificationRequestService, times(1)).save(any());


 }


 private JpaNotificationRequest buildNotificationRequest() {
  JpaNotificationTemplateContent notificationTemplateContent = new JpaNotificationTemplateContent();
  notificationTemplateContent.setId(1);
  notificationTemplateContent.setLang("EN");
  Set<JpaNotificationTemplateContent> notificationTemplateContents = new HashSet<>();
  notificationTemplateContents.add(notificationTemplateContent);
  JpaNotificationTemplate notificationTemplate = new JpaNotificationTemplate();
  notificationTemplate.setId(1);
  notificationTemplate.setNotificationTemplateContents(notificationTemplateContents);
  JpaNotificationRequest notificationRequest = new JpaNotificationRequest();
  notificationRequest.setId(1);
  notificationRequest.setUserId(1);
  notificationRequest.setUserLang("EN");
  notificationRequest.setNotificationTemplate(notificationTemplate);
  return notificationRequest;


 }
}
