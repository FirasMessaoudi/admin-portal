/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.repository.NotificationRequestRepository;
import com.elm.shj.admin.portal.orm.repository.UserNotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
 @InjectMocks
 private NotificationRequestService notificationRequestService;

 @Test
 public void test_process_Notification_Request() {
  String TEMPLATE_NAME_CODE = "PASSWORD_EXPIRATION";
  notificationRequestService.processNotificationRequest(any());
  verify(userNotificationRepository, times(1)).save(any());
  verify(notificationRequestRepository, times(1)).delete(any());

 }

//  private JpaNotificationRequest buildNotificationRequest (){
//
//  }
}
