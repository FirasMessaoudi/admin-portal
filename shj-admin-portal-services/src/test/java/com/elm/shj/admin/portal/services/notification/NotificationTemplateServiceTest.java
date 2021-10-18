/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.repository.NotificationTemplateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Testing class for service {@link NotificationTemplateService}
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@ExtendWith(MockitoExtension.class)
public class NotificationTemplateServiceTest {
    @Mock
    private NotificationTemplateRepository notificationTemplateRepository;
    @InjectMocks
    private NotificationTemplateService notificationTemplateServic;


    @Test
    public void test_find_Enabled_Notification_Template_By_NameCode() {
        String TEMPLATE_NAME_CODE = "PASSWORD_EXPIRATION";
        notificationTemplateServic.findEnabledNotificationTemplateByNameCode(TEMPLATE_NAME_CODE);
        verify(notificationTemplateRepository, times(1)).findByNameCodeAndEnabledTrue(anyString());
    }
}
