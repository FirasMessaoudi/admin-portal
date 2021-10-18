/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaUserNotification;
import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testing class for Repository {@link UserNotificationRepository}
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public class UserNotificationRepositoryTest extends AbstractJpaTest {
 private static final int TEST_USER_ID = 1;
 private static final long TEST_NOTIFICATION_ID = 982;
 private static final String TEST_READ_STATUS_CODE = "READ";
 private static final int TEST_EXPECTED_COUNT = 0;

 private static final String TEST_NEW_STATUS_CODE = "NEW";

 @Autowired
 UserNotificationRepository userNotificationRepository;

 @Test
 public void test_count_By_UserId_And_StatusCode() {
  int notificationCount = userNotificationRepository.countByUserIdAndStatusCode(TEST_USER_ID, TEST_NEW_STATUS_CODE);
  assertEquals(TEST_EXPECTED_COUNT, notificationCount);

 }

 @Test
 public void test_mark_User_Notification_As_Read() {
  JpaUserNotification userNotification = userNotificationRepository.findById(TEST_NOTIFICATION_ID).get();
  assertNotNull(userNotification);
  int numberOfAffectedRows = userNotificationRepository.markUserNotificationAsRead(TEST_NOTIFICATION_ID, TEST_READ_STATUS_CODE);

  assertEquals(userNotification.getStatusCode(), TEST_READ_STATUS_CODE);
  assertEquals(1, numberOfAffectedRows);

 }


}
