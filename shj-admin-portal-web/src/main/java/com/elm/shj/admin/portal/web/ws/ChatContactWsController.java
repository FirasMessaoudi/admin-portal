/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.dcc.foundation.commons.validation.SafeFile;
import com.elm.shj.admin.portal.services.applicant.ApplicantChatContactService;
import com.elm.shj.admin.portal.services.dto.ApplicantChatContactLiteDto;
import com.elm.shj.admin.portal.services.dto.ApplicantChatContactVo;
import com.elm.shj.admin.portal.services.dto.ApplicantIncidentDto;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualDto;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

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
}
