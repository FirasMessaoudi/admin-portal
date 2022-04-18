/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaSuggestedSupplicationLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for suggested supplication data.
 *
 * @author r.chebbi
 * @since 1.1.0
 **/
public interface SuggestedSupplicationLookupRepository extends JpaRepository<JpaSuggestedSupplicationLookup,Long> {

}
