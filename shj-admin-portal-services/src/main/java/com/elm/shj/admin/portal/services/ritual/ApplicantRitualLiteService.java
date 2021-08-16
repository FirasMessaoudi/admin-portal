/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.ritual;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualLiteDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling applicant rituals
 *
 * @author Ahmed elsayed
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantRitualLiteService extends GenericService<JpaApplicantRitual, ApplicantRitualLiteDto, Long> {

    private final ApplicantRitualRepository applicantRitualRepository;

    public List<ApplicantRitualLiteDto> findApplicantRitualByUinAndSeason(String uin, int season) {
        return getMapper().fromEntityList(applicantRitualRepository.findApplicantRitualByUinAndSeason(uin, season), mappingContext);
    }

    public ApplicantRitualLiteDto findLatestApplicantRitualByUin(String uin) {
        return getMapper().fromEntity(applicantRitualRepository.findTopByApplicantDigitalIdsUinOrderByDateStartHijriDesc(uin), mappingContext);
    }

}
