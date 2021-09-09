/*
 *  Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaAuthorityLookup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for authority lookup table.
 *
 * @author Ahmad Flaifel
 * @since 1.8.0
 */
public interface AuthorityLookupRepository extends JpaRepository<JpaAuthorityLookup, Long> {
    long INTEGRATION_WEB_SERVICE_AUTH_ID = 34;
    List<JpaAuthorityLookup> findAllByParentIsNullAndIdNot(long id);
}
