/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplate;
import com.elm.shj.admin.portal.orm.entity.JpaUserNotification;
import com.elm.shj.admin.portal.orm.repository.UserNotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Testing class for service {@link UserNotificationExpirationScheduler}
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@ExtendWith(MockitoExtension.class)
public class UserNotificationExpirationSchedulerTest {
    @Mock
    private UserNotificationRepository userNotificationRepository;
    @Mock
    private UserNotificationService userNotificationService;
    @InjectMocks
    private UserNotificationExpirationScheduler userNotificationExpirationScheduler;


    @BeforeEach
    public void beforeEach() throws Exception {
        ReflectionTestUtils.setField(userNotificationExpirationScheduler, "userNotificationExpirationBatchSize", 1000);
    }

    @Test
    public void test_mark_User_Notifications_As_Expired() {

        Page<JpaUserNotification> userNotifications = buildUserNotifications();
        when(userNotificationRepository.findByStatusCodeNot(any(), anyString())).thenReturn(userNotifications);
        userNotificationExpirationScheduler.markUserNotificationsAsExpired();
        verify(userNotificationService, times(1)).markUserNotificationsAsExpired(userNotifications.getContent().get(0).getId());

    }


    private Page<JpaUserNotification> buildUserNotifications() {
        JpaUserNotification userNotification = new JpaUserNotification();
        userNotification.setNotificationTemplate(new JpaNotificationTemplate());
        List<JpaUserNotification> userNotifications = new ArrayList<>();
        userNotification.setId(999);
        userNotification.getNotificationTemplate().setExpirationPeriodInMinutes(200);
        userNotification.setCreationDate(Date.from(LocalDateTime.now().minusMinutes(300).atZone(ZoneId.systemDefault()).toInstant()));
        userNotifications.add(userNotification);
        Page<JpaUserNotification> userNotificationsPage = new PageImpl<>(userNotifications);
        return userNotificationsPage;
    }
}
