/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.dcc.foundation.providers.recaptcha.exception.RecaptchaException;
import com.elm.shj.admin.portal.orm.entity.ApplicantEmergencyContactDto;
import com.elm.shj.admin.portal.orm.entity.CompanyStaffFullVO;
import com.elm.shj.admin.portal.services.applicant.*;
import com.elm.shj.admin.portal.services.card.BadgeService;
import com.elm.shj.admin.portal.services.company.*;
import com.elm.shj.admin.portal.services.data.request.DataRequestService;
import com.elm.shj.admin.portal.services.data.segment.DataSegmentService;
import com.elm.shj.admin.portal.services.digitalid.CompanyStaffDigitalIdService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.incident.ApplicantIncidentService;
import com.elm.shj.admin.portal.services.lookup.*;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualCardLiteService;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
import com.elm.shj.admin.portal.services.user.UserLocationService;
import com.elm.shj.admin.portal.web.admin.ValidateApplicantCmd;
import com.elm.shj.admin.portal.web.admin.ValidateStaffCmd;
import com.elm.shj.admin.portal.web.error.ApplicantNotFoundException;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtToken;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import com.elm.shj.admin.portal.web.security.otp.OtpAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private static final int EMPTY_FILE_UPLOADED_RESPONSE_CODE = 560;

    private final OtpAuthenticationProvider authenticationProvider;
    private final JwtTokenService jwtTokenService;
    private final ApplicantLiteService applicantLiteService;
    private final RitualTypeLookupService ritualTypeLookupService;
    private final CardStatusLookupService cardStatusLookupService;
    private final RelativeRelationshipLookupService relativeRelationshipLookupService;
    private final MaritalStatusLookupService maritalStatusLookupService;
    private final NationalityLookupService nationalityLookupService;
    private final HealthSpecialNeedsLookupService healthSpecialNeedsLookupService;
    private final ApplicantService applicantService;
    private final ApplicantHealthLiteService applicantHealthLiteService;
    private final ApplicantRitualCardLiteService applicantRitualCardLiteService;
    private final ApplicantRelativeService applicantRelativesService;
    private final ApplicantMainDataService applicantMainDataService;
    private final CompanyRitualStepService companyRitualStepService;
    private final CompanyStaffService companyStaffService;
    private final CompanyRitualStepLookupService companyRitualStepLookupService;
    private final CompanyStaffTitleLookupService companyStaffTitleLookupService;
    private final ApplicantPackageCateringService applicantPackageCateringService;
    private final ApplicantPackageHousingService applicantPackageHousingService;
    private final ApplicantPackageTransportationService applicantPackageTransportationService;
    private final HousingCategoryLookupService housingCategoryLookupService;
    private final HousingTypeLookupService housingTypeLookupService;
    private final PackageTypeLookupService packageTypeLookupService;
    private final HousingSiteLookupService housingSiteLookupService;
    private final TransportationTypeLookupService transportationTypeLookupService;
    private final CompanyRitualSeasonLiteService companyRitualSeasonLiteService;
    private final CompanyRitualSeasonService companyRitualSeasonService;
    private final HealthImmunizationLookupService healthImmunizationLookupService;
    private final ApplicantDigitalIdStatusLookupService applicantDigitalIdStatusLookupService;
    private final ReligiousOccasionsDayLookupService religiousOccasionsDayLookupService;
    private final NotificationCategoryLookupService notificationCategoryLookupService;
    private final NotificationTemplateNameLookupService notificationTemplateNameLookupService;
    private final MealTypeLookupService mealTypeLookupService;
    private final LanguageLookupService languageLookupService;
    private final PackageHousingService packageHousingService;
    private final ApplicantIncidentService applicantIncidentService;
    private final IncidentStatusLookupService incidentStatusLookupService;
    private final IncidentTypeLookupService incidentTypeLookupService;
    private final CompanyTypeLookupService companyTypeLookupService;
    private final ChatContactService chatContactService;
    private final ApplicantRitualService applicantRitualService;
    private final ApplicantPackageService applicantPackageService;
    private final CompanyService companyService;
    private final CompanyStaffDigitalIdService companyStaffDigitalIdService;
    private final UserLocationService userLocationService;
    private final RitualPackageService ritualPackageService;
    private final BadgeService badgeService;
    private final DataSegmentService dataSegmentService;
    private final DataRequestService dataRequestService;

    private enum EDataRequestFileTypeWS {
        O, // Original
        E // Errors
    }

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
                    WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body("Access denied").build());
        }

        JwtToken authentication;
        try {
            authentication = (JwtToken) authenticationProvider
                    .authenticate(new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password")));
        } catch (RecaptchaException rex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body("Multiple failed login attempts").build());
        }

        jwtTokenService.attachTokenCookie(response, authentication);

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(authentication.getToken()).build());
    }

    /**
     * List all ritual types.
     *
     * @return WsResponse of ritual types list
     */
    @GetMapping("/ritual-type/list")
    public ResponseEntity<WsResponse<?>> listRitualTypes() {
        log.info("list ritual types...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(ritualTypeLookupService.findAll()).build());
    }

    /**
     * List all card statuses.
     *
     * @return WsResponse of card statuses list
     */
    @GetMapping("/card-status/list")
    public ResponseEntity<WsResponse<?>> listCardStatuses() {
        log.debug("list card statuses...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(cardStatusLookupService.findAll()).build());
    }

    /**
     * List all relative relationships.
     *
     * @return WsResponse of relative relationships list
     */
    @GetMapping("/relative-relationship/list")
    public ResponseEntity<WsResponse<?>> listRelativeRelationships() {
        log.debug("list relative relationships...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(relativeRelationshipLookupService.findAll()).build());
    }

    /**
     * List all marital statuses.
     *
     * @return WsResponse of marital statuses list
     */
    @GetMapping("/marital-status/list")
    public ResponseEntity<WsResponse<?>> listMaritalStatuses() {
        log.debug("list marital statuses...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(maritalStatusLookupService.findAll()).build());
    }

    /**
     * List all countries.
     *
     * @return WsResponse of countries list
     */
    @GetMapping("/country/list")
    public ResponseEntity<WsResponse<?>> listCountries() {
        log.debug("list countries...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(nationalityLookupService.findAll()).build());
    }

    /**
     * List all supported languages
     *
     * @return WsResponse of supported languages list
     */
    @GetMapping("/language/list")
    public ResponseEntity<WsResponse<?>> listLanguages() {
        log.debug("list languages...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(languageLookupService.findAll()).build());
    }

    /**
     * List all health special needs.
     *
     * @return WsResponse of health special needs list
     */
    @GetMapping("/health-special-needs/list")
    public ResponseEntity<WsResponse<?>> listHealthSpecialNeeds() {
        log.debug("list health special needs...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(healthSpecialNeedsLookupService.findAll()).build());
    }


    /**
     * Verify an applicant based on cross-check factor, uin and date of birth.
     *
     * @param command contains uin and date of birth.
     * @return WsResponse of applicant (in case of success) or error (in case of failure)
     */
    @PostMapping("/verify")
    public ResponseEntity<WsResponse<?>> verify(@RequestBody @Validated ValidateApplicantCmd command) {
        Optional<ApplicantLiteDto> applicant = null;
        if (command.getType().equals("uin"))
            applicant = applicantLiteService.findByUin(command.getIdentifier());
        if (command.getType().equals("passport"))
            applicant = applicantLiteService.findByPassportNumber(command.getIdentifier(), command.getNationalityCode());
        if (command.getType().equals("id"))
            applicant = applicantLiteService.findByIdNumber(command.getIdentifier());

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
                        command.getIdentifier(), command.getDateOfBirthHijri(), command.getDateOfBirthGregorian());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                        .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED.getCode()).referenceNumber(command.getIdentifier()).build()).build());
            }
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicant).build());
        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(command.getIdentifier()).build()).build());
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
        if (!databaseApplicant.isPresent()) {
            log.error("invalid data for uin {}", command.getUin());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(command.getUin()).build()).build());
        }

        boolean dateOfBirthMatched = command.getDateOfBirthHijri() == databaseApplicant.get().getDateOfBirthHijri();
        if (!dateOfBirthMatched) {
            log.error("invalid data for uin {} and date of birth {}", command.getUin(), command.getDateOfBirthHijri());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED.getCode()).referenceNumber(command.getUin()).build()).build());
        }
        applicantService.updatePreferredLanguage(command.getUin(), "en");
        int updatedRowsCount = applicantService.updateApplicantContacts(databaseApplicant.get().getId(), command);
        if (updatedRowsCount < 1) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.UPDATE_APPLICANT_FAILED.getCode()).referenceNumber(command.getUin()).build()).build());
        }
        ApplicantLiteDto applicantLite = applicantLiteService.findByUin(command.getUin()).orElseThrow(() -> new ApplicantNotFoundException("No applicant found with uin " + command.getUin()));

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicantLite).build());
    }


    /**
     * Verify an applicant based on cross-check factor, uin and date of birth.
     *
     * @param applicantUin uin  .
     * @return WsResponse
     */
    @PostMapping("/create-relatives-chat-contact")
    public ResponseEntity<WsResponse<?>> createApplicantRelativesChatContacts(@RequestBody String applicantUin) {
        //find relatives for this applicant , check if they are not in the contacts  and add them one by one
        ApplicantRitualDto latestApplicantRitual = applicantRitualService.findLatestApplicantRitual(applicantUin);
        List<ApplicantRelativeDto> relatives = applicantRelativesService
                .findApplicantRelativesInLastRitual(applicantUin, latestApplicantRitual.getPackageReferenceNumber());
        if (!relatives.isEmpty()) {
            relatives.stream().forEach(relative -> {
                ChatContactDto applicantChatContact = chatContactService
                        .findApplicantChatContact(applicantUin, relative.getRelativeApplicant().getDigitalIds().get(0).getUin());
                if (applicantChatContact == null) {
                    chatContactService.createApplicantRelativesChatContacts(relative, latestApplicantRitual.getId());
                } else {
                    log.error("applicant chat contact already found for this relative {}", relative.getId());
                }
            });
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).build());
        } else {
            log.error("no relatives for this  applicant in this ritual");
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_RELATIVE_NOT_FOUND.getCode()).referenceNumber(applicantUin).build()).build());
        }

    }


    /**
     * finds applicant's health details by his UIN
     *
     * @param uin                the applicant's uin
     * @param applicantPackageId
     * @return the applicant health details or <code>null</code>
     */
    @GetMapping("/health/{uin}/{applicantPackageId}")
    public ResponseEntity<WsResponse<?>> findApplicantHealthDetails(@PathVariable String uin, @PathVariable Long applicantPackageId) {
        log.debug("Handler for {}", "Find applicant health details by uin and ritual id");
        Optional<ApplicantHealthLiteDto> applicantHealth = applicantHealthLiteService.findApplicantHealthDetailsByUinAndApplicantPackageId(uin, applicantPackageId);
        if (applicantHealth.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicantHealth).build());
        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED.getCode()).referenceNumber(uin).build()).build());
        }
    }

    /**
     * finds an applicant by his UIN and ritual id
     *
     * @param uin                the applicant's uin to find
     * @param applicantPackageId applicant ritual id
     * @return the found applicant or <code>null</code>
     */
    @GetMapping("/find/main-data/{uin}/{applicantPackageId}")
    public ResponseEntity<WsResponse<?>> findApplicantMainData(@PathVariable String uin, @PathVariable long applicantPackageId) {
        log.debug("Handler for {}", "Find applicant main data by uin");

        Optional<ApplicantMainDataDto> mainDataDtoOptional = applicantMainDataService.findByUin(uin, applicantPackageId);
        if (mainDataDtoOptional.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(mainDataDtoOptional.get()).build());

        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED.getCode()).referenceNumber(uin).build()).build());

        }

    }

    /**
     * finds an applicant card details by his UIN
     * to be used by applicant portal
     *
     * @param uin the applicant's card details by  uin
     * @return the found applicant card details or <code>null</code>
     */
    @GetMapping("/details/{uin}/{applicantPackageId}")
    public ResponseEntity<WsResponse<?>> findCardDetails(@PathVariable String uin, @PathVariable Long applicantPackageId) {
        //TODO this method should be deleted no needed after now, because we generate card as image see /card-image api
        log.debug("Handler for {}", "Find applicant card details by uin");
        Optional<ApplicantRitualCardLiteDto> returnedApplicantRitualCardLiteDto = applicantRitualCardLiteService.findCardDetailsByUinAndPackageId(uin, applicantPackageId);

        if (returnedApplicantRitualCardLiteDto.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(returnedApplicantRitualCardLiteDto).build());
        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.CARD_DETAILS_NOT_FOUND.getCode()).referenceNumber(uin).build()).build());
        }

    }

    /**
     * List of company ritual steps by uin.
     *
     * @return WsResponse of company ritual step list
     */
    @GetMapping("/company-ritual-step/{uin}")
    public ResponseEntity<WsResponse<?>> listCompanyRitualStep(@PathVariable String uin) {
        log.info("list company ritual step...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(companyRitualStepService.findCompanyRitualStepsByApplicantUin(uin)).build());
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
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(companyStaffService.findRelatedEmployeesByApplicantUinAndSeasonId(uin, seasonId)).build());
    }

    /**
     * finds an applicant group leaders by his UIN and SEASON ID
     * to be used by applicant portal
     *
     * @param uin      the applicant's group leaders details by  uin
     * @param seasonId the applicant's group leaders details by  season id
     * @return the company staff list
     */
    @GetMapping("/find/company-staff/group-leader/{uin}/{seasonId}")
    public ResponseEntity<WsResponse<?>> findGroupLeaderByUinAndSeasonId(@PathVariable String uin, @PathVariable long seasonId) {
        log.debug("Handler for {}", "Find company employee by uin and season ");
        Optional<CompanyStaffDto> groupLeader = companyStaffService.findGroupLeaderByApplicantUin(uin, seasonId);
        if (groupLeader.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(groupLeader.get()).build());
        }

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                .body(WsError.builder().error(WsError.EWsError.COMPANY_STAFF_NOT_FOUND.getCode()).referenceNumber(uin).build()).build());


    }

    @GetMapping("/company_ritual_step_label/list")
    public ResponseEntity<WsResponse<?>> listCompanyRitualStepsLabel() {
        log.debug("list company ritual step labels...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(companyRitualStepLookupService.findAllWithDescription()).build());

    }

    @GetMapping("/company_staff_title_label/list")
    public ResponseEntity<WsResponse<?>> listCompanyStaffTitlesLabel() {
        log.debug("list company Staff title labels...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(companyStaffTitleLookupService.findAll()).build());
    }


    /**
     * finds an applicant package by his UIN and  id
     *
     * @param uin                the applicant's uin to find
     * @param applicantPackageId applicant package id
     * @return the found applicant package data
     */
    @GetMapping("/applicant/package/{uin}/{applicantPackageId}")
    public ResponseEntity<WsResponse<?>> findApplicantPackageData(@PathVariable String uin, @PathVariable long applicantPackageId) {
        log.debug("Handler for {}", "Find applicant package details  by uin");

        ApplicantPackageDetailsDto applicantPackageDetails = new ApplicantPackageDetailsDto();
        ApplicantPackageDto applicantPackageDto = applicantPackageService.findOne(applicantPackageId);
        applicantPackageDetails.setApplicantPackageHousings(applicantPackageHousingService.findApplicantPackageHousingByUinAndApplicantPackageId(Long.parseLong(uin), applicantPackageId));
        applicantPackageDetails.setApplicantPackageCaterings(applicantPackageCateringService.findApplicantPackageCateringByUinAndApplicantPackageId(Long.parseLong(uin), applicantPackageId));
        applicantPackageDetails.setApplicantPackageTransportations(applicantPackageTransportationService.findApplicantPackageTransportationByUinAndApplicantPackageId(Long.parseLong(uin), applicantPackageId));
        applicantPackageDetails.setCompanyLite(companyService.findCompanyByCompanyRitualSeasonsIdAndApplicantUin(applicantPackageDto.getRitualPackage().getCompanyRitualSeason().getId(), Long.parseLong(uin)));

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicantPackageDetails).build());

    }


    /**
     * finds package catering related to applicant even if not have applicant package catering by his UIN and applicant package id
     *
     * @param uin                the applicant's uin to find
     * @param applicantPackageId
     * @return the found package Catering related to applicant
     */
    @GetMapping("/package/catering/{uin}/{applicantPackageId}")
    public ResponseEntity<WsResponse<?>> findApplicantPackageCatering(@PathVariable String uin, @PathVariable long applicantPackageId) {
        log.debug("Handler for {}", "Find package Catering  by uin");
        List<ApplicantPackageCateringDto> packageCateringDtoList = applicantPackageService.findPackageCateringFromRitualPackage(Long.parseLong(uin), applicantPackageId);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(packageCateringDtoList).build());
    }

    /**
     * List all housing categories.
     *
     * @return WsResponse of housing categories list
     */
    @GetMapping("/housing-category/list")
    public ResponseEntity<WsResponse<?>> listHousingCategories() {
        log.debug("list housing category...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(housingCategoryLookupService.findAll()).build());
    }

    /**
     * List all housing types.
     *
     * @return WsResponse of housing types list
     */
    @GetMapping("/housing-type/list")
    public ResponseEntity<WsResponse<?>> listHousingTypes() {
        log.debug("list housing types...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(housingTypeLookupService.findAll()).build());
    }

    /**
     * List all package types.
     *
     * @return WsResponse of package types list
     */
    @GetMapping("/package-type/list")
    public ResponseEntity<WsResponse<?>> listPackageTypes() {
        log.debug("list package types...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(packageTypeLookupService.findAll()).build());
    }

    /**
     * My program timetable by uin.
     *
     * @return WsResponse of company ritual step list
     */
    @GetMapping("/program-time-table/{uin}")
    public ResponseEntity<WsResponse<?>> programTimeTable(@PathVariable String uin) {
        log.info("list company ritual step...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(companyRitualStepService.findCompanyRitualStepsByApplicantUin(uin)).build());
    }

    /**
     * List all housing sites.
     *
     * @return WsResponse of housing sites list
     */
    @GetMapping("/housing-site/list")
    public ResponseEntity<WsResponse<?>> listHousingSites() {
        log.debug("list housing sites...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(housingSiteLookupService.findAll()).build());
    }

    /**
     * List all transportation types.
     *
     * @return WsResponse of transportation types list
     */
    @GetMapping("/transportation-type/list")
    public ResponseEntity<WsResponse<?>> listTransportationTypes() {
        log.debug("list transportation types...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(transportationTypeLookupService.findAll()).build());
    }

    /**
     * List all applicant digital ID statuses.
     *
     * @return WsResponse of applicant digital ID statuses list
     */
    @GetMapping("/digital-id-status/list")
    public ResponseEntity<WsResponse<?>> listDigitalIdStatuses() {
        log.debug("list digital ID statuses...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicantDigitalIdStatusLookupService.findAll()).build());
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
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(companyRitualSeasonLiteService.getListCompanyRitualSeasonByApplicantUin(uin)).build());
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
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(companyRitualSeasonLiteService.getLatestCompanyRitualSeasonByApplicantUin(uin)).build());
    }

    @GetMapping("/company-details/{uin}/{companyRitualSeasonId}")
    public ResponseEntity<WsResponse<?>> findApplicantCompanyDetailsByUinAndCompanyRitualSeasonId(@PathVariable String uin, @PathVariable long companyRitualSeasonId) {
        log.info("company details...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(companyService.findCompanyByCompanyRitualSeasonsIdAndApplicantUin(companyRitualSeasonId, Long.parseLong(uin))).build());
    }

    @GetMapping("/health-immunization/list")
    public ResponseEntity<WsResponse<?>> listImmunization() {
        log.debug("list health immunizations...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(healthImmunizationLookupService.findAll()).build());
    }

    @GetMapping("/religious-occasions-day/list")
    public ResponseEntity<WsResponse<?>> listReligiousOccasionsDay() {
        log.debug("list religious occasions day...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(religiousOccasionsDayLookupService.findAll()).build());
    }

    @GetMapping("/notification-category/list")
    public ResponseEntity<WsResponse<?>> listNotificationCategories() {
        log.debug("list notification categories...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(notificationCategoryLookupService.findAll()).build());
    }

    @GetMapping("/notification-name/list")
    public ResponseEntity<WsResponse<?>> listNotificationNames() {
        log.debug("list notification template name...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(notificationTemplateNameLookupService.findAll()).build());
    }

    @GetMapping("/meal-type/list")
    public ResponseEntity<WsResponse<?>> listMealTypes() {
        log.debug("list meal types...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(mealTypeLookupService.findAll()).build());
    }

    /**
     * List of incidents.
     *
     * @param applicantRitualId
     * @return WsResponse of list of incidents
     */
    @GetMapping("/incident/list/{applicantRitualId}")
    public ResponseEntity<WsResponse<?>> listApplicantRelatedIncidents(@PathVariable long applicantRitualId) {
        log.info("list incidents...");
        List<ApplicantIncidentDto> applicantIncidents = applicantIncidentService.listApplicantRelatedIncidents(applicantRitualId);
        if (applicantIncidents == null || applicantIncidents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(null).build());
        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicantIncidents).build());
        }
    }

    @GetMapping("/incident-status/list")
    public ResponseEntity<WsResponse<?>> listIncidentStatus() {
        log.debug("list incident status...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(incidentStatusLookupService.findAll()).build());
    }

    @GetMapping("/incident-type/list")
    public ResponseEntity<WsResponse<?>> listIncidentType() {
        log.debug("list incident type...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(incidentTypeLookupService.findAll()).build());
    }

    /**
     * List all organizer type.
     *
     * @return WsResponse of organizer type list
     */
    @GetMapping("/organizer-type/list")
    public ResponseEntity<WsResponse<?>> listOrganizerType() {
        log.debug("list countries...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(companyTypeLookupService.findAll()).build());
    }

    @GetMapping("/housing/{uin}/{applicantPackageId}")
    public ResponseEntity<WsResponse<?>> findCampLocation(@PathVariable long uin, @PathVariable long applicantPackageId) {
        log.debug("Camp Location ...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(packageHousingService.findCamp(applicantPackageId, uin)).build());
    }

    /**
     * find applicant ritual
     *
     * @param uin
     * @param applicantPackageId
     * @return
     */
    @GetMapping("/ritual/{uin}/{applicantPackageId}")
    public ResponseEntity<WsResponse<?>> finsApplicantRitual(@PathVariable String uin, @PathVariable long applicantPackageId) {
        log.debug("Handler for {}", "Find applicant ritual by uin");
        ApplicantRitualDto applicantRitualDtO = applicantRitualService.findByApplicantUinAndApplicantPackageId(uin, applicantPackageId);

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicantRitualDtO).build());
    }

    /**
     * Check the existence of an applicant based his UIN.
     *
     * @param uin The UIN of the applicant.
     * @return WsResponse of applicant (in case of success) or error (in case of failure)
     */
    @GetMapping("/applicant/find-by-uin/{uin}")
    public ResponseEntity<WsResponse<?>> findApplicantBasicDetailsByUin(@PathVariable String uin) {
        Optional<ApplicantLiteDto> applicant = applicantLiteService.findByUin(uin);
        if (!applicant.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(uin).build()).build());
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicant).build());
    }

    /**
     * Updates user preferred language
     *
     * @param uin  The UIN of the applicant.
     * @param lang the preferred language to update user with
     * @return WsResponse of applicant (in case of success) or error (in case of failure)
     */
    @PutMapping("/applicant/language/{uin}/{lang}")
    public ResponseEntity<WsResponse<?>> updateUserPreferredLanguage(@PathVariable String lang, @PathVariable String uin) {
        //FixMe: performance enhancement why here use applicantLiteService.findByUin and inside  applicantService.updatePreferredLanguage call findByUin again, here called unnecessary two queries, we can handle them in one query
        Optional<ApplicantLiteDto> applicant = applicantLiteService.findByUin(uin);
        if (applicant.isPresent()) {
            applicantService.updatePreferredLanguage(uin, lang);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(null).build());
        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(uin).build()).build());
        }
    }

    /**
     * @param uin
     * @return list of applicant ritual package
     */
    @GetMapping("/applicant/ritual-package/{uin}")
    public ResponseEntity<WsResponse<?>> findApplicantPackageAndRitualSeason(@PathVariable long uin) {
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicantPackageService.findApplicantRitualPackageByUin(uin)).build());

    }

    /**
     * @param uin
     * @return latest applicant ritual package
     */
    @GetMapping("/applicant/ritual-package/latest/{uin}")
    public ResponseEntity<WsResponse<?>> findLatestApplicantRitualSeason(@PathVariable long uin) {
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicantPackageService.findLatestApplicantRitualPackage(uin)).build());

    }

    @PostMapping("/verify-staff")
    public ResponseEntity<WsResponse<?>> verifyStaff(@RequestBody ValidateStaffCmd validateStaffCmd) {
        Optional<CompanyStaffLiteDto> staff = companyStaffService.findBySuin(validateStaffCmd.getSuin());
        if (staff.isPresent()) {
            boolean dateOfBirthMatched;
            SimpleDateFormat sdf = new SimpleDateFormat(ISO8601_DATE_PATTERN);
            // decide which date of birth to use
            if (validateStaffCmd.getDateOfBirthGregorian() != null && !validateStaffCmd.getDateOfBirthGregorian().equals("")) {
                try {
                    Date gregorianDate = sdf.parse(validateStaffCmd.getDateOfBirthGregorian());
                    String applicantDateFormatted = sdf.format(staff.get().getDateOfBirthGregorian());
                    String commandDataOfBirthFormatted = sdf.format(gregorianDate);
                    dateOfBirthMatched = commandDataOfBirthFormatted.equals(applicantDateFormatted);
                } catch (ParseException e) {
                    return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                            .body(WsError.builder().error(WsError.EWsError.COMPANY_STAFF_NOT_MATCHED.getCode()).referenceNumber(validateStaffCmd.getSuin()).build()).build());
                }

            } else {
                dateOfBirthMatched = validateStaffCmd.getDateOfBirthHijri() == staff.get().getDateOfBirthHijri();
            }
            if (!dateOfBirthMatched) {
                log.debug("unmatched data for {} suin and {} hijri date of birth and {} gregorian date of birth.",
                        validateStaffCmd.getSuin(), validateStaffCmd.getDateOfBirthHijri(), validateStaffCmd.getDateOfBirthGregorian());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                        .body(WsError.builder().error(WsError.EWsError.COMPANY_STAFF_NOT_MATCHED.getCode()).referenceNumber(validateStaffCmd.getSuin()).build()).build());
            }
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(staff).build());
        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.COMPANY_STAFF_NOT_FOUND.getCode()).referenceNumber(validateStaffCmd.getSuin()).build()).build());
        }
    }

    @PostMapping("/update-staff")
    public ResponseEntity<WsResponse<?>> updateStaff(@RequestBody @Validated UpdateStaffCmd command) {
        // TODO: 26/01/2022 should be refactored same as applicant
        Optional<CompanyStaffLiteDto> companyStaff = companyStaffService.findBySuin(command.getSuin());
        if (companyStaff.isPresent()) {
            //TODO I commented out this code because it is prevent us from update staff mobile number and email
            /*Optional<CompanyStaffDto> staffInAdminPortal = companyStaffService.findByStaffId(companyStaff.get().getId());
            if (staffInAdminPortal.isPresent()) {
                return ResponseEntity.ok(
                        WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                                .body(WsError.builder().error(WsError.EWsError.ALREADY_REGISTERED.getCode()).referenceNumber(command.getSuin()).build()).build());

            }*/
            boolean dateOfBirthMatched;
            SimpleDateFormat sdf = new SimpleDateFormat(ISO8601_DATE_PATTERN);
            // decide which date of birth to use
            if (command.getDateOfBirthGregorian() != null) {
                String applicantDateFormatted = sdf.format(companyStaff.get().getDateOfBirthGregorian());
                String commandDataOfBirthFormatted = sdf.format(command.getDateOfBirthGregorian());
                dateOfBirthMatched = commandDataOfBirthFormatted.equals(applicantDateFormatted);
            } else {
                dateOfBirthMatched = command.getDateOfBirthHijri() == companyStaff.get().getDateOfBirthHijri();
            }
            if (!dateOfBirthMatched) {
                log.error("invalid data for suin {} and date of birth {}", command.getSuin(), command.getDateOfBirthHijri());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                        .body(WsError.builder().error(WsError.EWsError.COMPANY_STAFF_NOT_MATCHED.getCode()).referenceNumber(command.getSuin()).build()).build());
            }
            int updatedRowsCount = companyStaffService.updateCompanyStaff(companyStaff.get().getId(), command);
            if (updatedRowsCount < 1) {
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                        .body(WsError.builder().error(WsError.EWsError.UPDATE_STAFF_FAILED.getCode()).referenceNumber(command.getSuin()).build()).build());
            }
            CompanyStaffLiteDto companyStaffLiteDto = companyStaffService.findBySuin(command.getSuin()).orElseThrow(() -> new ApplicantNotFoundException("No staff found with suin " + command.getSuin()));

            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(companyStaffLiteDto).build());
        } else {
            log.error("invalid data for suin {}", command.getSuin());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.COMPANY_STAFF_NOT_FOUND.getCode()).referenceNumber(command.getSuin()).build()).build());
        }
    }

    /**
     * Updates user mobileLogin flag to true when login, and false when logout
     *
     * @param uin            The UIN of the applicant.
     * @param mobileLoggedIn flag set to true when login, and false when logout
     */
    @PutMapping("/applicant/mobile-login/{uin}/{mobileLoggedIn}")
    public ResponseEntity<WsResponse<?>> updateLoggedInFromMobileAppFlag(@PathVariable String uin, @PathVariable boolean mobileLoggedIn) {
        Optional<ApplicantDto> applicant = applicantService.findByUin(uin);
        if (applicant.isPresent()) {
            applicantService.updateLoggedInFromMobileAppFlag(mobileLoggedIn, applicant.get().getId());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(null).build());
        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(uin).build()).build());
        }
    }

    @GetMapping("/validate-suin/{suin}")
    public ResponseEntity<WsResponse<?>> validateSuin(@PathVariable String suin) {
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(companyStaffDigitalIdService.findStaffSuinStatusCode(suin)).build());

    }


    /**
     * @param companyRitualSeasonId
     * @return list of today ritual steps
     */
    @GetMapping("/ritual-steps/today/{companyRitualSeasonId}")
    public ResponseEntity<WsResponse<?>> findTodayCompanyRitualStepsByCompanyRitualSeasonId(@PathVariable long companyRitualSeasonId) {
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(companyRitualStepService.findTodayCompanyRitualStepsByCompanyRitualSeasonId(companyRitualSeasonId)).build());

    }

    /**
     * @param suin
     * @return company ritual season
     */
    @GetMapping("/company-ritual-season/{suin}")
    public ResponseEntity<WsResponse<?>> findLatestCompanyRitualSeasonIdBySuin(@PathVariable String suin) {
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(companyRitualSeasonService.findLatestCompanyRitualSeasonIdBySuin(suin)).build());

    }

    @PostMapping("/store-user-locations")
    public ResponseEntity<WsResponse<?>> storeUserLocations(@RequestBody List<UserLocationDto> locationsList) {
        boolean isSaved = userLocationService.storeUserLocation(locationsList);
        if (!isSaved) {
            return ResponseEntity.ok(
                    WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                            .body(WsError.builder().error(WsError.EWsError.INVALID_LOCATION_ENTRIES.getCode()).build()).build());
        }

        return ResponseEntity.ok(
                WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(true).build());

    }

    @GetMapping("/applicant/relatives/{applicantUin}")
    public ResponseEntity<WsResponse<?>> findApplicantRelatives(@PathVariable String applicantUin) {
        ApplicantRitualDto latestApplicantRitual = applicantRitualService.findLatestApplicantRitual(applicantUin);
        List<ApplicantRelativeDto> relatives = applicantRelativesService
                .findApplicantRelativesInLastRitual(applicantUin, latestApplicantRitual.getPackageReferenceNumber());


        return ResponseEntity.ok(
                WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(relatives).build());

    }

    /**
     * @param companyRitualSeasonId
     * @return list of ritual steps
     */
    @GetMapping("/ritual-steps/all/{companyRitualSeasonId}")
    public ResponseEntity<WsResponse<?>> findCompanyRitualStepsByCompanyRitualSeasonId(@PathVariable long companyRitualSeasonId) {
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(companyRitualStepService.findCompanyRitualStepsByCompanyRitualSeasonId(companyRitualSeasonId)).build());

    }

    /**
     * @param companyRitualSeasonId
     * @return latest Ritual Package
     */
    @GetMapping("/ritual-package/latest/{companyRitualSeasonId}")
    public ResponseEntity<WsResponse<?>> findLatestRitualPackageByCompanyRitualSeasonId(@PathVariable long companyRitualSeasonId) {
        RitualPackageDto ritualPackage = ritualPackageService.findRitualPackageByCompanyRitualSeasonId(companyRitualSeasonId);
        if (ritualPackage == null)
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.RITUAL_PACKAGE_NOT_FOUND.getCode()).build()).build());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(ritualPackage).build());

    }

    /**
     * @param applicantUin
     * @return generated badge for applicant
     */
    @GetMapping("/badge/generate/{applicantUin}/{withQr}")
    public ResponseEntity<WsResponse<?>> findApplicantBadge(@PathVariable String applicantUin, @PathVariable boolean withQr) {
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(badgeService.generateApplicantBadge(applicantUin, withQr)).build());

    }

    /**
     * @param suin
     * @return generated badge for staff
     */
    @GetMapping("/badge/staff/generate/{suin}")
    public ResponseEntity<WsResponse<?>> findApplicantBadge(@PathVariable String suin) {
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(badgeService.generateStaffCard(suin)).build());

    }

    /**
     * save user registration action to audit mobile log
     *
     * @param uin The UIN of the applicant.
     */
    @GetMapping("/applicant/save-register-event/{uin}")
    public ResponseEntity<WsResponse<?>> storeSignupEventFromMobile(@PathVariable String uin) {
        Optional<ApplicantDto> applicant = applicantService.findByUin(uin);
        if (applicant.isPresent()) {
            applicantService.storeSignupEvent(applicant.get().getId());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(null).build());
        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(uin).build()).build());
        }
    }

    @PostMapping("/mark-as-registered/{channel}")
    public ResponseEntity<WsResponse<?>> update(@RequestBody String uin, @PathVariable String channel) {
        Optional<ApplicantDto> databaseApplicant = applicantService.findByUin(uin);
        if (!databaseApplicant.isPresent()) {
            log.error("invalid data for uin {}", uin);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(uin).build()).build());
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicantService.markAsRegistered(databaseApplicant.get().getId(), channel)).build());
    }

    /**
     * @return all data segments
     */
    @GetMapping("/data/segments")
    public ResponseEntity<WsResponse<?>> findAllDataSegments() {
        log.info("listing all segments");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(dataSegmentService.findOrganizerSegments()).build());

    }

    @PostMapping(value = "/data/request/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<WsResponse<?>> create(@RequestPart("request") DataRequestDto dataRequest,
                                                @RequestPart("file") MultipartFile file) throws Exception {
        log.info("Creating data request for segment#{}", dataRequest.getDataSegment().getId());

        if (dataRequestService.readItemsCount(file) == 0) {
            return ResponseEntity.status(EMPTY_FILE_UPLOADED_RESPONSE_CODE).body(null);

        } else {
            dataRequest.setItemCount(dataRequestService.readItemsCount(file));
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(dataRequestService.save(dataRequest, file)).build());
        }

    }

    /**
     * Confirms a newly created data request
     *
     * @param dataRequestId the data request id to be confirmed
     */
    @PostMapping(value = "/data/request/confirm/{dataRequestId}/{companyRefCode}")
    public ResponseEntity<WsResponse<?>> confirm(@PathVariable long dataRequestId, @PathVariable String companyRefCode) throws Exception {
        log.info("Confirming data request #{}", dataRequestId);
        log.info("Confirming data request #{}", companyRefCode);
        dataRequestService.confirm(dataRequestId, companyRefCode);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body("SUCCESS").build());

    }

    @GetMapping("/data/request/{dataRequestId}/file/{fileType}")
    public ResponseEntity<Resource> downloadFile(@PathVariable long dataRequestId, @PathVariable String fileType) throws Exception {
        log.info("Downloading file for data request#{} and file type #{}", dataRequestId, fileType);
        Resource file = null;
        switch (fileType) {
            case "O":
                file = dataRequestService.fetchOriginalFile(dataRequestId);
                break;
            case "E":
                file = dataRequestService.fetchErrorsFile(dataRequestId);
                break;
        }
        if (file != null) {
            String fileName = "file.xlsx";
            if (Objects.requireNonNull(file.getDescription()).contains("[")) {
                fileName = file.getDescription().split("\\[")[1].replaceAll("]", "");
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(file);
        }
        return null;
    }

    /**
     * Downloads a template for a specific segment
     *
     * @param segmentId data segment Id
     * @return the template for the given segment
     */
    @GetMapping("/data/request/tpl/{segmentId}")
    public ResponseEntity<Resource> downloadTemplate(@PathVariable long segmentId) {
        DataSegmentDto dataSegment = dataSegmentService.findOne(segmentId);
        log.info("Downloading template for data segment#{}", segmentId);
        if (dataSegment == null) {
            log.warn("Now data segment found with #{}", segmentId);
            return null;
        }
        Resource tplFile = dataSegmentService.loadTemplateFile(dataSegment);
        if (tplFile.exists()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + tplFile.getFilename() + "\"")
                    .body(tplFile);
        }
        return null;
    }

    @GetMapping("/data/request/list")
    public ResponseEntity<WsResponse<?>> listDataRequests(Pageable pageable) {
        log.info("listing all data requests");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(dataRequestService.findAllOrganizerDataRequest(pageable)).build());

    }

    @GetMapping("/data/request/find/{dataRequestId}")
    public ResponseEntity<WsResponse<?>> find(@PathVariable long dataRequestId) {
        log.debug("Finding data request #{}", dataRequestId);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(dataRequestService.findById(dataRequestId)).build());
    }

    @PostMapping("/staff/list")
    public ResponseEntity<WsResponse<?>> searchStaff(@RequestBody CompanyStaffFilterDto companyStaffFilterDto) {
        log.info("find employees by code and type code");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(companyStaffService.searchStaff(companyStaffFilterDto)).build());

    }

    @GetMapping("/staff/details/{id}")
    public ResponseEntity<WsResponse<?>> findStaffById(@PathVariable long id) {
        log.debug("Handler for {}", "Find staff");
        Optional<CompanyStaffFullVO> staff = companyStaffService.searchStaffById(id);
        if (staff.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(staff.get()).build());
        }
        log.info("Finish findCompanyStaffBySuin {}, {}", "FAILURE", "COMPANY_STAFF_NOT_FOUND");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                .body(WsError.builder().error(WsError.EWsError.COMPANY_STAFF_NOT_FOUND.getCode()).referenceNumber(String.valueOf(id)).build()).build());
    }

    @PutMapping("/staff/update-job-title")
    public ResponseEntity<WsResponse<?>> updateCompanyStaffTitle(@RequestBody UpdateStaffTitleCmd command) {
        log.info("find employees by code and type code");
        UpdateStaffTitleCmd cmd = companyStaffService.updateCompanyStaffTitle(command);
        if (cmd == null) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.UPDATE_STAFF_FAILED.getCode()).referenceNumber(String.valueOf(command.getId())).build()).build());
        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(cmd).build());
        }
    }

    @GetMapping("/applicant/emergency-contact/get/{applicantUin}")
    public ResponseEntity<WsResponse<?>> findApplicantEmergencyContactByApplicantId(@PathVariable String applicantUin) {
        ApplicantEmergencyContactDto applicantEmergencyContactByApplicantId = applicantLiteService.findApplicantEmergencyContactByApplicantId(applicantUin);
        if (applicantEmergencyContactByApplicantId == null) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.NOT_FOUND_IN_ADMIN.getCode()).build()).build());
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicantEmergencyContactByApplicantId).build());
    }

    @PostMapping("/applicant/emergency-contact/update/{applicantUin}")
    public ResponseEntity<WsResponse<?>> updateApplicantEmergencyContactByApplicantId(@PathVariable String applicantUin, @RequestBody ApplicantEmergencyContactDto applicantEmergencyContact) {

        if (applicantEmergencyContact == null || applicantEmergencyContact.getEmergencyContactName() == null || applicantEmergencyContact.getEmergencyContactMobileNumber() == null ||
                applicantEmergencyContact.getEmergencyContactName().trim().isEmpty() || applicantEmergencyContact.getEmergencyContactMobileNumber().trim().isEmpty()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode()).build()).build());
        }
        ApplicantEmergencyContactDto applicantEmergencyContactByApplicantId = applicantLiteService.updateApplicantEmergencyContactByApplicantId(applicantUin, applicantEmergencyContact);
        if (applicantEmergencyContactByApplicantId == null) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.NOT_FOUND_IN_ADMIN.getCode()).build()).build());
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicantEmergencyContactByApplicantId).build());
    }

    @PostMapping("/applicants/list/{companyRefCode}/{companyTypeCode}")
    public ResponseEntity<WsResponse<?>> findOrganizerApplicants(@RequestBody ApplicantSearchCriteriaDto applicantSearchCriteriaDto, @PathVariable Long companyRefCode, @PathVariable String companyTypeCode) {
        log.info("find employees by code and type code");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(applicantService.findOrganizerApplicants(applicantSearchCriteriaDto, companyRefCode, companyTypeCode)).build());
    }


}
