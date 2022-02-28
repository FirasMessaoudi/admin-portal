/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.orm.entity.ApplicantChatContactVo;
import com.elm.shj.admin.portal.orm.entity.ChatMessageVo;
import com.elm.shj.admin.portal.services.applicant.ApplicantChatContactService;
import com.elm.shj.admin.portal.services.applicant.ApplicantLiteService;
import com.elm.shj.admin.portal.services.applicant.ChatMessageService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.ApplicantChatContactDto;
import com.elm.shj.admin.portal.services.dto.ApplicantLiteDto;
import com.elm.shj.admin.portal.services.dto.ChatMessageDto;
import com.elm.shj.admin.portal.services.dto.CompanyStaffLiteDto;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final ApplicantLiteService applicantLiteService;
    private final CompanyStaffService companyStaffService;
    private final ChatMessageService chatMessageService;
    private final ApplicantRitualService applicantRitualService;

    /**
     * finds chat contacts by uin and applicant ritual ID
     *
     * @param uin               the UIN of the applicant
     * @param applicantRitualId the selected ritual ID
     * @param systemDefined
     * @return the found chat contacts or empty structure
     */
    @GetMapping("/{uin}/{applicantRitualId}")
    public ResponseEntity<WsResponse<?>> findChatContactsByUinAndRitualId(@PathVariable String uin,
                                                                          @PathVariable Long applicantRitualId,
                                                                          @RequestParam(required = false) Boolean systemDefined) {
        log.debug("List chat contacts by uin {} and applicant ritual ID {}", uin, applicantRitualId);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(applicantChatContactService.list(uin, applicantRitualId, systemDefined)).build());
    }

    /**
     * Find applicant chat contact
     *
     * @param applicantUin applicant uin
     * @param contactUin   applicant chat contact uin
     * @return WsResponse of chat contact
     */
    @GetMapping("/find/{applicantUin}/{contactUin}")
    public ResponseEntity<WsResponse<?>> findApplicantChatByApplicantUinAndContactUin(@PathVariable String applicantUin, @PathVariable String contactUin) {
        ApplicantChatContactDto applicantChatContactDto = applicantChatContactService.findApplicantChatContact(applicantUin, contactUin);
        if (applicantChatContactDto == null)
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode()).referenceNumber(applicantUin).build()).build());
        log.debug("Handler for {}", "Create Applicant Chat Contact");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicantChatContactDto).build());
    }

    /**
     * Create new applicant chat contact
     *
     * @param ritualId the selected ritual ID
     * @param contact  applicant chat contact details
     * @return WsResponse of the persisted chat contact
     */
    @PostMapping( "/create/{ritualId}")
    public ResponseEntity<WsResponse<?>> createApplicant(@PathVariable Long ritualId,
                                                         @RequestBody ApplicantChatContactDto contact) throws Exception {
        if (contact.isAutoAdded() == false) {
            if (contact.getAlias().length() > 50 || contact.getAlias().equals("") || null == contact.getAlias()) {
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                        .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode()).referenceNumber(contact.getAlias()).build()).build());
            }
            boolean isRitualPresent = applicantRitualService.findByRitualIdAndApplicantUin(ritualId, contact.getApplicantUin());
            if (!isRitualPresent) {
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                        .body(WsError.builder().error(WsError.EWsError.APPLICANT_RITUAL_NOT_FOUND.getCode()).referenceNumber(ritualId.toString()).build()).build());
            }
        }
        if (contact.getApplicantUin().equals("") || null == contact.getApplicantUin()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(contact.getApplicantUin()).build()).build());
        }
        if (contact.getContactUin().equals("") || null == contact.getContactUin()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode()).referenceNumber(contact.getContactUin()).build()).build());
        }

        if (contact.getContactUin().equals(contact.getApplicantUin())) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(contact.getContactUin()).build()).build());
        }
        List<ApplicantChatContactVo> applicantChatContactList = applicantChatContactService.list(contact.getApplicantUin(), ritualId, null);
        boolean isFound = applicantChatContactList.stream().anyMatch(p -> p.getContactUin().equals(contact.getContactUin()) && p.isAutoAdded() == false);
        if (isFound)
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED.getCode()).referenceNumber(contact.getContactUin()).build()).build());

        boolean isContactPresent = applicantLiteService.existsByUin(contact.getContactUin());
        if (!isContactPresent) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode()).referenceNumber(contact.getContactUin()).build()).build());
        }
        ApplicantChatContactDto chatContactDto = applicantChatContactService.findApplicantChatContact(contact.getApplicantUin(), contact.getContactUin());
        if (chatContactDto != null && chatContactDto.isAutoAdded() == false) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_ALREADY_EXIST.getCode()).referenceNumber(contact.getContactUin()).build()).build());
        } else if (chatContactDto != null && chatContactDto.isAutoAdded() == true) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(applicantChatContactService.updateUserDefinedChatContact(chatContactDto.getId(), contact)).build());
        }
        log.debug("Handler for {}", "Create Applicant Chat Contact");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(applicantChatContactService.createApplicantContact(ritualId, contact)).build());
    }

    /**
     * Add new applicant chat contact
     *
     * @param applicantUin the uin of the relationship owner
     * @param ritualId the selected ritual ID
     * @param suin the suin of the company staff
     * @return WsResponse of the persisted chat contact
     */
    @PostMapping("/create-staff/{applicantUin}/{ritualId}/{suin}")
    public ResponseEntity<WsResponse<?>> createStaff(@PathVariable String applicantUin, @PathVariable Long ritualId, @PathVariable String suin) {
        log.debug("Handler for {}", "Create Staff Chat Contact");
        //TODO
        Optional<CompanyStaffLiteDto> companyStaff = companyStaffService.findBySuin(suin);
        if (companyStaff.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(applicantChatContactService.createStaffContact(applicantUin, ritualId.equals(-1L) ? null : ritualId, companyStaff)).build());
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode()).referenceNumber(suin).build()).build());
    }

    /**
     * Updates user defined chat contact
     *
     * @param contact applicant chat contact details
     * @return WsResponse of the persisted chat contact
     */
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<WsResponse<?>> update(@PathVariable Long id,
                                                @RequestBody @Valid ApplicantChatContactDto contact) throws Exception {
        if (contact.getAlias().length() > 50 || contact.getAlias().equals("") || null == contact.getAlias()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode()).referenceNumber(contact.getAlias()).build()).build());
        }

        if(null != contact.getMobileNumber()){
            if(contact.getMobileNumber().length() < 5 || contact.getMobileNumber().length() > 16){
                return ResponseEntity.ok(
                        WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                                .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode()).referenceNumber(contact.getMobileNumber()).build()).build());
            }
        }
        ApplicantChatContactDto applicantChatContact = applicantChatContactService.findById(id);
        if (applicantChatContact == null) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode()).referenceNumber(id.toString()).build()).build());
        }
        if (applicantChatContact.isSystemDefined()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode()).referenceNumber(id.toString()).build()).build());
        }
        log.debug("Handler for {}", "Update User Defined Chat Contact");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
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
        log.debug("Delete Applicant Chat Contact...");
        int numberOfAffectedRows = applicantChatContactService.deleteApplicantChatContact(applicantUin, contactUin);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body("number of affected rows : " + numberOfAffectedRows).build());
    }

    //TODO Add missing slash to request URL; Replace 'find-one' with 'find'
    @GetMapping("find-one/{uin}/{applicantRitualId}/{applicantUin}")
    public ResponseEntity<WsResponse<?>> findOneApplicantByUinAndRitualId(@PathVariable String uin,
                                                                          @PathVariable Long applicantRitualId,
                                                                          @PathVariable String applicantUin) {
        log.debug("find chat contact by uin {} and applicant ritual ID {}", uin, applicantRitualId);
        if (uin.equals(applicantUin)) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(uin).build()).build());
        }
        List<ApplicantChatContactVo> applicantChatContactList = applicantChatContactService.list(uin, applicantRitualId, null);
        boolean isFound = applicantChatContactList.stream().anyMatch(p -> p.getContactUin().equals(applicantUin) && !p.isAutoAdded());
        if (isFound)
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED.getCode()).referenceNumber(uin).build()).build());

        Optional<ApplicantLiteDto> applicant = applicantLiteService.findByUin(applicantUin);
        if (!applicant.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(uin).build()).build());
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicant).build());
    }

    @GetMapping("validate-applicant/{suin}/{applicantUin}")
    public ResponseEntity<WsResponse<?>> validateApplicantByUin(@PathVariable String suin,
                                                                @PathVariable String applicantUin) {
        log.debug("find chat contact by uin {} and applicant ritual ID {}", suin);

        List<ApplicantChatContactVo> staffContacts = applicantChatContactService.listStaffContact(suin, null);
        boolean isFound = staffContacts.stream().anyMatch(p -> p.getContactUin().equals(applicantUin) && !p.isAutoAdded());
        if (isFound)
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED.getCode()).referenceNumber(suin).build()).build());

        Optional<ApplicantLiteDto> applicant = applicantLiteService.findByUin(applicantUin);
        if (!applicant.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(suin).build()).build());
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicant).build());
    }

    @GetMapping("/find-staff/{suin}")
    public ResponseEntity<WsResponse<?>> findStaffContactByValidSuin(@PathVariable String suin) {
        Optional<CompanyStaffLiteDto> companyStaff = companyStaffService.findBySuin(suin);
        if (companyStaff.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(companyStaff).build());
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode()).referenceNumber(suin).build()).build());
    }

    @GetMapping("/chat-list/{uin}")
    public ResponseEntity<WsResponse<?>> listChatContactsWithLatestMessage(@PathVariable String uin) {
        List<ChatMessageVo> chatMessageList = chatMessageService.listChatContactsWithLatestMessage(uin);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(chatMessageList).build());
    }

    @GetMapping("/messages/{contactId}")
    public ResponseEntity<WsResponse<?>> listChatMessagesBySenderIdOrReceiverId(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                                @RequestParam(value = "limit", defaultValue = "0") int limit,
                                                                                @PathVariable long contactId,
                                                                                @RequestParam(value = "time", defaultValue = "0") long time) {
        List<ChatMessageDto> messagesList = chatMessageService.findChatMessagesBySenderIdOrReceiverId(page, limit, contactId, time);

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(messagesList).build());
    }

    @PostMapping("/save-chat-message")
    public ResponseEntity<WsResponse<?>> saveChatMessage(@RequestBody ChatMessageDto chatMessage) {
        ChatMessageDto chatMessageDto = chatMessageService.saveMessage(chatMessage);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(chatMessageDto).build());
    }

    /**
     * finds chat contacts by suin
     *
     * @param suin          the suin of the staff
     * @param systemDefined
     * @return the found chat contacts or empty structure
     */
    @GetMapping("/staff/{suin}")
    public ResponseEntity<WsResponse<?>> findChatContactsBySuin(@PathVariable String suin,
                                                                @RequestParam(required = false) Boolean systemDefined) {
        log.debug("List chat contacts by suin {}", suin);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(applicantChatContactService.listStaffContact(suin, systemDefined)).build());
    }

    /**
     * Create new applicant chat contact for the staff
     *
     * @param contact applicant chat contact details
     * @return WsResponse of the persisted chat contact
     */
    @PostMapping("/create")
    public ResponseEntity<WsResponse<?>> createApplicantForStaff(@RequestBody ApplicantChatContactDto contact) throws Exception {
        if (contact.isAutoAdded() == false) {
            if (contact.getAlias().length() > 50 || contact.getAlias().equals("") || null == contact.getAlias()) {
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                        .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode()).referenceNumber(contact.getAlias()).build()).build());
            }
        }
        if (contact.getApplicantUin().equals("") || null == contact.getApplicantUin()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(contact.getApplicantUin()).build()).build());
        }
        if (contact.getContactUin().equals("") || null == contact.getContactUin()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode()).referenceNumber(contact.getContactUin()).build()).build());
        }

        if (contact.getContactUin().equals(contact.getApplicantUin())) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(contact.getContactUin()).build()).build());
        }
        List<ApplicantChatContactVo> applicantChatContactList = applicantChatContactService.listStaffContact(contact.getApplicantUin(), null);
        boolean isFound = applicantChatContactList.stream().anyMatch(p -> p.getContactUin().equals(contact.getContactUin()) && p.isAutoAdded() == false);
        if (isFound)
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED.getCode()).referenceNumber(contact.getContactUin()).build()).build());

        boolean isContactPresent = applicantLiteService.existsByUin(contact.getContactUin());
        if (!isContactPresent) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode()).referenceNumber(contact.getContactUin()).build()).build());
        }
        ApplicantChatContactDto chatContactDto = applicantChatContactService.findApplicantChatContact(contact.getApplicantUin(), contact.getContactUin());
        if (chatContactDto != null && chatContactDto.isAutoAdded() == false) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_ALREADY_EXIST.getCode()).referenceNumber(contact.getContactUin()).build()).build());
        } else if (chatContactDto != null && chatContactDto.isAutoAdded() == true) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(applicantChatContactService.updateUserDefinedChatContact(chatContactDto.getId(), contact)).build());
        }
        log.debug("Handler for {}", "Create Applicant Chat Contact");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(applicantChatContactService.createApplicantContact(null, contact)).build());
    }


    @PostMapping("/v2/save-chat-message")
    public ResponseEntity<WsResponse<?>> saveChatMessageNew(@RequestBody ChatMessageDto chatMessage) {
        log.debug("chatMessage => {}",chatMessage);
        ApplicantChatContactDto senderChatContact = chatMessage.getSender();
        ApplicantChatContactDto senderInReceiverContact = applicantChatContactService.findApplicantChatContact(senderChatContact.getContactUin(), senderChatContact.getApplicantUin());
        if(senderInReceiverContact == null){
             senderInReceiverContact = applicantChatContactService.createAutoAddedChatContact(senderChatContact.getContactUin(), senderChatContact.getApplicantUin());

        }
        chatMessage.setReceiver(senderInReceiverContact);
        ChatMessageDto chatMessageDto = chatMessageService.saveMessage(chatMessage);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(chatMessageDto).build());
    }

    @PutMapping("/read-chat-messages/{chatContactId}")
    public ResponseEntity<WsResponse<?>> markChatMessageAsRead(@PathVariable("chatContactId") long chatContactId) {
        chatMessageService.markMessagesAsRead(chatContactId);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).build());
    }
}
