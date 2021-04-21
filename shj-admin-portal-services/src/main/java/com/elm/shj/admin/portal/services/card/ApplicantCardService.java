/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.card;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantCard;
import com.elm.shj.admin.portal.services.dto.ApplicantCardDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
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
public class ApplicantCardService extends GenericService<JpaApplicantCard, ApplicantCardDto, Long> {

    /**
     * Find all applicants cards.
     *
     * @param pageable the current page information
     * @return the list of applicants cards
     */
    public Page<ApplicantCardDto> findAll(Pageable pageable) {
        return mapPage(getRepository().findAll(pageable));
    }
}
