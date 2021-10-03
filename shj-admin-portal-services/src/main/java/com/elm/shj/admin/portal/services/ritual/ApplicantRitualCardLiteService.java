/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.ritual;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualRepository;
import com.elm.shj.admin.portal.services.applicant.CompanyRitualSeasonLiteService;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualCardLiteDto;
import com.elm.shj.admin.portal.services.dto.CompanyRitualSeasonLiteDto;
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
    private final CompanyRitualSeasonLiteService companyRitualSeasonLiteService;

    public Optional<ApplicantRitualCardLiteDto> findCardDetailsByUinAndRitualId(String uin, String companyRitualSeasonId) {

        CompanyRitualSeasonLiteDto companyRitualSeasonLiteDto = companyRitualSeasonLiteService.findOne(Long.parseLong(companyRitualSeasonId));

        if (companyRitualSeasonLiteDto == null) {
            return Optional.empty();
        }

        JpaApplicantRitual card = applicantRitualRepository.findByApplicantDigitalIdsUinAndApplicantPackageRitualPackageCompanyRitualSeasonId(uin, Long.parseLong(companyRitualSeasonId));
        if (card == null)
            return Optional.empty();
        ApplicantRitualCardLiteDto returnedDto = getMapper().fromEntity(card, mappingContext);
        returnedDto.setRitualType(companyRitualSeasonLiteDto.getRitualSeason().getRitualTypeCode().toUpperCase());
        returnedDto.setFullNameEn(card.getApplicant().getFullNameEn());
        returnedDto.setFullNameAr(card.getApplicant().getFullNameAr());
        returnedDto.setNationalityCode(card.getApplicant().getNationalityCode().toUpperCase());
        returnedDto.setPhoto(card.getApplicant().getPhoto());
        return Optional.of(returnedDto);
    }

}
