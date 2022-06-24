/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;


import com.elm.shj.admin.portal.orm.entity.JpaApplicantBasic;
import com.elm.shj.admin.portal.orm.repository.ApplicantBasicRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantBasicDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service handling lightweight version of applicant
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantBasicService extends GenericService<JpaApplicantBasic, ApplicantBasicDto, Long> {

    private final ApplicantBasicRepository applicantBasicRepository;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Page<ApplicantBasicDto> findAllWithoutDigitalId() {
        log.info("Start findAllWithoutDigitalId");
        Page<ApplicantBasicDto> applicantLiteDtos = mapPage(applicantBasicRepository.findAllApplicantsWithoutDigitalId(PageRequest.of(0, 1000)));
        log.info("Finish findAllWithoutDigitalId with {} digital ids", applicantLiteDtos.getContent().size());
        return applicantLiteDtos;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Long findIdByBasicInfo(String idNumber, String passportNumber, String nationalityCode) {
        log.info("Start findIdByBasicInfo for {} id number, {} passport number, {} nationality code.", idNumber, passportNumber, nationalityCode);
        Long applicantId = applicantBasicRepository.findIdByBasicInfo(idNumber, passportNumber, nationalityCode);
        log.info("Finish findIdByBasicInfo and found {} applicant id.", applicantId);
        return applicantId;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public String findPackageReferenceNumberById(Long applicantId) {
        log.info("Start findPackageReferenceNumberById for {} id.", applicantId);
        String packageRefNumber = applicantBasicRepository.findPackageReferenceNumberById(applicantId);
        log.info("Finish findPackageReferenceNumberById and found {} packageRefNumber.", packageRefNumber);
        return packageRefNumber;
    }
}
