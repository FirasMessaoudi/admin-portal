/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.applicant.ApplicantHealthService;
import com.elm.shj.admin.portal.services.applicant.ApplicantLiteService;
import com.elm.shj.admin.portal.services.applicant.ApplicantMainDataService;
import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.ApplicantLiteDto;
import com.elm.shj.admin.portal.services.dto.ApplicantMainDataDto;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualLiteService;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
import com.elm.shj.admin.portal.web.error.ApiErrorResponse;
import com.elm.shj.admin.portal.web.error.ApplicantNotFoundException;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.security.RolesAllowed;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Main controller for applicant management pages
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@RestController
@RequestMapping(Navigation.API_APPLICANTS)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantController {

    public final static String ISO8601_DATE_PATTERN = "yyyy-MM-dd";
    public final static String SAUDI_MOBILE_NUMBER_REGEX = "^(009665|9665|\\+9665|05|5)([0-9]{8})$";
    private static final int APPLICANT_NOT_FOUND_RESPONSE_CODE = 561;
    private static final String APPLICANT_NOT_FOUND_ERROR_MSG = "not found";

    private final ApplicantService applicantService;
    private final ApplicantLiteService applicantLiteService;
    private final ApplicantMainDataService applicantMainDataService;
    private final ApplicantRitualService applicantRitualService;
    private final ApplicantRitualLiteService applicantRitualLiteService;
    private final ApplicantHealthService applicantHealthService;

    @GetMapping("/list/all")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public Page<ApplicantDto> listApplicants(Pageable pageable) {
        log.debug("List applicants...");
        return applicantService.findAll(pageable);
    }

    /**
     * finds an applicant by his UIN
     *
     * @param uin the applicant's uin to find
     * @return the found applicant or <code>null</code>
     */
    @GetMapping("/find/main-data/{uin}")
    public ApplicantMainDataDto findApplicantMainData(@PathVariable String uin) {
        log.debug("Handler for {}", "Find applicant main data by uin");

        return applicantMainDataService.findByUin(uin).orElseThrow(
                () -> {
                    Map<String, String> errors = new HashMap<>();
                    errors.put("uin", APPLICANT_NOT_FOUND_ERROR_MSG);

                    return new ApplicantNotFoundException("No applicant found with uin " + uin, errors);
                });
    }

    /**
     * finds an applicant by his UIN
     *
     * @param uin the applicant's uin to find
     * @return the found applicant or <code>null</code>
     */
    @GetMapping("/find/{uin}")
    public ApplicantLiteDto findApplicant(@PathVariable String uin) {
        log.debug("Handler for {}", "Find applicant by uin");

        return applicantLiteService.findByUin(uin).orElseThrow(() -> {
            Map<String, String> errors = new HashMap<>();
            errors.put("uin", APPLICANT_NOT_FOUND_ERROR_MSG);

            return new ApplicantNotFoundException("No applicant found with uin " + uin, errors);
        });
    }

    /**
     * finds an applicant based on cross-check factor
     *
     * @return the found applicant or <code>null</code>
     */
    @PostMapping("/verify")
    public ResponseEntity<ApplicantLiteDto> verify(@RequestBody @Validated ValidateApplicantCmd command) {

        Optional<ApplicantLiteDto> applicant = applicantLiteService.findByUin(command.getUin());

        Map<String, String> errors = new HashMap<>();
        errors.put("uin", APPLICANT_NOT_FOUND_ERROR_MSG);

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
            if (dateOfBirthMatched) {
                return ResponseEntity.ok(applicant.get());
            } else throw new ApplicantNotFoundException("No applicant found with uin " + command.getUin(), errors);
        } else throw new ApplicantNotFoundException("No applicant found with uin " + command.getUin(), errors);
    }

    /**
     * Updates an existing applicant
     *
     * @return the updated applicant
     */
    @PostMapping("/update")
    public ResponseEntity<ApplicantLiteDto> update(@RequestBody @Validated UpdateApplicantCmd command) {
        Optional<ApplicantDto> databaseApplicant = applicantService.findByUin(command.getUin());
        if (databaseApplicant.isPresent()) {
            boolean dateOfBirthMatched = false;
            dateOfBirthMatched = command.getDateOfBirthHijri() == databaseApplicant.get().getDateOfBirthHijri();
            if (!dateOfBirthMatched) {
                log.error("invalid data for uin {} and date of birth {}", command.getUin(), command.getDateOfBirthHijri());
                return ResponseEntity.status(APPLICANT_NOT_FOUND_RESPONSE_CODE).build();
            }

        }
        if (databaseApplicant.isPresent()) {
            // sets form fields to database applicant instance
            databaseApplicant.get().getContacts().get(0).setEmail(command.getEmail());
            databaseApplicant.get().getContacts().get(0).setCountryCode(command.getCountryCode());
            if (command.getMobileNumber().matches(SAUDI_MOBILE_NUMBER_REGEX)) {
                databaseApplicant.get().getContacts().get(0).setLocalMobileNumber(command.getMobileNumber());
            } else {
                databaseApplicant.get().getContacts().get(0).setIntlMobileNumber(command.getMobileNumber());
            }
            applicantService.save(databaseApplicant.get());

            ApplicantLiteDto applicantLite = applicantLiteService.findByUin(command.getUin()).orElseThrow(() -> new ApplicantNotFoundException("No applicant found with uin " + command.getUin()));

            return ResponseEntity.ok(Objects.requireNonNull(applicantLite));
        } else {
            log.error("invalid data for uin {}", command.getUin());
            return ResponseEntity.status(APPLICANT_NOT_FOUND_RESPONSE_CODE).build();
        }
    }


    @ExceptionHandler({ApplicantNotFoundException.class})
    public ResponseEntity<Object> handleApplicantNotFoundException(
            ApplicantNotFoundException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);

        ApiErrorResponse apiError =
                new ApiErrorResponse(APPLICANT_NOT_FOUND_RESPONSE_CODE, ex.getMessage(), ex.getErrors());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }


}
