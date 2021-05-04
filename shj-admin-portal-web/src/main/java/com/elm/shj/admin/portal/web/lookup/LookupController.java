/*
 *  Copyright (c) 2020 ELM. All rights reserved.
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

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * Controller for lookup data
 *
 * @author ahmad flaifel
 * @since 1.3.0
 */
@RestController
@RequestMapping(Navigation.API_LOOKUP)
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LookupController {

    private final AuthorityLookupService authorityLookupService;
    private final RitualTypeLookupService ritualTypeLookupService;
    private final CardStatusLookupService cardStatusLookupService;
    private final RelativeRelationshipLookupService relativeRelationshipLookupService;
    private final CountryLookupService countryLookupService;

    @GetMapping("/authority/list/parent")
    @RolesAllowed(AuthorityConstants.ROLE_MANAGEMENT)
    public List<AuthorityLookupDto> listParentAuthorities(Authentication authentication) {
        return authorityLookupService.findAllParentAuthorities();
    }

    @GetMapping("/ritual-type/list")
    @RolesAllowed(AuthorityConstants.ROLE_MANAGEMENT) //TODO: change it
    public List<RitualTypeLookupDto> listRitualTypes(Authentication authentication) {
        log.debug("list ritual types...");
        return ritualTypeLookupService.findAll();
    }

    @GetMapping("/card-status/list")
    @RolesAllowed(AuthorityConstants.ROLE_MANAGEMENT) //TODO: change it
    public List<CardStatusLookupDto> listCardStatuses(Authentication authentication) {
        log.debug("list card statuses...");
        return cardStatusLookupService.findAll();
    }

    @GetMapping("/relative-relationship/list")
    @RolesAllowed(AuthorityConstants.ROLE_MANAGEMENT) //TODO: change it
    public List<RelativeRelationshipLookupDto> listRelativeRelationships(Authentication authentication) {
        log.debug("list relative relationships...");
        return relativeRelationshipLookupService.findAll();
    }

    @GetMapping("/country/list")
    @RolesAllowed(AuthorityConstants.ROLE_MANAGEMENT) //TODO: change it
    public List<CountryLookupDto> listCountries(Authentication authentication) {
        log.debug("list countries...");
        return countryLookupService.findAll();
    }
}
