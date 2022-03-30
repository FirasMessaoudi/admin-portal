/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.prinitng;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequest;
import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestBatch;
import com.elm.shj.admin.portal.orm.repository.PrintRequestBatchRepository;
import com.elm.shj.admin.portal.services.card.CompanyStaffCardService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service handling print request
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PrintRequestBatchService extends GenericService<JpaPrintRequestBatch, PrintRequestBatchDto, Long> {
    private final PrintRequestBatchRepository printRequestBatchRepository;

    public List<PrintRequestBatchDto> findPrintRequestBatches(long printRequestId){
        return mapList(printRequestBatchRepository.findPrintRequestBatches(printRequestId));
    }

    public List<PrintRequestBatchDto> findStaffPrintRequestBatches(long printRequestId){
        return mapList(printRequestBatchRepository.findStaffPrintRequestBatches(printRequestId));
    }
}
