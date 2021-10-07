package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Notification Template.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface NotificationTemplateRepository extends JpaRepository<JpaNotificationTemplate, Long> {
     JpaNotificationTemplate findByNameCodeAndEnabledTrue(String nameCode);

}
