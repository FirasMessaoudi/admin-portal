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
        Page<ApplicantBasicDto> applicantLiteDtos = mapPage(applicantBasicRepository.findAllApplicantsWithoutDigitalId(PageRequest.of(0, 500)));
        log.info("Finish findAllWithoutDigitalId with {} digital ids", applicantLiteDtos.getContent().size());
        return applicantLiteDtos;
    }
}
