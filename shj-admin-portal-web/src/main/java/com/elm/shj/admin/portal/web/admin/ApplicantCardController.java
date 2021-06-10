/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.card.ApplicantCardService;
import com.elm.shj.admin.portal.services.dto.ApplicantCardDto;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
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

    @GetMapping("/searchResult")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public Page<ApplicantCardDto> getApplicantCardsSearchResult(@RequestParam Map<String,String> searchParams, Pageable pageable, Authentication authentication) {

         log.info("list search result cards.");
         JsonObject searchCriteriaAsJson = new JsonParser().parse(searchParams.get("searchCriteria")).getAsJsonObject();
        int pageNumber= Integer.valueOf(searchParams.get("page"));
        String uin = !searchCriteriaAsJson.get("uin").isJsonNull() ? searchCriteriaAsJson.get("uin").getAsString().trim() : null ;
         String idNum=!searchCriteriaAsJson.get("idNumber").isJsonNull()  ?searchCriteriaAsJson.get("idNumber").getAsString().trim():null;
        String cardStatus = searchCriteriaAsJson.has("cardStatus")?!searchCriteriaAsJson.get("cardStatus").isJsonNull() ? searchCriteriaAsJson.get("cardStatus").getAsString().trim() : null:null ;
        return applicantCardService.getApplicantCardsSearchResult( uin==null||uin.equals("") ?null:uin, idNum==null||idNum.equals("") ?null: Long.valueOf(idNum),
                cardStatus==null||cardStatus.equals("") ?null:cardStatus, PageRequest.of(pageNumber, 10));

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
