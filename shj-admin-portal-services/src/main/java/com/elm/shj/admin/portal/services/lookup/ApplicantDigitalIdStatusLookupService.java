/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantDigitalIdStatusLookup;
import com.elm.shj.admin.portal.services.dto.ApplicantDigitalIdStatusLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling applicant digital ID status lookup
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Service
@Slf4j
public class ApplicantDigitalIdStatusLookupService extends GenericService<JpaApplicantDigitalIdStatusLookup, ApplicantDigitalIdStatusLookupDto, Long> {
}
