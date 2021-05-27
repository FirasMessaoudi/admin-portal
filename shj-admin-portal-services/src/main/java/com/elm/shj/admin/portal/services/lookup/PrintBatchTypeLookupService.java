/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaPrintBatchTypeLookup;
import com.elm.shj.admin.portal.services.dto.PrintBatchTypeLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling print batch type lookup
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Service
@Slf4j
public class PrintBatchTypeLookupService extends GenericService<JpaPrintBatchTypeLookup, PrintBatchTypeLookupDto, Long> {
}
