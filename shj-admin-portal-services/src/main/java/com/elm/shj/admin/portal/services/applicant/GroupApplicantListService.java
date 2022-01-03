/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaGroupApplicantList;
import com.elm.shj.admin.portal.orm.repository.GroupApplicantListRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantGroupDto;
import com.elm.shj.admin.portal.services.dto.GroupApplicantListDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service handling group applicant lite domain.
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class GroupApplicantListService extends GenericService<JpaGroupApplicantList, GroupApplicantListDto, Long> {

    private final GroupApplicantListRepository groupApplicantListRepository;
    private final ApplicantGroupService applicantGroupService;

    public boolean registerUserToGroup(String applicantUin, String referenceNumber) {

        ApplicantGroupDto applicantGroup = applicantGroupService.getApplicantGroupByReferenceNumber(referenceNumber);

        if (applicantGroup != null) {
            Optional<JpaGroupApplicantList> groupApplicantListOptional = groupApplicantListRepository.findByApplicantUinAndApplicantGroupReferenceNumber(applicantUin, referenceNumber);
            if (!groupApplicantListOptional.isPresent()) {
                GroupApplicantListDto groupApplicantListDto = GroupApplicantListDto.builder().applicantUin(applicantUin).applicantGroup(applicantGroup).build();
                this.save(groupApplicantListDto);
                return true;
            }

        }

        return false;
    }
}
