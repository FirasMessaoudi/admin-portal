/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.mapper;

import com.elm.shj.admin.portal.services.dto.DataSegmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Registry used to access data row mappers information by data segment.
 * These information are collected from the application context.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DataSegmentMappingRegistry {

    private final ApplicationContext appContext;
    private final Map<Long, AbstractRowMapper<?>> registry = new HashMap<>();

    /**
     * initiates the registry from the application context
     */
    @SuppressWarnings("rawtypes")
    @PostConstruct
    public void initRegistry() {
        Collection<AbstractRowMapper> foundMappers = appContext.getBeansOfType(AbstractRowMapper.class).values();
        foundMappers.forEach(foundMapper -> {
            registry.put(foundMapper.getDataSegmentId().getId(), foundMapper);
        });
    }

    /**
     * Returns the AbstractRowMapper for the given data segment.
     *
     * @param dataSegment the data segment
     * @return {@link AbstractRowMapper} instance
     */
    public AbstractRowMapper<?> mapperOf(DataSegmentDto dataSegment) {
        return registry.get(dataSegment.getId());
    }

}
