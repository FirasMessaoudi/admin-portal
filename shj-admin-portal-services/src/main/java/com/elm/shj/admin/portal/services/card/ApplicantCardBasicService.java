/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.card;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantCardBasic;
import com.elm.shj.admin.portal.orm.repository.ApplicantCardBasicRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantCardBasicDto;
import com.elm.shj.admin.portal.services.dto.ECardStatus;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service handling applicant card
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantCardBasicService extends GenericService<JpaApplicantCardBasic, ApplicantCardBasicDto, Long> {

    private final ApplicantCardBasicRepository applicantCardBasicRepository;

    @Transactional
    public void deleteAllApplicantCards(Long applicantId) {
        log.info("Start deleteAllApplicantCards for {} applicant id.", applicantId);
        int noCardsDeleted = applicantCardBasicRepository.deleteAllApplicantCards(applicantId, ECardStatus.CANCELLED.name());
        log.info("Finish deleteAllApplicantCards for {} applicant id and impacted cards is {}.", applicantId, noCardsDeleted);
    }
}
