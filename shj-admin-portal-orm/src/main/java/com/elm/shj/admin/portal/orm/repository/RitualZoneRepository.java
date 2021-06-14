/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaRitualZone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for ritual zone table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public interface RitualZoneRepository extends JpaRepository<JpaRitualZone, Long> {

    boolean existsByCode(String code);
}
