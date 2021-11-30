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
                    .mobileNumber(c.getMobileNumber())
                    .deleted(c.getDeleted())
                    .creationDate(c.getCreationDate())
                    .updateDate(c.getUpdateDate())
                    .build();
        }).collect(Collectors.toList());
    }

    @Transactional
    public int deleteApplicantChatContact(String applicantUin,String contactUin) {
        return applicantChatContactRepository.markDeleted(applicantUin,contactUin);
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
        return ApplicantChatContactLiteDto.builder()
                .id(savedContact.getId())
                .applicantUin(savedContact.getApplicantUin())
                .contactUin(savedContact.getContactUin())
                .contactFullNameAr(applicantRitual.getApplicant().getFullNameAr())
                .contactFullNameEn((applicantRitual.getApplicant().getFullNameEn()))
                .typeId(savedContact.getType().getId())
                .alias(savedContact.getAlias())
                .avatar(savedContact.getAvatar())
                .systemDefined(savedContact.getSystemDefined())
                .staffTitleCode(savedContact.getStaffTitleCode())
                .mobileNumber(savedContact.getMobileNumber())
                .deleted(savedContact.getDeleted())
                .creationDate(savedContact.getCreationDate())
                .updateDate(savedContact.getUpdateDate())
                .build();
    }
}
