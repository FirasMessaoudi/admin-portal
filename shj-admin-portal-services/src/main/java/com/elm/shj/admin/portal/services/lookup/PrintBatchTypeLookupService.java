/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaPrintBatchTypeLookup;
import com.elm.shj.admin.portal.orm.repository.PrintBatchTypeRepository;
import com.elm.shj.admin.portal.services.dto.PrintBatchTypeLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling print batch type lookup
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Service
@Slf4j
public class PrintBatchTypeLookupService extends GenericService<JpaPrintBatchTypeLookup, PrintBatchTypeLookupDto, Long> {
    private final PrintBatchTypeRepository printBatchTypeRepository;

    public PrintBatchTypeLookupService(PrintBatchTypeRepository printBatchTypeRepository) {
        this.printBatchTypeRepository = printBatchTypeRepository;
    }

    public List<PrintBatchTypeLookupDto> findBatchTypeByTarget(String target) {
        return mapList(printBatchTypeRepository.findAllByTarget(target));
    }
}
