/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicant;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Service handling applicant
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Service
public class ApplicantService extends GenericService<JpaApplicant, ApplicantDto, Long> {

    /**
     * Find all applicants.
     *
     * @param pageable the current page information
     * @return the list of applicants
     */
    public Page<ApplicantDto> findAll(Pageable pageable) {
        return mapPage(getRepository().findAll(pageable));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public ApplicantDto save(ApplicantDto applicant)
    {
        if (applicant.getId() != 0)
            applicant.setUpdateDate(new Date());
        else
            applicant.setCreationDate(new Date());
        return super.save(applicant);
    }
}
