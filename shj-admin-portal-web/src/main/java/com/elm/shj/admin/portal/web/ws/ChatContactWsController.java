/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.orm.entity.ChatContactVo;
import com.elm.shj.admin.portal.orm.entity.ChatMessageVo;
import com.elm.shj.admin.portal.services.applicant.ApplicantLiteService;
import com.elm.shj.admin.portal.services.applicant.ChatContactService;
import com.elm.shj.admin.portal.services.applicant.ChatMessageService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.ApplicantLiteDto;
import com.elm.shj.admin.portal.services.dto.ChatContactDto;
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


    private final ChatContactService chatContactService;
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
        log.info("Start findChatContactsByUinAndRitualId uin: {}, applicantRitualId: {}, systemDefined:{}", uin, applicantRitualId, systemDefined);
        List<ChatContactVo> list = chatContactService.list(uin, applicantRitualId, systemDefined);
        log.info("Finish findChatContactsByUinAndRitualId {}, list.size:{}","SUCCESS", list.size());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(list).build());
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
        log.info("Start findApplicantChatByApplicantUinAndContactUin applicantUin: {}, contactUin: {}", applicantUin, contactUin);
        ChatContactDto chatContactDto = chatContactService.findApplicantChatContact(applicantUin, contactUin);
        if (chatContactDto == null) {
            log.info("Finish findApplicantChatByApplicantUinAndContactUin {},{}", "FAILURE", WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode()).referenceNumber(applicantUin).build()).build());
        }
        log.info("Finish findApplicantChatByApplicantUinAndContactUin {}, chatContactDtoId {}", "SUCCESS", chatContactDto.getId());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(chatContactDto).build());
    }

    /**
     * Create new applicant chat contact
     *
     * @param ritualId the selected ritual ID
     * @param contact  applicant chat contact details
     * @return WsResponse of the persisted chat contact
     */
    @PostMapping("/create/{ritualId}")
    public ResponseEntity<WsResponse<?>> createApplicant(@PathVariable Long ritualId,
                                                         @RequestBody ChatContactDto contact) {
        log.info("Start createApplicant ritualId: {} DigitalId: {}, ContactDigitalId: {}", ritualId, contact.getDigitalId(), contact.getContactDigitalId());
        if (contact.isAutoAdded() == false) {
            if (contact.getAlias().length() > 50 || contact.getAlias().equals("") || null == contact.getAlias()) {
                log.info("Finish createApplicant {},{}", "FAILURE", WsError.EWsError.INVALID_INPUT.getCode());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                        .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode()).referenceNumber(contact.getAlias()).build()).build());
            }
            boolean isRitualPresent = applicantRitualService.findByRitualIdAndApplicantUin(ritualId, contact.getDigitalId());
            if (!isRitualPresent) {
                log.info("Finish createApplicant {},{}", "FAILURE", WsError.EWsError.APPLICANT_RITUAL_NOT_FOUND.getCode());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                        .body(WsError.builder().error(WsError.EWsError.APPLICANT_RITUAL_NOT_FOUND.getCode()).referenceNumber(ritualId.toString()).build()).build());
            }
        }
        if (contact.getDigitalId().equals("") || null == contact.getDigitalId()) {
            log.info("Finish createApplicant {},{}", "FAILURE", WsError.EWsError.APPLICANT_NOT_FOUND.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(contact.getDigitalId()).build()).build());
        }
        if (contact.getContactDigitalId().equals("") || null == contact.getContactDigitalId()) {
            log.info("Finish createApplicant {},{}", "FAILURE", WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode()).referenceNumber(contact.getContactDigitalId()).build()).build());
        }

        if (contact.getContactDigitalId().equals(contact.getDigitalId())) {
            log.info("Finish createApplicant {},{}", "FAILURE", WsError.EWsError.APPLICANT_NOT_FOUND.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(contact.getContactDigitalId()).build()).build());
        }
        List<ChatContactVo> applicantChatContactList = chatContactService.list(contact.getDigitalId(), ritualId, null);
        boolean isFound = applicantChatContactList.stream().anyMatch(p -> p.getContactDigitalId().equals(contact.getContactDigitalId()) && p.isAutoAdded() == false);
        if (isFound) {
            log.info("Finish createApplicant {},{}", "FAILURE", WsError.EWsError.APPLICANT_NOT_MATCHED.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED.getCode()).referenceNumber(contact.getContactDigitalId()).build()).build());
        }
        boolean isContactPresent = applicantLiteService.existsByUin(contact.getContactDigitalId());
        if (!isContactPresent) {
            log.info("Finish createApplicant {},{}", "FAILURE", WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode()).referenceNumber(contact.getContactDigitalId()).build()).build());
        }
        ChatContactDto chatContactDto = chatContactService.findApplicantChatContact(contact.getDigitalId(), contact.getContactDigitalId());
        if (chatContactDto != null && chatContactDto.isAutoAdded() == false) {
            if (!chatContactDto.isDeleted()) {
                log.info("Finish createApplicant {},{}", "FAILURE", WsError.EWsError.APPLICANT_CHAT_CONTACT_ALREADY_EXIST.getCode());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                        .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_ALREADY_EXIST.getCode()).referenceNumber(contact.getContactDigitalId()).build()).build());
            } else {
                ChatContactDto updatedChatContactDto = chatContactService.updateUserDefinedChatContact(chatContactDto.getId(), contact, false);
                log.info("Finish createApplicant {}, updatedChatContactDtoId: {}", "SUCCESS", updatedChatContactDto.getId());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                        .body(updatedChatContactDto).build());
            }
        } else if (chatContactDto != null && chatContactDto.isAutoAdded() == true) {
            ChatContactDto updatedUserDefinedChatContact = chatContactService.updateUserDefinedChatContact(chatContactDto.getId(), contact, chatContactDto.isDeleted());
            log.info("Finish createApplicant {}, updatedUserDefinedChatContactId: {}", "SUCCESS", updatedUserDefinedChatContact.getId());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(updatedUserDefinedChatContact).build());
        }
        ChatContactVo applicantContact = chatContactService.createApplicantContact(ritualId, contact);
        log.info("Finish createApplicant {}, applicantContactId: {}", "SUCCESS", applicantContact.getId());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(applicantContact).build());
    }

    /**
     * Add new applicant chat contact
     *
     * @param digitalId the uin of the relationship owner
     * @param ritualId  the selected ritual ID
     * @param suin      the suin of the company staff
     * @return WsResponse of the persisted chat contact
     */
    @PostMapping("/create-staff/{digitalId}/{ritualId}/{suin}")
    public ResponseEntity<WsResponse<?>> createStaff(@PathVariable String digitalId, @PathVariable Long ritualId, @PathVariable String suin) {
        log.info("Start createStaff digitalId: {}, ritualId: {}, suin: {}", digitalId, ritualId, suin);
        Optional<CompanyStaffLiteDto> companyStaff = companyStaffService.findBySuin(suin);
        if (companyStaff.isPresent()) {
            ChatContactVo staffContact = chatContactService.createStaffContact(digitalId, ritualId.equals(-1L) ? null : ritualId, companyStaff);
            log.info("Finish createStaff {}, staffContactId: {}", "SUCCESS", staffContact.getId());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(staffContact).build());
        }
        log.info("Finish createStaff {},{}", "FAILURE", WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode());
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
    public ResponseEntity<WsResponse<?>> updateContact(@PathVariable Long id,
                                                       @RequestBody @Valid ChatContactDto contact) {
        log.info("Start updateContact id: {}, contactId: {}", id, contact.getId());
        if (contact.getAlias().length() > 50 || contact.getAlias().equals("") || null == contact.getAlias()) {
            log.info("Finish updateContact {},{}", "FAILURE", WsError.EWsError.INVALID_INPUT.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode()).referenceNumber(contact.getAlias()).build()).build());
        }

        if (null != contact.getMobileNumber()) {
            if (contact.getMobileNumber().length() < 5 || contact.getMobileNumber().length() > 16) {
                log.info("Finish updateContact {},{}", "FAILURE", WsError.EWsError.INVALID_INPUT.getCode());
                return ResponseEntity.ok(
                        WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                                .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode()).referenceNumber(contact.getMobileNumber()).build()).build());
            }
        }
        ChatContactDto applicantChatContact = chatContactService.findById(id);
        if (applicantChatContact == null) {
            log.info("Finish updateContact {},{}", "FAILURE", WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode()).referenceNumber(id.toString()).build()).build());
        }
        if (applicantChatContact.isSystemDefined()) {
            log.info("Finish updateContact {},{}", "FAILURE", WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode()).referenceNumber(id.toString()).build()).build());
        }

        if (applicantChatContact.isAutoAdded()) {
            contact.setAutoAdded(false);
        }
        ChatContactDto chatContactDto = chatContactService.updateUserDefinedChatContact(id, contact, false);
        log.info("Finish updateContact {}, chatContactDtoId: {}", "SUCCESS", chatContactDto.getId());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(chatContactDto).build());
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
        log.info("Start deleteApplicantChatContact applicantUin: {}, contactUin: {}", applicantUin, contactUin);
        int numberOfAffectedRows = chatContactService.deleteApplicantChatContact(applicantUin, contactUin);
        log.info("Finish deleteApplicantChatContact {},  numberOfAffectedRows: {}", "SUCCESS",  numberOfAffectedRows);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body("number of affected rows : " + numberOfAffectedRows).build());
    }

    //TODO Add missing slash to request URL; Replace 'find-one' with 'find'
    @GetMapping("find-one/{uin}/{applicantRitualId}/{applicantUin}")
    public ResponseEntity<WsResponse<?>> findOneApplicantByUinAndRitualId(@PathVariable String uin,
                                                                          @PathVariable Long applicantRitualId,
                                                                          @PathVariable String applicantUin) {
        log.info("Start findOneApplicantByUinAndRitualId uin: {}, applicantRitualId: {}, applicantUin: {}", uin, applicantRitualId, applicantUin);
        if (uin.equals(applicantUin)) {
            log.info("Finish findOneApplicantByUinAndRitualId {}, {}", "FAILURE", WsError.EWsError.APPLICANT_NOT_FOUND.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(uin).build()).build());
        }
        List<ChatContactVo> applicantChatContactList = chatContactService.list(uin, applicantRitualId, null);
        boolean isFound = applicantChatContactList.stream().anyMatch(p -> p.getContactDigitalId().equals(applicantUin) && !p.isAutoAdded());
        if (isFound) {
            log.info("Finish findOneApplicantByUinAndRitualId {}, {}", "FAILURE", WsError.EWsError.APPLICANT_NOT_MATCHED.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED.getCode()).referenceNumber(uin).build()).build());
        }
        Optional<ApplicantLiteDto> applicant = applicantLiteService.findByUin(applicantUin);
        if (!applicant.isPresent()) {
            log.info("Finish findOneApplicantByUinAndRitualId {}, {}", "FAILURE", WsError.EWsError.APPLICANT_NOT_FOUND.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(uin).build()).build());
        }
        log.info("Finish findOneApplicantByUinAndRitualId {}, applicantId: {}", "SUCCESS", applicant.get().getId());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicant).build());
    }

    @GetMapping("validate-applicant/{suin}/{applicantUin}")
    public ResponseEntity<WsResponse<?>> validateApplicantByUin(@PathVariable String suin,
                                                                @PathVariable String applicantUin) {
        log.info("Start validateApplicantByUin suin: {}, applicantUin: {}", suin, applicantUin);

        List<ChatContactVo> staffContacts = chatContactService.listStaffContact(suin, null);
        boolean isFound = staffContacts.stream().anyMatch(p -> p.getContactDigitalId().equals(applicantUin) && !p.isAutoAdded());
        if (isFound) {
            log.info("Finish validateApplicantByUin {}, {}", "FAILURE", WsError.EWsError.APPLICANT_NOT_MATCHED.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED.getCode()).referenceNumber(suin).build()).build());
        }
        Optional<ApplicantLiteDto> applicant = applicantLiteService.findByUin(applicantUin);
        if (!applicant.isPresent()) {
            log.info("Finish validateApplicantByUin {}, {}", "FAILURE", WsError.EWsError.APPLICANT_NOT_FOUND.getCode());

            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(suin).build()).build());
        }
        log.info("Finish validateApplicantByUin {}, applicantId: {}", "SUCCESS", applicant.get().getId());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicant).build());
    }

    @GetMapping("/find-staff/{suin}")
    public ResponseEntity<WsResponse<?>> findStaffContactByValidSuin(@PathVariable String suin) {
        log.info("Start findStaffContactByValidSuin suin: {}", suin);
        Optional<CompanyStaffLiteDto> companyStaff = companyStaffService.findBySuin(suin);
        if (companyStaff.isPresent()) {
            log.info("Finish findStaffContactByValidSuin {}, companyStaffId: {}", "SUCCESS", companyStaff.get().getId());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(companyStaff).build());
        }
        log.info("Finish findStaffContactByValidSuin {}, {}", "FAILURE", WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode()).referenceNumber(suin).build()).build());
    }

    @GetMapping("/chat-list/{uin}")
    public ResponseEntity<WsResponse<?>> listChatContactsWithLatestMessage(@PathVariable String uin) {
        log.info("Start listChatContactsWithLatestMessage uin: {}", uin);
        List<ChatMessageVo> chatMessageList = chatMessageService.listChatContactsWithLatestMessage(uin);
        log.info("Finish listChatContactsWithLatestMessage {}, chatMessageListSize: {}", "SUCCESS", chatMessageList.size());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(chatMessageList).build());
    }

    @GetMapping("/messages/{contactId}")
    public ResponseEntity<WsResponse<?>> listChatMessagesBySenderIdOrReceiverId(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                                @RequestParam(value = "limit", defaultValue = "0") int limit,
                                                                                @PathVariable long contactId,
                                                                                @RequestParam(value = "time", defaultValue = "0") long time) {
        log.info("Start listChatMessagesBySenderIdOrReceiverId page: {},limit: {},contactId: {},time: {}", page, limit, contactId, time);
        List<ChatMessageDto> messagesList = chatMessageService.findChatMessagesBySenderIdOrReceiverId(page, limit, contactId, time);
        log.info("Finish listChatMessagesBySenderIdOrReceiverId {},messagesListSize: {}", "SUCCESS", messagesList.size());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(messagesList).build());
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
        log.info("Start findChatContactsBySuin suin: {}, systemDefined: {}", suin, systemDefined);
        List<ChatContactVo> chatContactVos = chatContactService.listStaffContact(suin, systemDefined);
        log.info("Finish findChatContactsBySuin {}, chatContactVosSize: {}", "SUCCESS", chatContactVos.size());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(chatContactVos).build());
    }

    /**
     * Create new applicant chat contact for the staff
     *
     * @param contact applicant chat contact details
     * @return WsResponse of the persisted chat contact
     */
    @PostMapping("/create")
    public ResponseEntity<WsResponse<?>> createApplicantForStaff(@RequestBody ChatContactDto contact) throws Exception {
        log.info("Start createApplicantForStaff DigitalId: {}, ContactDigitalId: {}", contact.getDigitalId(), contact.getContactDigitalId());
        if (contact.isAutoAdded() == false) {
            if (contact.getAlias().length() > 50 || contact.getAlias().equals("") || null == contact.getAlias()) {
                log.info("Finish createApplicantForStaff {},{}", "FAILURE", WsError.EWsError.INVALID_INPUT.getCode());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                        .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode()).referenceNumber(contact.getAlias()).build()).build());
            }
        }
        if (contact.getDigitalId().equals("") || null == contact.getDigitalId()) {
            log.info("Finish createApplicantForStaff {},{}", "FAILURE", WsError.EWsError.APPLICANT_NOT_FOUND.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(contact.getDigitalId()).build()).build());
        }
        if (contact.getContactDigitalId().equals("") || null == contact.getContactDigitalId()) {
            log.info("Finish createApplicantForStaff {},{}", "FAILURE", WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode()).referenceNumber(contact.getContactDigitalId()).build()).build());
        }

        if (contact.getContactDigitalId().equals(contact.getDigitalId())) {
            log.info("Finish createApplicantForStaff {},{}", "FAILURE", WsError.EWsError.APPLICANT_NOT_FOUND.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_FOUND.getCode()).referenceNumber(contact.getContactDigitalId()).build()).build());
        }
        List<ChatContactVo> applicantChatContactList = chatContactService.listStaffContact(contact.getDigitalId(), null);
        boolean isFound = applicantChatContactList.stream().anyMatch(p -> p.getContactDigitalId().equals(contact.getContactDigitalId()) && p.isAutoAdded() == false);
        if (isFound) {
            log.info("Finish createApplicantForStaff {},{}", "FAILURE", WsError.EWsError.APPLICANT_NOT_MATCHED.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_NOT_MATCHED.getCode()).referenceNumber(contact.getContactDigitalId()).build()).build());
        }
        boolean isContactPresent = applicantLiteService.existsByUin(contact.getContactDigitalId());
        if (!isContactPresent) {
            log.info("Finish createApplicantForStaff {},{}", "FAILURE", WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_NOT_FOUND.getCode()).referenceNumber(contact.getContactDigitalId()).build()).build());
        }
        ChatContactDto chatContactDto = chatContactService.findApplicantChatContact(contact.getDigitalId(), contact.getContactDigitalId());
        if (chatContactDto != null && chatContactDto.isAutoAdded() == false) {
            if (!chatContactDto.isDeleted()) {
                log.info("Finish createApplicantForStaff {},{}", "FAILURE", WsError.EWsError.APPLICANT_CHAT_CONTACT_ALREADY_EXIST.getCode());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                        .body(WsError.builder().error(WsError.EWsError.APPLICANT_CHAT_CONTACT_ALREADY_EXIST.getCode()).referenceNumber(contact.getContactDigitalId()).build()).build());
            } else {
                ChatContactDto updatedChatContactDto = chatContactService.updateUserDefinedChatContact(chatContactDto.getId(), contact, false);
                log.info("Finish createApplicantForStaff {}, updatedChatContactDtoId: {}", "SUCCESS", updatedChatContactDto.getId());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                        .body(updatedChatContactDto).build());
            }
        } else if (chatContactDto != null && chatContactDto.isAutoAdded() == true) {
            ChatContactDto updatedUserDefinedChatContact = chatContactService.updateUserDefinedChatContact(chatContactDto.getId(), contact, chatContactDto.isDeleted());
            log.info("Finish createApplicantForStaff {},updatedUserDefinedChatContactId: {}", "SUCCESS", updatedUserDefinedChatContact.getId());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(updatedUserDefinedChatContact).build());
        }
        ChatContactVo applicantContact = chatContactService.createApplicantContact(null, contact);
        log.info("Finish createApplicantForStaff {}, applicantContactId: {}", "SUCCESS", applicantContact.getId());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(applicantContact).build());
    }


    @PostMapping("/save-chat-message")
    public ResponseEntity<WsResponse<?>> saveChatMessageNew(@RequestBody ChatMessageDto chatMessage) {
        log.info("Start saveChatMessageNew {} ", chatMessage.getText());

        ChatContactDto senderChatContact = chatMessage.getSender();
        if (chatMessage.getSender().getId() == 0) {
            log.debug("saveChatMessageNew createAutoAddedChatContact receiverInSenderContact DigitalId: {}, ContactDigitalId: {} ", senderChatContact.getDigitalId(), senderChatContact.getContactDigitalId());
            ChatContactDto receiverInSenderContact = chatContactService.createAutoAddedChatContact(senderChatContact.getDigitalId(), senderChatContact.getContactDigitalId(),senderChatContact.getAvatar());
            chatMessage.setSender(receiverInSenderContact);
        }

        ChatContactDto senderInReceiverContact = chatContactService.findApplicantChatContact(senderChatContact.getContactDigitalId(), senderChatContact.getDigitalId());
        if (senderInReceiverContact == null) {
            log.debug("saveChatMessageNew createAutoAddedChatContact senderInReceiverContact DigitalId: {}, ContactDigitalId: {} ", senderChatContact.getDigitalId(), senderChatContact.getContactDigitalId());
            senderInReceiverContact = chatContactService.createAutoAddedChatContact(senderChatContact.getContactDigitalId(), senderChatContact.getDigitalId(), null);

        }
        chatMessage.setReceiver(senderInReceiverContact);
        log.debug("saveChatMessageNew saveMessage senderId: {}, receiverId: {} ", chatMessage.getSender().getId(), chatMessage.getReceiver().getId());
        ChatMessageDto chatMessageDto = chatMessageService.saveMessage(chatMessage);
        log.info("Finish saveChatMessageNew {}, chatMessageDtoId: {} ", "SUCCESS", chatMessageDto.getId());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(chatMessageDto).build());
    }

    @PutMapping("/read-chat-messages/{chatContactId}")
    public ResponseEntity<WsResponse<?>> markChatMessageAsRead(@PathVariable("chatContactId") long chatContactId) {
        log.info("Start markChatMessageAsRead chatContactId: {} ", chatContactId);
        chatMessageService.markMessagesAsRead(chatContactId);
        log.info("Finish markChatMessageAsRead {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).build());
    }
}
