package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repository for Notification Template.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface NotificationTemplateRepository extends JpaRepository<JpaNotificationTemplate, Long>, JpaSpecificationExecutor<JpaNotificationTemplate> {
    JpaNotificationTemplate findByNameCodeAndEnabledTrue(String nameCode);

}
