/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaUserNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for User Notification.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface UserNotificationRepository extends JpaRepository<JpaUserNotification, Long> {
    List<JpaUserNotification> findByUin(String uin);
}
