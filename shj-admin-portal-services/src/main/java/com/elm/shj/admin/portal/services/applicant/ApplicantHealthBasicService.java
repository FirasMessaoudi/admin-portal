/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealthBasic;
import com.elm.shj.admin.portal.services.dto.ApplicantHealthBasicDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service handling basic version of applicant health details
 *
 * @author f.messaoudi
 * @since 1.3.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantHealthBasicService extends GenericService<JpaApplicantHealthBasic, ApplicantHealthBasicDto, Long> {

    private final ApplicantHealthService applicantHealthService;
    private final ApplicantHealthLiteService applicantHealthLiteService;

    @Transactional
    public ApplicantHealthBasicDto update(ApplicantHealthBasicDto applicantHealthDto) {
        applicantHealthDto.setPackageReferenceNumber(applicantHealthLiteService.findPackageByHealth(applicantHealthDto.getId()));
        applicantHealthDto.getDiseases().forEach(applicantHealthDiseaseBasicDto -> {
            applicantHealthDiseaseBasicDto.setApplicantHealth(applicantHealthDto);
        });
        applicantHealthDto.getImmunizations().forEach(applicantHealthImmunizationBasicDto -> {
            applicantHealthImmunizationBasicDto.setApplicantHealth(applicantHealthDto);
        });
        applicantHealthDto.getSpecialNeeds().forEach(applicantHealthSpecialNeedsBasicDto -> {
            applicantHealthSpecialNeedsBasicDto.setApplicantHealth(applicantHealthDto);
        });
        ApplicantHealthBasicDto updatedHealth = save(applicantHealthDto);
        applicantHealthService.updateApplicantHealthApplicantRitual(applicantHealthLiteService.findApplicantRitualIdByHealth(applicantHealthDto.getId()), applicantHealthDto.getApplicant().getId(), applicantHealthDto.getPackageReferenceNumber());
        return updatedHealth;
    }
}
