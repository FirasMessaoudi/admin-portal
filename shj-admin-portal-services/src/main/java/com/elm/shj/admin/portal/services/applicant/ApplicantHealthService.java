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
    public ApplicantHealthDto findByApplicantIdAndPackageReferenceNumber(long applicantId, String packageReferenceNumber) {
        return getMapper().fromEntity(applicantHealthRepository.
                        findByApplicantIdAndApplicantRitualPackageReferenceNumber(applicantId, packageReferenceNumber),
                mappingContext);
    }
}
