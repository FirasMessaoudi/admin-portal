/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;


import com.elm.shj.admin.portal.orm.entity.JpaUserCardStatusAudit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for UserCardStatusAudit Table.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */

public interface UserCardStatusAuditRepository extends JpaRepository<JpaUserCardStatusAudit, Long> {

}
