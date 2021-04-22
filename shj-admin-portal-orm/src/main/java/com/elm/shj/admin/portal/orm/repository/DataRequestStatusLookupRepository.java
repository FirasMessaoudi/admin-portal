/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaDataRequestStatusLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for DataRequestStatusLookup Table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public interface DataRequestStatusLookupRepository extends JpaRepository<JpaDataRequestStatusLookup, Long> {
}
