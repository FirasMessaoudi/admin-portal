/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicant;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.repository.ApplicantContactRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantBasicInfoDto;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.UpdateApplicantCmd;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    private final ApplicantRepository applicantRepository;
    private final ApplicantContactRepository applicantContactRepository;
    private final ApplicantRitualRepository applicantRitualRepository;
    private final ApplicantLiteService applicantLiteService;
    public final static String SAUDI_MOBILE_NUMBER_REGEX = "^(009665|9665|\\+9665|05|5)([0-9]{8})$";

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
     * Find all applicants without digital IDs
     *
     * @return the list of applicants
     */
    public List<ApplicantDto> findAllWithoutDigitalId() {
        return mapList(((ApplicantRepository) getRepository()).findAllApplicantsWithoutDigitalId());
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
     * Finds an applicant by his uin
     *
     * @param uin the uin of applicant to find
     * @return the found applicant or empty structure
     */
    public Optional<ApplicantDto> findByUin(String uin) {
        JpaApplicant applicant = applicantRepository.findByUin(uin);
        return (applicant != null) ? Optional.of(getMapper().fromEntity(applicant, mappingContext)) : Optional.empty();
    }


    /**
     * Finds an applicant by his uin
     *
     * @param applicantId the applicant ID
     * @param command     the request body
     * @return the lite version of applicant  or empty structure
     */
    @Transactional
    public void updateApplicantContacts(long applicantId, UpdateApplicantCmd command) {
        JpaApplicantRitual jpaApplicantRitual = applicantRitualRepository.findTopByApplicantDigitalIdsUinOrderByDateStartHijriDesc(command.getUin());

        if (command.getMobileNumber().matches(SAUDI_MOBILE_NUMBER_REGEX)) {
            applicantContactRepository.updateContactLocalNumber(command.getEmail(), command.getCountryCode(), command.getMobileNumber(), applicantId, jpaApplicantRitual.getId());
        } else {
            applicantContactRepository.updateContactIntNumber(command.getEmail(), command.getCountryCode(), command.getMobileNumber(), applicantId, jpaApplicantRitual.getId());
        }

    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public ApplicantDto save(ApplicantDto applicant) {
        // persist the record
        return super.save(applicant);
    }
}
