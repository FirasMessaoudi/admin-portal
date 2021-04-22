/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.request;

import com.elm.shj.admin.portal.orm.entity.JpaDataRequest;
import com.elm.shj.admin.portal.services.dto.DataRequestDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service handling data requests
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Service
public class DataRequestService extends GenericService<JpaDataRequest, DataRequestDto, Long> {

    /**
     * Find all data requests.
     *
     * @param pageable the current page information
     * @return the list of data requests
     */
    public Page<DataRequestDto> findAll(Pageable pageable) {
        return mapPage(getRepository().findAll(pageable));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public DataRequestDto save(DataRequestDto dto) {
        return super.save(dto);
    }
}
