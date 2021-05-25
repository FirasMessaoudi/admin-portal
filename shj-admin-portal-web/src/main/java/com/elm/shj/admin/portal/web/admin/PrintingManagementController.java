/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
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

    @GetMapping("/list")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public Page<PrintRequestDto> list(Pageable pageable, Authentication authentication) {
        log.debug("List print requests...");
        return printRequestService.findAll(pageable);
    }

    @GetMapping("/list/new")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public Page<PrintRequestDto> listNew(Pageable pageable, Authentication authentication) {
        log.debug("List print requests with status new...");
        return printRequestService.findNew(pageable);
    }

    @PostMapping("/create")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public PrintRequestDto create(@RequestBody List<Long> applicantsIds) {
        log.debug("Creating print request");
        return printRequestService.save(applicantsIds);
    }

}
