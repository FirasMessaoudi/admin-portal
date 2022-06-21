/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.complaint;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantComplaintLite;
import com.elm.shj.admin.portal.orm.repository.ApplicantComplaintLiteRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantComplaintLiteDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service handling Applicant Complaint operations
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicantComplaintLiteService extends GenericService<JpaApplicantComplaintLite, ApplicantComplaintLiteDto, Long> {

    private final ApplicantComplaintLiteRepository applicantComplaintLiteRepository;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ApplicantComplaintLiteDto findByCrmTicketNumber(String crmTicketNumber) {
        return getMapper().fromEntity(applicantComplaintLiteRepository.findByCrmTicketNumber(crmTicketNumber), mappingContext);
    }


}

