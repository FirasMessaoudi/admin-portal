/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaTransportationTypeLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for transportation type lookup table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface TransportationTypeLookupRepository extends JpaRepository<JpaTransportationTypeLookup, Long> {
}
