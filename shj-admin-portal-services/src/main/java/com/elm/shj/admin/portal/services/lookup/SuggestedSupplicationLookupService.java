/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaSuggestedSupplicationLookup;
import com.elm.shj.admin.portal.services.dto.SuggestedSupplicationLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service handling suggested supplication
 *
 * @author r.chebbi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SuggestedSupplicationLookupService extends GenericService<JpaSuggestedSupplicationLookup, SuggestedSupplicationLookupDto, Long> {

}
