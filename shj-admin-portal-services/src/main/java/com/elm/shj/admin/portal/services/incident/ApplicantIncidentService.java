/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.incident;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantIncident;
import com.elm.shj.admin.portal.services.dto.ApplicantIncidentDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling Applicant Incident operations
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Service
@Slf4j
public class ApplicantIncidentService extends GenericService<JpaApplicantIncident, ApplicantIncidentDto, Long> {
}
