/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.orm.entity.PrintRequestFilterVo;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.prinitng.PrintRequestLiteService;
import com.elm.shj.admin.portal.services.prinitng.PrintRequestService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Main controller for printing management pages
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@RestController
@RequestMapping(Navigation.API_PRINTING_REQUESTS)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PrintingManagementController {

    private final PrintRequestService printRequestService;
    private final PrintRequestLiteService printRequestLiteService;
    /**
     * List paginated print requests.
     *
     * @return paginated print requests.
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.APPLICANT_PRINTING_REQUEST_MANAGEMENT + "')")
    public Page<PrintRequestLiteDto> list(Pageable pageable, Authentication authentication) {
        log.debug("List print requests based on search criteria...");
        return printRequestLiteService.findAll(EPrintingRequestTarget.APPLICANT.name(), pageable);
    }

    /**
     * List paginated print requests based on search criteria.
     *
     * @param pageable the page configuration for the pagination
     * @return the list of print requests
     */

    @PostMapping("/list")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.APPLICANT_PRINTING_REQUEST_MANAGEMENT + "')")
    public Page<PrintRequestLiteDto> list(@RequestBody PrintRequestFilterVo filterVo, Pageable pageable, Authentication authentication) {
        log.debug("List print requests based on search criteria...");
        return printRequestLiteService.findByFilter(filterVo, EPrintingRequestTarget.APPLICANT.name(), pageable);
    }

    /**
     * finds a print request by his ID
     *
     * @param printRequestId the request id to find
     * @return the found user or <code>null</code>
     */
    @GetMapping("/find/{printRequestId}")
    @PreAuthorize("hasAuthority('"+AuthorityConstants.VIEW_PRINTING_REQUEST_DETAILS+"')")
    public PrintRequestDto find(@PathVariable long printRequestId) {
        log.debug("Handler for {}", "Find Print Request");
        return printRequestService.findOne(printRequestId);
    }

    /**
     * Add new print request
     *
     * @param cardsIds TODO Complete documentation     * @return the created request
     */
    @PostMapping("/prepare")
    @PreAuthorize("hasAuthority('"+AuthorityConstants.ADD_PRINTING_REQUEST+"')")
    public PrintRequestDto prepare(@RequestBody List<Long> cardsIds) {
        log.debug("Preparing print request");
        return printRequestService.prepare(cardsIds);
    }

    @PostMapping("/batch")
    @PreAuthorize("hasAuthority('"+AuthorityConstants.ADD_PRINTING_REQUEST+"')")
    public PrintRequestDto batch(@RequestBody PrintRequestDto printRequest, @RequestParam List<EPrintBatchType> types) {
        log.debug("Batching print request");
        return printRequestService.processBatching(printRequest, types, EPrintingRequestTarget.APPLICANT.name());
    }

    @PostMapping("/confirm")
    @PreAuthorize("hasAuthority('"+AuthorityConstants.ADD_PRINTING_REQUEST+"')")
    public PrintRequestDto confirm(@RequestBody PrintRequestDto printRequest) {
        log.debug("Confirming print request");
        return printRequestService.confirm(printRequest, EPrintingRequestTarget.APPLICANT.name());
    }

}

