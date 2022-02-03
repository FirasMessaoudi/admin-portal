/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.card.ApplicantCardService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.prinitng.PrintRequestLiteService;
import com.elm.shj.admin.portal.services.prinitng.PrintRequestService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    private final PrintRequestLiteService printRequestLiteService;
    private final ApplicantCardService applicantCardService;

    /**
     * List paginated print requests.
     *
     * @return paginated print requests.
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.APPLICANT_PRINTING_REQUEST_MANAGEMENT + "')")
    public Page<PrintRequestDto> list(Pageable pageable, Authentication authentication) {
        log.debug("List print requests based on search criteria...");
        return printRequestLiteService.findAll(EPrintingRequestTarget.APPLICANT.name(), pageable);
    }

    /**
     * List paginated print requests based on search criteria.
     *
     * @param pageable the page configuration for the pagination
     * @return the list of print requests
     */

    @PostMapping("/list")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.APPLICANT_PRINTING_REQUEST_MANAGEMENT + "')")
    public Page<PrintRequestDto> list(@RequestBody PrintRequestCriteriaDto criteria, Pageable pageable, Authentication authentication) {
        log.debug("List print requests based on search criteria...");
        return printRequestLiteService.findByFilter(criteria, EPrintingRequestTarget.APPLICANT.name(), pageable);
    }

    /**
     * finds a print request by his ID
     *
     * @param printRequestId the request id to find
     * @return the found user or <code>null</code>
     */
    @GetMapping("/find/{printRequestId}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.VIEW_PRINTING_REQUEST_DETAILS + "')")
    public PrintRequestDto find(@PathVariable long printRequestId) {
        log.debug("Handler for {}", "Find Print Request");
        PrintRequestDto printRequestDto = printRequestService.findOne(printRequestId);
        printRequestDto.getPrintRequestBatches().stream().forEach(batch -> {
            // will be called for each print request batch, get all applicant cards for that batch
            List<Long> cardIds = batch.getPrintRequestBatchCards().stream().map(batchCard -> batchCard.getCardId()).collect(Collectors.toList());
            // to get applicant cards based on the ids list from DB by JPQL Query
            List<ApplicantCardDto> applicantCards = applicantCardService.findApplicantCards(cardIds);
            // map between applicant card and batch card
            batch.getPrintRequestBatchCards().stream().forEach(batchCard -> {
                long cardId = batchCard.getCardId();
                //get the applicant card by id through findAny on stream of applicantCards
                applicantCards.stream().filter(appCard -> appCard.getId() == cardId).findAny()
                        .ifPresent(applicantCardDto -> batchCard.setCard(mapApplicantCardToCardVO(applicantCardDto)));
                //map applicantCard to CardVO and attach it to batch card like below
            });
        });
        return printRequestDto;
    }


    /**
     * Add new print request
     *
     * @param cards TODO Complete documentation     * @return the created request
     */
    @PostMapping("/prepare")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADD_PRINTING_REQUEST + "')")
    public PrintRequestDto prepare(@RequestBody List<CardVO> cards) {
        log.debug("Preparing print request");
        return printRequestService.prepare(cards);
    }

    @PostMapping("/batch")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADD_PRINTING_REQUEST + "')")
    public PrintRequestDto batch(@RequestBody PrintRequestDto printRequest, @RequestParam List<EPrintBatchType> types) {
        log.debug("Batching print request");
        return printRequestService.processBatching(printRequest, types, EPrintingRequestTarget.APPLICANT.name());
    }

    @PostMapping("/confirm")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADD_PRINTING_REQUEST + "')")
    public PrintRequestDto confirm(@RequestBody PrintRequestDto printRequest) {
        log.debug("Confirming print request");
        return printRequestService.confirm(printRequest, EPrintingRequestTarget.APPLICANT.name());
    }

    private CardVO mapApplicantCardToCardVO(ApplicantCardDto applicantCardDto) {
        return CardVO.builder().digitalId(applicantCardDto.getApplicantRitual().getApplicant().getDigitalIds().get(0).getUin())
                .id(applicantCardDto.getId())
                .passportNumber(applicantCardDto.getApplicantRitual().getApplicant().getPassportNumber())
                .fullNameAr(applicantCardDto.getApplicantRitual().getApplicant().getFullNameAr())
                .fullNameEn(applicantCardDto.getApplicantRitual().getApplicant().getFullNameEn())
                .idNumber(applicantCardDto.getApplicantRitual().getApplicant().getIdNumber())
                .referenceNumber(applicantCardDto.getReferenceNumber())
                .statusCode(applicantCardDto.getStatusCode()).build();
    }
}

