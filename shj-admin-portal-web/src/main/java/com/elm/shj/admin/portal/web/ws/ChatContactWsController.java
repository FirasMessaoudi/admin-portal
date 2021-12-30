/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.applicant.ApplicantChatContactService;
import com.elm.shj.admin.portal.services.applicant.ApplicantLiteService;
import com.elm.shj.admin.portal.services.applicant.ChatMessageService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final ApplicantLiteService applicantLiteService;
    private final CompanyStaffService companyStaffService;
    private final ChatMessageService chatMessageService;

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
     * Find applicant chat contact
     *
     * @param applicantUin applicant uin
     * @param contactUin   applicant chat contact uin
     * @return WsResponse of chat contact
     */
    @GetMapping(value = "/find/{applicantUin}/{contactUin}")
    public ResponseEntity<WsResponse<?>> findApplicantChatByApplicantUinAndContactUin(
            @PathVariable String applicantUin,
            @PathVariable String contactUin
    ) throws Exception {
        ApplicantChatContactDto applicantChatContactDto = applicantChatContactService.findApplicantChatContact(applicantUin, contactUin);
        if (applicantChatContactDto == null)
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND).referenceNumber(applicantUin).build()).build());
        log.debug("Handler for {}", "Create Applicant Chat Contact");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS)
                .body(applicantChatContactDto).build());
    }

    /**
     * Add new applicant chat contact
     *
     * @param contact applicant chat contact details
     * @return WsResponse of the persisted chat contact
     */
    @PostMapping(value = "/create/{applicantRitualId}")
    public ResponseEntity<WsResponse<?>> createApplicant(
            @PathVariable Long applicantRitualId,
            @RequestBody ApplicantChatContactLiteDto contact
    ) throws Exception {
        if (contact.getContactUin().equals(contact.getApplicantUin())) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND).referenceNumber(contact.getContactUin()).build()).build());
        }
        List<ApplicantChatContactLiteDto> applicantChatContactList = applicantChatContactService.listApplicantChatContacts(contact.getContactUin(), applicantRitualId, null);
        boolean isFound = applicantChatContactList.stream().anyMatch(p -> p.getContactUin().equals(contact.getApplicantUin()) && p.isAutoAdded());
        if (isFound)
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED).referenceNumber(contact.getContactUin()).build()).build());

        boolean isContactPresent = applicantLiteService.existsByUin(contact.getContactUin());
        if (!isContactPresent) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND).referenceNumber(contact.getContactUin()).build()).build());
        }
        if (applicantChatContactService.findApplicantChatContact(contact.getApplicantUin(), contact.getContactUin()) != null)
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_ALREADY_EXIST).referenceNumber(contact.getContactUin()).build()).build());
        log.debug("Handler for {}", "Create Applicant Chat Contact");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS)
                .body(applicantChatContactService.createApplicantChatContact(applicantRitualId, contact)).build());
    }

    /**
     * Add new applicant chat contact
     *
     * @param applicantUin      applicant (uin)
     * @param applicantRitualId
     * @param staffUin          staff chat contact (suin)
     * @return WsResponse of the persisted chat contact
     */
    @PostMapping(value = "/create-staff/{applicantUin}/{applicantRitualId}/{staffUin}")
    public ResponseEntity<WsResponse<?>> createStaff(@PathVariable String applicantUin,
                                                     @PathVariable Long applicantRitualId,
                                                     @PathVariable String staffUin) throws Exception {
        log.debug("Handler for {}", "Create Staff Chat Contact  for Applicant");
        Optional<CompanyStaffLiteDto> existStaff = companyStaffService.findBySuin(staffUin);
        if (existStaff.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS)
                    .body(applicantChatContactService.createStaffChatContact(applicantUin, applicantRitualId, existStaff)).build());
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND).referenceNumber(staffUin).build()).build());


    }

    /**
     * Updates user defined chat contact
     *
     * @param contact applicant chat contact details
     * @return WsResponse of the persisted chat contact
     */
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<WsResponse<?>> update(@PathVariable Long id,
                                                @RequestBody ApplicantChatContactLiteDto contact
    ) throws Exception {
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
                .body(applicantChatContactService.updateUserDefinedChatContact(id, contact)).build());
    }

    /**
     * Delete applicant chat contact.
     *
     * @param applicantUin the UIN of the applicant
     * @param contactUin   the UIN of the chat contact
     * @return WsResponse of number of selected rows
     */
    @PostMapping("/delete/{applicantUin}/{contactUin}")
    public ResponseEntity<WsResponse<?>> deleteApplicantChatContact(@PathVariable String applicantUin, @PathVariable String contactUin) {
        log.info("Delete Applicant Chat Contact...");
        int numberOfAffectedRows = applicantChatContactService.deleteApplicantChatContact(applicantUin, contactUin);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body("number of affected rows : " + numberOfAffectedRows).build());
    }

    @GetMapping("find-one/{uin}/{applicantRitualId}/{applicantUin}")
    public ResponseEntity<WsResponse<?>> findOneApplicantByUinAndRitualId(@PathVariable String uin,
                                                                          @PathVariable Long applicantRitualId,
                                                                          @PathVariable String applicantUin) {
        log.debug("find chat contact by uin {} and applicant ritual ID {}", uin, applicantRitualId);
        if (uin.equals(applicantUin)) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND).referenceNumber(uin).build()).build());
        }
        List<ApplicantChatContactLiteDto> applicantChatContactList = applicantChatContactService.listApplicantChatContacts(uin, applicantRitualId, null);
        boolean isFound = applicantChatContactList.stream().anyMatch(p -> p.getContactUin().equals(applicantUin) && p.isAutoAdded());
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


    @GetMapping("/find-staff/{suin}")
    public ResponseEntity<WsResponse<?>> findStaffContactByValidSuin(@PathVariable String suin) {
        Optional<CompanyStaffLiteDto> existStaff = companyStaffService.findBySuin(suin);
        if (existStaff.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS).body(existStaff).build());
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE)
                .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND).referenceNumber(suin).build()).build());


    }

    @GetMapping("/chat-list/{uin}")
    public ResponseEntity<WsResponse<?>> listChatContactsWithLatestMessage(@PathVariable String uin) {
        List<ChatMessageLiteDto> chatMessageLiteDtos = chatMessageService.listChatContactsWithLatestMessage(uin);

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS)
                .body(chatMessageLiteDtos).build());


    }

    @GetMapping("/messages/{contactId}")
    public ResponseEntity<WsResponse<?>> listChatMessagesBySenderIdOrReceiverId(@PathVariable long contactId) {
        List<ChatMessageDto> messagesList = chatMessageService.findChatMessagesBySenderIdOrReceiverId(contactId);

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS)
                .body(messagesList).build());


    }

    @PostMapping("/save-chat-message")
    public ResponseEntity<WsResponse<?>> saveChatMessage(@RequestBody ChatMessageDto chatMessage) {
        ChatMessageDto chatMessageDto = chatMessageService.saveMessage(chatMessage);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS)
                .body(chatMessageDto).build());
    }

}
