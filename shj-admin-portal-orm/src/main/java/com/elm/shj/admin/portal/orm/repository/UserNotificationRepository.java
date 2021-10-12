/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaUserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository for User Notification  .
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface UserNotificationRepository extends JpaRepository<JpaUserNotification, Long> {
    List<JpaUserNotification> findByUserId(Long userId);


    @Modifying
    @Query("update JpaUserNotification n set n.statusCode = :statusCode where n.id =:notificationId ")
    int updateUserNotificationStatus(Long notificationId, String statusCode);

}
