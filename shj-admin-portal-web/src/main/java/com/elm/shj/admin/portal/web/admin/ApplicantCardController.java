/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.card.ApplicantCardService;
import com.elm.shj.admin.portal.services.dto.ApplicantCardDto;
import com.elm.shj.admin.portal.services.dto.ApplicantCardSearchCriteriaDto;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.util.*;
/**
 * Main controller for applicant card management pages
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@RestController
@RequestMapping(Navigation.API_APPLICANT_CARDS)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantCardController {

    private final ApplicantCardService applicantCardService;

    @GetMapping("/list")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public Page<ApplicantCardDto> listApplicantCards(Pageable pageable, Authentication authentication) {
        log.debug("List applicant cards...");
        return applicantCardService.findAll(pageable);
    }

    @GetMapping("/search-applicant-cards")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public Page<ApplicantCardDto> searchApplicantCards(@RequestParam(value = "applicantCardSearchCriteria") String applicantCardSearchCriteria,
                                                       @RequestParam(value = "page") int page, Pageable pageable, Authentication authentication) throws IOException {

        log.info("list search result cards.");
        final ApplicantCardSearchCriteriaDto searchCriteria =
                new ObjectMapper().readValue(applicantCardSearchCriteria, ApplicantCardSearchCriteriaDto.class);


        String uin = searchCriteria.getUin() != null && !searchCriteria.getUin().trim().equals("") ? searchCriteria.getUin().trim() : null;
        Long idNum = searchCriteria.getIdNumber() != null && !searchCriteria.getIdNumber().trim().equals("") ? Long.valueOf(searchCriteria.getIdNumber().trim()) : null;
        String passportNumber = searchCriteria.getPassportNumber() != null && !searchCriteria.getPassportNumber().trim().equals("") ? searchCriteria.getPassportNumber().trim() : null;

        return applicantCardService.searchApplicantCards(uin, idNum, passportNumber, PageRequest.of(page, pageable.getPageSize()));

    }



    /**
     * List cards to print satisfying search parameters.
     *
     * @param excludedCardsIds
     * @param pageable
     * @param uin
     * @param idNumber
     * @param hamlahNumber
     * @param motawefNumber
     * @param passportNumber
     * @param nationality
     * @param authentication
     * @return the list of printing cards
     */
    @GetMapping("/list/ready-to-print/{uin}/{idNumber}/{hamlahNumber}/{motawefNumber}/{passportNumber}/{nationality}")
    @RolesAllowed({AuthorityConstants.USER_MANAGEMENT}) //TODO: Change it
    public Page<ApplicantCardDto> listReadyToPrintCards(@RequestParam List<Long> excludedCardsIds, Pageable pageable,
                                                        @PathVariable String uin, @PathVariable Long idNumber, @PathVariable String hamlahNumber,
                                                        @PathVariable String motawefNumber, @PathVariable String passportNumber,
                                                        @PathVariable String nationality, Authentication authentication) {
        log.info("list printing cards.");
        return applicantCardService.findPrintingCards("-1".equals(uin) ? null : uin,
                idNumber <= 0 ? null : idNumber, "-1".equals(hamlahNumber) ? null : hamlahNumber,
                "-1".equals(motawefNumber) ? null : motawefNumber, "-1".equals(passportNumber) ? null : passportNumber,
                "-1".equals(nationality) ? null : nationality, excludedCardsIds, pageable);
    }

    @GetMapping("/list/ready-to-print/all/{uin}/{idNumber}/{hamlahNumber}/{motawefNumber}/{passportNumber}/{nationality}")
    @RolesAllowed({AuthorityConstants.USER_MANAGEMENT}) //TODO: Change it
    public List<ApplicantCardDto> listReadyToPrintCards(@PathVariable String uin, @PathVariable Long idNumber, @PathVariable String hamlahNumber,
                                                        @PathVariable String motawefNumber, @PathVariable String passportNumber,
                                                        @PathVariable String nationality, @RequestParam List<Long> excludedCardsIds,
                                                        Authentication authentication) {
        log.info("list all printing cards.");
        return applicantCardService.findAllPrintingCards("-1".equals(uin) ? null : uin,
                idNumber <= 0 ? null : idNumber, "-1".equals(hamlahNumber) ? null : hamlahNumber,
                "-1".equals(motawefNumber) ? null : motawefNumber, "-1".equals(passportNumber) ? null : passportNumber,
                "-1".equals(nationality) ? null : nationality, excludedCardsIds);
    }

    /**
     * Finds a card by its ID
     *
     * @param cardId the card ID to find
     * @return the found card or <code>null</code>
     */
    @GetMapping("/find/{cardId}")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public ApplicantCardDto findApplicantCard(@PathVariable long cardId) {
        log.debug("Handler for {}", "Find Applicant Card");
        return applicantCardService.findOne(cardId);
    }
}
