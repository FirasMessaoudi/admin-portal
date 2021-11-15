/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.repository.UserNotificationRepository;
import com.elm.shj.admin.portal.services.dto.EUserNotificationStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Testing class for service {@link UserNotificationService}
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@ExtendWith(MockitoExtension.class)
public class UserNotificationServiceTest {
    @Mock
    private UserNotificationRepository userNotificationRepository;
    @InjectMocks
    private UserNotificationService userNotificationService;


    @Test
    public void test_find_User_Notifications() {
        String userId = "1";
        userNotificationService.findUserNotifications(userId);
        verify(userNotificationRepository, times(1)).findByUserIdAndStatusCodeNot(anyString(), anyString());
    }

    @Test
    public void test_mark_User_Notification_As_Read() {
        long notificationId = 1;
        userNotificationService.markUserNotificationAsRead(notificationId);
        verify(userNotificationRepository, times(1)).updateUserNotificationStatus(anyLong(), eq(EUserNotificationStatus.READ.name()));
    }

    @Test
    public void test_mark_User_Notification_As_Expired() {
        long notificationId = 1;
        userNotificationService.markUserNotificationsAsExpired(notificationId);
        verify(userNotificationRepository, times(1)).updateUserNotificationStatus(anyLong(), eq(EUserNotificationStatus.EXPIRED.name()));
    }


    @Test
    public void test_retrieve_User_New_Notifications_Count() {
        String userId = "1";
        userNotificationService.retrieveUserNewNotificationsCount(userId);
        verify(userNotificationRepository, times(1)).countByUserIdAndStatusCode(anyLong(), eq(EUserNotificationStatus.NEW.name()));
    }

}
