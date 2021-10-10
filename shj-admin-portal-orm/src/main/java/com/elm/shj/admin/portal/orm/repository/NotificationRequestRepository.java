package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for   Notification Request .
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface NotificationRequestRepository extends JpaRepository<JpaNotificationRequest, Long> {
}
