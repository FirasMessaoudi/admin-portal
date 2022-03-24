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
            return Optional.of(applicantLiteDto);
        } else return Optional.empty();
    }

    public boolean existsByUin(String uin) {
        return ((ApplicantLiteRepository) getRepository()).existsByUin(uin);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public List<ApplicantLiteDto> findAllWithoutDigitalId() {
        return mapList(applicantLiteRepository.findAllApplicantsWithoutDigitalId());
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantLiteDto findByBasicInfo(ApplicantBasicInfoDto applicantBasicInfo) {
        List<JpaApplicantLite> applicantLites = applicantLiteRepository.findByBasicInfo(applicantBasicInfo.getIdNumber(), applicantBasicInfo.getDateOfBirthHijri(),
                applicantBasicInfo.getPassportNumber(), applicantBasicInfo.getDateOfBirthGregorian());
        if (applicantLites.isEmpty()) {
            return null;
        }
        return getMapper().fromEntity(applicantLites.get(0), mappingContext);
    }

    /**
     * Checks if an applicant exists with the same basic info
     *
     * @param applicantBasicInfo the applicant basic info
     * @return true if applicant with the same basic info exists
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public boolean existsByBasicInfo(ApplicantBasicInfoDto applicantBasicInfo) {
        return applicantLiteRepository.existsByBasicInfo(applicantBasicInfo.getIdNumber(), applicantBasicInfo.getDateOfBirthHijri(), applicantBasicInfo.getPassportNumber(), applicantBasicInfo.getDateOfBirthGregorian());
    }

    public Optional<ApplicantStaffVO> findApplicantRitualByIdNumber(String value) {
        List<ApplicantStaffVO> applicantList = applicantLiteRepository.findApplicantRitualByIdNumber(value, EDigitalIdStatus.VALID.name(), ECardStatus.CANCELLED.name(), ECardStatus.SUSPENDED.name());
        return applicantList.size() == 0? Optional.empty(): Optional.of(applicantList.get(0));
    }

    public Optional<ApplicantStaffVO> findApplicantRitualByUin(String uin) {
        List<ApplicantStaffVO> applicantList  = applicantLiteRepository.findApplicantRitualByUin(uin, EDigitalIdStatus.VALID.name(), ECardStatus.CANCELLED.name(), ECardStatus.SUSPENDED.name());
        return applicantList.size() == 0? Optional.empty(): Optional.of(applicantList.get(0));
    }
}
