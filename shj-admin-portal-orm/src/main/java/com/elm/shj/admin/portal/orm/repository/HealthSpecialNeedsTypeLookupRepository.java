/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaHealthSpecialNeedsTypeLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for health special needs type lookup table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
public interface HealthSpecialNeedsTypeLookupRepository extends JpaRepository<JpaHealthSpecialNeedsTypeLookup, Long> {

    boolean existsByCode(String specialNeedsCode);
}
