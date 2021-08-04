/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.lookup;

import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.lookup.*;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for applicant portal lookup data
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Slf4j
@RestController
@RequestMapping(Navigation.API_APPLICANT_LOOKUP)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicantLookupController {

    private final RitualTypeLookupService ritualTypeLookupService;
    private final CardStatusLookupService cardStatusLookupService;
    private final RelativeRelationshipLookupService relativeRelationshipLookupService;
    private final MaritalStatusLookupService maritalStatusLookupService;
    private final CountryLookupService countryLookupService;
    private final HealthSpecialNeedsLookupService healthSpecialNeedsLookupService;

    @GetMapping("/ritual-type/list")
    public List<RitualTypeLookupDto> listRitualTypes(Authentication authentication) {
        log.debug("list ritual types...");
        return ritualTypeLookupService.findAll();
    }

    @GetMapping("/card-status/list")
    public List<CardStatusLookupDto> listCardStatuses(Authentication authentication) {
        log.debug("list card statuses...");
        return cardStatusLookupService.findAll();
    }

    @GetMapping("/relative-relationship/list")
    public List<RelativeRelationshipLookupDto> listRelativeRelationships(Authentication authentication) {
        log.debug("list relative relationships...");
        return relativeRelationshipLookupService.findAll();
    }

    @GetMapping("/marital-status/list")
    public List<MaritalStatusLookupDto> listMaritalStatuses(Authentication authentication) {
        log.debug("list marital statuses...");
        return maritalStatusLookupService.findAll();
    }

    @GetMapping("/country/list")
    public List<CountryLookupDto> listCountries(Authentication authentication) {
        log.debug("list countries...");
        return countryLookupService.findAll();
    }

    @GetMapping("/health-special-needs/list")
    public List<HealthSpecialNeedsTypeLookupDto> listHealthSpecialNeeds(Authentication authentication) {
        log.debug("list health special needs...");
        return healthSpecialNeedsLookupService.findAll();
    }
}
