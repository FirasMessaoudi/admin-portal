/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaRitualGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for ritual group table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public interface RitualGroupRepository extends JpaRepository<JpaRitualGroup, Long> {

    boolean existsByCode(String code);
}
