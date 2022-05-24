/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

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

    public boolean existsByUin(String uin) {
        log.info("Start existsByUin uin:{}", uin);
        boolean existsByUin = ((ApplicantLiteRepository) getRepository()).existsByUin(uin);
        log.info("Finish existsByUin isExists:{}", existsByUin);
        return existsByUin;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public List<ApplicantLiteDto> findAllWithoutDigitalId() {
        log.info("Start findAllWithoutDigitalId");
        List<ApplicantLiteDto> applicantLiteDtos = mapList(applicantLiteRepository.findAllApplicantsWithoutDigitalId());
        log.info("Finish findAllWithoutDigitalId with applicantLiteDtoListSize:{}", applicantLiteDtos.size());
        return applicantLiteDtos;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantLiteDto findByBasicInfo(ApplicantBasicInfoDto applicantBasicInfo) {
        log.info("Start findByBasicInfo RowNum:{}", applicantBasicInfo == null?null: applicantBasicInfo.getRowNum());
        List<JpaApplicantLite> applicantLites = applicantLiteRepository.findByBasicInfo(applicantBasicInfo.getIdNumber(), applicantBasicInfo.getDateOfBirthHijri(),
                applicantBasicInfo.getPassportNumber(), applicantBasicInfo.getDateOfBirthGregorian());
        if (applicantLites.isEmpty()) {
            log.info("Finish findByBasicInfo not found with RowNum:{}", applicantBasicInfo == null?null: applicantBasicInfo.getRowNum());
            return null;
        }
        ApplicantLiteDto applicantLiteDto = getMapper().fromEntity(applicantLites.get(0), mappingContext);
        log.info("Finish findByBasicInfo found with FullNameEn:{}", applicantLiteDto == null?null: applicantLiteDto.getFullNameEn());
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
        log.info("Start existsByBasicInfo RowNum:{}", applicantBasicInfo == null?null: applicantBasicInfo.getRowNum());
        boolean exists = applicantLiteRepository.existsByBasicInfo(applicantBasicInfo.getIdNumber(), applicantBasicInfo.getDateOfBirthHijri(), applicantBasicInfo.getPassportNumber(), applicantBasicInfo.getDateOfBirthGregorian());
        log.info("Finish existsByBasicInfo isExists:{}", exists);
        return exists;
    }

    public Optional<ApplicantStaffVO> findApplicantRitualByIdNumber(String value) {
        log.info("Start findApplicantRitualByIdNumber IdNumber:{}", value);
        List<ApplicantStaffVO> applicantList = applicantLiteRepository.findApplicantRitualByIdNumber(value, EDigitalIdStatus.VALID.name(), ECardStatus.CANCELLED.name(), ECardStatus.SUSPENDED.name());
        log.info("Finish findApplicantRitualByIdNumber IdNumber:{}", applicantList.size());
         if(applicantList.size() == 0) {
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
}
