/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.card.CompanyStaffCardService;
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
 * Main controller for staff printing management pages
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@RestController
@RequestMapping(Navigation.API_STAFF_PRINTING_REQUESTS)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class StaffPrintingManagementController {

    private final PrintRequestService printRequestService;
    private final PrintRequestLiteService printRequestLiteService;
    private final CompanyStaffCardService companyStaffCardService;

    /**
     * List paginated print requests.
     *
     * @param pageable the page configuration for the pagination
     * @return paginated print requests.
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.STAFF_PRINTING_REQUEST_MANAGEMENT + "')")
    public Page<PrintRequestDto> list(Pageable pageable, Authentication authentication) {
        log.debug("List print requests based on search criteria...");
        return printRequestLiteService.findAll(EPrintingRequestTarget.STAFF.name(), pageable);
    }

    /*    *//**
     * List paginated print requests based on search criteria.
     *
     * @param pageable the page configuration for the pagination
     * @return the list of print requests
     *//*
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.STAFF_PRINTING_REQUEST_MANAGEMENT + "')")
    public Page<PrintRequestLiteDto> list(@RequestBody PrintRequestFilterVo filterVo, Pageable pageable, Authentication authentication) {
        log.debug("List print requests based on search criteria...");
        return printRequestLiteService.findByFilter(filterVo, EPrintingRequestTarget.STAFF.name(), pageable);
    }*/

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
            List<CompanyStaffCardDto> staffCards = companyStaffCardService.findStaffCards(cardIds);
            // map between applicant card and batch card
            batch.getPrintRequestBatchCards().stream().forEach(batchCard -> {
                long cardId = batchCard.getCardId();
                //get the applicant card by id through findAny on stream of applicantCards
                staffCards.stream().filter(staffCard -> staffCard.getId() == cardId).findAny()
                        .ifPresent(staffCardDto -> batchCard.setCard(mapStaffCardToCardVO(staffCardDto)));
                //map applicantCard to CardVO and attach it to batch card like below
            });
        });
        return printRequestDto;
    }


    /**
     * Add new print request
     *
     * @param cards list of cards in this print request TODO Complete documentation
     * @return the created request
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
        return printRequestService.processBatching(printRequest, types, EPrintingRequestTarget.STAFF.name());
    }

    @PostMapping("/confirm")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADD_PRINTING_REQUEST + "')")
    public PrintRequestDto confirm(@RequestBody PrintRequestDto printRequest) {
        log.debug("Confirming print request");
        return printRequestService.confirm(printRequest, EPrintingRequestTarget.STAFF.name());
    }

    /**
     * List paginated print requests based on search criteria.
     *
     * @param pageable the page configuration for the pagination
     * @return the list of print requests
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.STAFF_PRINTING_REQUEST_MANAGEMENT + "')")
    public Page<PrintRequestDto> list(@RequestBody PrintRequestCriteriaDto criteria, Pageable pageable, Authentication authentication) {
        log.debug("List print requests based on search criteria...");
        return printRequestLiteService.findByFilter(criteria, EPrintingRequestTarget.STAFF.name(), pageable);
    }

    private CardVO mapStaffCardToCardVO(CompanyStaffCardDto companyStaffCardDto) {
        return CardVO.builder().digitalId(companyStaffCardDto.getCompanyStaffDigitalId().getSuin())
                .id(companyStaffCardDto.getId())
                .passportNumber(companyStaffCardDto.getCompanyStaffDigitalId().getCompanyStaff().getPassportNumber())
                .fullNameAr(companyStaffCardDto.getCompanyStaffDigitalId().getCompanyStaff().getFullNameAr())
                .fullNameEn(companyStaffCardDto.getCompanyStaffDigitalId().getCompanyStaff().getFullNameEn())
                .idNumber(companyStaffCardDto.getCompanyStaffDigitalId().getCompanyStaff().getIdNumber())
                .referenceNumber(companyStaffCardDto.getReferenceNumber())
                .statusCode(companyStaffCardDto.getStatusCode()).build();
    }
}
