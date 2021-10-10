package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for Notification Template.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface NotificationTemplateRepository extends JpaRepository<JpaNotificationTemplate, Long> {
     JpaNotificationTemplate findByNameCodeAndEnabledTrue(String nameCode);

     @Query(value = "select n.enabled from JpaNotificationTemplate n where " + " n.nameCode = :nameCode ")
     boolean isNotificationTemplateEnabled(@Param("nameCode") String nameCode);

}
