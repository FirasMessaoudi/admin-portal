/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaUserNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for user notifications.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface UserNotificationRepository extends JpaRepository<JpaUserNotification, Long> {

    List<JpaUserNotification> findByUserIdAndStatusCodeNot(Long userId, String statusCode);

    Page<JpaUserNotification> findByStatusCodeNot(Pageable pageable, String statusCode);

    @Modifying
    @Query("update JpaUserNotification n set n.statusCode = :statusCode where n.id =:notificationId ")
    int updateUserNotificationStatus(@Param("notificationId") Long notificationId, @Param("statusCode") String statusCode);

    int countByUserIdAndStatusCode(long userId, String statusCode);
}
