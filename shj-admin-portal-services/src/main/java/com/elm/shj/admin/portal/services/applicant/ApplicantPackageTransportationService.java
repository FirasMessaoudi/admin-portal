/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageTransportation;
import com.elm.shj.admin.portal.orm.repository.ApplicantPackageTransportationRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantPackageTransportationDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling applicant package transportation
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantPackageTransportationService extends GenericService<JpaApplicantPackageTransportation, ApplicantPackageTransportationDto, Long> {

    private final ApplicantPackageTransportationRepository applicantPackageTransportationRepository;

    public List<ApplicantPackageTransportationDto> findApplicantPackageTransportationByUinAndApplicantPackageId(long applicantUin, long companyRitualSeasonId) {
        log.info("Start findApplicantPackageTransportationByUinAndApplicantPackageId ::: applicantUin:{}, companyRitualSeasonId:{} ", applicantUin,  companyRitualSeasonId);
        List<ApplicantPackageTransportationDto> applicantPackageTransportationDtos = getMapper().fromEntityList(applicantPackageTransportationRepository.findAllByApplicantPackageApplicantUinAndApplicantPackageId(applicantUin, companyRitualSeasonId), mappingContext);
        log.info("Finish findApplicantPackageTransportationByUinAndApplicantPackageId ::: applicantPackageTransportationDtosListSize", applicantPackageTransportationDtos.size());
        return  applicantPackageTransportationDtos;
    }

}
