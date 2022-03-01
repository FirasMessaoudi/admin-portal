/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaAreaLayerLookup;
import com.elm.shj.admin.portal.orm.repository.AreaLayerLookupRepository;
import com.elm.shj.admin.portal.services.dto.AreaLayerLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service handling area layer lookup
 *
 * @author jaafer jarray
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AreaLayerLookupService extends GenericService<JpaAreaLayerLookup, AreaLayerLookupDto, Long> {
    private final AreaLayerLookupRepository repository;
}
