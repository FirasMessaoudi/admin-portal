/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaIncidentTypeLookup;
import com.elm.shj.admin.portal.orm.entity.JpaRitualTypeLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for incident type lookup table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface IncidentTypeLookupRepository extends JpaRepository<JpaIncidentTypeLookup, Long> {
    JpaIncidentTypeLookup findFirstByCode(String typeCode);
}
