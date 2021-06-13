/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaRitualTypeLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for JpaRitualTypeLookup.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public interface RitualTypeLookupRepository extends JpaRepository<JpaRitualTypeLookup, Long> {

    boolean existsByCode(String ritualTypeCode);

    JpaRitualTypeLookup findFirstByCode(String ritualTypeCode);
}
