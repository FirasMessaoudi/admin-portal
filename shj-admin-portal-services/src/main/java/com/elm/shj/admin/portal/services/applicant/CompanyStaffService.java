/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaff;
import com.elm.shj.admin.portal.orm.repository.CompanyStaffRepository;
import com.elm.shj.admin.portal.services.dto.CompanyStaffDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyStaffService extends GenericService<JpaCompanyStaff, CompanyStaffDto, Long> {

    private final CompanyStaffRepository companyStaffRepository;


    public List<CompanyStaffDto> findRelatedEmployeesByApplicantUinAndSeasonId(String uin, long sid){
        return mapList(companyStaffRepository.findByApplicantGroupsGroupApplicantListsApplicantUinAndCompanyRitualSeasonId(uin, sid));}

}
