/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.applicant.*;
import com.elm.shj.admin.portal.services.card.ApplicantCardService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualCardLiteService;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
import com.elm.shj.admin.portal.web.error.ActionNotAllowedException;
import com.elm.shj.admin.portal.web.error.CardDetailsNotFoundException;
import com.elm.shj.admin.portal.web.error.UserNotAllowedException;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private final CompanyRitualSeasonLiteService companyRitualSeasonLiteService;
    private final ApplicantPackageCateringService applicantPackageCateringService;
    private final ApplicantPackageHousingService applicantPackageHousingService;
    private final ApplicantPackageTransportationService applicantPackageTransportationService;
    private final CompanyStaffService companyStaffService;
    private final CompanyLiteService companyLiteService;
    private final ApplicantRitualCardLiteService applicantRitualCardLiteService;
    private final CompanyRitualStepService companyRitualStepService;
    private final ApplicantRitualService applicantRitualService;
    private static final String APPLICANT_CARD_DETAILS_NOT_FOUND_ERROR_MSG = "no card details found for applicant with this uin";
    private static final int CARD_DETAILS_NOT_FOUND_RESPONSE_CODE = 561;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.CARD_MANAGEMENT + "')")
    public Page<ApplicantCardDto> listApplicantCards(Pageable pageable, Authentication authentication) {
        log.debug("List applicant cards...");
        return applicantCardService.findAll(pageable);
    }

    @GetMapping("/list-applicant-cards")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.CARD_MANAGEMENT + "')")
    public Page<ApplicantCardDto> searchApplicantCards(@RequestParam(value = "applicantCardSearchCriteria") String applicantCardSearchCriteria,
                                                       Pageable pageable, Authentication authentication) throws IOException {

        log.info("list search result cards.");
        final ApplicantCardSearchCriteriaDto searchCriteria =
                new ObjectMapper().readValue(applicantCardSearchCriteria, ApplicantCardSearchCriteriaDto.class);


        String uin = searchCriteria.getUin() != null && !searchCriteria.getUin().trim().equals("") ? searchCriteria.getUin().trim() : null;
        String idNum = searchCriteria.getIdNumber() != null && !searchCriteria.getIdNumber().trim().equals("") ? searchCriteria.getIdNumber().trim() : null;
        String passportNumber = searchCriteria.getPassportNumber() != null && !searchCriteria.getPassportNumber().trim().equals("") ? searchCriteria.getPassportNumber().trim() : null;

        return applicantCardService.searchApplicantCards(uin, idNum, passportNumber, pageable);

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
        ApplicantRitualDto applicantRitualDto = applicantRitualService.findApplicantRitualWithContactsAndRelatives(applicantCardDto.getApplicantRitual().getId());


        applicantCardDto.setApplicantRitual(applicantRitualDto);
        applicantCardDto.getApplicantRitual().getApplicant().setContacts(new ArrayList<>(applicantRitualDto.getContacts()));
        applicantCardDto.getApplicantRitual().getApplicant().setRelatives(new ArrayList<>(applicantRitualDto.getRelatives()));

        if (CollectionUtils.isNotEmpty(applicantRitualDto.getApplicantHealths())) {
            applicantCardDto.getApplicantRitual().getApplicant().setApplicantHealth(new ArrayList<>(applicantRitualDto.getApplicantHealths()).get(0));
        }
        List<ApplicantDigitalIdDto> digitalIds = applicantCardDto.getApplicantRitual().getApplicant().getDigitalIds();
        if (digitalIds.size() > 0) {

            String uin = digitalIds.get(0).getUin();
            CompanyRitualSeasonDto companyRitualSeasonDto = applicantRitualDto.getApplicantPackage().getRitualPackage().getCompanyRitualSeason();
            if (companyRitualSeasonDto != null) {
                applicantCardDto.getApplicantRitual().setTypeCode(companyRitualSeasonDto.getRitualSeason().getRitualTypeCode());

                long companyRitualSeasonId = companyRitualSeasonDto.getId();
                applicantCardDto.setApplicantPackageHousings(applicantPackageHousingService.findApplicantPackageHousingByUinAndCompanyRitualSeasonId(Long.parseLong(uin), companyRitualSeasonId));
                applicantCardDto.setApplicantPackageCaterings(applicantPackageCateringService.findApplicantPackageCateringByUinAndCompanyRitualSeasonId(Long.parseLong(uin), companyRitualSeasonId));
                applicantCardDto.setApplicantPackageTransportations(applicantPackageTransportationService.findApplicantPackageTransportationByUinAndCompanyRitualSeasonId(Long.parseLong(uin), companyRitualSeasonId));
                applicantCardDto.setCompanyLite(companyLiteService.findCompanyByCompanyRitualSeasonsIdAndApplicantUin(companyRitualSeasonId, Long.parseLong(uin)));
                List<CompanyRitualStepDto> companyRitualSteps = companyRitualStepService.findCompanyRitualStepsByApplicantUinAndRitualId(uin, companyRitualSeasonId);
                applicantCardDto.setCompanyRitualSteps(companyRitualSteps);
                List<CompanyStaffDto> groupLeaders = companyStaffService.findRelatedEmployeesByApplicantUinAndSeasonId(uin, companyRitualSeasonId);
                applicantCardDto.setGroupLeaders(groupLeaders);
            }
        }
        return applicantCardDto;
    }

    /**
     * change card status
     *
     * @param cardId     the card ID to find
     * @param actionCode the new status Code
     * @throws CardDetailsNotFoundException
     * @throws UserNotAllowedException
     * @throws ActionNotAllowedException
     */
    @PostMapping("/change-status/{cardId}/{actionCode}")
    public void changeCardStatus(@PathVariable long cardId, @PathVariable String actionCode, Authentication authentication) throws CardDetailsNotFoundException, ActionNotAllowedException, UserNotAllowedException {
        Set<GrantedAuthority> userAuthorities = (Set<GrantedAuthority>) ((User) authentication.getPrincipal()).getAuthorities();
        ApplicantCardDto card = applicantCardService.findApplicantCard(cardId);
        if (card == null) {
            throw new CardDetailsNotFoundException("no card found with id : " + cardId);
        }
        String changeResult = applicantCardService.changeCardStatus(card, actionCode, userAuthorities);
        if (changeResult.equalsIgnoreCase("user not allowed")) {
            log.debug(changeResult);
            throw new UserNotAllowedException("this user does not have the authority to take this action");
        } else if (changeResult.equalsIgnoreCase("action not allowed")) {
            log.debug(changeResult);
            throw new ActionNotAllowedException("this user can not take the action of { " + actionCode + " } on card with status { " + card.getStatusCode() + " }");
        } else {
            log.debug(changeResult);
        }
    }

}
