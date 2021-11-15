/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaUserNotificationCategoryPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for user notifications category preference.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
public interface UserNotificationCategoryPreferenceRepository extends JpaRepository<JpaUserNotificationCategoryPreference, Long> {

    List<JpaUserNotificationCategoryPreference> findByUserId(String userId);
}
