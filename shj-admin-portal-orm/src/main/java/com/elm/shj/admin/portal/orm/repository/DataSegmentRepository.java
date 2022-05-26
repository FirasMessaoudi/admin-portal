/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaDataSegment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for DataSegment Table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public interface DataSegmentRepository extends JpaRepository<JpaDataSegment, Long> {
    List<JpaDataSegment> findByIdIn(List<Long> ids);

    List<JpaDataSegment> findByIdNotIn(List<Long> ids);
}
