/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestStatusLookup;
import com.elm.shj.admin.portal.services.dto.PrintRequestStatusLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling print request status lookup
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Service
@Slf4j
public class PrintRequestStatusLookupService extends GenericService<JpaPrintRequestStatusLookup, PrintRequestStatusLookupDto, Long> {
}
