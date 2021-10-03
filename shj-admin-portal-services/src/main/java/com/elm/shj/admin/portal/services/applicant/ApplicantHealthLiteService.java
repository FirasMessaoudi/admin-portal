/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealth;
import com.elm.shj.admin.portal.orm.repository.ApplicantHealthRepository;
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
public class ApplicantHealthLiteService extends GenericService<JpaApplicantHealth, ApplicantHealthLiteDto, Long> {

    private final ApplicantHealthRepository applicantHealthRepository;

    /**
     * Finds applicant's health details by applicant's uin and ritual id
     *
     * @param uin      the uin of the applicant
     * @param ritualId
     * @return the found health details or empty structure
     */
    public Optional<ApplicantHealthLiteDto> findByUinAndRitualId(String uin, Long companyRitualSeasonId) {
        JpaApplicantHealth healthProfile = applicantHealthRepository.findByApplicantDigitalIdsUinAndApplicantRitualApplicantPackageRitualPackageCompanyRitualSeasonId(uin, companyRitualSeasonId);
        if (healthProfile == null) {
            return Optional.empty();
        } else {
            ApplicantHealthLiteDto returnedDto = getMapper().fromEntity(healthProfile, mappingContext);
            return Optional.of(returnedDto);
        }
    }

}
