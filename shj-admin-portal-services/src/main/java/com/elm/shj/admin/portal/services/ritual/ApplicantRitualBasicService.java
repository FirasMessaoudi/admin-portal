/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.ritual;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitualBasic;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualBasicRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualBasicDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Service handling applicant rituals
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantRitualBasicService extends GenericService<JpaApplicantRitualBasic, ApplicantRitualBasicDto, Long> {

    private final ApplicantRitualBasicRepository applicantRitualBasicRepository;

    /**
     * Find all applicants without digital IDs
     *
     * @return the list of applicants
     */
    public Page<ApplicantRitualBasicDto> findAllWithoutCards() {
        log.info("Start findAllWithoutCards");
        Page<ApplicantRitualBasicDto> applicantRitualIdsPage =  mapPage(applicantRitualBasicRepository.findWithExistingDigitalIdAndWithoutCard(PageRequest.of(0, 300)));
        log.info("Finish findAllWithoutCards with {} cards", applicantRitualIdsPage.getContent().size());
        return applicantRitualIdsPage;
    }
}
