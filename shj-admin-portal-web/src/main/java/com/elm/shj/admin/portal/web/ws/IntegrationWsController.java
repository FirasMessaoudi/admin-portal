/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.dcc.foundation.providers.recaptcha.exception.RecaptchaException;
import com.elm.shj.admin.portal.services.applicant.*;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.lookup.*;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualCardLiteService;
import com.elm.shj.admin.portal.web.admin.ValidateApplicantCmd;
import com.elm.shj.admin.portal.web.error.ApplicantNotFoundException;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtToken;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import com.elm.shj.admin.portal.web.security.otp.OtpAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controller for exposing web services for external party.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@CrossOrigin(
        originPatterns = "*",
        maxAge = 3600,
        exposedHeaders = {"Authorization", JwtTokenService.CALLER_TYPE_HEADER_NAME, JwtTokenService.TOKEN_COOKIE_NAME},
        allowCredentials = "true"
)
@Slf4j
@RestController
@RequestMapping(Navigation.API_INTEGRATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IntegrationWsController {

    public final static String ISO8601_DATE_PATTERN = "yyyy-MM-dd";

    private final OtpAuthenticationProvider authenticationProvider;
    private final JwtTokenService jwtTokenService;
    private final ApplicantLiteService applicantLiteService;
    private final RitualTypeLookupService ritualTypeLookupService;
    private final CardStatusLookupService cardStatusLookupService;
    private final RelativeRelationshipLookupService relativeRelationshipLookupService;
    private final MaritalStatusLookupService maritalStatusLookupService;
    private final CountryLookupService countryLookupService;
    private final HealthSpecialNeedsLookupService healthSpecialNeedsLookupService;
    private final ApplicantService applicantService;
    private final ApplicantHealthLiteService applicantHealthLiteService;
    private final ApplicantRitualCardLiteService applicantRitualCardLiteService;
    private final ApplicantMainDataService applicantMainDataService;
    private final CompanyRitualStepService companyRitualStepService;
    private final CompanyStaffService companyStaffService;
    private final CompanyRitualStepLookupService companyRitualStepLookupService;
    private final CompanyStaffLookupService companyStaffLookupService;
    private final ApplicantPackageCateringService applicantPackageCateringService;
    private final ApplicantPackageHousingService applicantPackageHousingService;
    private final ApplicantPackageTransportationService applicantPackageTransportationService;
    private final CompanyLiteService companyLiteService;
    private final HousingCategoryLookupService housingCategoryLookupService;
    private final HousingTypeLookupService housingTypeLookupService;
    private final PackageTypeLookupService packageTypeLookupService;
    private final HousingSiteLookupService housingSiteLookupService;
    private final TransportationTypeLookupService transportationTypeLookupService;
    private final CompanyRitualSeasonLiteService companyRitualSeasonLiteService;
    private final HealthImmunizationLookupService healthImmunizationLookupService;
    private final ApplicantDigitalIdStatusLookupService applicantDigitalIdStatusLookupService;
    private final ReligiousOccasionsDayLookupService religiousOccasionsDayLookupService;
    private final NotificationCategoryLookupService notificationCategoryLookupService;
    /**
     * Authenticates the user requesting a webservice call
     *
     * @param credentials the user credentials
     * @return the operation result
     */
    @PostMapping("/auth")
    public ResponseEntity<WsResponse<?>> login(@RequestBody Map<String, String> credentials, HttpServletRequest request, HttpServletResponse response) {
        log.debug("Auth Webservice request handler");

        String callerType = request.getHeader(JwtTokenService.CALLER_TYPE_HEADER_NAME);
        if (callerType == null || !callerType.equals(JwtTokenService.WEB_SERVICE_CALLER_TYPE)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE).body("Access denied").build());
        }

        JwtToken authentication;
        try {
            authentication = (JwtToken) authenticationProvider
                    .authenticate(new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password")));
        } catch (RecaptchaException rex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE).body("Multiple failed login attempts").build());
        }

        jwtTokenService.attachTokenCookie(response, authentication);

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(authentication.getToken()).build());
    }

    /**
     * List all ritual types.
     *
     * @return WsResponse of ritual types list
     */
    @GetMapping("/ritual-type/list")
    public ResponseEntity<WsResponse<?>> listRitualTypes() {
        log.info("list ritual types...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(ritualTypeLookupService.findAll()).build());
    }

    /**
     * List all card statuses.
     *
     * @return WsResponse of card statuses list
     */
    @GetMapping("/card-status/list")
    public ResponseEntity<WsResponse<?>> listCardStatuses() {
        log.debug("list card statuses...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(cardStatusLookupService.findAll()).build());
    }

    /**
     * List all relative relationships.
     *
     * @return WsResponse of relative relationships list
     */
    @GetMapping("/relative-relationship/list")
    public ResponseEntity<WsResponse<?>> listRelativeRelationships() {
        log.debug("list relative relationships...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(relativeRelationshipLookupService.findAll()).build());
    }

    /**
     * List all marital statuses.
     *
     * @return WsResponse of marital statuses list
     */
    @GetMapping("/marital-status/list")
    public ResponseEntity<WsResponse<?>> listMaritalStatuses() {
        log.debug("list marital statuses...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(maritalStatusLookupService.findAll()).build());
    }

    /**
     * List all countries.
     *
     * @return WsResponse of countries list
     */
    @GetMapping("/country/list")
    public ResponseEntity<WsResponse<?>> listCountries() {
        log.debug("list countries...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(countryLookupService.findAll()).build());
    }

    /**
     * List all health special needs.
     *
     * @return WsResponse of health special needs list
     */
    @GetMapping("/health-special-needs/list")
    public ResponseEntity<WsResponse<?>> listHealthSpecialNeeds() {
        log.debug("list health special needs...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(healthSpecialNeedsLookupService.findAll()).build());
    }


    /**
     * Verify an applicant based on cross-check factor, uin and date of birth.
     *
     * @param command contains uin and date of birth.
     * @return WsResponse of applicant (in case of success) or error (in case of failure)
     */
    @PostMapping("/verify")
    public ResponseEntity<WsResponse<?>> verify(@RequestBody @Validated ValidateApplicantCmd command) {
        Optional<ApplicantLiteDto> applicant = applicantLiteService.findByUin(command.getUin());
        if (applicant.isPresent()) {
            boolean dateOfBirthMatched;
            SimpleDateFormat sdf = new SimpleDateFormat(ISO8601_DATE_PATTERN);
            // decide which date of birth to use
            if (command.getDateOfBirthGregorian() != null) {
                String applicantDateFormatted = sdf.format(applicant.get().getDateOfBirthGregorian());
                String commandDataOfBirthFormatted = sdf.format(command.getDateOfBirthGregorian());
                dateOfBirthMatched = commandDataOfBirthFormatted.equals(applicantDateFormatted);
            } else {
                dateOfBirthMatched = command.getDateOfBirthHijri() == applicant.get().getDateOfBirthHijri();
            }
            if (!dateOfBirthMatched) {
                log.debug("unmatched data for {} uin and {} hijri date of birth and {} gregorian date of birth.",
                        command.getUin(), command.getDateOfBirthHijri(), command.getDateOfBirthGregorian());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                        .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED).referenceNumber(command.getUin()).build()).build());
            }
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(applicant).build());
        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND).referenceNumber(command.getUin()).build()).build());
        }
    }

    /**
     * Updates an existing applicant
     *
     * @return the updated applicant
     */
    @PostMapping("/update")
    public ResponseEntity<WsResponse<?>> update(@RequestBody @Validated UpdateApplicantCmd command) {
        Optional<ApplicantDto> databaseApplicant = applicantService.findByUin(command.getUin());
        if (databaseApplicant.isPresent()) {
            boolean dateOfBirthMatched;
            dateOfBirthMatched = command.getDateOfBirthHijri() == databaseApplicant.get().getDateOfBirthHijri();
            if (!dateOfBirthMatched) {
                log.error("invalid data for uin {} and date of birth {}", command.getUin(), command.getDateOfBirthHijri());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                        .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED).referenceNumber(command.getUin()).build()).build());
            }
            int updatedRowsCount = applicantService.updateApplicantContacts(databaseApplicant.get().getId(), command);
            if (updatedRowsCount < 1) {
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                        .body(WsError.builder().error(WsError.EWsError.UPDATE_APPLICANT_FAILED).referenceNumber(command.getUin()).build()).build());
            }
            ApplicantLiteDto applicantLite = applicantLiteService.findByUin(command.getUin()).orElseThrow(() -> new ApplicantNotFoundException("No applicant found with uin " + command.getUin()));

            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(applicantLite).build());
        } else {
            log.error("invalid data for uin {}", command.getUin());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND).referenceNumber(command.getUin()).build()).build());
        }
    }

    /**
     * finds applicant's health details by his UIN
     *
     * @param uin                   the applicant's uin
     * @param companyRitualSeasonId
     * @return the applicant health details or <code>null</code>
     */
    @GetMapping("/health/{uin}/{companyRitualSeasonId}")
    public ResponseEntity<WsResponse<?>> findApplicantHealthDetails(@PathVariable String uin, @PathVariable Long companyRitualSeasonId) {
        log.debug("Handler for {}", "Find applicant health details by uin and ritual id");
        Optional<ApplicantHealthLiteDto> applicantHealth = applicantHealthLiteService.findByUinAndRitualId(uin, companyRitualSeasonId);
        if (applicantHealth.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(applicantHealth).build());
        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED).referenceNumber(uin).build()).build());
        }
    }

    /**
     * finds an applicant by his UIN and ritual id
     *
     * @param uin                   the applicant's uin to find
     * @param companyRitualSeasonId applicant ritual id
     * @return the found applicant or <code>null</code>
     */
    @GetMapping("/find/main-data/{uin}/{companyRitualSeasonId}")
    public ResponseEntity<WsResponse<?>> findApplicantMainData(@PathVariable String uin, @PathVariable long companyRitualSeasonId) {
        log.debug("Handler for {}", "Find applicant main data by uin");

        Optional<ApplicantMainDataDto> mainDataDtoOptional = applicantMainDataService.findByUin(uin, companyRitualSeasonId);
        if (mainDataDtoOptional.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(mainDataDtoOptional.get()).build());

        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED).referenceNumber(uin).build()).build());

        }

    }

    /**
     * finds an applicant card details by his UIN
     * to be used by applicant portal
     *
     * @param uin the applicant's card details by  uin
     * @return the found applicant card details or <code>null</code>
     */
    @GetMapping("/details/{uin}/{companyRitualSeasonId}")
    public ResponseEntity<WsResponse<?>> findCardDetails(@PathVariable String uin, @PathVariable String companyRitualSeasonId) {
        log.debug("Handler for {}", "Find applicant card details by uin");
        Optional<ApplicantRitualCardLiteDto> returnedApplicantRitualCardLiteDto = applicantRitualCardLiteService.findCardDetailsByUinAndRitualId(uin, companyRitualSeasonId);

        if (returnedApplicantRitualCardLiteDto.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(returnedApplicantRitualCardLiteDto).build());
        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.CARD_DETAILS_NOT_FOUND).referenceNumber(uin).build()).build());
        }

    }

    /**
     * List of company ritual steps by uin and ritual season id.
     *
     * @return WsResponse of company ritual step list
     */
    @GetMapping("/company-ritual-step/{uin}/{seasonRitualId}")
    public ResponseEntity<WsResponse<?>> listCompanyRitualStep(@PathVariable String uin, @PathVariable long seasonRitualId) {
        log.info("list company ritual step...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(companyRitualStepService.findCompanyRitualStepsByApplicantUinAndRitualId(uin, seasonRitualId)).build());
    }

    /**
     * finds an applicant group leaders by his UIN and SEASON ID
     * to be used by applicant portal
     *
     * @param uin      the applicant's group leaders details by  uin
     * @param seasonId the applicant's group leaders details by  season id
     * @return the company staff list
     */
    @GetMapping("/find/company-employees/{uin}/{seasonId}")
    public ResponseEntity<WsResponse<?>> findCompanyEmployeesByUinAndSeasonId(@PathVariable String uin, @PathVariable long seasonId) {
        log.debug("Handler for {}", "Find company employee by uin and season ");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(companyStaffService.findRelatedEmployeesByApplicantUinAndSeasonId(uin, seasonId)).build());
    }

    @GetMapping("/company_ritual_step_label/list")
    public ResponseEntity<WsResponse<?>> listCompanyRitualStepsLabel() {
        log.debug("list company ritual step labels...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(companyRitualStepLookupService.findAllWithDescription()).build());

    }

    @GetMapping("/company_staff_title_label/list")
    public ResponseEntity<WsResponse<?>> listCompanyStaffTitlesLabel() {
        log.debug("list company Staff title labels...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(companyStaffLookupService.findAll()).build());
    }


    /**
     * finds an applicant package by his UIN and company season ritual id
     *
     * @param uin                   the applicant's uin to find
     * @param companyRitualSeasonId applicant ritual id
     * @return the found applicant package data
     */
    @GetMapping("/applicant/package/{uin}/{companyRitualSeasonId}")
    public ResponseEntity<WsResponse<?>> findApplicantPackageData(@PathVariable String uin, @PathVariable long companyRitualSeasonId) {
        log.debug("Handler for {}", "Find applicant package details  by uin");

        ApplicantPackageDetailsDto applicantPackageDetails = new ApplicantPackageDetailsDto();

        applicantPackageDetails.setApplicantPackageHousings(applicantPackageHousingService.findApplicantPackageHousingByUinAndCompanyRitualSeasonId(Long.parseLong(uin), companyRitualSeasonId));
        applicantPackageDetails.setApplicantPackageCaterings(applicantPackageCateringService.findApplicantPackageCateringByUinAndCompanyRitualSeasonId(Long.parseLong(uin), companyRitualSeasonId));
        applicantPackageDetails.setApplicantPackageTransportations(applicantPackageTransportationService.findApplicantPackageTransportationByUinAndCompanyRitualSeasonId(Long.parseLong(uin), companyRitualSeasonId));
        applicantPackageDetails.setCompanyLite(companyLiteService.findCompanyByCompanyRitualSeasonsIdAndApplicantUin(companyRitualSeasonId, Long.parseLong(uin)));

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(applicantPackageDetails).build());

    }

    /**
     * List all housing categories.
     *
     * @return WsResponse of housing categories list
     */
    @GetMapping("/housing-category/list")
    public ResponseEntity<WsResponse<?>> listHousingCategories() {
        log.debug("list housing category...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(housingCategoryLookupService.findAll()).build());
    }

    /**
     * List all housing types.
     *
     * @return WsResponse of housing types list
     */
    @GetMapping("/housing-type/list")
    public ResponseEntity<WsResponse<?>> listHousingTypes() {
        log.debug("list housing types...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(housingTypeLookupService.findAll()).build());
    }

    /**
     * List all package types.
     *
     * @return WsResponse of package types list
     */
    @GetMapping("/package-type/list")
    public ResponseEntity<WsResponse<?>> listPackageTypes() {
        log.debug("list package types...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(packageTypeLookupService.findAll()).build());
    }

    /**
     * My program time table by uin and ritual season id.
     *
     * @return WsResponse of company ritual step list
     */
    @GetMapping("/program-time-table/{uin}/{seasonRitualId}")
    public ResponseEntity<WsResponse<?>> programTimeTable(@PathVariable String uin, @PathVariable long seasonRitualId) {
        log.info("list company ritual step...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(companyRitualStepService.findCompanyRitualStepsByApplicantUinAndRitualId(uin, seasonRitualId)).build());
    }

    /**
     * List all housing sites.
     *
     * @return WsResponse of housing sites list
     */
    @GetMapping("/housing-site/list")
    public ResponseEntity<WsResponse<?>> listHousingSites() {
        log.debug("list housing sites...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(housingSiteLookupService.findAll()).build());
    }

    /**
     * List all transportation types.
     *
     * @return WsResponse of transportation types list
     */
    @GetMapping("/transportation-type/list")
    public ResponseEntity<WsResponse<?>> listTransportationTypes() {
        log.debug("list transportation types...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(transportationTypeLookupService.findAll()).build());
    }

    /**
     * List all applicant digital ID statuses.
     *
     * @return WsResponse of applicant digital ID statuses list
     */
    @GetMapping("/digital-id-status/list")
    public ResponseEntity<WsResponse<?>> listDigitalIdStatuses() {
        log.debug("list digital ID statuses...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(applicantDigitalIdStatusLookupService.findAll()).build());
    }

    /**
     * finds all applicant ritual season by his UIN
     *
     * @param uin the applicant's uin to find
     * @return the found company ritual seasons list
     */
    @GetMapping("/applicant/ritual-season/{uin}")
    public ResponseEntity<WsResponse<?>> findApplicantRitualSeasonByUin(@PathVariable Long uin) {
        log.debug("Handler for {}", "Find all applicant ritual season by uin");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(companyRitualSeasonLiteService.getListCompanyRitualSeasonByApplicantUin(uin)).build());
    }

    /**
     * finds latest applicant ritual season by his UIN
     *
     * @param uin the applicant's uin to find
     * @return the found company ritual seasons for applicant
     */
    @GetMapping("/applicant/ritual-season/latest/{uin}")
    public ResponseEntity<WsResponse<?>> findLatestApplicantRitualSeasonByUin(@PathVariable Long uin) {
        log.debug("Handler for {}", "Find all applicant ritual season by uin");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(companyRitualSeasonLiteService.getLatestCompanyRitualSeasonByApplicantUin(uin)).build());
    }

    @GetMapping("/company-details/{uin}/{seasonRitualId}")
    public ResponseEntity<WsResponse<?>> findApplicantCompanyDetailsByUinAndRitualId(@PathVariable String uin, @PathVariable long seasonRitualId) {
        log.info("company details...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(companyLiteService.findCompanyByCompanyRitualSeasonsIdAndApplicantUin(seasonRitualId, Long.parseLong(uin))).build());
    }

    @GetMapping("/health-immunization/list")
    public ResponseEntity<WsResponse<?>> listImmunization() {
        log.debug("list health immunizations...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(healthImmunizationLookupService.findAll()).build());
    }

    @GetMapping("/religious-occasions-day/list")
    public ResponseEntity<WsResponse<?>>  listReligiousOccasionsDay() {
        log.debug("list religious occasions day...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(religiousOccasionsDayLookupService.findAll()).build());
    }
    @GetMapping("/notification-category/list")
    public ResponseEntity<WsResponse<?>> listNotificationCategories() {
        log.debug("list notification categories...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(notificationCategoryLookupService.findAll()).build());
    }
}
