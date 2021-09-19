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

import java.util.List;

/**
 * Controller for lookup data
 *
 * @author ahmad flaifel
 * @since 1.3.0
 */
@Slf4j
@RestController
@RequestMapping(Navigation.API_LOOKUP)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LookupController {

    private final AuthorityLookupService authorityLookupService;
    private final RitualTypeLookupService ritualTypeLookupService;
    private final CardStatusLookupService cardStatusLookupService;
    private final RelativeRelationshipLookupService relativeRelationshipLookupService;
    private final MaritalStatusLookupService maritalStatusLookupService;
    private final CountryLookupService countryLookupService;
    private final HealthSpecialNeedsLookupService healthSpecialNeedsLookupService;
    private final PrintRequestStatusLookupService printRequestStatusLookupService;
    private final PrintBatchTypeLookupService printBatchTypeLookupService;
    private final CompanyRitualStepLookupService companyRitualStepLookupService;
    private final CompanyStaffLookupService companyStaffLookupService;
    private final HousingCategoryLookupService housingCategoryLookupService;
    private final HousingTypeLookupService housingTypeLookupService;
    private final PackageTypeLookupService packageTypeLookupService;
    private final HousingSiteLookupService housingSiteLookupService;

    @GetMapping("/authority/list/parent")
    public List<AuthorityLookupDto> listParentAuthorities(Authentication authentication) {
        return authorityLookupService.findAllParentAuthorities();
    }

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

    @GetMapping("/print-request-status/list")
    public List<PrintRequestStatusLookupDto> listPrintRequestStatuses(Authentication authentication) {
        log.debug("list print request statuses...");
        return printRequestStatusLookupService.findAll();
    }

    @GetMapping("/print-batch-type/list")
    public List<PrintBatchTypeLookupDto> listPrintRequestBatchTypes(Authentication authentication) {
        log.debug("list print batch types...");
        return printBatchTypeLookupService.findAll();
    }

    @GetMapping("/company_ritual_step/list")
    public List<CompanyRitualStepLookupDto> listCompanyRitualStepsLabel(Authentication authentication) {
        log.debug("list company ritual step labels...");
        return companyRitualStepLookupService.findAll();
    }

    @GetMapping("/company_staff_title/list")
    public List<CompanyStaffTitleLookupDto> listCompanyStaffTitleLabel(Authentication authentication) {
        log.debug("list company staff title labels...");
        return companyStaffLookupService.findAll();
    }

    @GetMapping("/housing-category/list")
    public List<HousingCategoryLookupDto> listHousingCategories(Authentication authentication) {
        log.debug("list housing category...");
        return housingCategoryLookupService.findAll();
    }

    @GetMapping("/housing-type/list")
    public List<HousingTypeLookupDto> listHousingTypes(Authentication authentication) {
        log.debug("list housing type...");
        return housingTypeLookupService.findAll();
    }

    @GetMapping("/package-type/list")
    public List<PackageTypeLookupDto> listPackageTypes(Authentication authentication) {
        log.debug("list package type...");
        return packageTypeLookupService.findAll();
    }

    @GetMapping("/housing-site/list")
    public List<HousingSiteLookupDto> listHousingSites(Authentication authentication) {
        log.debug("list housing site...");
        return housingSiteLookupService.findAll();
    }


}
