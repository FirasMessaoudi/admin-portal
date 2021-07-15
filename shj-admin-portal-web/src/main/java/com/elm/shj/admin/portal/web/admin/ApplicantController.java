/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
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

    @GetMapping("/list/all")
    @RolesAllowed(AuthorityConstants.USER_MANAGEMENT) //TODO: Change it
    public Page<ApplicantDto> listApplicants(Pageable pageable) {
        log.debug("List applicants...");
        return applicantService.findAll(pageable);
    }

    /**
     * finds an applicant based on cross-check factor
     *
     * @return the found applicant or <code>null</code>
     */
    @PostMapping("/validate")
    public ApplicantDto validate(@RequestBody @Valid ValidateApplicantCmd command) {

        ApplicantDto applicant = applicantService.findByUin(command.getUin()).orElseThrow(() -> new UsernameNotFoundException("No applicant found with uin " + command.getUin()));

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
    @PostMapping("/{applicantId}/update")
    public ResponseEntity<ApplicantDto> updateUser(@PathVariable long applicantId,
                                                   @RequestBody @Valid UpdateApplicantCmd command) {
        log.debug("Handler for {}", "Update applicant");

        ApplicantDto databaseApplicant = applicantService.findOne(applicantId);

        // sets form fields to database applicant instance
        databaseApplicant.getContacts().get(0).setEmail(command.getEmail());
        databaseApplicant.getContacts().get(0).setLocalMobileNumber(command.getLocalMobileNumber());

        ApplicantDto savedApplicant = applicantService.save(databaseApplicant);

        return ResponseEntity.ok(Objects.requireNonNull(savedApplicant));
    }
}
