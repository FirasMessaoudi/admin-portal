/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaHealthImmunizationLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for health immunization lookup table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
public interface HealthImmunizationLookupRepository extends JpaRepository<JpaHealthImmunizationLookup, Long> {

    boolean existsByCode(String immunizationCode);
}
