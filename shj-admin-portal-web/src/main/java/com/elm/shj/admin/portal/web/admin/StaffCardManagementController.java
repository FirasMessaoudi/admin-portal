/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.card.CompanyStaffCardService;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.dto.CompanyStaffCardDto;
import com.elm.shj.admin.portal.services.dto.CompanyStaffCardFilterDto;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
    @PreAuthorize("hasAuthority('" + AuthorityConstants.CARD_MANAGEMENT + "')")
    public Page<CompanyStaffCardDto> searchApplicantCards(@RequestParam(value = "staffCardSearchCriteria") String staffCardSearchCriteria,
                                                          Pageable pageable, Authentication authentication) throws IOException {
        log.info("list search result cards.");
        final CompanyStaffCardFilterDto searchCriteria =  new ObjectMapper().readValue(staffCardSearchCriteria, CompanyStaffCardFilterDto.class);
        return companyStaffCardService.searchStaffCards(searchCriteria, pageable);
    }

    @GetMapping("/list/ready-to-print/all/{uin}/{idNumber}/{hamlahNumber}/{motawefNumber}/{passportNumber}/{nationality}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADD_PRINTING_REQUEST + "')")
    public List<CompanyStaffCardDto> listReadyToPrintCards(@PathVariable String uin, @PathVariable String idNumber, @PathVariable String hamlahNumber,
                                                           @PathVariable String motawefNumber, @PathVariable String passportNumber,
                                                           @PathVariable String nationality, @RequestParam List<Long> excludedCardsIds,
                                                           Authentication authentication) {
        log.info("list all printing cards.");
        return companyStaffCardService.findAllPrintingCards("-1".equals(uin) ? null : uin,
                "-1".equals(idNumber) ? null : idNumber, "-1".equals(hamlahNumber) ? null : hamlahNumber,
                "-1".equals(motawefNumber) ? null : motawefNumber, "-1".equals(passportNumber) ? null : passportNumber,
                "-1".equals(nationality) ? null : nationality, excludedCardsIds);
    }

    @GetMapping("/list/ready-to-print/{uin}/{idNumber}/{hamlahNumber}/{motawefNumber}/{passportNumber}/{nationality}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADD_PRINTING_REQUEST + "')")
    public Page<CompanyStaffCardDto> listReadyToPrintCards(@PathVariable String uin, @PathVariable String idNumber, @PathVariable String hamlahNumber,
                                                           @PathVariable String motawefNumber, @PathVariable String passportNumber,
                                                           @PathVariable String nationality, @RequestParam List<Long> excludedCardsIds,
                                                           Authentication authentication, Pageable pageable) {
        log.info("list all printing cards.");
        return companyStaffCardService.findPrintingCards("-1".equals(uin) ? null : uin,
                "-1".equals(idNumber) ? null : idNumber, "-1".equals(hamlahNumber) ? null : hamlahNumber,
                "-1".equals(motawefNumber) ? null : motawefNumber, "-1".equals(passportNumber) ? null : passportNumber,
                "-1".equals(nationality) ? null : nationality, excludedCardsIds, pageable);
    }


    @GetMapping("/find/{id}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.CARD_MANAGEMENT + "')")
    public CompanyStaffCardDto findById(@PathVariable long id, Authentication authentication) throws IOException {
        log.info("list search result cards.");
        return companyStaffCardService.findOne(id);
    }

}
