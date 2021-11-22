/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.incident;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantIncident;
import com.elm.shj.admin.portal.orm.repository.ApplicantIncidentRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantIncidentDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service handling Applicant Incident operations
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantIncidentService extends GenericService<JpaApplicantIncident, ApplicantIncidentDto, Long> {

    private final ApplicantIncidentRepository applicantIncidentRepository;
    /**
     * List of applicant related incidents.
     * @param  applicantRitualId
     * @return  List of applicant related incidents
     */

    public List<ApplicantIncidentDto> listApplicantRelatedIncidents(long applicantRitualId){
        return  mapList(applicantIncidentRepository.findByApplicantRitualId(applicantRitualId));
    }


}
