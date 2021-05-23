/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.card;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantCard;
import com.elm.shj.admin.portal.orm.repository.ApplicantCardRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantCardDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service handling applicant card
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantCardService extends GenericService<JpaApplicantCard, ApplicantCardDto, Long> {

    private final ApplicantCardRepository applicantCardRepository;

    /**
     * Find all applicants cards.
     *
     * @param pageable the current page information
     * @return the list of applicants cards
     */
    public Page<ApplicantCardDto> findAll(Pageable pageable) {
        return mapPage(getRepository().findAll(pageable));
    }

    /**
     * Find applicants cards with status ready to print.
     *
     * @param pageable the current page information
     * @return the list of ready to print applicants cards
     */
    public Page<ApplicantCardDto> findReadyToPrint(Pageable pageable) {
        return mapPage(applicantCardRepository.findByStatusCode("READY_TO_PRINT", pageable));
    }
}
