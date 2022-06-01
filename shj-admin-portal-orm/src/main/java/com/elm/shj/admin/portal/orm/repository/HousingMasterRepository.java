/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaHousingMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for housing master  table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public interface HousingMasterRepository extends JpaRepository<JpaHousingMaster, Long> {
    boolean existsByHousingReferenceCode(String code);
}
