/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.segment;

import com.elm.shj.admin.portal.orm.entity.JpaDataSegment;
import com.elm.shj.admin.portal.orm.repository.DataSegmentRepository;
import com.elm.shj.admin.portal.services.dto.DataSegmentDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Service handling data segments
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DataSegmentService extends GenericService<JpaDataSegment, DataSegmentDto, Long> {

    private final DataSegmentRepository dataSegmentRepository;

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

    public List<DataSegmentDto> findOrganizerSegments() {
        return mapList(dataSegmentRepository.findSegments(Arrays.asList(11L, 12L, 13L)));
    }

    public List<DataSegmentDto> findCommandSegments() {
        return mapList(dataSegmentRepository.findSegments(Arrays.asList(1L, 2L, 3L,4L, 5L, 6L,7L, 8L, 9L,10L)));
    }
}
