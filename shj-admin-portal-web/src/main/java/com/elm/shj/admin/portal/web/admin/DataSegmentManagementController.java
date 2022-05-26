/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.data.segment.DataSegmentService;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.dto.DataSegmentDto;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * Main controller for data segment management page
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@RestController
@RequestMapping(Navigation.API_DATA_SEGMENT)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DataSegmentManagementController {

    private final DataSegmentService dataSegmentService;

    /**
     * List all data segments.
     *
     * @return the list of data segments
     */
    @GetMapping("/list")
    @RolesAllowed({AuthorityConstants.ROLE_MANAGEMENT})
    public List<DataSegmentDto> list() {
        log.info("list all data segments.");
        return dataSegmentService.findCommandSegments();
    }

}
