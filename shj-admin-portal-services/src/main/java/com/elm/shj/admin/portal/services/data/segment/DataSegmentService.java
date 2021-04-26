/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.segment;

import com.elm.shj.admin.portal.orm.entity.JpaDataSegment;
import com.elm.shj.admin.portal.services.dto.DataSegmentDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service handling data segments
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Service
public class DataSegmentService extends GenericService<JpaDataSegment, DataSegmentDto, Long> {

    /**
     * Find all data segments.
     *
     * @param pageable the current page information
     * @return the list of data segments
     */
    public Page<DataSegmentDto> findAll(Pageable pageable) {
        return mapPage(getRepository().findAll(pageable));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public DataSegmentDto save(DataSegmentDto dataSegment) {
        return super.save(dataSegment);
    }

    /**
     * Loads the data segment template file from the classpath
     *
     * @param dataSegment the data segment object
     * @return the data segment template file as resource
     */
    public Resource loadTemplateFile(DataSegmentDto dataSegment) {
        return new ClassPathResource("/templates/excel/" + dataSegment.getId() + "/" + dataSegment.getTemplateFileName());
    }
}
