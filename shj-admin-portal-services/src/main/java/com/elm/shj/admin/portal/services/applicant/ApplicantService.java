/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicant;
import com.elm.shj.admin.portal.orm.repository.ApplicantRepository;
import com.elm.shj.admin.portal.services.digitalid.DigitalIdService;
import com.elm.shj.admin.portal.services.dto.ApplicantBasicInfoDto;
import com.elm.shj.admin.portal.services.dto.ApplicantDigitalIdDto;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * Service handling applicant
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantService extends GenericService<JpaApplicant, ApplicantDto, Long> {

    private final DigitalIdService digitalIdService;

    /**
     * Find all applicants.
     *
     * @param pageable the current page information
     * @return the list of applicants
     */
    public Page<ApplicantDto> findAll(Pageable pageable) {
        return mapPage(getRepository().findAll(pageable));
    }

    /**
     * finds an applicant with the same basic info exists
     *
     * @param applicantBasicInfo the applicant basic info
     * @return the found applicant with the same basic info exists
     */
    public ApplicantDto findByBasicInfo(ApplicantBasicInfoDto applicantBasicInfo) {
        return getMapper().fromEntity(((ApplicantRepository) getRepository()).findByBasicInfo(applicantBasicInfo.getIdNumber(), applicantBasicInfo.getDateOfBirthHijri(), applicantBasicInfo.getPassportNumber(), applicantBasicInfo.getDateOfBirthGregorian()), mappingContext);
    }

    /**
     * checks if an applicant exists with the same basic info exists
     *
     * @param applicantBasicInfo the applicant basic info
     * @return if applicant with the same basic info exists
     */
    public boolean existsByBasicInfo(ApplicantBasicInfoDto applicantBasicInfo) {
        return ((ApplicantRepository) getRepository()).existsByBasicInfo(applicantBasicInfo.getIdNumber(), applicantBasicInfo.getDateOfBirthHijri(), applicantBasicInfo.getPassportNumber(), applicantBasicInfo.getDateOfBirthGregorian());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public ApplicantDto save(ApplicantDto applicant) {
        // generate digital id before save
        applicant.setDigitalIds(Collections.singletonList(ApplicantDigitalIdDto.builder().applicant(applicant).uin(digitalIdService.generate(applicant, null)).build()));
        // persist the record
        return super.save(applicant);
    }
}
