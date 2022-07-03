/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaDataSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for DataSegment Table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public interface DataSegmentRepository extends JpaRepository<JpaDataSegment, Long> {

    @Query("SELECT ds FROM JpaDataSegment ds WHERE ds.id in :ids ")
    List<JpaDataSegment> findSegments(@Param("ids") List<Long> ids);

}
