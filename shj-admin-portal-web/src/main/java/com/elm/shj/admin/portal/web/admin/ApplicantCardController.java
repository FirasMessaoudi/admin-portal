/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.card.ApplicantCardBasicService;
import com.elm.shj.admin.portal.services.card.ApplicantCardService;
import com.elm.shj.admin.portal.services.card.UserCardStatusAuditService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualBasicService;
import com.elm.shj.admin.portal.web.error.CardDetailsNotFoundException;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtToken;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
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
@RequestMapping(Navigation.API_APPLICANT_CARDS)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantCardController {

    private final ApplicantCardService applicantCardService;
    private final JwtTokenService jwtTokenService;
    private final ApplicantCardBasicService applicantCardBasicService;
    private final UserCardStatusAuditService userCardStatusAuditService;
    private final ApplicantRitualBasicService applicantRitualBasicService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.CARD_MANAGEMENT + "')")
    public Page<ApplicantCardDto> listApplicantCards(Pageable pageable, Authentication authentication) {
        log.debug("List applicant cards...");
        return applicantCardService.findAll(pageable);
    }

    @PostMapping("/list-applicant-cards")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.CARD_MANAGEMENT + "')")
    public Page<ApplicantCardDto> searchApplicantCards(@RequestBody ApplicantCardSearchCriteriaDto criteria, Pageable pageable, Authentication authentication) {
        log.info("list search result cards.");
        return applicantCardService.searchApplicantCards(criteria, pageable);
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
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADD_PRINTING_REQUEST + "')")
    public Page<ApplicantCardDto> listReadyToPrintCards(@RequestParam List<Long> excludedCardsIds, Pageable pageable,
                                                        @PathVariable String uin, @PathVariable String idNumber, @PathVariable String hamlahNumber,
                                                        @PathVariable String motawefNumber, @PathVariable String passportNumber,
                                                        @PathVariable String nationality, Authentication authentication) {
        log.info("list printing cards.");
        return applicantCardService.findPrintingCards("-1".equals(uin) ? null : uin,
                "-1".equals(idNumber) ? null : idNumber, "-1".equals(hamlahNumber) ? null : hamlahNumber,
                "-1".equals(motawefNumber) ? null : motawefNumber, "-1".equals(passportNumber) ? null : passportNumber,
                "-1".equals(nationality) ? null : nationality, excludedCardsIds, pageable);
    }

    @GetMapping("/list/ready-to-print/all/{uin}/{idNumber}/{hamlahNumber}/{motawefNumber}/{passportNumber}/{nationality}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADD_PRINTING_REQUEST + "')")
    public List<ApplicantCardDto> listReadyToPrintCards(@PathVariable String uin, @PathVariable String idNumber, @PathVariable String hamlahNumber,
                                                        @PathVariable String motawefNumber, @PathVariable String passportNumber,
                                                        @PathVariable String nationality, @RequestParam List<Long> excludedCardsIds,
                                                        Authentication authentication) {
        log.info("list all printing cards.");
        return applicantCardService.findAllPrintingCards("-1".equals(uin) ? null : uin,
                "-1".equals(idNumber) ? null : idNumber, "-1".equals(hamlahNumber) ? null : hamlahNumber,
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
    @PreAuthorize("hasAuthority('" + AuthorityConstants.VIEW_CARD_DETAILS + "')")
    public ApplicantCardDto findApplicantCard(@PathVariable long cardId) {
        log.debug("Handler for {}", "Find Applicant Card");
        ApplicantCardDto applicantCardDto = applicantCardService.findApplicantCard(cardId);
        if (applicantCardDto == null) {
            throw new CardDetailsNotFoundException("no card found with id : " + cardId);
        }
        applicantCardDto = applicantCardService.buildApplicantCard(applicantCardDto);
        return applicantCardDto;
    }

    /**
     * change card status
     *
     * @param cardId     the card ID to find
     * @param actionCode the new status Code
     * @return card after updating the status
     * @throws CardDetailsNotFoundException
     * @throws NotAuthorizedException
     */
    @PostMapping("/change-status/{cardId}/{actionCode}")
    public ApplicantCardDto changeCardStatus(@PathVariable long cardId, @PathVariable String actionCode, Authentication authentication) throws CardDetailsNotFoundException, NotAuthorizedException {
        ApplicantCardDto card = applicantCardService.findApplicantCard(cardId);
        if (card == null) {
            throw new CardDetailsNotFoundException("no card found with id : " + cardId);
        }
        boolean isUserAllowed = isUserAuthorizedToChangeCardStatus(card, actionCode, authentication);
        if (isUserAllowed) {
            JwtToken loggedInUser = (JwtToken) authentication;
            Optional<Long> userId = jwtTokenService.retrieveUserIdFromToken(loggedInUser.getToken());
            card = applicantCardService.buildApplicantCard(applicantCardService.changeCardStatus(card, actionCode, userId));
            return card;
        } else {
            throw new NotAuthorizedException("the user is not authorized or this action is not allowed on card with id :  " + card.getId());
        }

    }

    private boolean isUserAuthorizedToChangeCardStatus(ApplicantCardDto card, String actionCode, Authentication authentication) {
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

    @PostMapping("/generate-card")
    public ApplicantCardBasicDto generateCard(@RequestBody ApplicantCreateCardDto applicantCreateCardDto,Authentication authentication)  {
        // Get Applicant Card
        ApplicantCardDto card = applicantCardService.findApplicantCard(applicantCreateCardDto.getCardId());

        // Reissued The Card
        JwtToken loggedInUser = (JwtToken) authentication;
        Optional<Long> userId = jwtTokenService.retrieveUserIdFromToken(loggedInUser.getToken());
        card.setStatusCode(ECardStatus.REISSUED.name());
        applicantCardService.save(card);

        // Sent Card Reprint
        ApplicantRitualBasicDto applicantRitualBasic =  applicantRitualBasicService.findOne(applicantCreateCardDto.getRitualId());
        ApplicantCardBasicDto savedCard = applicantCardBasicService.save(ApplicantCardBasicDto.builder().applicantRitual(applicantRitualBasic).statusCode(ECardStatus.READY_TO_PRINT.name()).build());
        userCardStatusAuditService.saveUserBasicCardStatusAudit(savedCard, Constants.SYSTEM_USER_ID_NUMBER);

        // Mark Active as New Card

        ApplicantCardDto newCard = applicantCardService.findApplicantCard(savedCard.getId());
        newCard.setStatusCode(ECardStatus.ACTIVE.name());
        applicantCardService.save(newCard);

        return savedCard;
    }

}
