/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.ritual;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStep;
import com.elm.shj.admin.portal.orm.entity.JpaGroupApplicantList;
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
    private final GroupApplicantListRepository groupApplicantListRepository;
    private final CompanyRitualStepRepository companyRitualStepRepository;

    /**
     * find group applicant list by uin
     * find company ritual steps by applicant group
     *
     * @return list of company ritual steps
     */
    public List<CompanyRitualStepDto> findByApplicantUin(String applicantUin){
        Optional<JpaGroupApplicantList> groupApplicantList = groupApplicantListRepository.findByApplicantUin(applicantUin);
        if(groupApplicantList.isPresent()){
            List<JpaCompanyRitualStep> companyRitualSteps = companyRitualStepRepository.findByApplicantGroupId(groupApplicantList.get().getApplicantGroup().getId());
            return mapList(companyRitualSteps);
        }
        return null;
    }
}
