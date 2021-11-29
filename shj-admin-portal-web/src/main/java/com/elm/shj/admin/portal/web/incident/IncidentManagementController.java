/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.incident;

import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.incident.ApplicantIncidentService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main controller for incident management page
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@RestController
@RequestMapping(Navigation.API_INCIDENTS)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IncidentManagementController {

    private final ApplicantIncidentService applicantIncidentService;

    /**
     * List all incidents.
     *
     * @return the list of incidents
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.NOTIFICATION_MANAGEMENT + "')") //TODO Change it to INCIDENT_MANAGEMENT
    public Page<ApplicantIncidentDto> list(@RequestBody IncidentSearchCriteriaDto criteria, Pageable pageable, Authentication authentication) {
        return applicantIncidentService.findAll(criteria, pageable);
    }


}
