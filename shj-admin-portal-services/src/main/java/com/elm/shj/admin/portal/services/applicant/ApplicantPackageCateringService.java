/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageCatering;
import com.elm.shj.admin.portal.orm.repository.ApplicantPackageCateringRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantPackageCateringDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling applicant package catering
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantPackageCateringService extends GenericService<JpaApplicantPackageCatering, ApplicantPackageCateringDto, Long> {

    private final ApplicantPackageCateringRepository applicantPackageCateringRepository;

    public List<ApplicantPackageCateringDto> findApplicantPackageCateringByUinAndCompanyRitualSeasonId(long applicantUin, long companyRitualSeasonId) {
        return getMapper().fromEntityList(applicantPackageCateringRepository.findAllByApplicantPackageApplicantUinAndApplicantPackageRitualPackageCompanySeasonPackagesCompanyRitualSeasonId(applicantUin, companyRitualSeasonId), mappingContext);
    }


}
