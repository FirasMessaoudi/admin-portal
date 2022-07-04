/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealthLite;
import com.elm.shj.admin.portal.orm.repository.ApplicantHealthLiteRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantHealthLiteDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service handling lightweight version of applicant health details
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantHealthLiteService extends GenericService<JpaApplicantHealthLite, ApplicantHealthLiteDto, Long> {

    private final ApplicantHealthLiteRepository applicantHealthLiteRepository;

    /**
     * Finds applicant's health details by applicant's uin and ritual id
     *
     * @param uin      the uin of the applicant
     * @param applicantPackageId
     * @return the found health details or empty structure
     */
    public Optional<ApplicantHealthLiteDto> findApplicantHealthDetailsByUinAndApplicantPackageId(String uin, Long applicantPackageId) {
        log.info("Start findApplicantHealthDetailsByUinAndApplicantPackageId uin:{}, applicantPackageId:{}", uin, applicantPackageId);
        JpaApplicantHealthLite healthProfile = applicantHealthLiteRepository.findByApplicantDigitalIdsUinAndApplicantRitualApplicantPackageId(uin, applicantPackageId);
        if (healthProfile == null) {
            log.info("Finish findApplicantHealthDetailsByUinAndApplicantPackageId not found with uin:{}, applicantPackageId:{}", uin, applicantPackageId);
            return Optional.empty();
        } else {
            ApplicantHealthLiteDto returnedDto = getMapper().fromEntity(healthProfile, mappingContext);
            log.info("Finish findApplicantHealthDetailsByUinAndApplicantPackageId uin:{}, applicantPackageId:{}", uin, applicantPackageId);
            return Optional.of(returnedDto);
        }
    }

    public Long findApplicantRitualIdByHealth(Long applicantHealthId) {
        log.info("Start findApplicantRitualIdByHealth applicantHealthId:{}", applicantHealthId);
        Long applicantRitualId = applicantHealthLiteRepository.findApplicantRitualId(applicantHealthId);
        log.info("Finish findApplicantRitualIdByHealth applicantHealthId:{}", applicantHealthId);
        return applicantRitualId;
    }

    public String findPackageByHealth(Long applicantHealthId) {
        log.info("Start findPackageByHealth applicantHealthId:{}", applicantHealthId);
        String applicantHealthPackage = applicantHealthLiteRepository.findApplicantHealthPackage(applicantHealthId);
        log.info("Finish findPackageByHealth applicantHealthId:{}", applicantHealthId);
        return applicantHealthPackage;
    }

}
