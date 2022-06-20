/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;


import com.elm.shj.admin.portal.orm.entity.ApplicantEmergencyContactDto;
import com.elm.shj.admin.portal.orm.entity.ApplicantStaffVO;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantLite;
import com.elm.shj.admin.portal.orm.repository.ApplicantLiteRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantBasicInfoDto;
import com.elm.shj.admin.portal.services.dto.ApplicantLiteDto;
import com.elm.shj.admin.portal.services.dto.ECardStatus;
import com.elm.shj.admin.portal.services.dto.EDigitalIdStatus;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service handling lightweight version of applicant
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantLiteService extends GenericService<JpaApplicantLite, ApplicantLiteDto, Long> {

    private final ApplicantLiteRepository applicantLiteRepository;

    /**
     * Finds an applicant by his uin
     *
     * @param uin the uin of applicant to find
     * @return the found applicant or empty structure
     */
    public Optional<ApplicantLiteDto> findByUin(String uin) {
        log.info("Start findByUin uin:{}", uin);

        JpaApplicantLite applicant = applicantLiteRepository.findByUin(uin);
        if (applicant != null) {
            ApplicantLiteDto applicantLiteDto = getMapper().fromEntity(applicant, mappingContext);
            applicantLiteDto.setEmail(applicant.getContacts().get(0).getEmail());
            if (applicant.getContacts().get(0).getLocalMobileNumber() != null && applicant.getContacts().get(0).getLocalMobileNumber().length() != 0) {
                applicantLiteDto.setMobileNumber(applicant.getContacts().get(0).getLocalMobileNumber());
            } else {
                applicantLiteDto.setMobileNumber(applicant.getContacts().get(0).getIntlMobileNumber());
            }
            applicantLiteDto.setCountryCode(applicant.getContacts().get(0).getCountryCode());
            applicantLiteDto.setHasLocalMobileNumber(applicant.getContacts().get(0).getLocalMobileNumber() != null && !applicant.getContacts().get(0).getLocalMobileNumber().isEmpty());
            applicantLiteDto.setGender(applicant.getGender());
            log.info("Finish findByUin found with FullNameEn:{}", applicantLiteDto.getFullNameEn());
            return Optional.of(applicantLiteDto);
        } else {
            log.info("Finish findByUin not found with uin:{}", uin);
            return Optional.empty();
        }
    }

    /**
     * Finds an applicant by his passport number
     *
     * @param passportNumber the uin of applicant to find
     * @return the found applicant or empty structure
     */
    public Optional<ApplicantLiteDto> findByPassportNumber(String passportNumber, String nationalityCode) {
        log.info("Start findByPassportNumber passport number:{}", passportNumber);

        JpaApplicantLite applicant = applicantLiteRepository.findByPassportNumberAndCountryCode(passportNumber, nationalityCode);
        if (applicant != null) {
            ApplicantLiteDto applicantLiteDto = getMapper().fromEntity(applicant, mappingContext);
            applicantLiteDto.setEmail(applicant.getContacts().get(0).getEmail());
            if (applicant.getContacts().get(0).getLocalMobileNumber() != null && applicant.getContacts().get(0).getLocalMobileNumber().length() != 0) {
                applicantLiteDto.setMobileNumber(applicant.getContacts().get(0).getLocalMobileNumber());
            } else {
                applicantLiteDto.setMobileNumber(applicant.getContacts().get(0).getIntlMobileNumber());
            }
            applicantLiteDto.setCountryCode(applicant.getContacts().get(0).getCountryCode());
            applicantLiteDto.setHasLocalMobileNumber(applicant.getContacts().get(0).getLocalMobileNumber() != null && !applicant.getContacts().get(0).getLocalMobileNumber().isEmpty());
            applicantLiteDto.setGender(applicant.getGender());
            log.info("Finish findByPassportNumber found with FullNameEn:{}", applicantLiteDto.getFullNameEn());
            return Optional.of(applicantLiteDto);
        } else {
            log.info("Finish findByPassportNumber not found with passport number:{}", passportNumber);
            return Optional.empty();
        }
    }

    /**
     * Finds an applicant by his id number
     *
     * @param idNumber the uin of applicant to find
     * @return the found applicant or empty structure
     */
    public Optional<ApplicantLiteDto> findByIdNumber(String idNumber) {
        log.info("Start findByIdNumber id number:{}", idNumber);

        JpaApplicantLite applicant = applicantLiteRepository.findByIdNumber(idNumber);
        if (applicant != null) {
            ApplicantLiteDto applicantLiteDto = getMapper().fromEntity(applicant, mappingContext);
            applicantLiteDto.setEmail(applicant.getContacts().get(0).getEmail());
            if (applicant.getContacts().get(0).getLocalMobileNumber() != null && applicant.getContacts().get(0).getLocalMobileNumber().length() != 0) {
                applicantLiteDto.setMobileNumber(applicant.getContacts().get(0).getLocalMobileNumber());
            } else {
                applicantLiteDto.setMobileNumber(applicant.getContacts().get(0).getIntlMobileNumber());
            }
            applicantLiteDto.setCountryCode(applicant.getContacts().get(0).getCountryCode());
            applicantLiteDto.setHasLocalMobileNumber(applicant.getContacts().get(0).getLocalMobileNumber() != null && !applicant.getContacts().get(0).getLocalMobileNumber().isEmpty());
            applicantLiteDto.setGender(applicant.getGender());
            log.info("Finish findByIdNumber found with FullNameEn:{}", applicantLiteDto.getFullNameEn());
            return Optional.of(applicantLiteDto);
        } else {
            log.info("Finish findByIdNumber not found with id number:{}", idNumber);
            return Optional.empty();
        }
    }

    public boolean existsByUin(String uin) {
        log.info("Start existsByUin uin:{}", uin);
        boolean existsByUin = ((ApplicantLiteRepository) getRepository()).existsByUin(uin);
        log.info("Finish existsByUin isExists:{}", existsByUin);
        return existsByUin;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Page<ApplicantLiteDto> findAllWithoutDigitalId() {
        log.info("Start findAllWithoutDigitalId");
        Page<ApplicantLiteDto> applicantLiteDtos = mapPage(applicantLiteRepository.findAllApplicantsWithoutDigitalId(PageRequest.of(0, 500)));
        log.info("Finish findAllWithoutDigitalId with {} digital ids", applicantLiteDtos.getContent().size());
        return applicantLiteDtos;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantLiteDto findByBasicInfo(ApplicantBasicInfoDto applicantBasicInfo) {
        log.info("Start findByBasicInfo RowNum:{}", applicantBasicInfo == null ? null : applicantBasicInfo.getRowNum());
        List<JpaApplicantLite> applicantLites = applicantLiteRepository.findByBasicInfo(applicantBasicInfo.getIdNumber(), applicantBasicInfo.getDateOfBirthHijri(),
                applicantBasicInfo.getPassportNumber(), applicantBasicInfo.getDateOfBirthGregorian());
        if (applicantLites.isEmpty()) {
            log.info("Finish findByBasicInfo not found with RowNum:{}", applicantBasicInfo == null ? null : applicantBasicInfo.getRowNum());
            return null;
        }
        ApplicantLiteDto applicantLiteDto = getMapper().fromEntity(applicantLites.get(0), mappingContext);
        log.info("Finish findByBasicInfo found with FullNameEn:{}", applicantLiteDto == null ? null : applicantLiteDto.getFullNameEn());
        return applicantLiteDto;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantLiteDto findByBasicInfo(String idNumber, String passportNumber, String nationalityCode) {
        log.info("Start findByBasicInfo RowNum:{}", idNumber == null ? passportNumber : idNumber);
        List<JpaApplicantLite> applicantLites = applicantLiteRepository.findByBasicInfo(idNumber, passportNumber,
                nationalityCode);
        if (applicantLites.isEmpty()) {
            log.info("Finish findByBasicInfo not found with RowNum:{}", idNumber == null ? passportNumber : idNumber);
            return null;
        }
        ApplicantLiteDto applicantLiteDto = getMapper().fromEntity(applicantLites.get(0), mappingContext);
        log.info("Finish findByBasicInfo found with FullNameEn:{}", applicantLiteDto == null ? null : applicantLiteDto.getFullNameEn());
        return applicantLiteDto;
    }

    /**
     * Checks if an applicant exists with the same basic info
     *
     * @param applicantBasicInfo the applicant basic info
     * @return true if applicant with the same basic info exists
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public boolean existsByBasicInfo(ApplicantBasicInfoDto applicantBasicInfo) {
        log.info("Start existsByBasicInfo RowNum:{}", applicantBasicInfo == null ? null : applicantBasicInfo.getRowNum());
        boolean exists = applicantLiteRepository.existsByBasicInfo(applicantBasicInfo.getIdNumber(), applicantBasicInfo.getDateOfBirthHijri(), applicantBasicInfo.getPassportNumber(), applicantBasicInfo.getDateOfBirthGregorian());
        log.info("Finish existsByBasicInfo isExists:{}", exists);
        return exists;
    }

    public boolean existsByBasicInfo(String idNumber, String passportNumber, String nationalityCode) {
        if (idNumber != null && idNumber.isBlank()) {
            idNumber = null;
        }
        if (passportNumber != null && passportNumber.isBlank()) {
            passportNumber = null;
        }
        boolean exists = applicantLiteRepository.existsByBasicInfo(idNumber, passportNumber, nationalityCode);
        return exists;
    }

    public Optional<ApplicantStaffVO> findApplicantRitualByIdNumber(String value) {
        log.info("Start findApplicantRitualByIdNumber IdNumber:{}", value);
        List<ApplicantStaffVO> applicantList = applicantLiteRepository.findApplicantRitualByIdNumber(value, EDigitalIdStatus.VALID.name(), ECardStatus.CANCELLED.name(), ECardStatus.SUSPENDED.name());
        log.info("Finish findApplicantRitualByIdNumber IdNumber:{}", applicantList.size());
        if (applicantList.size() == 0) {
            log.info("Finish findApplicantRitualByIdNumber not found IdNumber:{}", value);
            return Optional.empty();
        } else {
            log.info("Finish findApplicantRitualByIdNumber found with FullNameEn:{}", applicantList.get(0).getFullNameEn());
             return  Optional.of(applicantList.get(0));
         }
    }

    public Optional<ApplicantStaffVO> findApplicantRitualByUin(String uin) {
        log.info("Start findApplicantRitualByUin uin:{}", uin);
        List<ApplicantStaffVO> applicantList = applicantLiteRepository.findApplicantRitualByUin(uin, EDigitalIdStatus.VALID.name(), ECardStatus.CANCELLED.name(), ECardStatus.SUSPENDED.name());
        if(applicantList.size() == 0) {
            log.info("Finish findApplicantRitualByUin not found uin:{}", uin);
            return Optional.empty();
        } else {
            log.info("Finish findApplicantRitualByUin found with FullNameEn:{}", applicantList.get(0).getFullNameEn());
            return  Optional.of(applicantList.get(0));
        }
    }

    public ApplicantEmergencyContactDto findApplicantEmergencyContactByApplicantId(String applicantUin){
        ApplicantEmergencyContactDto applicantEmergencyContact =  applicantLiteRepository.findApplicantEmergencyContactByApplicantId(applicantUin);
        return applicantEmergencyContact;
    }
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantEmergencyContactDto updateApplicantEmergencyContactByApplicantId(String applicantUin, ApplicantEmergencyContactDto applicantEmergencyContact) {

        Optional<ApplicantLiteDto> applicant = findByUin(applicantUin);
        if(applicant.isPresent()== false) {
            return null;
        }
      int numberOfAffectedRows =   applicantLiteRepository.updateApplicantEmergencyContactByApplicantId(applicant.get().getId(), applicantEmergencyContact.getEmergencyContactName(),applicantEmergencyContact.getEmergencyContactMobileNumber());
      if(numberOfAffectedRows == 1){

      return applicantEmergencyContact;
      }
        return null;
    }
}
