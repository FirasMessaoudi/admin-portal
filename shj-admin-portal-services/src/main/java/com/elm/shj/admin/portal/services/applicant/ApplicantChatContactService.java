/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantChatContact;
import com.elm.shj.admin.portal.orm.repository.ApplicantChatContactRepository;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
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
    private final CompanyStaffService companyStaffService;

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
            ApplicantChatContactLiteDto contact = mapChatContactToChatContactLite(c);
            if (EChatContactType.APPLICANT.getId() == c.getType().getId()) {
                Optional<ApplicantLiteDto> applicantLite = applicantLiteService.findByUin(c.getContactUin());
                contact.setContactFullNameAr(applicantLite.map(ApplicantLiteDto::getFullNameAr).orElse(null));
                contact.setContactFullNameEn(applicantLite.map(ApplicantLiteDto::getFullNameEn).orElse(null));
            } else if (EChatContactType.STAFF.getId() == c.getType().getId()) {
                Optional<CompanyStaffLiteDto> staffLite = companyStaffService.findBySuin(c.getContactUin());
                contact.setContactFullNameAr(staffLite.map(CompanyStaffLiteDto::getFullNameAr).orElse(null));
                contact.setContactFullNameEn(staffLite.map(CompanyStaffLiteDto::getFullNameEn).orElse(null));
            }
            return contact;
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
                .relationshipCode(applicantChatContact.getRelationshipCode())
                .staffTitleCode(applicantChatContact.getStaffTitleCode())
                .avatar(applicantChatContact.getAvatar())
                .systemDefined(applicantChatContact.getSystemDefined())
                .staffTitleCode(applicantChatContact.getStaffTitleCode())
                .mobileNumber(applicantChatContact.getMobileNumber())
                .countryPhonePrefix(applicantChatContact.getCountryPhonePrefix())
                .countryCode(applicantChatContact.getCountryCode())
                .deleted(applicantChatContact.getDeleted())
                .creationDate(applicantChatContact.getCreationDate())
                .updateDate(applicantChatContact.getUpdateDate())
                .autoAdded(applicantChatContact.getAutoAdded())
                .build();
    }

    /**
     * Creates a new chat contact
     *
     * @param contact the chat contact to save
     * @return savedContact saved one
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantChatContactLiteDto createApplicantChatContact(Long applicantRitualId, ApplicantChatContactLiteDto contact) throws IOException {
        ApplicantRitualDto applicantRitual = applicantRitualService.findById(applicantRitualId);

        ApplicantChatContactDto savedContact = ApplicantChatContactDto
                .builder()
                .applicantUin(contact.getApplicantUin())
                .contactUin(contact.getContactUin())
                .alias(contact.getAlias())
                .mobileNumber(contact.getMobileNumber())
                .countryPhonePrefix(contact.getCountryPhonePrefix())
                .countryCode(contact.getCountryCode())
                .systemDefined(false)
                .deleted(false)
                .avatar(contact.getAvatar())
                .applicantRitual(applicantRitual)
                .autoAdded(false)
                .type(ContactTypeLookupDto.builder().id(EChatContactType.APPLICANT.getId()).build())
                .build();

        savedContact = save(savedContact);
        ApplicantChatContactLiteDto chatContactLite = mapChatContactToChatContactLite(savedContact);
        chatContactLite.setContactFullNameAr(applicantRitual.getApplicant().getFullNameAr());
        chatContactLite.setContactFullNameEn(applicantRitual.getApplicant().getFullNameEn());

        return chatContactLite;
    }

    /**
     * Creates a new staff chat contact
     *
     * @param applicantUin      the chat contact uin
     * @param applicantRitualId the applicant ritual id
     * @param companyStaff      the company staff
     * @return savedContact saved one
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantChatContactLiteDto createStaffChatContact(String applicantUin, Long applicantRitualId, Optional<CompanyStaffLiteDto> companyStaff) {
        ApplicantRitualDto applicantRitual = applicantRitualService.findById(applicantRitualId);
        ApplicantChatContactDto savedContact = ApplicantChatContactDto
                .builder()
                .applicantUin(applicantUin)
                .contactUin(companyStaff.get().getSuin())
                .mobileNumber(companyStaff.map(CompanyStaffLiteDto::getMobileNumber).orElse(null))
                .countryCode(companyStaff.map(CompanyStaffLiteDto::getNationalityCode).orElse(null))
                .systemDefined(false)
                .deleted(false)
                .applicantRitual(applicantRitual)
                .avatar(companyStaff.map(CompanyStaffLiteDto::getPhoto).orElse(null))
                .staffTitleCode(companyStaff.map(CompanyStaffLiteDto::getTitleCode).orElse(null))
                .type(ContactTypeLookupDto.builder().id(EChatContactType.STAFF.getId()).build())
                .autoAdded(false)
                .build();

        savedContact = save(savedContact);
        ApplicantChatContactLiteDto chatContactLite = mapChatContactToChatContactLite(savedContact);
        chatContactLite.setContactFullNameAr(companyStaff.map(CompanyStaffLiteDto::getFullNameAr).orElse(null));
        chatContactLite.setContactFullNameEn(companyStaff.map(CompanyStaffLiteDto::getFullNameEn).orElse(null));
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
    public ApplicantChatContactLiteDto updateUserDefinedChatContact(Long id, ApplicantChatContactLiteDto contact) throws IOException {
        ApplicantChatContactDto applicantChatContact = findOne(id);
        applicantChatContact.setAlias(contact.getAlias());
        applicantChatContact.setMobileNumber(contact.getMobileNumber());
        applicantChatContact.setCountryPhonePrefix(contact.getCountryPhonePrefix());
        applicantChatContact.setCountryCode(contact.getCountryCode());
        applicantChatContact.setAvatar(contact.getAvatar());
        applicantChatContact.setAutoAdded(false);
        ApplicantChatContactDto savedContact = save(applicantChatContact);
        return mapChatContactToChatContactLite(savedContact);
    }
}
