/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealth;
import com.elm.shj.admin.portal.orm.repository.ApplicantHealthRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantHealthDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service handling applicant health details
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantHealthService extends GenericService<JpaApplicantHealth, ApplicantHealthDto, Long> {

    private final ApplicantHealthRepository applicantHealthRepository;

    /**
     * Finds applicant's health details by applicant's uin
     *
     * @param uin the uin of the applicant
     * @return the found health details or empty structure
     */
    public Optional<ApplicantHealthDto> findByUin(String uin) {
        JpaApplicantHealth applicantHealth = applicantHealthRepository.findByUin(uin);
        if (applicantHealth != null) {
            return Optional.of(getMapper().fromEntity(applicantHealth, mappingContext));
        } else return Optional.empty();
    }

}
