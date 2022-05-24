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
        log.info("Start list chat Contacts applicantUin: {}, ritualId: {}, systemDefined: {}", applicantUin, ritualId, systemDefined);
        if (systemDefined == null) {
            List<ChatContactVo> applicantList = chatContactRepository.findContactApplicantList(applicantUin, ritualId);
            List<ChatContactVo> staffList = chatContactRepository.findContactStaffList(applicantUin, ritualId);
            applicantList.addAll(staffList);
            log.info("Finish list chat Contacts systemDefined is null listSize: {}", applicantList.size());
            return applicantList;
        } else if (systemDefined) {
            List<ChatContactVo> bySystemDefinedTrue = ((ChatContactRepository) getRepository()).findBySystemDefinedTrue(applicantUin, ritualId);
            log.info("Finish list chat Contacts systemDefined is true listSize: {}", bySystemDefinedTrue.size());
            return bySystemDefinedTrue;
        } else {
            List<ChatContactVo> bySystemDefinedFalse = ((ChatContactRepository) getRepository()).findBySystemDefinedFalse(applicantUin);
            log.info("Finish list chat Contacts systemDefined is true listSize: {}", bySystemDefinedFalse.size());
            return bySystemDefinedFalse;
        }
    }

    @Transactional
    public int deleteApplicantChatContact(String applicantUin, String contactUin) {
        log.info("Start deleteApplicantChatContact  applicantUin: {}, contactUin: {}", applicantUin, contactUin);
        int numberOfAffectedRows = chatContactRepository.markDeleted(applicantUin, contactUin);
        log.info("Finish deleteApplicantChatContact  numberOfAffectedRows: {}", numberOfAffectedRows);
        return numberOfAffectedRows;
    }

    @Transactional
    public int deleteInvalidStaffChatContact(String staffUin) {
        log.info("Start deleteInvalidStaffChatContact  staffUin: {}", staffUin);
        int numberOfAffectedRows = chatContactRepository.markStaffDeleted(staffUin);
        log.info("Finish deleteInvalidStaffChatContact  numberOfAffectedRows: {}", numberOfAffectedRows);
        return numberOfAffectedRows;
    }

    public ChatContactDto findApplicantChatContact(String applicantUin, String contactUin) {
        log.info("Start findApplicantChatContact  applicantUin: {}, contactUin: {}", applicantUin, contactUin);
        Optional<JpaChatContact> applicantChatContact = chatContactRepository.findByDigitalIdAndContactDigitalId(applicantUin, contactUin);
        log.info("Finish findApplicantChatContact ChatContactDtoId: {}", applicantChatContact.isPresent() ? applicantChatContact.get().getId() : null);
        ChatContactDto chatContactDto = applicantChatContact.map(chatContact -> getMapper().fromEntity(chatContact, mappingContext)).orElse(null);
        return chatContactDto;
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
        log.info("Start createApplicantContact  ritualId: {}, DigitalId: {}, ContactDigitalId: {}", ritualId, contact.getDigitalId(), contact.getContactDigitalId());
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
        log.info("Finish createApplicantContact SavedChatContactDtoId: {}", savedContact.getId());
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
        log.info("Start createStaffContact digitalId: {}, ritualId: {}, CompanyStaffLiteDtoId: {}", digitalId, ritualId, companyStaff.isPresent() ? companyStaff.get().getId() : null);
        ChatContactDto chatContactDto = findApplicantChatContact(digitalId, companyStaff.map(CompanyStaffLiteDto::getSuin).orElse(null));
        if (chatContactDto != null) {
            if (chatContactDto.isDeleted()) {
                log.debug("createStaffContact chat contact already exist calling updateUserDefinedChatContact ChatContactDtoId: {}", chatContactDto.getId());
                updateUserDefinedChatContact(chatContactDto.getId(), chatContactDto, false);
            }
            log.info("Finish createStaffContact chat contact already exist ChatContactDtoId: {}", chatContactDto.getId());
            return ((ChatContactRepository) getRepository()).findStaffContactVoById(chatContactDto.getId()).orElse(null);
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
        log.info("Finish createStaffContact savedContactId: {}", savedContact.getId());
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
        log.info("Start findById chatContactId: {}", chatContactId);
        ChatContactDto chatContactDto = findOne(chatContactId);
        log.info("Finish findById chatContactDtoId: {}", chatContactDto.getId());
        return chatContactDto;
    }

    /**
     * Updates user defined chat contact
     *
     * @param contact the chat contact to save
     * @return savedContact saved one
     */
    public ChatContactDto updateUserDefinedChatContact(Long id, ChatContactDto contact, boolean deleted) {
        log.info("Start updateUserDefinedChatContact id: {}, DigitalId: {}, ContactDigitalId: {}, deleted: {}", id,contact.getDigitalId(), contact.getContactDigitalId(),deleted);
        ChatContactDto applicantChatContact = findOne(id);
        applicantChatContact.setAlias(contact.getAlias());
        applicantChatContact.setMobileNumber(contact.getMobileNumber());
        applicantChatContact.setCountryPhonePrefix(contact.getCountryPhonePrefix());
        applicantChatContact.setCountryCode(contact.getCountryCode());
        applicantChatContact.setAvatar(contact.getAvatar());
        applicantChatContact.setAutoAdded(contact.isAutoAdded());
        applicantChatContact.setDeleted(deleted);
        log.info("Finish updateUserDefinedChatContact applicantChatContactId: {}", applicantChatContact.getId());
        return save(applicantChatContact);
    }

    @Transactional
    public void createGroupLeaderContact(String applicantUin, CompanyStaffDto companyStaff, int seasonYear) {
        log.info("Start createGroupLeaderContact applicantUin: {}, CompanyStaffDtoId: {}, seasonYear: {}", applicantUin, companyStaff.getId(), seasonYear);
        ApplicantRitualPackageVo latestApplicantPackage = applicantPackageService.findLatestApplicantRitualPackage(Long.parseLong(applicantUin));
        log.debug("createGroupLeaderContact findLatestApplicantRitualPackage {}, ",latestApplicantPackage.getApplicantPackageId());
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
            log.debug("createGroupLeaderContact chat contact is not null DigitalId: {}, ContactDigitalId: {}", contactBuilder.getDigitalId(), contactBuilder.getContactDigitalId());
            contactBuilder.setId(chatContact.getId());
            contactBuilder.setCreationDate(chatContact.getCreationDate());
        }
        log.info("Finish createGroupLeaderContact savedChatContactDtoId {}", contactBuilder.getId());
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
        log.info("Start createApplicantRelativesChatContacts ApplicantRelativeDtoId: {}, applicantRitualId: {}", applicantRelative.getId(), applicantRitualId);
        ApplicantDto relativeApplicant = applicantRelative.getRelativeApplicant();
        String relativeApplicantUin = relativeApplicant.getDigitalIds().get(0).getUin();

        ApplicantDto mainApplicant = applicantRelative.getApplicant();
        String mainApplicantUin = mainApplicant.getDigitalIds().get(0).getUin();

        log.debug("create applicant relatives chat contacts for the main applicant");
        createChatContact(mainApplicantUin, relativeApplicant, applicantRitualId, applicantRelative.getRelationshipCode());
        Long relativeApplicantRitualId = applicantRitualService.findIdByApplicantIdAndPackageReferenceNumber(relativeApplicant.getId(), relativeApplicant.getPackageReferenceNumber());
        if (relativeApplicantRitualId != null) {
            log.debug("create main applicant chat contact for the relative applicant");
            String mainApplicantRelationshipCode = mapOwnerRelationship(applicantRelative.getRelationshipCode(), mainApplicant.getGender());
            log.info("Finish mapOwnerRelationship mainApplicantRelationshipCode: {}",mainApplicantRelationshipCode);
            createChatContact(relativeApplicantUin, mainApplicant, relativeApplicantRitualId, mainApplicantRelationshipCode);
        }
        log.info("Finish createApplicantRelativesChatContacts");
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
        log.info("Start createChatContact contactOwnerUin: {}, contactApplicant: {}, applicantRitualId: {}, applicantRitualId: {}",contactOwnerUin,  contactApplicant,  applicantRitualId,  relationshipCode);
        String contactUin = contactApplicant.getDigitalIds().get(0).getUin();
        String mobileNumber = null, countryCode = "SA";
        if (!contactApplicant.getContacts().isEmpty()) {
            log.debug("createChatContact applicant contacts is not empty");
            ApplicantContactDto relativeApplicantContact = contactApplicant.getContacts().get(0);
            if (relativeApplicantContact.getLocalMobileNumber() != null) {
                log.debug("createChatContact relative applicant contact LocalMobileNumber: {}",relativeApplicantContact.getLocalMobileNumber() );
                mobileNumber = relativeApplicantContact.getLocalMobileNumber();
                countryCode = "SA";
            } else {
                mobileNumber = relativeApplicantContact.getIntlMobileNumber();
                countryCode = relativeApplicantContact.getCountryCode();
                log.debug("createChatContact relative applicant contact IntlMobileNumber: {}", mobileNumber);
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
        log.info("Finish createChatContact DigitalId: {}, ContactDigitalId: {}",createdContact.getDigitalId(), createdContact.getContactDigitalId());

    }

    /**
     * Map main applicant relationship code based on the main applicant gender and relative relationship code.
     *
     * @param relativeRelationshipCode
     * @param gender
     * @return
     */
    private String mapOwnerRelationship(String relativeRelationshipCode, String gender) {
        log.info("Start mapOwnerRelationship relativeRelationshipCode: {}, gender: {}",relativeRelationshipCode, gender);
        if (relativeRelationshipCode != null) {
            if (relativeRelationshipCode.equalsIgnoreCase(FATHER.name()) && gender.equalsIgnoreCase("M")) {
                return SON.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(FATHER.name()) && gender.equalsIgnoreCase("F")) {
                return DAUGHTER.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(SON.name()) && gender.equalsIgnoreCase("M")) {
                return FATHER.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(SON.name()) && gender.equalsIgnoreCase("F")) {
                return MOTHER.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(MOTHER.name()) && gender.equalsIgnoreCase("M")) {
                return SON.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(MOTHER.name()) && gender.equalsIgnoreCase("F")) {
                return DAUGHTER.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(HUSBAND.name())) {
                return WIFE.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(WIFE.name())) {
                return HUSBAND.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(SISTER.name()) && gender.equalsIgnoreCase("M")) {
                return BROTHER.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(SISTER.name()) && gender.equalsIgnoreCase("F")) {
                return SISTER.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(BROTHER.name()) && gender.equalsIgnoreCase("M")) {
                return BROTHER.name();
            }
            if (relativeRelationshipCode.equalsIgnoreCase(BROTHER.name()) && gender.equalsIgnoreCase("F")) {
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
        log.info("Start listStaffContact suin: {}, systemDefined: {}",suin,  systemDefined);
        if (systemDefined == null) {
            List<ChatContactVo> applicantList = chatContactRepository.findContactApplicantList(suin, null);
            List<ChatContactVo> staffList = chatContactRepository.findContactStaffList(suin, null);
            applicantList.addAll(staffList);
            log.info("Finish listStaffContact systemDefined is null contactListSize: {}",applicantList.size());
            return applicantList;
        } else if (systemDefined) {
            log.info("Finish listStaffContact systemDefined is true");
            return ((ChatContactRepository) getRepository()).findBySystemDefinedTrue(suin, null);
        } else {
            log.info("Finish listStaffContact systemDefined is false");
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
     * @param avatar
     * @return the value object of the saved applicant contact chat
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ChatContactDto createAutoAddedChatContact(String shaaerNumber, String contactUin, String avatar) {
        log.info("Start createAutoAddedChatContact shaaerNumber: {}, contactUin: {} ",shaaerNumber, contactUin);
        int chatContactTypeId = contactUin.length() == 12 ? EChatContactType.STAFF.getId() : EChatContactType.APPLICANT.getId();
        ChatContactDto contactBuilder = ChatContactDto.builder()
                .digitalId(shaaerNumber)
                .contactDigitalId(contactUin)
                .autoAdded(true)
                .type(ContactTypeLookupDto.builder().id(chatContactTypeId).build())
                .avatar(avatar)
                .build();
        ChatContactDto savedContact = save(contactBuilder);
        log.info("Finish createAutoAddedChatContact savedChatContactDtoId: {} ",savedContact.getId());
        return savedContact;
    }
}
