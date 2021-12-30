/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.ritual;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.repository.ApplicantLiteRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualDto;
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
 * Service handling applicant rituals
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantRitualService extends GenericService<JpaApplicantRitual, ApplicantRitualDto, Long> {

    private final ApplicantRitualRepository applicantRitualRepository;

    /**
     * Find all applicants without digital IDs
     *
     * @return the list of applicants
     */
    public List<ApplicantRitualDto> findAllWithoutCards() {
        return mapList(((ApplicantRitualRepository) getRepository()).findAllApplicantRitualsWithoutCard());
    }

    /**
     * finds an applicant ritual by its ID
     *
     * @param applicantRitualId the data request id to find
     * @return the found applicant request or <code>null</code>
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantRitualDto findById(Long applicantRitualId) {
        return findOne(applicantRitualId);
    }

    public ApplicantRitualDto findByApplicantUinAndCompanyRitualSeasonId(String uin, Long companyRitualSeasonId) {
        JpaApplicantRitual applicantRitual = applicantRitualRepository.findByApplicantDigitalIdsUinAndApplicantPackageRitualPackageCompanyRitualSeasonId(uin, companyRitualSeasonId);
        return getMapper().fromEntity(applicantRitual, mappingContext);
    }

    @Transactional
    public ApplicantRitualDto findApplicantRitualWithContactsAndRelatives(Long applicantRitualId) {
        Optional<JpaApplicantRitual> applicantRitualOptional = applicantRitualRepository.findById(applicantRitualId);
        if (applicantRitualOptional.isPresent()) {
            JpaApplicantRitual applicantRitual = applicantRitualOptional.get();
            applicantRitual.getContacts().size();
            applicantRitual.getRelatives().size();
            applicantRitual.getApplicantHealths().size();
            return getMapper().fromEntity(applicantRitual, mappingContext);
        }

        return null;
    }

    /**
     * Find all  without applicant ID
     *
     * @return the list of applicants
     */
    public List<ApplicantRitualDto> findAllByApplicantId(Long id) {
        return mapList(((ApplicantRitualRepository) getRepository()).findAllByApplicantId(id));
    }

    public boolean exitsByRitualId(Long applicantRitualId){
        return ((ApplicantRitualRepository) getRepository()).existsById(applicantRitualId);
    }
}
