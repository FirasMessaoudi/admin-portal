/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.card.CompanyStaffCardService;
import com.elm.shj.admin.portal.services.dto.ApplicantCardSearchCriteriaDto;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.dto.CompanyStaffCardDto;
import com.elm.shj.admin.portal.services.dto.CompanyStaffCardFilterDto;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Main controller for applicant card management pages
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@RestController
@RequestMapping(Navigation.API_STAFF_CARDS)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class StaffCardManagementController {

    private final CompanyStaffCardService companyStaffCardService;

    private final JwtTokenService jwtTokenService;

    private static final String APPLICANT_CARD_DETAILS_NOT_FOUND_ERROR_MSG = "no card details found for applicant with this uin";
    private static final int CARD_DETAILS_NOT_FOUND_RESPONSE_CODE = 561;


    @GetMapping("/list")
    //@PreAuthorize("hasAuthority('" + AuthorityConstants.CARD_MANAGEMENT + "')")
    public Page<CompanyStaffCardDto> searchApplicantCards(@RequestParam(value = "staffCardSearchCriteria") String staffCardSearchCriteria,
                                                          Pageable pageable, Authentication authentication) throws IOException {
        log.info("list search result cards.");
        final CompanyStaffCardFilterDto searchCriteria =  new ObjectMapper().readValue(staffCardSearchCriteria, CompanyStaffCardFilterDto.class);
        return companyStaffCardService.searchStaffCards(searchCriteria, pageable);
    }

}
