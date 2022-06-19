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
import java.util.Optional;

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

    public List<ApplicantPackageHousingDto> findApplicantPackageHousingByUinAndApplicantPackageId(long applicantUin, long companyRitualSeasonId) {
        log.debug("Start findApplicantPackageHousingByUinAndApplicantPackageId uin:{} , companyRitualSeasonId:{}", applicantUin, companyRitualSeasonId);
        List<JpaApplicantPackageHousing> jpaApplicantPackageHousings = applicantPackageHousingRepository.findAllByApplicantPackageApplicantUinAndApplicantPackageId(applicantUin, companyRitualSeasonId);
        log.debug("Finish findAllByApplicantPackageApplicantUinAndPackageCateringPackageHousingId uin:{} , ApplicantPackageHousingsListSize:{}", applicantUin, jpaApplicantPackageHousings.size());
        return getMapper().fromEntityList(jpaApplicantPackageHousings, mappingContext);
    }

    public ApplicantPackageHousingDto findByApplicantPackageIdAndHousingPackageId(long applicantPackageId, long packageHousingId) {
        log.debug("Start findByApplicantPackageIdAndHousingPackageId applicantPackageId:{} , packageHousingId:{}", applicantPackageId, packageHousingId);
        Optional<JpaApplicantPackageHousing> jpaApplicantPackageHousing = applicantPackageHousingRepository.findTopByApplicantPackageIdAndPackageHousingId(applicantPackageId, packageHousingId);
        log.debug("Finish findByApplicantPackageIdAndHousingPackageId applicantPackageId:{} , packageHousingId:{}", applicantPackageId, packageHousingId);
        return jpaApplicantPackageHousing.isPresent() ? getMapper().fromEntity(jpaApplicantPackageHousing.get(), mappingContext) : null;
    }
}
