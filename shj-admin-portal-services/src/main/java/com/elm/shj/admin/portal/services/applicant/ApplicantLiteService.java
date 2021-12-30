/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantLite;
import com.elm.shj.admin.portal.orm.repository.ApplicantLiteRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantLiteDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            applicantLiteDto.setHasLocalMobileNumber(applicant.getContacts().get(0).getLocalMobileNumber() != null);
            applicantLiteDto.setGender(applicant.getGender());
            return Optional.of(applicantLiteDto);
        } else return Optional.empty();
    }

    public boolean existsByUin(String uin) {
        return ((ApplicantLiteRepository) getRepository()).existsByUin(uin);

    }
}