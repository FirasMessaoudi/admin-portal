/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.ritual;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualRepository;
import com.elm.shj.admin.portal.services.applicant.ApplicantPackageService;
import com.elm.shj.admin.portal.services.company.CompanyRitualSeasonLiteService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.ApplicantPackageDto;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualCardLiteDto;
import com.elm.shj.admin.portal.services.dto.CompanyStaffDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service handling applicant rituals
 *
 * @author Ahmed ali
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantRitualCardLiteService extends GenericService<JpaApplicantRitual, ApplicantRitualCardLiteDto, Long> {

    private final ApplicantRitualRepository applicantRitualRepository;
    private final CompanyRitualSeasonLiteService companyRitualSeasonLiteService;
    private final CompanyStaffService companyStaffService;
    private final ApplicantPackageService applicantPackageService;

    public Optional<ApplicantRitualCardLiteDto> findCardDetailsByUinAndRitualId(String uin, long applicantPackageId) {

        ApplicantPackageDto applicantPackageDto = applicantPackageService.findOne(applicantPackageId);

        if (applicantPackageDto == null) {
            return Optional.empty();
        }

        Optional<JpaApplicantRitual> applicantRitual = applicantRitualRepository.findByApplicantDigitalIdsUinAndApplicantPackageId(uin, applicantPackageId);

        if (!applicantRitual.isPresent())
            return Optional.empty();

        List<CompanyStaffDto> groupLeaders = companyStaffService.findRelatedEmployeesByApplicantUinAndSeasonId(uin, applicantPackageDto.getRitualPackage().getCompanyRitualSeason().getId());

        ApplicantRitualCardLiteDto returnedDto = getMapper().fromEntity(applicantRitual.get(), mappingContext);
        returnedDto.setRitualType(applicantPackageDto.getRitualPackage().getCompanyRitualSeason().getRitualSeason().getRitualTypeCode().toUpperCase());
        returnedDto.setFullNameEn(applicantRitual.get().getApplicant().getFullNameEn());
        returnedDto.setFullNameAr(applicantRitual.get().getApplicant().getFullNameAr());
        returnedDto.setNationalityCode(applicantRitual.get().getApplicant().getNationalityCode().toUpperCase());
        returnedDto.setPhoto(applicantRitual.get().getApplicant().getPhoto());
        returnedDto.setHijriSeason(applicantPackageDto.getRitualPackage().getCompanyRitualSeason().getRitualSeason().getSeasonYear());
        //TODO: get the first leader, this logic has to be reviewed when card business requirement is more clear.
        returnedDto.setLeaderMobile(groupLeaders.get(0).getMobileNumber());
        returnedDto.setLeaderNameAr(groupLeaders.get(0).getFullNameAr());
        returnedDto.setLeaderNameEn(groupLeaders.get(0).getFullNameEn());

        return Optional.of(returnedDto);
    }

}
