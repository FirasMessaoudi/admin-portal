/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaBatchMainCollection;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for batch main collection table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public interface BatchMainCollectionRepository extends JpaRepository<JpaBatchMainCollection, Long> {
}
