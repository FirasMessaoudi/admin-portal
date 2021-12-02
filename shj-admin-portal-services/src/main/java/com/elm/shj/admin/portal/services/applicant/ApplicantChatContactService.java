/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantChatContact;
import com.elm.shj.admin.portal.orm.repository.ApplicantChatContactRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service handling applicant chat contacts
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantChatContactService extends GenericService<JpaApplicantChatContact, ApplicantChatContactDto, Long> {

    private final ApplicantChatContactRepository applicantChatContactRepository;
    private final ApplicantLiteService applicantLiteService;
    private final ApplicantRitualService applicantRitualService;

    /**
     * List all chat contacts of a specific applicant.
     *
     * @param applicantUin the UIN of the applicant
     * @return the list of chat contacts
     */
    public List<ApplicantChatContactLiteDto> listApplicantChatContacts(String applicantUin, Long applicantRitualId, Boolean systemDefined) {
        List<ApplicantChatContactDto> contacts;
        if (systemDefined == null) {
            contacts = mapList(applicantChatContactRepository.findAllByUinAndRitualIdAndSystemDefined(applicantUin, applicantRitualId));
        } else if (systemDefined) {
            contacts = mapList(applicantChatContactRepository.findSystemDefined(applicantUin, applicantRitualId));
        } else {
            contacts = mapList(applicantChatContactRepository.findUserDefined(applicantUin));
        }
        return contacts.parallelStream().map(c -> {
            Optional<ApplicantLiteDto> applicantLite = applicantLiteService.findByUin(c.getContactUin());
            return ApplicantChatContactLiteDto.builder()
                    .id(c.getId())
                    .applicantUin(c.getApplicantUin())
                    .contactUin(c.getContactUin())
                    .contactFullNameAr(applicantLite.map(ApplicantLiteDto::getFullNameAr).orElse(null))
                    .contactFullNameEn(applicantLite.map(ApplicantLiteDto::getFullNameEn).orElse(null))
                    .typeId(c.getType().getId())
                    .alias(c.getAlias())
                    .systemDefined(c.getSystemDefined())
                    .staffTitleCode(c.getStaffTitleCode())
                    .relationshipCode(c.getRelationshipCode())
                    .mobileNumber(c.getMobileNumber())
                    .deleted(c.getDeleted())
                    .creationDate(c.getCreationDate())
                    .updateDate(c.getUpdateDate())
                    .avatar(c.getAvatar())
                    .build();
        }).collect(Collectors.toList());
    }

    @Transactional
    public int deleteApplicantChatContact(String applicantUin, String contactUin) {
        return applicantChatContactRepository.markDeleted(applicantUin, contactUin);
    }

    private ApplicantChatContactLiteDto mapChatContactToChatContactLite(ApplicantChatContactDto applicantChatContact) {
        return ApplicantChatContactLiteDto.builder()
                .id(applicantChatContact.getId())
                .applicantUin(applicantChatContact.getApplicantUin())
                .contactUin(applicantChatContact.getContactUin())
                .typeId(applicantChatContact.getType().getId())
                .alias(applicantChatContact.getAlias())
                .avatar(applicantChatContact.getAvatar())
                .systemDefined(applicantChatContact.getSystemDefined())
                .staffTitleCode(applicantChatContact.getStaffTitleCode())
                .mobileNumber(applicantChatContact.getMobileNumber())
                .deleted(applicantChatContact.getDeleted())
                .creationDate(applicantChatContact.getCreationDate())
                .updateDate(applicantChatContact.getUpdateDate())
                .build();
    }

    /**
     * Creates a new chat contact
     *
     * @param contact the chat contact to save
     * @return savedContact saved one
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantChatContactLiteDto createChatContact(String applicantUin, ApplicantChatContactVo contact, Long applicantRitualId, MultipartFile contactAvatarFile) throws IOException {
        ApplicantRitualDto applicantRitual = applicantRitualService.findById(applicantRitualId);

        ApplicantChatContactDto savedContact = ApplicantChatContactDto
                .builder()
                .applicantUin(applicantUin)
                .contactUin(contact.getUin())
                .alias(contact.getAlias())
                .mobileNumber(contact.getMobileNumber())
                .systemDefined(false)
                .deleted(false)
                .applicantRitual(applicantRitual)
                .type(ContactTypeLookupDto.builder().id(EChatContactType.APPLICANT.getId()).build())
                .build();

        // Update applicant chat contact avatar
        if (contactAvatarFile != null) {
            String encodedAvatarStr = Base64.getEncoder().encodeToString(contactAvatarFile.getBytes());
            savedContact.setAvatar(encodedAvatarStr);
        }

        savedContact = save(savedContact);
        ApplicantChatContactLiteDto chatContactLite = mapChatContactToChatContactLite(savedContact);
        chatContactLite.setContactFullNameAr(applicantRitual.getApplicant().getFullNameAr());
        chatContactLite.setContactFullNameEn(applicantRitual.getApplicant().getFullNameEn());

        return chatContactLite;
    }

    /**
     * finds a chat contact by its ID
     *
     * @param chatContactId the chat contact id to find
     * @return the found contact or <code>null</code>
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantChatContactDto findById(long chatContactId) {
        return findOne(chatContactId);
    }

    /**
     * Updates user defined chat contact
     *
     * @param contact the chat contact to save
     * @return savedContact saved one
     */
    public ApplicantChatContactLiteDto updateUserDefinedChatContact(Long id, ApplicantChatContactVo contact, MultipartFile contactAvatarFile) throws IOException {
        ApplicantChatContactDto applicantChatContact = findOne(id);
        applicantChatContact.setAlias(contact.getAlias());
        applicantChatContact.setMobileNumber(contact.getMobileNumber());
        // Update applicant chat contact avatar
        if (contactAvatarFile != null) {
            String encodedAvatarStr = Base64.getEncoder().encodeToString(contactAvatarFile.getBytes());
            applicantChatContact.setAvatar(encodedAvatarStr);
        }
        return mapChatContactToChatContactLite(applicantChatContact);
    }
}
