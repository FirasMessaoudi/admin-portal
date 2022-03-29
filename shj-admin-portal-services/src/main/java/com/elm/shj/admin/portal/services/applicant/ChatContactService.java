/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.ApplicantRitualPackageVo;
import com.elm.shj.admin.portal.orm.entity.ChatContactVo;
import com.elm.shj.admin.portal.orm.entity.JpaChatContact;
import com.elm.shj.admin.portal.orm.repository.ChatContactRepository;
import com.elm.shj.admin.portal.orm.repository.CompanyStaffDigitalIdRepository;
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

import static com.elm.shj.admin.portal.services.dto.ERelativeRelationship.*;

/**
 * Service handling applicant chat contacts
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ChatContactService extends GenericService<JpaChatContact, ChatContactDto, Long> {

    private final ChatContactRepository chatContactRepository;
    private final ApplicantRitualService applicantRitualService;
    private final ApplicantPackageService applicantPackageService;
    private final CompanyStaffDigitalIdRepository companyStaffDigitalIdRepository;

    /**
     * List all chat contacts of a specific applicant.
     *
     * @param applicantUin  the UIN of the applicant
     * @param ritualId      the selected ritual ID
     * @param systemDefined a boolean flag to define whether the chat contact is added by system or not
     * @return the list of chat contacts
     */
    public List<ChatContactVo> list(String applicantUin, Long ritualId, Boolean systemDefined) {
        if (systemDefined == null) {
            List<ChatContactVo> applicantList = chatContactRepository.findContactApplicantList(applicantUin, ritualId);
            List<ChatContactVo> staffList = chatContactRepository.findContactStaffList(applicantUin, ritualId);
            applicantList.addAll(staffList);
            return applicantList;
        } else if (systemDefined) {
            return ((ChatContactRepository) getRepository()).findBySystemDefinedTrue(applicantUin, ritualId);
        } else {
            return ((ChatContactRepository) getRepository()).findBySystemDefinedFalse(applicantUin);
        }
    }

    @Transactional
    public int deleteApplicantChatContact(String applicantUin, String contactUin) {
        return chatContactRepository.markDeleted(applicantUin, contactUin);
    }

    @Transactional
    public int deleteInvalidStaffChatContact(String staffUin) {
        return chatContactRepository.markStaffDeleted(staffUin);
    }

    public ChatContactDto findApplicantChatContact(String applicantUin, String contactUin) {
        Optional<JpaChatContact> applicantChatContact = chatContactRepository.findByDigitalIdAndContactDigitalId(applicantUin, contactUin);
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
    public ChatContactVo createApplicantContact(Long ritualId, ChatContactDto contact) {
        ChatContactDto contactBuilder = ChatContactDto.builder()
                .digitalId(contact.getDigitalId())
                .contactDigitalId(contact.getContactDigitalId())
                .alias(contact.getAlias())
                .mobileNumber(contact.getMobileNumber())
                .countryPhonePrefix(contact.getCountryPhonePrefix())
                .countryCode(contact.getCountryCode())
                .avatar(contact.getAvatar())
                .applicantRitualId(ritualId)
                .autoAdded(contact.isAutoAdded())
                .type(ContactTypeLookupDto.builder().id(EChatContactType.APPLICANT.getId()).build())
                .build();
        ChatContactDto savedContact = save(contactBuilder);
        return ((ChatContactRepository) getRepository()).findApplicantContactVoById(savedContact.getId()).orElse(null);
    }

    /**
     * Creates a new staff chat contact
     *
     * @param digitalId    the digital id  of the relationship owner
     * @param ritualId     the selected ritual ID
     * @param companyStaff the company staff
     * @return the value object of the saved staff contact chat
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ChatContactVo createStaffContact(String digitalId, Long ritualId, Optional<CompanyStaffLiteDto> companyStaff) {
        ChatContactDto chatContactDto = findApplicantChatContact(digitalId, companyStaff.map(CompanyStaffLiteDto::getSuin).orElse(null));
        if(chatContactDto!=null){
            if(chatContactDto.isDeleted()){
             updateUserDefinedChatContact(chatContactDto.getId(),chatContactDto,false);
             }
           return  ((ChatContactRepository) getRepository()).findStaffContactVoById(chatContactDto.getId()).orElse(null);
        }
        ChatContactDto contactBuilder = ChatContactDto.builder()
                .digitalId(digitalId)
                .contactDigitalId(companyStaff.map(CompanyStaffLiteDto::getSuin).orElse(null))
                .mobileNumber(companyStaff.map(CompanyStaffLiteDto::getMobileNumber).orElse(null))
                //TODO country code and nationality code may not match
                //TODO Missing country code prefix and country code
                .countryCode(companyStaff.map(CompanyStaffLiteDto::getNationalityCode).orElse(null))
                .applicantRitualId(ritualId)
                .avatar(companyStaff.map(CompanyStaffLiteDto::getPhoto).orElse(null))
                .staffTitleCode(companyStaff.map(CompanyStaffLiteDto::getTitleCode).orElse(null))
                .type(ContactTypeLookupDto.builder().id(EChatContactType.STAFF.getId()).build())
                .build();

        ChatContactDto savedContact = save(contactBuilder);
        return ((ChatContactRepository) getRepository()).findStaffContactVoById(savedContact.getId()).orElse(null);
    }

    /**
     * finds a chat contact by its ID
     *
     * @param chatContactId the chat contact id to find
     * @return the found contact or <code>null</code>
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ChatContactDto findById(long chatContactId) {
        return findOne(chatContactId);
    }

    /**
     * Updates user defined chat contact
     *
     * @param contact the chat contact to save
     * @return savedContact saved one
     */
    public ChatContactDto updateUserDefinedChatContact(Long id, ChatContactDto contact, boolean deleted) {
        ChatContactDto applicantChatContact = findOne(id);
        applicantChatContact.setAlias(contact.getAlias());
        applicantChatContact.setMobileNumber(contact.getMobileNumber());
        applicantChatContact.setCountryPhonePrefix(contact.getCountryPhonePrefix());
        applicantChatContact.setCountryCode(contact.getCountryCode());
        applicantChatContact.setAvatar(contact.getAvatar());
        applicantChatContact.setAutoAdded(contact.isAutoAdded());
        applicantChatContact.setDeleted(deleted);
        return save(applicantChatContact);
    }

    @Transactional
    public void createGroupLeaderContact(String applicantUin, CompanyStaffDto companyStaff,int seasonYear) {
        ApplicantRitualPackageVo latestApplicantPackage = applicantPackageService.findLatestApplicantRitualPackage(Long.parseLong(applicantUin));
        String leaderSuin = companyStaffDigitalIdRepository.findStaffSuinByStaffIdAndStatusCodeAndSeasonYear(companyStaff.getId(), seasonYear, EStaffDigitalIdStatus.VALID.name());
        ChatContactDto contactBuilder = ChatContactDto.builder()
                .digitalId(applicantUin)
                .contactDigitalId(leaderSuin)
                .mobileNumber(companyStaff.getMobileNumber() != null ? companyStaff.getMobileNumber() : companyStaff.getMobileNumberIntl())
                //TODO country code and nationality code may not match
                //TODO Missing country code prefix and country code
                .countryCode(companyStaff.getMobileNumber() != null ? "SA" : companyStaff.getNationalityCode())
                .applicantRitualId(applicantRitualService.findByApplicantUinAndApplicantPackageId(applicantUin, latestApplicantPackage.getApplicantPackageId()).getId())
                .avatar(companyStaff.getPhoto())
                .systemDefined(true)
                .staffTitleCode(companyStaff.getTitleCode())
                .type(ContactTypeLookupDto.builder().id(EChatContactType.STAFF.getId()).build())
                .build();
        Optional<JpaChatContact> applicantChatContact = chatContactRepository.findByDigitalIdAndContactDigitalIdAndDeletedFalse(applicantUin, contactBuilder.getContactDigitalId());
        ChatContactDto chatContact = applicantChatContact.isPresent() ? getMapper().fromEntity(applicantChatContact.get(), mappingContext) : null;

        if (chatContact != null) {
            log.debug("update System Defined Applicant Chat Contact applicantUin: {} groupLeaderSuin: {}", applicantUin, contactBuilder.getContactDigitalId());
            contactBuilder.setId(chatContact.getId());
            contactBuilder.setCreationDate(chatContact.getCreationDate());
        }
        save(contactBuilder);
    }

    /**
     * Create chat contacts for applicant relatives,
     * add applicant relatives chat contacts to the main applicant and
     * add main applicant chat contact for the relative applicant.
     *
     * @param applicantRelative
     * @param applicantRitualId
     */
    public void createApplicantRelativesChatContacts(ApplicantRelativeDto applicantRelative, long applicantRitualId) {
        ApplicantDto relativeApplicant = applicantRelative.getRelativeApplicant();
        String relativeApplicantUin = relativeApplicant.getDigitalIds().get(0).getUin();

        ApplicantDto mainApplicant = applicantRelative.getApplicant();
        String mainApplicantUin = mainApplicant.getDigitalIds().get(0).getUin();

        // create applicant relatives chat contacts for the main applicant
        createChatContact(mainApplicantUin, relativeApplicant, applicantRitualId, applicantRelative.getRelationshipCode());
        // create main applicant chat contact for the relative applicant if relative applicant ritual is exists
        if (applicantRelative.getApplicantRitual() != null) {
            String mainApplicantRelationshipCode = mapOwnerRelationship(applicantRelative.getRelationshipCode(), mainApplicant.getGender());
            createChatContact(relativeApplicantUin, mainApplicant, applicantRelative.getApplicantRitual().getId(), mainApplicantRelationshipCode);
        }
    }

    /**
     * Create a chat contact.
     *
     * @param contactOwnerUin
     * @param contactApplicant
     * @param applicantRitualId
     * @param relationshipCode
     */
    private void createChatContact(String contactOwnerUin, ApplicantDto contactApplicant, long applicantRitualId, String relationshipCode) {
        String contactUin = contactApplicant.getDigitalIds().get(0).getUin();
        String mobileNumber = null, countryCode= "SA";
        if(!contactApplicant.getContacts().isEmpty()) {
            ApplicantContactDto relativeApplicantContact = contactApplicant.getContacts().get(0);
            if (relativeApplicantContact.getLocalMobileNumber() != null) {
                mobileNumber = relativeApplicantContact.getLocalMobileNumber();
                countryCode = "SA";
            } else {
                mobileNumber = relativeApplicantContact.getIntlMobileNumber();
                countryCode = relativeApplicantContact.getCountryCode();
            }
        }
        ChatContactDto createdContact = ChatContactDto
                .builder()
                .digitalId(contactOwnerUin)
                .contactDigitalId(contactUin)
                .alias(null)
                .mobileNumber(mobileNumber)
                .countryCode(countryCode)
                .systemDefined(true)
                .avatar(contactApplicant.getPhoto())
                .applicantRitualId(applicantRitualId)
                .relationshipCode(relationshipCode)
                .type(ContactTypeLookupDto.builder().id(EChatContactType.APPLICANT.getId()).build())
                .build();

        Optional<JpaChatContact> applicantChatContact = chatContactRepository.findByDigitalIdAndContactDigitalIdAndDeletedFalse(contactOwnerUin, contactUin);
        ChatContactDto chatContact = applicantChatContact.isPresent() ? getMapper().fromEntity(applicantChatContact.get(), mappingContext) : null;
        if (chatContact != null) {
            log.debug("update System Defined Applicant Chat Contact applicantUin: {} contactUin: {}", contactOwnerUin, contactUin);
            createdContact.setId(chatContact.getId());
        }
        save(createdContact);
    }

    /**
     * Map main applicant relationship code based on the main applicant gender and relative relationship code.
     *
     * @param relativeRelationshipCode
     * @param Gender
     * @return
     */
    private String mapOwnerRelationship(String relativeRelationshipCode, String Gender) {
        if (relativeRelationshipCode != null) {
            if (relativeRelationshipCode.equalsIgnoreCase(FATHER.name()) && Gender.equalsIgnoreCase("M")) {
                return SON.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(FATHER.name()) && Gender.equalsIgnoreCase("F")) {
                return DAUGHTER.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(SON.name()) && Gender.equalsIgnoreCase("M")) {
                return FATHER.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(SON.name()) && Gender.equalsIgnoreCase("F")) {
                return MOTHER.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(MOTHER.name()) && Gender.equalsIgnoreCase("M")) {
                return SON.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(MOTHER.name()) && Gender.equalsIgnoreCase("F")) {
                return DAUGHTER.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(HUSBAND.name())) {
                return WIFE.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(WIFE.name())) {
                return HUSBAND.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(SISTER.name()) && Gender.equalsIgnoreCase("M")) {
                return BROTHER.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(SISTER.name()) && Gender.equalsIgnoreCase("F")) {
                return SISTER.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(BROTHER.name()) && Gender.equalsIgnoreCase("M")) {
                return BROTHER.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(BROTHER.name()) && Gender.equalsIgnoreCase("F")) {
                return SISTER.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(COMPANION.name())) {
                return COMPANION.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(RELATIVE.name())) {
                return RELATIVE.name();
            }
        }

        return COMPANION.name();
    }

    /**
     * List all chat contacts of a specific applicant.
     *
     * @param suin          the suin of the staff
     * @param systemDefined a boolean flag to define whether the chat contact is added by system or not
     * @return the list of chat contacts
     */
    public List<ChatContactVo> listStaffContact(String suin, Boolean systemDefined) {
        if (systemDefined == null) {
            List<ChatContactVo> applicantList = chatContactRepository.findContactApplicantList(suin, null);
            List<ChatContactVo> staffList = chatContactRepository.findContactStaffList(suin, null);
            applicantList.addAll(staffList);
            return applicantList;
        } else if (systemDefined) {
            return ((ChatContactRepository) getRepository()).findBySystemDefinedTrue(suin, null);
        } else {
            return ((ChatContactRepository) getRepository()).findBySystemDefinedFalse(suin);
        }
    }

    /**
     * Creates auto added new chat contact
     * This contact will be added if and only if the receiver does not
     * have the sender in his contact
     *
     * @param shaaerNumber represent uin or suin
     * @param contactUin   the chat contact uin to save
     * @return the value object of the saved applicant contact chat
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ChatContactDto createAutoAddedChatContact(String shaaerNumber, String contactUin) {
        int chatContactTypeId = contactUin.length() == 12 ? EChatContactType.STAFF.getId() : EChatContactType.APPLICANT.getId();
        ChatContactDto contactBuilder = ChatContactDto.builder()
                .digitalId(shaaerNumber)
                .contactDigitalId(contactUin)
                .autoAdded(true)
                .type(ContactTypeLookupDto.builder().id(chatContactTypeId).build())
                .build();
        ChatContactDto savedContact = save(contactBuilder);
        return savedContact;
    }
}
