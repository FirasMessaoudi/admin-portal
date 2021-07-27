/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.applicant.ApplicantLiteService;
import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.ApplicantLiteDto;
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

    private final ApplicantService applicantService;
    private final ApplicantLiteService applicantLiteService;

    @GetMapping("/list/all")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public Page<ApplicantDto> listApplicants(Pageable pageable) {
        log.debug("List applicants...");
        return applicantService.findAll(pageable);
    }

    /**
     * finds an applicant by his ID
     *
     * @param applicantId the applicant id to find
     * @return the found user or <code>null</code>
     */
    @GetMapping("/find/{applicantId}")
    public ApplicantDto findApplicant(@PathVariable long applicantId) {
        log.debug("Handler for {}", "Find applicant");
        return applicantService.findOne(applicantId);
    }

    /**
     * finds an applicant based on cross-check factor
     *
     * @return the found applicant or <code>null</code>
     */
    @PostMapping("/verify")
    public ApplicantLiteDto validate(@RequestBody @Valid ValidateApplicantCmd command) {

        ApplicantLiteDto applicant = applicantLiteService.findByUin(command.getUin()).orElseThrow(() -> new UsernameNotFoundException("No applicant found with uin " + command.getUin()));

        boolean dateOfBirthMatched;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
    @PostMapping("/update/{uin}")
    public ResponseEntity<ApplicantLiteDto> update(@PathVariable String uin,
                                               @RequestBody @Valid UpdateApplicantCmd command) {
        log.debug("Handler for {}", "Update applicant");

        ApplicantDto databaseApplicant = applicantService.findByUin(uin).orElseThrow(() -> new UsernameNotFoundException("No applicant found with uin " + uin));

        // sets form fields to database applicant instance
        databaseApplicant.getContacts().get(0).setEmail(command.getEmail());
        databaseApplicant.getContacts().get(0).setLocalMobileNumber(command.getLocalMobileNumber());

        applicantService.save(databaseApplicant);

        ApplicantLiteDto applicantLite = applicantLiteService.findByUin(uin).orElseThrow(() -> new UsernameNotFoundException("No applicant found with uin " + uin));

        return ResponseEntity.ok(Objects.requireNonNull(applicantLite));
    }
}
