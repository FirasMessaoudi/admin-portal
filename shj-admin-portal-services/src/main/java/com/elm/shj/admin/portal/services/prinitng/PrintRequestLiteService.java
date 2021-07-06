/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.prinitng;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestLite;
import com.elm.shj.admin.portal.orm.entity.PrintRequestFilterVo;
import com.elm.shj.admin.portal.orm.repository.PrintRequestBatchRepository;
import com.elm.shj.admin.portal.orm.repository.PrintRequestCardRepository;
import com.elm.shj.admin.portal.orm.repository.PrintRequestLiteRepository;
import com.elm.shj.admin.portal.services.dto.PrintRequestLiteDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Service handling print request lite
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PrintRequestLiteService extends GenericService<JpaPrintRequestLite, PrintRequestLiteDto, Long> {

    private final PrintRequestLiteRepository printRequestLiteRepository;
    private final PrintRequestCardRepository printRequestCardRepository;
    private final PrintRequestBatchRepository printRequestBatchRepository;

    /**
     * Find the lite version of all print requests.
     *
     * @param pageable the current page information
     * @return the list of print requests
     */
    public Page<PrintRequestLiteDto> findAll(Pageable pageable) {
        Page<PrintRequestLiteDto> litePrintRequests = mapPage(printRequestLiteRepository.getAll(pageable));
        litePrintRequests.forEach(p -> {
            p.setCardsCount(printRequestCardRepository.countAllByPrintRequest_Id(p.getId()));
            p.setBatchesCount(printRequestBatchRepository.countAllByPrintRequest_Id(p.getId()));
        });
        return litePrintRequests;
    }

    /**
     * Find paginated print requests based on filter.
     *
     * @param pageable requested page of result.
     * @param filterVo filter value object
     * @return
     */
    @Transactional
    public Page<PrintRequestLiteDto> findByFilter(PrintRequestFilterVo filterVo, Pageable pageable) {
        // at the time being, filter has only status code.
        Page<PrintRequestLiteDto> litePrintRequests = mapPage(printRequestLiteRepository.findByStatusCode(filterVo.getStatusCode(), pageable));
        litePrintRequests.forEach(p -> {
            p.setCardsCount(printRequestCardRepository.countAllByPrintRequest_Id(p.getId()));
            p.setBatchesCount(printRequestBatchRepository.countAllByPrintRequest_Id(p.getId()));
        });
        return litePrintRequests;
    }

}
