/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.orm.entity.PrintRequestFilterVo;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.dto.EPrintBatchType;
import com.elm.shj.admin.portal.services.dto.PrintRequestDto;
import com.elm.shj.admin.portal.services.print.request.PrintRequestService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
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

    /**
     * List paginated print requests.
     *
     * @param pageable the page configuration for the pagination
     * @return paginated print requests.
     */
    @GetMapping("/list")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public Page<PrintRequestDto> list(Pageable pageable, Authentication authentication) {
        log.debug("List print requests based on search criteria...");
        return printRequestService.findOtherThanNew(pageable);
    }

    /**
     * List paginated print requests based on search criteria.
     *
     * @param pageable the page configuration for the pagination
     * @return the list of print requests
     */
    @PostMapping("/list")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public Page<PrintRequestDto> list(@RequestBody PrintRequestFilterVo filterVo, Pageable pageable, Authentication authentication) {
        log.debug("List print requests based on search criteria...");
        return printRequestService.findByFilter(filterVo, pageable);
    }

    /**
     * finds a print request by his ID
     *
     * @param printRequestId the request id to find
     * @return the found user or <code>null</code>
     */
    @GetMapping("/find/{printRequestId}")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public PrintRequestDto find(@PathVariable long printRequestId) {
        log.debug("Handler for {}", "Find Print Request");
        return printRequestService.findOne(printRequestId);
    }

    /**
     * Add new print request
     *
     * @param cardsIds TODO Complete documentation
     * @return the created request
     */
    @PostMapping("/create")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public PrintRequestDto create(@RequestBody List<Long> cardsIds) {
        log.debug("Creating print request");
        return printRequestService.save(cardsIds);
    }

    @PostMapping("/batch")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public PrintRequestDto batch(@RequestBody PrintRequestDto printRequest, @RequestParam List<EPrintBatchType> types) {
        log.debug("Batching print request");
        return printRequestService.processBatching(printRequest, types);
    }

    @PostMapping("/confirm")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public PrintRequestDto confirm(@RequestBody PrintRequestDto printRequest) {
        log.debug("Confirming print request");
        return printRequestService.confirm(printRequest);
    }

}
