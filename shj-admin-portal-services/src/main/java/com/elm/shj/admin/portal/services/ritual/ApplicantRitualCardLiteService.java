/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.ritual;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualRepository;
import com.elm.shj.admin.portal.services.applicant.ApplicantPackageService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.ApplicantPackageDto;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualCardLiteDto;
import com.elm.shj.admin.portal.services.dto.CompanyStaffDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private final CompanyStaffService companyStaffService;
    private final ApplicantPackageService applicantPackageService;

    public Optional<ApplicantRitualCardLiteDto> findCardDetailsByUinAndPackageId(String uin, long applicantPackageId) {

        ApplicantPackageDto applicantPackageDto = applicantPackageService.findOne(applicantPackageId);

        if (applicantPackageDto == null) {
            return Optional.empty();
        }

        Optional<JpaApplicantRitual> applicantRitual = applicantRitualRepository.findByApplicantDigitalIdsUinAndApplicantPackageId(uin, applicantPackageId);

        if (!applicantRitual.isPresent())
            return Optional.empty();

        Optional<CompanyStaffDto> groupLeader = companyStaffService.findGroupLeaderByApplicantUin(uin, applicantPackageDto.getRitualPackage().getCompanyRitualSeason().getId());
        if (!groupLeader.isPresent())
            return Optional.empty();
        ApplicantRitualCardLiteDto returnedDto = getMapper().fromEntity(applicantRitual.get(), mappingContext);
        returnedDto.setRitualType(applicantPackageDto.getRitualPackage().getCompanyRitualSeason().getRitualSeason().getRitualTypeCode().toUpperCase());
        returnedDto.setFullNameEn(applicantRitual.get().getApplicant().getFullNameEn());
        returnedDto.setFullNameAr(applicantRitual.get().getApplicant().getFullNameAr());
        returnedDto.setNationalityCode(applicantRitual.get().getApplicant().getNationalityCode().toUpperCase());
        returnedDto.setPhoto(applicantRitual.get().getApplicant().getPhoto());
        returnedDto.setHijriSeason(applicantPackageDto.getRitualPackage().getCompanyRitualSeason().getRitualSeason().getSeasonYear());
        //TODO: get the first leader, this logic has to be reviewed when card business requirement is more clear.
        returnedDto.setLeaderMobile(groupLeader.get().getMobileNumber());
        returnedDto.setLeaderNameAr(groupLeader.get().getFullNameAr());
        returnedDto.setLeaderNameEn(groupLeader.get().getFullNameEn());

        return Optional.of(returnedDto);
    }

}
