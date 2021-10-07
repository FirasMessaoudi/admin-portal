package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationCategoryLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for JpaNotificationCategoryLookup.
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
public interface NotificationCategoryLookupRepository extends JpaRepository<JpaNotificationCategoryLookup, Long> {
}
