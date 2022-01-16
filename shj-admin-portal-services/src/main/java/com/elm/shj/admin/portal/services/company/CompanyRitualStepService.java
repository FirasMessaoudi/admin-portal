/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.company;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStep;
import com.elm.shj.admin.portal.orm.entity.JpaGroupApplicantList;
import com.elm.shj.admin.portal.orm.repository.CompanyRitualStepRepository;
import com.elm.shj.admin.portal.orm.repository.GroupApplicantListRepository;
import com.elm.shj.admin.portal.services.dto.CompanyRitualStepDto;
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
    private final GroupApplicantListRepository groupApplicantListRepository;

    /**
     * find company ritual steps by applicant uin
     *
     * @return list of company ritual steps
     */
    public List<CompanyRitualStepDto> findCompanyRitualStepsByApplicantUin(String applicantUin) {
        try {
            //TODO: aflaifel: get the group id only, no need to get the group applicant list object.
            Optional<JpaGroupApplicantList> groupApplicantList = groupApplicantListRepository.findTopByApplicantUinOrderByCreationDateDesc(applicantUin);
            if (groupApplicantList.isPresent()) {
                List<JpaCompanyRitualStep> companyRitualSteps = companyRitualStepRepository.findByApplicantGroupIdOrderByStepIndexAsc(groupApplicantList.get().getApplicantGroup().getId());
                List<CompanyRitualStepDto> result = mapList(companyRitualSteps);
                result.forEach(companyRitualStep -> companyRitualStep.setReferenceNumber(companyRitualSteps.get(0).getApplicantGroup().getReferenceNumber()));
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }
}
