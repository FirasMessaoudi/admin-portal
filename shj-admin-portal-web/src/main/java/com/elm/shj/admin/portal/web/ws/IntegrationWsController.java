/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.dcc.foundation.providers.recaptcha.exception.RecaptchaException;
import com.elm.shj.admin.portal.services.applicant.ApplicantHealthLiteService;
import com.elm.shj.admin.portal.services.applicant.ApplicantLiteService;
import com.elm.shj.admin.portal.services.applicant.ApplicantMainDataService;
import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.lookup.*;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualCardLiteService;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualLiteService;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
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
    public final static String SAUDI_MOBILE_NUMBER_REGEX = "^(009665|9665|\\+9665|05|5)([0-9]{8})$";

    private final OtpAuthenticationProvider authenticationProvider;
    private final JwtTokenService jwtTokenService;
    private final ApplicantLiteService applicantLiteService;
    private final RitualTypeLookupService ritualTypeLookupService;
    private final CardStatusLookupService cardStatusLookupService;
    private final RelativeRelationshipLookupService relativeRelationshipLookupService;
    private final MaritalStatusLookupService maritalStatusLookupService;
    private final CountryLookupService countryLookupService;
    private final HealthSpecialNeedsLookupService healthSpecialNeedsLookupService;
    private final ApplicantRitualService applicantRitualService;
    private final ApplicantRitualLiteService applicantRitualLiteService;
    private final ApplicantService applicantService;
    private final ApplicantHealthLiteService applicantHealthLiteService;
    private final ApplicantRitualCardLiteService applicantRitualCardLiteService;
    private final ApplicantMainDataService applicantMainDataService;

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
            applicantService.updateApplicantContacts(databaseApplicant.get().getId(), command);
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
     * @param uin      the applicant's uin
     * @param ritualId
     * @return the applicant health details or <code>null</code>
     */
    @GetMapping("/health/{uin}/{ritualId}")
    public ResponseEntity<WsResponse<?>> findApplicantHealthDetails(@PathVariable String uin, @PathVariable Long ritualId) {
        log.debug("Handler for {}", "Find applicant health details by uin and ritual id");
        Optional<ApplicantHealthLiteDto> applicantHealth = applicantHealthLiteService.findByUinAndRitualId(uin, ritualId);
        if (applicantHealth.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(applicantHealth).build());
        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED).referenceNumber(uin).build()).build());
        }
    }

    /**
     * finds an applicant seasons by his UIN
     *
     * @param uin the applicant's uin to find
     * @return the found applicant seasons list
     */
    @GetMapping("/find/ritual-seasons/{uin}")
    public ResponseEntity<WsResponse<?>> findApplicantRitualSeasons(@PathVariable String uin) {
        log.debug("Handler for {}", "Find applicant by uin");

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(applicantRitualService.findHijriSeasonsByUin(uin)).build());

    }

    /**
     * finds an applicant ritual lite by his UIN and season number
     *
     * @param uin    the applicant's uin to find
     * @param season season number
     * @return the found applicant seasons list
     */
    @GetMapping("/find/ritual-lite/{uin}/{season}")
    public ResponseEntity<WsResponse<?>> findApplicantRitualByUinAndSeasons(@PathVariable String uin, @PathVariable int season) {
        log.debug("Handler for {}", "Find applicant ritual by uin and season id");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(applicantRitualLiteService.findApplicantRitualByUinAndSeason(uin, season)).build());
    }

    /**
     * finds latest applicant ritual lite by his UIN
     *
     * @param uin the applicant's uin to find
     * @return the found applicant seasons list
     */
    @GetMapping("/find/ritual-lite/latest/{uin}")
    public ResponseEntity<WsResponse<?>> findLatestApplicantRitualByUin(@PathVariable String uin) {
        log.debug("Handler for {}", "Find latest applicant ritual by uin ");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(applicantRitualLiteService.findLatestApplicantRitualByUin(uin)).build());
    }

    /**
     * finds an applicant by his UIN and ritual id
     *
     * @param uin      the applicant's uin to find
     * @param ritualId applicant ritual id
     * @return the found applicant or <code>null</code>
     */
    @GetMapping("/find/main-data/{uin}/{ritualId}")
    public ResponseEntity<WsResponse<?>> findApplicantMainData(@PathVariable String uin, @PathVariable long ritualId) {
        log.debug("Handler for {}", "Find applicant main data by uin");

        Optional<ApplicantMainDataDto> mainDataDtoOptional = applicantMainDataService.findByUin(uin, ritualId);
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
    @GetMapping("/details/{uin}/{ritualId}")
    public ResponseEntity<WsResponse<?>> findCardDetails(@PathVariable String uin, @PathVariable String ritualId) {
        log.debug("Handler for {}", "Find applicant card details by uin");
        Optional<ApplicantRitualCardLiteDto> returnedApplicantRitualCardLiteDto = applicantRitualCardLiteService.findCardDetailsByUinAndRitualId(uin, ritualId);

        if (returnedApplicantRitualCardLiteDto.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(returnedApplicantRitualCardLiteDto).build());
        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.CARD_DETAILS_NOT_FOUND).referenceNumber(uin).build()).build());
        }

    }

}
