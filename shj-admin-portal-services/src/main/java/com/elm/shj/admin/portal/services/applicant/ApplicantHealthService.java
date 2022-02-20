/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealth;
import com.elm.shj.admin.portal.orm.repository.ApplicantHealthRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.ApplicantHealthDto;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service handling applicant health
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantHealthService extends GenericService<JpaApplicantHealth, ApplicantHealthDto, Long> {

    private final ApplicantHealthRepository applicantHealthRepository;

    /**
     * Find applicant health based on applicant id and package reference number.
     *
     * @param applicantId
     * @param packageReferenceNumber
     * @return
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantHealthDto findByApplicantIdAndPackageReferenceNumber(long applicantId, String packageReferenceNumber) {
        return getMapper().fromEntity(applicantHealthRepository.
                        findByApplicantIdAndPackageReferenceNumber(applicantId, packageReferenceNumber),
                mappingContext);
    }

    /**
     * Find applicant health id based on applicant id and package reference number.
     *
     * @param applicantId
     * @param packageReferenceNumber
     * @return
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    protected Long findIdByApplicantIdAndPackageReferenceNumber(long applicantId, String packageReferenceNumber) {
        return applicantHealthRepository.findIdByApplicantIdAndPackageReferenceNumber(applicantId, packageReferenceNumber);
    }

    @Transactional
    public Long findIdByApplicantIdAndPackageReferenceNumber(Long applicantId, String packageReferenceNumber, Long applicantRitualId, boolean createIfNotExist) {
        Long applicantHealthId = findIdByApplicantIdAndPackageReferenceNumber(applicantId, packageReferenceNumber);
        if (applicantHealthId == null && createIfNotExist) {
            ApplicantHealthDto applicantHealth = ApplicantHealthDto.builder().applicant(ApplicantDto.builder().id(applicantId).build()).packageReferenceNumber(packageReferenceNumber).build();
            if (applicantRitualId != null) {
                applicantHealth.setApplicantRitual(ApplicantRitualDto.builder().id(applicantRitualId).build());
            }
            applicantHealth = save(applicantHealth);
            applicantHealthId = applicantHealth.getId();
        }
        return applicantHealthId;
    }

    /**
     * Set applicant ritual id for the applicant health.
     *
     * @param applicantRitualId
     * @param applicantId
     * @param packageReferenceNumber
     * @return
     */
    @Transactional
    public int updateApplicantHealthApplicantRitual(long applicantRitualId, long applicantId, String packageReferenceNumber) {
        return applicantHealthRepository.updateApplicantHealthApplicantRitual(applicantRitualId, applicantId, packageReferenceNumber);
    }

}
