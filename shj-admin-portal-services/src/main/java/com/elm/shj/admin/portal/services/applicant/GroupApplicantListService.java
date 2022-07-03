/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.ApplicantVo;
import com.elm.shj.admin.portal.orm.entity.JpaGroupApplicantList;
import com.elm.shj.admin.portal.orm.repository.GroupApplicantListRepository;
import com.elm.shj.admin.portal.services.company.CompanyRitualSeasonService;
import com.elm.shj.admin.portal.services.dto.ApplicantGroupDto;
import com.elm.shj.admin.portal.services.dto.GroupApplicantListDto;
import com.elm.shj.admin.portal.services.dto.UpdateGroupCmd;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
    private final CompanyRitualSeasonService companyRitualSeasonService;
    @Transactional
    public boolean registerUserToGroup(String applicantUin, String referenceNumber) {
        log.info("GroupApplicantListService ::: Start registerUserToGroup  applicantUin: {} ,  referenceNumber: {}", applicantUin, referenceNumber);
        ApplicantGroupDto applicantGroup = applicantGroupService.getApplicantGroupByReferenceNumber(referenceNumber);

        if (applicantGroup != null) {
            Optional<JpaGroupApplicantList> groupApplicantListOptional = groupApplicantListRepository.findByApplicantUinAndApplicantGroupReferenceNumber(applicantUin, referenceNumber);
            if (!groupApplicantListOptional.isPresent()) {
                GroupApplicantListDto groupApplicantListDto = GroupApplicantListDto.builder().applicantUin(applicantUin).applicantGroup(applicantGroup).build();
                List<GroupApplicantListDto> groupList = new ArrayList<>();
                groupList.add(groupApplicantListDto);
                applicantGroup.setGroupApplicantLists(groupList);
                save(groupApplicantListDto);
                log.info("GroupApplicantListService ::: Finish registerUserToGroup  return : {}", true);
                return true;
            }

        }
        log.info("GroupApplicantListService ::: Finish registerUserToGroup  return : {}", false);
        return false;
    }

    /**
     * will return all ApplicantVo values except lat and lng
     */
    public List<ApplicantVo> findGroupApplicantListBySuin(String suin) {
        log.info("GroupApplicantListService ::: Start findGroupApplicantListBySuin  suin: {}", suin);
        //return getMapper().fromEntityList(groupApplicantListRepository.findByApplicantGroupGroupLeaderDigitalIdsSuin(suin), mappingContext);
        List<ApplicantVo> applicantLiteDtoList = groupApplicantListRepository.findApplicantDetailsWithoutLocationByGroupeLeaderSuin(suin);
        //applicantLiteDtoList.removeAll(Collections.singleton(null));
        log.info("GroupApplicantListService ::: Start findGroupApplicantListBySuin  applicantLiteDtoListSize: {}", applicantLiteDtoList.size());
        return applicantLiteDtoList;
    }

    /**
     * will return ApplicantVo only values uin, lat and lng
     */
    public List<ApplicantVo> findGroupApplicantsLastLocationsBySuin(String suin) {
        log.info("GroupApplicantListService ::: Start findGroupApplicantsLastLocationsBySuin  suin: {}", suin);
        List<ApplicantVo> applicantLiteDtoList = groupApplicantListRepository.findApplicantLocationsByGroupeLeaderSuin(suin);
        //applicantLiteDtoList.removeAll(Collections.singleton(null));
        log.info("GroupApplicantListService ::: Start findGroupApplicantsLastLocationsBySuin  applicantLiteDtoListSize: {}", applicantLiteDtoList.size());
        return applicantLiteDtoList;
    }

    public GroupApplicantListDto findByUin(String uin, long id) {
        Optional<JpaGroupApplicantList> groupApplicantList = groupApplicantListRepository.findTopByApplicantUinAndApplicantGroupIdOrderByCreationDateDesc(uin, id);
        return groupApplicantList.map(jpaGroupApplicantList -> getMapper().fromEntity(jpaGroupApplicantList, mappingContext)).orElse(null);
    }

    public boolean updateGroup(UpdateGroupCmd updateGroupCmd) {
        log.info("GroupApplicantListService ::: Start updateGroup  oldGroup: {}", updateGroupCmd.getOldGroup());

        ApplicantGroupDto applicantGroupDto = applicantGroupService.getApplicantGroupByReferenceNumberAndCompany(updateGroupCmd.getNewGroup(), updateGroupCmd.getCompanyCode());
        if (applicantGroupDto == null) {
            return false;
        }

        GroupApplicantListDto existingGroupApplicantList = getMapper().fromEntity(groupApplicantListRepository.findTopByApplicantUinOrderByCreationDateDesc(updateGroupCmd.getUin()).orElse(null), mappingContext);

        if (existingGroupApplicantList != null) {
            existingGroupApplicantList.setApplicantGroup(applicantGroupDto);
            save(existingGroupApplicantList);
            return true;
        } else {
            GroupApplicantListDto groupApplicantListDto = GroupApplicantListDto.builder()
                    .applicantGroup(applicantGroupDto)
                    .applicantUin(updateGroupCmd.getUin()).build();
            save(groupApplicantListDto);
            return true;
        }

    }

    public Integer findApplicantEstablishment(String uin) {
        log.info("GroupApplicantListService ::: Start findApplicantEstablishment  uin: {}", uin);
        return groupApplicantListRepository.findApplicantEstablishment(uin);
    }

    public GroupApplicantListDto findByUin(String uin){
        return  getMapper().fromEntity(groupApplicantListRepository.findTopByApplicantUinOrderByCreationDateDesc(uin).orElse(null), mappingContext);
    }
}
