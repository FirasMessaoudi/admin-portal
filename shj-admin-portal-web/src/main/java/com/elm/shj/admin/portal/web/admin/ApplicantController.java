/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.applicant.ApplicantLiteService;
import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.ApplicantLiteDto;
import com.elm.shj.admin.portal.services.dto.ApplicantSearchCriteriaDto;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.web.error.ApplicantNotFoundException;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main controller for applicant management pages
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@RestController
@RequestMapping(Navigation.API_APPLICANTS)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantController {

    public final static String ISO8601_DATE_PATTERN = "yyyy-MM-dd";
    public final static String SAUDI_MOBILE_NUMBER_REGEX = "^(009665|9665|\\+9665|05|5)([0-9]{8})$";
    private static final int APPLICANT_NOT_FOUND_RESPONSE_CODE = 561;
    private static final String APPLICANT_NOT_FOUND_ERROR_MSG = "not found";

    private final ApplicantService applicantService;
    private final ApplicantLiteService applicantLiteService;


    @GetMapping("/list/all")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public Page<ApplicantDto> listApplicants(Pageable pageable) {
        log.debug("List applicants...");
        return applicantService.findAll(pageable);
    }

    /**
     * finds a list of applicants matching criteria
     *
     * @return the list of applicants or empty list
     */
    @PostMapping("/find")
    @RolesAllowed(AuthorityConstants.USER_DEFINED_NOTIFICATION_MANAGEMENT)
    public Page<ApplicantDto> findApplicants(@RequestBody ApplicantSearchCriteriaDto applicantSearchCriteria,
                                             @RequestParam List<Long> excludedIds,
                                             Pageable pageable) {
        log.debug("Find applicants matching criteria...");
        return applicantService.findAllByCriteriaAndNotInExcludedIds(applicantSearchCriteria, excludedIds, pageable);
    }
}
