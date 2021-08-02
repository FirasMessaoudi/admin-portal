/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.applicant.ApplicantLiteService;
import com.elm.shj.admin.portal.services.applicant.ApplicantMainDataService;
import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.ApplicantLiteDto;
import com.elm.shj.admin.portal.services.dto.ApplicantMainDataDto;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Objects;

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
    public final static String SAUDI_MOBILE_NUMBER_REGEX = "^(009665|9665|\\+9665|05|5)([503649187])([0-9]{7})$";

    private final ApplicantService applicantService;
    private final ApplicantLiteService applicantLiteService;
    private final ApplicantMainDataService applicantMainDataService;

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
        log.debug("Handler for {}", "Find applicant by uin");
        return applicantMainDataService.findByUin(uin).orElseThrow(() -> new UsernameNotFoundException("No applicant found with uin " + uin));
    }

    /**
     * finds an applicant by his UIN
     *
     * @param uin the applicant's uin to find
     * @return the found applicant or <code>null</code>
     */
    @GetMapping("/find/{uin}")
    public ApplicantDto findApplicant(@PathVariable String uin) {
        log.debug("Handler for {}", "Find applicant by uin");
        return applicantService.findByUin(uin).orElseThrow(() -> new UsernameNotFoundException("No applicant found with uin " + uin));
    }

    /**
     * finds an applicant based on cross-check factor
     *
     * @return the found applicant or <code>null</code>
     */
    @PostMapping("/verify")
    public ApplicantLiteDto verify(@RequestBody @Valid ValidateApplicantCmd command) {

        ApplicantLiteDto applicant = applicantLiteService.findByUin(command.getUin()).orElseThrow(() -> new UsernameNotFoundException("No applicant found with uin " + command.getUin()));

        boolean dateOfBirthMatched;

        SimpleDateFormat sdf = new SimpleDateFormat(ISO8601_DATE_PATTERN);
        // decide which date of birth to use
        if (command.getDateOfBirthGregorian() != null) {
            String applicantDateFormatted = sdf.format(applicant.getDateOfBirthGregorian());
            String commandDataOfBirthFormatted = sdf.format(command.getDateOfBirthGregorian());
            dateOfBirthMatched = commandDataOfBirthFormatted.equals(applicantDateFormatted);
        } else {
            dateOfBirthMatched = command.getDateOfBirthHijri() == applicant.getDateOfBirthHijri();
        }
        if (dateOfBirthMatched) {
            return applicant;
        } else {
            log.debug("invalid data for uin {}", command.getUin());
            throw new UsernameNotFoundException("invalid data");
        }
    }

    /**
     * Updates an existing applicant
     *
     * @return the updated applicant
     */
    @PostMapping("/update")
    public ResponseEntity<ApplicantLiteDto> update(@RequestBody @Valid UpdateApplicantCmd command) {
        log.debug("Handler for {}", "Update applicant");

        ApplicantDto databaseApplicant = applicantService.findByUin(command.getUin()).orElseThrow(() -> new UsernameNotFoundException("No applicant found with uin " + command.getUin()));

        // sets form fields to database applicant instance
        databaseApplicant.getContacts().get(0).setEmail(command.getEmail());

        if (command.getMobileNumber().matches(SAUDI_MOBILE_NUMBER_REGEX)) {
            databaseApplicant.getContacts().get(0).setLocalMobileNumber(command.getMobileNumber());
        } else {
            databaseApplicant.getContacts().get(0).setIntlMobileNumber(command.getMobileNumber());
        }

        applicantService.save(databaseApplicant);

        ApplicantLiteDto applicantLite = applicantLiteService.findByUin(command.getUin()).orElseThrow(() -> new UsernameNotFoundException("No applicant found with uin " + command.getUin()));

        return ResponseEntity.ok(Objects.requireNonNull(applicantLite));
    }
}
