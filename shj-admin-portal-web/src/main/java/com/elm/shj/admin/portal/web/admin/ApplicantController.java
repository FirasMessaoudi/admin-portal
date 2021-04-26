/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * Main controller for applicant management pages
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@RestController
@RequestMapping(Navigation.API_APPLICANTS)
@Slf4j
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @GetMapping("/list/all")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public Page<ApplicantDto> listApplicants(Pageable pageable) {
        log.debug("List applicants...");
        return applicantService.findAll(pageable);
    }

}
