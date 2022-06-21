/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaComplaintTypeLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for complaint type lookup table.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
public interface ComplaintTypeLookupRepository extends JpaRepository<JpaComplaintTypeLookup, Long> {
    JpaComplaintTypeLookup findFirstByCode(int code);
}
