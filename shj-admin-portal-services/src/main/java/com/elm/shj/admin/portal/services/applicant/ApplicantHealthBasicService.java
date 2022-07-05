/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.ApplicantRitualPackageVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealthBasic;
import com.elm.shj.admin.portal.services.dto.ApplicantHealthBasicDto;
import com.elm.shj.admin.portal.services.dto.ApplicantLiteDto;
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
    private final ApplicantLiteService applicantLiteService;
    private final ApplicantPackageService applicantPackageService;

    @Transactional
    public ApplicantHealthBasicDto update(ApplicantHealthBasicDto applicantHealthDto) {
        log.info("start update applicant health with id {} ", applicantHealthDto.getId());
        String packageRefNumber;
        Long applicantRitualId;
        if (applicantHealthDto.getId() == 0) {
            ApplicantLiteDto applicantLiteDto = applicantLiteService.findOne(applicantHealthDto.getApplicant().getId());
            ApplicantRitualPackageVo applicantRitualPackageVo = applicantPackageService.findLatestApplicantRitualPackage(Long.parseLong(applicantLiteDto.getDigitalIds().get(0).getUin()));
            packageRefNumber = applicantRitualPackageVo.getPackageReferenceNumber();
            applicantRitualId = applicantRitualPackageVo.getApplicantRitualId();
        } else {
            packageRefNumber = applicantHealthLiteService.findPackageByHealth(applicantHealthDto.getId());
            applicantRitualId = applicantHealthLiteService.findApplicantRitualIdByHealth(applicantHealthDto.getId());
        }
        applicantHealthDto.setPackageReferenceNumber(packageRefNumber);
        if (applicantHealthDto.getDiseases() != null)
            applicantHealthDto.getDiseases().forEach(applicantHealthDiseaseBasicDto -> {
                applicantHealthDiseaseBasicDto.setApplicantHealth(applicantHealthDto);
            });
        if (applicantHealthDto.getImmunizations() != null)
            applicantHealthDto.getImmunizations().forEach(applicantHealthImmunizationBasicDto -> {
                applicantHealthImmunizationBasicDto.setApplicantHealth(applicantHealthDto);
            });
        if (applicantHealthDto.getHasSpecialNeeds() != null)
            applicantHealthDto.getSpecialNeeds().forEach(applicantHealthSpecialNeedsBasicDto -> {
                applicantHealthSpecialNeedsBasicDto.setApplicantHealth(applicantHealthDto);
            });
        ApplicantHealthBasicDto updatedHealth = save(applicantHealthDto);
        applicantHealthService.updateApplicantHealthApplicantRitual(applicantRitualId, applicantHealthDto.getApplicant().getId(), packageRefNumber);
        log.info("finish update applicant health with id {} ", updatedHealth.getId());

        return updatedHealth;
    }
}
