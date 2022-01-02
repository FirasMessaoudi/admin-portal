/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.ApplicantChatContactVo;
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

import java.util.List;
import java.util.Optional;

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

    /**
     * List all chat contacts of a specific applicant.
     *
     * @param applicantUin  the UIN of the applicant
     * @param ritualId      the selected ritual ID
     * @param systemDefined a boolean flag to define whether the chat contact is added by system or not
     * @return the list of chat contacts
     */
    public List<ApplicantChatContactVo> list(String applicantUin, Long ritualId, Boolean systemDefined) {
        if (systemDefined == null) {
            return ((ApplicantChatContactRepository) getRepository()).list(applicantUin, ritualId);
        } else if (systemDefined) {
            return ((ApplicantChatContactRepository) getRepository()).findBySystemDefinedTrue(applicantUin, ritualId);
        } else {
            return ((ApplicantChatContactRepository) getRepository()).findBySystemDefinedFalse(applicantUin);
        }
    }

    @Transactional
    public int deleteApplicantChatContact(String applicantUin, String contactUin) {
        return applicantChatContactRepository.markDeleted(applicantUin, contactUin);
    }

    public ApplicantChatContactDto findApplicantChatContact(String applicantUin, String contactUin) {
        Optional<JpaApplicantChatContact> applicantChatContact = applicantChatContactRepository.findByApplicantUinAndContactUin(applicantUin, contactUin);
        return applicantChatContact.map(chatContact -> getMapper().fromEntity(chatContact, mappingContext)).orElse(null);
    }

    /**
     * Creates a new chat contact
     *
     * @param ritualId the selected ritual ID
     * @param contact  the chat contact to save
     * @return the value object of the saved applicant contact chat
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantChatContactVo createApplicantContact(Long ritualId, ApplicantChatContactDto contact) {
        ApplicantChatContactDto contactBuilder = ApplicantChatContactDto.builder()
                .applicantUin(contact.getApplicantUin())
                .contactUin(contact.getContactUin())
                .alias(contact.getAlias())
                .mobileNumber(contact.getMobileNumber())
                .countryPhonePrefix(contact.getCountryPhonePrefix())
                .countryCode(contact.getCountryCode())
                .systemDefined(false)
                .avatar(contact.getAvatar())
                .applicantRitualId(ritualId)
                .type(ContactTypeLookupDto.builder().id(EChatContactType.APPLICANT.getId()).build())
                .build();
        ApplicantChatContactDto savedContact = save(contactBuilder);
        return ((ApplicantChatContactRepository) getRepository()).findApplicantContactVoById(savedContact.getId()).orElse(null);
    }

    /**
     * Creates a new staff chat contact
     *
     * @param applicantUin the uin of the relationship owner
     * @param ritualId     the selected ritual ID
     * @param companyStaff the company staff
     * @return the value object of the saved staff contact chat
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantChatContactVo createStaffContact(String applicantUin, Long ritualId, Optional<CompanyStaffLiteDto> companyStaff) {
        ApplicantChatContactDto contactBuilder = ApplicantChatContactDto.builder()
                .applicantUin(applicantUin)
                .contactUin(companyStaff.map(CompanyStaffLiteDto::getSuin).orElse(null))
                .mobileNumber(companyStaff.map(CompanyStaffLiteDto::getMobileNumber).orElse(null))
                //TODO country code and nationality code may not match
                //TODO Missing country code prefix and country code
                .countryCode(companyStaff.map(CompanyStaffLiteDto::getNationalityCode).orElse(null))
                .applicantRitualId(ritualId)
                .avatar(companyStaff.map(CompanyStaffLiteDto::getPhoto).orElse(null))
                .staffTitleCode(companyStaff.map(CompanyStaffLiteDto::getTitleCode).orElse(null))
                .type(ContactTypeLookupDto.builder().id(EChatContactType.STAFF.getId()).build())
                .build();

        ApplicantChatContactDto savedContact = save(contactBuilder);
        return ((ApplicantChatContactRepository) getRepository()).findStaffContactVoById(savedContact.getId()).orElse(null);
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
    public ApplicantChatContactDto updateUserDefinedChatContact(Long id, ApplicantChatContactDto contact) {
        ApplicantChatContactDto applicantChatContact = findOne(id);
        applicantChatContact.setAlias(contact.getAlias());
        applicantChatContact.setMobileNumber(contact.getMobileNumber());
        applicantChatContact.setCountryPhonePrefix(contact.getCountryPhonePrefix());
        applicantChatContact.setCountryCode(contact.getCountryCode());
        applicantChatContact.setAvatar(contact.getAvatar());
        return save(applicantChatContact);
    }

    public void createSystemDefinedApplicantChatContact(ApplicantRelativeDto applicantRelative) {
        String applicantUin = applicantRelative.getApplicant().getDigitalIds().get(0).getUin();
        String contactUin = applicantRelative.getRelativeApplicant().getDigitalIds().get(0).getUin();
        Optional<JpaApplicantChatContact> jpaApplicantChatContact = applicantChatContactRepository.findByApplicantUinAndContactUinAndDeleted(applicantUin, contactUin, false);
        ApplicantChatContactDto chatContact = jpaApplicantChatContact.isPresent() ? getMapper().fromEntity(jpaApplicantChatContact.get(), mappingContext) : null;
        String mobileNumber = null;
        String countryCode = null;
        Optional<ApplicantContactDto> first = applicantRelative.getRelativeApplicant().getContacts().stream().findFirst();
        if (first.isPresent()) {
            if (first.get().getLocalMobileNumber() != null) {
                mobileNumber = first.get().getLocalMobileNumber();
                countryCode = "SA";
            } else {
                mobileNumber = first.get().getIntlMobileNumber();
                countryCode = first.get().getCountryCode();
            }
        }
        ApplicantChatContactDto savedContact = ApplicantChatContactDto
                .builder()
                .applicantUin(applicantUin)
                .contactUin(contactUin)
                .alias(null)
                .mobileNumber(mobileNumber)
                .countryCode(countryCode)
                .systemDefined(true)
                .deleted(false)
                .avatar(applicantRelative.getRelativeApplicant().getPhoto())
                .applicantRitualId(applicantRelative.getApplicantRitual().getId())
                .autoAdded(false)
                .relationshipCode(applicantRelative.getRelationshipCode())
                .type(ContactTypeLookupDto.builder().id(EChatContactType.APPLICANT.getId()).build())
                .build();


        if (chatContact != null) {
            savedContact.setId(chatContact.getId());
            savedContact.setCreationDate(chatContact.getCreationDate());
            log.debug(" update System Defined Applicant Chat Contact applicantUin: {} contactUin: {}", applicantUin, contactUin);
        } else {
            log.debug(" create System Defined Applicant Chat Contact applicantUin: {} contactUin: {}", applicantUin, contactUin);
        }
        save(savedContact);

    }
}
