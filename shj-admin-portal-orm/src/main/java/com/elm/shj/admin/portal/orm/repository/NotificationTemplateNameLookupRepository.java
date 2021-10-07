package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplateNameLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for JpaNotificationTemplateNameLookup.
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
public interface NotificationTemplateNameLookupRepository extends JpaRepository<JpaNotificationTemplateNameLookup, Long> {
}
