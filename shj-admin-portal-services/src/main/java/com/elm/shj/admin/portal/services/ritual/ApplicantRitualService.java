/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.ritual;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
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
        return mapList(applicantRitualRepository.findWithExistingDigitalIdAndWithoutCard());
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

    public ApplicantRitualDto findByApplicantUinAndApplicantPackageId(String uin, Long applicantPackageId) {
        Optional <JpaApplicantRitual> applicantRitual = applicantRitualRepository.findByApplicantDigitalIdsUinAndApplicantPackageId(uin, applicantPackageId);
        if(applicantRitual.isPresent()) {
            return getMapper().fromEntity(applicantRitual.get(), mappingContext);
        }
        return null;
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
        return mapList(applicantRitualRepository.findAllByApplicantId(id));
    }

    public boolean findByRitualIdAndApplicantUin(Long applicantRitualId, String uin){
        JpaApplicantRitual ritual = applicantRitualRepository.findCardDetailsByUinAndRitualId(uin, applicantRitualId);
        return ritual != null ? true : false;
    }

    /**
     * Find latest applicant ritual.
     *
     * @param uin
     * @return
     */
    public ApplicantRitualDto findLatestApplicantRitual(String uin) {
        return getMapper().fromEntity(applicantRitualRepository.findFirstByApplicantDigitalIdsUinOrderByCreationDateDesc(uin), mappingContext);
    }

    /**
     * Find uncommitted applicant ritual based on applicant id and package reference number.
     *
     * @param applicantId
     * @param referenceNumber
     * @return
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantRitualDto findDirtyByApplicantIdAndPackageReferenceNumber(long applicantId, String referenceNumber) {
        return getMapper().fromEntity(applicantRitualRepository.findByApplicantIdAndPackageReferenceNumber(applicantId, referenceNumber), mappingContext);
    }

    /**
     * Set applicant package for the applicant ritual.
     *
     * @param newApplicantPackageId
     * @param applicantRitualId
     */
    @Transactional
    public void updateApplicantRitualApplicantPackage(long newApplicantPackageId, long applicantRitualId) {
        applicantRitualRepository.updateApplicantRitualApplicantPackage(newApplicantPackageId, applicantRitualId);
    }

    /**
     * Find applicant ritual id based on applicant id and package reference number.
     *
     * @param applicantId
     * @param packageReferenceNumber
     * @return
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Long findIdByApplicantIdAndPackageReferenceNumber(long applicantId, String packageReferenceNumber) {
        return applicantRitualRepository.findIdByApplicantIdAndPackageReferenceNumber(applicantId, packageReferenceNumber);
    }
}
