/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationProcessingStatusLookup;
import com.elm.shj.admin.portal.orm.entity.JpaNotificationRequest;
import com.elm.shj.admin.portal.orm.repository.NotificationRequestRepository;
import com.elm.shj.admin.portal.services.dto.ENotificationProcessingStatus;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Testing class for service {@link NotificationProcessingScheduler}
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@ExtendWith(MockitoExtension.class)
public class NotificationProcessingSchedulerTest {

    @Mock
    private NotificationRequestRepository notificationRequestRepository;
    @Mock
    private NotificationRequestService notificationRequestService;
    @InjectMocks
    private NotificationProcessingScheduler notificationProcessingScheduler;

    @BeforeEach
    public void beforeEach() throws Exception {
        ReflectionTestUtils.setField(notificationProcessingScheduler, "notificationProcessingBatchSize", 1000);


    }


    @Test
    public void test_send_User_Notifications() {

        Page<JpaNotificationRequest> notificationRequests = buildNotificationRequests();
        when(notificationRequestRepository.findNotificationRequests(any(), any(), any())).thenReturn(notificationRequests);
        doNothing().when(notificationRequestService).processNotificationRequest(any());
        notificationProcessingScheduler.sendUserNotifications();
        verify(notificationRequestRepository, times(1)).save(notificationRequests.getContent().get(0));
        verify(notificationRequestService, times(1)).processNotificationRequest(notificationRequests.getContent().get(0));

    }


    @Test
    public void test_send_User_Notifications_throw_exception() {

        Page<JpaNotificationRequest> notificationRequests = buildNotificationRequests();
        when(notificationRequestRepository.findNotificationRequests(any(), any(), any())).thenReturn(notificationRequests);
        doThrow(new RuntimeException()).when(notificationRequestService).processNotificationRequest(any());
        notificationProcessingScheduler.sendUserNotifications();
        verify(notificationRequestRepository, times(2)).save(notificationRequests.getContent().get(0));
        Assert.assertEquals(notificationRequests.getContent().get(0).getProcessingStatus().getId(), ENotificationProcessingStatus.FAILED.getId());
    }


    private Page<JpaNotificationRequest> buildNotificationRequests() {
        List<JpaNotificationRequest> requests = new ArrayList<>();
        JpaNotificationRequest notificationRequest = new JpaNotificationRequest();
        JpaNotificationProcessingStatusLookup jpaNotificationProcessingStatusLookup = new JpaNotificationProcessingStatusLookup();
        jpaNotificationProcessingStatusLookup.setId(ENotificationProcessingStatus.NEW.getId());
        notificationRequest.setProcessingStatus(jpaNotificationProcessingStatusLookup);
        requests.add(notificationRequest);
        Page<JpaNotificationRequest> notificationRequests = new PageImpl<>(requests);
        return notificationRequests;
    }
}
