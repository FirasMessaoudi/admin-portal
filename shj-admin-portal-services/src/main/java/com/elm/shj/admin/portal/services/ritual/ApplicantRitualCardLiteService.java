/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.ritual;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualRepository;
import com.elm.shj.admin.portal.services.applicant.CompanyRitualSeasonLiteService;
import com.elm.shj.admin.portal.services.applicant.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualCardLiteDto;
import com.elm.shj.admin.portal.services.dto.CompanyRitualSeasonLiteDto;
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

    public Optional<ApplicantRitualCardLiteDto> findCardDetailsByUinAndRitualId(String uin, String companyRitualSeasonId) {

        CompanyRitualSeasonLiteDto companyRitualSeasonLiteDto = companyRitualSeasonLiteService.findOne(Long.parseLong(companyRitualSeasonId));

        if (companyRitualSeasonLiteDto == null) {
            return Optional.empty();
        }

        JpaApplicantRitual applicantRitual = applicantRitualRepository.findByApplicantDigitalIdsUinAndApplicantPackageRitualPackageCompanyRitualSeasonId(uin, Long.parseLong(companyRitualSeasonId));

        if (applicantRitual == null)
            return Optional.empty();

        List<CompanyStaffDto> groupLeaders = companyStaffService.findRelatedEmployeesByApplicantUinAndSeasonId(uin, Long.parseLong(companyRitualSeasonId));

        ApplicantRitualCardLiteDto returnedDto = getMapper().fromEntity(applicantRitual, mappingContext);
        returnedDto.setRitualType(companyRitualSeasonLiteDto.getRitualSeason().getRitualTypeCode().toUpperCase());
        returnedDto.setFullNameEn(applicantRitual.getApplicant().getFullNameEn());
        returnedDto.setFullNameAr(applicantRitual.getApplicant().getFullNameAr());
        returnedDto.setNationalityCode(applicantRitual.getApplicant().getNationalityCode().toUpperCase());
        returnedDto.setPhoto(applicantRitual.getApplicant().getPhoto());
        returnedDto.setHijriSeason(companyRitualSeasonLiteDto.getRitualSeason().getSeasonYear());
        //TODO: get the first leader, this logic has to be reviewed when card business requirement is more clear.
        returnedDto.setLeaderMobile(groupLeaders.get(0).getMobileNumber());
        returnedDto.setLeaderNameAr(groupLeaders.get(0).getFullNameAr());
        returnedDto.setLeaderNameEn(groupLeaders.get(0).getFullNameEn());

        return Optional.of(returnedDto);
    }

}
