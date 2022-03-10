/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaAreaLayer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for area layer table.
 *
 * @author Jaafer Jarray
 * @since 1.1.0
 */
public interface AreaLayerRepository extends JpaRepository<JpaAreaLayer, Long> {
}
