/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.incident;

import com.elm.shj.admin.portal.services.incident.ApplicantIncidentService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

}
