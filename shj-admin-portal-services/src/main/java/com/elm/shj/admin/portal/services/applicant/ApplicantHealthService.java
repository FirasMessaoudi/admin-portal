/*
 * Copyright (c) 2022 ELM. All rights reserved.
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
