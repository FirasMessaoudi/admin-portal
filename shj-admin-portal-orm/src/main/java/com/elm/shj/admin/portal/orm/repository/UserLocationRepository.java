/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaUserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository for user location table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
public interface UserLocationRepository extends JpaRepository<JpaUserLocation, Long> {

    Optional<JpaUserLocation> findTopByUserIdAndUserTypeOrderByCreationDateDesc(@Param("uin") String uin, @Param("type") String type);
}