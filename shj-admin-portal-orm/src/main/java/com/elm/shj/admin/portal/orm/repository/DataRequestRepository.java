/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaDataRequest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for DataRequest Table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public interface DataRequestRepository extends JpaRepository<JpaDataRequest, Long> {
}
