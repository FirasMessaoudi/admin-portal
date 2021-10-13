/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationRequestParameterValue;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for  Notification Request Parameter Value   .
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface NotificationRequestParameterValueRepository extends JpaRepository<JpaNotificationRequestParameterValue, Long> {

}
