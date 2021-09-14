/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualSeason;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStep;
import com.elm.shj.admin.portal.orm.entity.JpaGroupApplicantList;
import com.elm.shj.admin.portal.orm.repository.CompanyRitualSeasonRepository;
import com.elm.shj.admin.portal.orm.repository.CompanyRitualStepRepository;
import com.elm.shj.admin.portal.orm.repository.GroupApplicantListRepository;
import com.elm.shj.admin.portal.services.dto.CompanyRitualStepDto;
import com.elm.shj.admin.portal.services.dto.GroupApplicantListDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service handling company ritual step
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyRitualStepService extends GenericService<JpaCompanyRitualStep, CompanyRitualStepDto, Long> {
    private final CompanyRitualStepRepository companyRitualStepRepository;
    private final CompanyRitualSeasonRepository companyRitualSeasonRepository;
    /**
     * find company ritual steps by applicant uin
     *
     * @return list of company ritual steps
     */
    public List<CompanyRitualStepDto> findByApplicantUin(String applicantUin){
            JpaCompanyRitualSeason jpaCompanyRitualSeason = companyRitualSeasonRepository.findTopByApplicantGroupsGroupApplicantListsApplicantUinOrderBySeasonStartDesc(applicantUin);
            if(jpaCompanyRitualSeason!=null) {
                List<JpaCompanyRitualStep> companyRitualSteps = companyRitualStepRepository.findByApplicantGroupGroupApplicantListsApplicantUinAndApplicantGroupCompanyRitualSeasonIdOrderByStepIndexAsc(applicantUin, jpaCompanyRitualSeason.getId());
                return mapList(companyRitualSteps);
            }
            return null;
    }
}
