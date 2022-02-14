/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaUserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for user location table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
public interface UserLocationRepository extends JpaRepository<JpaUserLocation, Long> {
}