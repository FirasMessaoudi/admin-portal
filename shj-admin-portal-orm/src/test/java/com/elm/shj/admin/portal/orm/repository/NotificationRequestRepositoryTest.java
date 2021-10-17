package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationRequest;
import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationRequestRepositoryTest extends AbstractJpaTest {
    private static final int TEST_BATCH_SIZE = 1000;
    private static final String TEST_PROCESSING_STATUS_CODE = "NEW";
    private static final int TEST_EXPECTED_COUNT = 0;
    @Autowired
    NotificationRequestRepository notificationRequestRepository;

    @Test
    public void test_find_Notification_Requests_Status_New_And_Sending_Date_Before() {
        Page<JpaNotificationRequest> notificationRequests = notificationRequestRepository.findNotificationRequests(PageRequest.ofSize(TEST_BATCH_SIZE), TEST_PROCESSING_STATUS_CODE, new Date());
        assertEquals(TEST_EXPECTED_COUNT, notificationRequests.getContent().size());
//        assertNotNull( notificationRequests.getContent().get(0).getNotificationRequestParameterValues());

    }

}
