/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaRitualUnit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for ritual unit table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public interface RitualUnitRepository extends JpaRepository<JpaRitualUnit, Long> {

    boolean existsByCode(String code);
}
