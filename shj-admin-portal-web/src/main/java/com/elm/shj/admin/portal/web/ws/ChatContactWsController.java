/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.dcc.foundation.commons.validation.SafeFile;
import com.elm.shj.admin.portal.services.applicant.ApplicantChatContactService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.applicant.ApplicantLiteService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Controller for exposing chat contacts web services for external party.
 *
 * @author Slim Ben Hadj
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
@RequestMapping(Navigation.API_CHAT_CONTACT_INTEGRATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChatContactWsController {

    private final ApplicantChatContactService applicantChatContactService;

    /**
     * finds chat contacts by uin and applicant ritual ID
     *
     * @param uin               the UIN of the applicant
     * @param applicantRitualId the selected ritual ID
     * @param systemDefined
     * @return the found chat contacts or empty structure
     */
    @GetMapping("/{uin}/{applicantRitualId}")
    public ResponseEntity<WsResponse<?>> findAChatContactsByUinAndRitualId(@PathVariable String uin,
                                                                           @PathVariable Long applicantRitualId,
                                                                           @RequestParam(required = false) Boolean systemDefined) {
        log.debug("List chat contacts by uin {} and applicant ritual ID {}", uin, applicantRitualId);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(
                applicantChatContactService.listApplicantChatContacts(uin, applicantRitualId, systemDefined)).build());
    }

    /**
     * Add new applicant chat contact
     *
     * @param contact applicant chat contact details
     * @return WsResponse of the persisted chat contact
     */
    @PostMapping(value = "/create/{applicantUin}/{applicantRitualId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<WsResponse<?>> create(@PathVariable String applicantUin,
                                                @PathVariable Long applicantRitualId,
                                                @RequestPart @Valid ApplicantChatContactVo contact,
                                                @RequestPart(value = "avatar", required = false) @SafeFile MultipartFile contactAvatarFile) throws Exception {
        log.debug("Handler for {}", "Create Applicant Chat Contact");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS)
                .body(applicantChatContactService.createChatContact(applicantUin, contact, applicantRitualId, contactAvatarFile)).build());
    }

    /**
     * Updates user defined chat contact
     *
     * @param contact applicant chat contact details
     * @return WsResponse of the persisted chat contact
     */
    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<WsResponse<?>> update(@PathVariable Long id,
                                                @RequestPart @Valid ApplicantChatContactVo contact,
                                                @RequestPart(value = "avatar", required = false) @SafeFile MultipartFile contactAvatarFile) throws Exception {
        ApplicantChatContactDto applicantChatContact = applicantChatContactService.findById(id);
        if (applicantChatContact == null) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND).referenceNumber(id.toString()).build()).build());
        }
        if (applicantChatContact.getSystemDefined()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND).referenceNumber(id.toString()).build()).build());
        }
        log.debug("Handler for {}", "Update User Defined Chat Contact");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS)
                .body(applicantChatContactService.updateUserDefinedChatContact(id, contact, contactAvatarFile)).build());
    }

    /**
     * Delete applicant chat contact.
     *
     * @param applicantUin the UIN of the applicant
     * @param contactUin   the UIN of the chat contact
     * @return WsResponse of number of selected rows
     */
    @PostMapping("/{applicantUin}/{contactUin}")
    public ResponseEntity<WsResponse<?>> deleteApplicantChatContact(@PathVariable String applicantUin, @PathVariable String contactUin) {
        log.info("Delete Applicant Chat Contact...");
        int numberOfAffectedRows = applicantChatContactService.deleteApplicantChatContact(applicantUin, contactUin);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body("number of affected rows : " + numberOfAffectedRows).build());
    }

    @GetMapping("find-one/{uin}/{applicantRitualId}/{applicantUin}")
    public ResponseEntity<WsResponse<?>> findOneApplicantByUinAndRitualId(@PathVariable String uin,
                                                                        @PathVariable Long applicantRitualId,
                                                                        @RequestParam(required = false) Boolean systemDefined,
                                                                        @PathVariable String applicantUin) {
        log.debug("find chat contact by uin {} and applicant ritual ID {}", uin, applicantRitualId);
        if(uin.equals(applicantUin)){
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND).referenceNumber(uin).build()).build());

        }
        List<ApplicantChatContactLiteDto> applicantChatContactList = applicantChatContactService.listApplicantChatContacts(uin, applicantRitualId, systemDefined);
        boolean isFound = applicantChatContactList.parallelStream().anyMatch(p -> p.getContactUin().equals(applicantUin));
        if (isFound)
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED).referenceNumber(uin).build()).build());

        Optional<ApplicantLiteDto> applicant = applicantLiteService.findByUin(applicantUin);
        if (!applicant.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND).referenceNumber(uin).build()).build());
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(applicant).build());

    }
}
