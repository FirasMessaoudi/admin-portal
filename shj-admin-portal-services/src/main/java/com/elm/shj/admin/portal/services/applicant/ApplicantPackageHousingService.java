/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageHousing;
import com.elm.shj.admin.portal.orm.repository.ApplicantPackageHousingRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantPackageHousingDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling applicant package housing
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantPackageHousingService extends GenericService<JpaApplicantPackageHousing, ApplicantPackageHousingDto, Long> {

    private final ApplicantPackageHousingRepository applicantPackageHousingRepository;

    public List<ApplicantPackageHousingDto> findApplicantPackageHousingByUinAndCompanyRitualSeasonId(long applicantUin, long companyRitualSeasonId) {
        List<JpaApplicantPackageHousing> jpaApplicantPackageHousings = applicantPackageHousingRepository.findAllByApplicantPackageApplicantUinAndApplicantPackageRitualPackageCompanyRitualSeasonId(applicantUin, companyRitualSeasonId);
        return getMapper().fromEntityList(jpaApplicantPackageHousings, mappingContext);
    }
}
