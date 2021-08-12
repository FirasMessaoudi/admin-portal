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

import java.util.List;

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
     * Find hijriSeasons by uin
     *
     * @return the list of hijriSeasons
     */
    public List<Integer> findHijriSeasonsByUin(String uin) {
        return applicantRitualRepository.findApplicantRitualHijriSeasonsByUin(uin);
    }


}
