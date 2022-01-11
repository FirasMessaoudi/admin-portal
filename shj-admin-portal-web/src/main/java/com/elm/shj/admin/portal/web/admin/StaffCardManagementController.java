/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.card.ApplicantCardService;
import com.elm.shj.admin.portal.services.card.CompanyStaffCardService;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.dto.CompanyStaffCardDto;
import com.elm.shj.admin.portal.services.dto.CompanyStaffCardFilterDto;
import com.elm.shj.admin.portal.web.error.CardDetailsNotFoundException;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtToken;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.NotAuthorizedException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        final CompanyStaffCardFilterDto searchCriteria = new ObjectMapper().readValue(staffCardSearchCriteria, CompanyStaffCardFilterDto.class);
        return companyStaffCardService.searchStaffCards(searchCriteria, pageable);
    }

    @GetMapping("/list/ready-to-print/all/{uin}/{companyCode}//{nationality}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADD_PRINTING_REQUEST + "')")
    public List<CompanyStaffCardDto> listReadyToPrintCards(@PathVariable String uin, @PathVariable String companyCode,
                                                           @PathVariable String nationality, @RequestParam List<Long> excludedCardsIds,
                                                           Authentication authentication) {
        log.info("list all printing cards.");
        return companyStaffCardService.findAllPrintingCards("-1".equals(uin) ? null : uin
                , "-1".equals(companyCode) ? null : companyCode,
                "-1".equals(nationality) ? null : nationality, excludedCardsIds);
    }

    @GetMapping("/list/ready-to-print/{uin}/{companyCode}/{nationality}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADD_PRINTING_REQUEST + "')")
    public Page<CompanyStaffCardDto> listReadyToPrintCards(@PathVariable String uin, @PathVariable String companyCode,
                                                           @PathVariable String nationality, @RequestParam List<Long> excludedCardsIds,
                                                           Authentication authentication, Pageable pageable) {
        log.info("list all printing cards.");
        return companyStaffCardService.findPrintingCards("-1".equals(uin) ? null : uin
                , "-1".equals(companyCode) ? null : companyCode,
                "-1".equals(nationality) ? null : nationality, excludedCardsIds, pageable);
    }


/*    @GetMapping("/find/{id}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.CARD_MANAGEMENT + "')")
    public CompanyStaffCardDto findById(@PathVariable long id, Authentication authentication) throws IOException {
        log.info("list search result cards.");
        return companyStaffCardService.findOne(id);
    }*/

    /**
     * Finds a card by its ID
     *
     * @param cardId the card ID to find
     * @return the found card or <code>null</code>
     */
    @GetMapping("/find/{cardId}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.VIEW_CARD_DETAILS + "')")
    public CompanyStaffCardDto findStaffCard(@PathVariable long cardId) {
        log.debug("Handler for {}", "Find Applicant Card");
        CompanyStaffCardDto companyStaffCardDto = companyStaffCardService.findById(cardId);
        if (companyStaffCardDto == null) {
            throw new CardDetailsNotFoundException("no card found with id : " + cardId);
        }
        return companyStaffCardDto;
    }

    /**
     * change card status
     *
     * @param cardId     the card ID to find
     * @param actionCode the new status Code
     * @return card after updating the status
     */
    @PostMapping("/change-status/{cardId}/{actionCode}")
    public CompanyStaffCardDto changeCardStatus(@PathVariable long cardId,
                                                @PathVariable String actionCode,
                                                Authentication authentication) throws CardDetailsNotFoundException, NotAuthorizedException {
        CompanyStaffCardDto card = companyStaffCardService.findById(cardId);
        if (card == null) {
            throw new CardDetailsNotFoundException("no card found with id : " + cardId);
        }
        boolean isUserAllowed = isUserAuthorizedToChangeCardStatus(card, actionCode, authentication);
        if (isUserAllowed) {
            JwtToken loggedInUser = (JwtToken) authentication;
            Optional<Long> userId = jwtTokenService.retrieveUserIdFromToken(loggedInUser.getToken());
            return companyStaffCardService.changeCardStatus(card, actionCode, userId);
        } else {
            throw new NotAuthorizedException("the user is not authorized or this action is not allowed on card with id :  " + card.getId());
        }

    }

    private boolean isUserAuthorizedToChangeCardStatus(CompanyStaffCardDto card, String actionCode, Authentication authentication) {
        Set<GrantedAuthority> userAuthorities = (Set<GrantedAuthority>) ((User) authentication.getPrincipal()).getAuthorities();
        boolean isUserAllowed = userAuthorities.stream().anyMatch(auth -> auth.getAuthority().equalsIgnoreCase(actionCode));
        if (!isUserAllowed) {
            log.error("this user does not have the authority to take this action on  card status");
            return false;
        }
        String[] allowedActions = ApplicantCardService.CARD_STATUS_ALLOWED_ACTION.get(card.getStatusCode().toUpperCase());
        if (allowedActions != null) {
            boolean isActionAllowed = Arrays.stream(allowedActions).anyMatch(action -> action.equalsIgnoreCase(actionCode));
            if (!isActionAllowed) {
                log.error("action with status code {} is not allowed on card with status {}", actionCode, card.getStatusCode());
                return false;
            }
        }
        return true;
    }

}
