package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.ApplicantGroupDetailsVo;
import com.elm.shj.admin.portal.orm.entity.CompanyStaffVO;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantGroup;
import com.elm.shj.admin.portal.orm.repository.ApplicantGroupRepository;
import com.elm.shj.admin.portal.orm.repository.GroupApplicantListRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantGroupDto;
import com.elm.shj.admin.portal.services.dto.EUserType;
import com.elm.shj.admin.portal.services.dto.GroupNameLookupDto;
import com.elm.shj.admin.portal.services.dto.UserLocationDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.user.UserLocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantGroupService extends GenericService<JpaApplicantGroup, ApplicantGroupDto, Long> {

    private final ApplicantGroupRepository applicantGroupRepository;
    private final GroupApplicantListRepository groupApplicantListRepository;
    private final UserLocationService userLocationService;
    public ApplicantGroupDto getApplicantGroupByReferenceNumber(String referenceNumber) {
        log.info("Start getApplicantGroupByReferenceNumber ReferenceNumber:{}", referenceNumber);
        Optional<JpaApplicantGroup> applicantGroupOptional = applicantGroupRepository.findByReferenceNumber(referenceNumber);
        if (applicantGroupOptional.isPresent()) {
            ApplicantGroupDto applicantGroupDto = getMapper().fromEntity(applicantGroupOptional.get(), mappingContext);
            log.info("Finish getApplicantGroupByReferenceNumber ReferenceNumber:{}", applicantGroupDto.getReferenceNumber());
            return applicantGroupDto;
        }
        log.info("Finish getApplicantGroupByReferenceNumber not found with referenceNumber: {}", referenceNumber);
        return null;
    }

    public ApplicantGroupDto getApplicantGroupByReferenceNumberAndCompany(String referenceNumber, String companyCode) {
        log.info("Start getApplicantGroupByReferenceNumberAndCompany ReferenceNumber:{}, companyCode:{}", referenceNumber, companyCode);
        Optional<JpaApplicantGroup> applicantGroupOptional = applicantGroupRepository.findTopByReferenceNumberAndCompanyRitualSeasonCompanyCodeOrderByCreationDateDesc(referenceNumber, companyCode);
        if (applicantGroupOptional.isPresent()) {
            ApplicantGroupDto applicantGroupDto = getMapper().fromEntity(applicantGroupOptional.get(), mappingContext);
            log.info("Finish getApplicantGroupByReferenceNumberAndCompany with ReferenceNumber:{}, companyCode:{}", referenceNumber, companyCode);
            return applicantGroupDto;
        }
        log.info("Finish getApplicantGroupByReferenceNumberAndCompany not found with ReferenceNumber:{}, companyCode:{}", referenceNumber, companyCode);
        return null;
    }

    public ApplicantGroupDto getApplicantGroupByReferenceNumberAndCompanyRitualSeasonId(String referenceNumber, long companyRitualSeasonId) {
        log.info("Start getApplicantGroupByReferenceNumberAndCompanyRitualSeasonId ReferenceNumber:{}, companyRitualSeasonId:{}", referenceNumber, companyRitualSeasonId);
        Optional<JpaApplicantGroup> applicantGroupOptional = applicantGroupRepository.getApplicantGroupByReferenceNumberAndCompanyRitualSeasonId(referenceNumber, companyRitualSeasonId);
        if (applicantGroupOptional.isPresent()) {
            ApplicantGroupDto applicantGroupDto = getMapper().fromEntity(applicantGroupOptional.get(), mappingContext);
            log.info("Finish getApplicantGroupByReferenceNumberAndCompanyRitualSeasonId with ReferenceNumber:{}, companyRitualSeasonId:{}", applicantGroupDto.getReferenceNumber(), companyRitualSeasonId);
            return applicantGroupDto;
        }
        log.info("Finish getApplicantGroupByReferenceNumberAndCompanyRitualSeasonId not found with ReferenceNumber:{}, companyRitualSeasonId:{}", referenceNumber, companyRitualSeasonId);
        return null;
    }

    public Page<ApplicantGroupDto> findGroupsByCompanyCode(String companyCode, Pageable pageable) {
        log.info("Start findGroupsByCompanyCode companyCode:{}", companyCode);
        Page<ApplicantGroupDto> applicantGroups = mapPage(applicantGroupRepository.findByCompanyRitualSeasonCompanyCode(companyCode, pageable));
        applicantGroups.getContent().forEach(applicantGroupDto -> {
            applicantGroupDto.setCountApplicants(groupApplicantListRepository.countByApplicantGroupId(applicantGroupDto.getId()));
            UserLocationDto userLocationDto = userLocationService.findTopByUserIdAndUserTypeOrderByCreationDateDesc(applicantGroupDto.getGroupLeader().getDigitalIds().isEmpty() ? null : applicantGroupDto.getGroupLeader().getDigitalIds().get(0).getSuin(),
                    EUserType.STAFF.name());
            applicantGroupDto.getGroupLeader().setLatitude(userLocationDto == null ? null : userLocationDto.getLatitude());
            applicantGroupDto.getGroupLeader().setLongitude(userLocationDto == null ? null : userLocationDto.getLongitude());
        });
        log.info("Finish findGroupsByCompanyCode companyCode:{}", companyCode);
        return applicantGroups;
    }

    public List<ApplicantGroupDto> findAllGroupByCompanyCode(String companyCode) {
        log.info("Start findAllGroupByCompanyCode companyCode:{}", companyCode);

        List<ApplicantGroupDto> applicantGroups = mapList(applicantGroupRepository.findByCompanyRitualSeasonCompanyCode(companyCode));
        applicantGroups.forEach(applicantGroupDto -> {
            applicantGroupDto.setCountApplicants(groupApplicantListRepository.countByApplicantGroupId(applicantGroupDto.getId()));
            UserLocationDto userLocationDto = userLocationService.findTopByUserIdAndUserTypeOrderByCreationDateDesc(applicantGroupDto.getGroupLeader().getDigitalIds().isEmpty() ? null : applicantGroupDto.getGroupLeader().getDigitalIds().get(0).getSuin(),
                    EUserType.STAFF.name());
            applicantGroupDto.getGroupLeader().setLatitude(userLocationDto == null ? null : userLocationDto.getLatitude());
            applicantGroupDto.getGroupLeader().setLongitude(userLocationDto == null ? null : userLocationDto.getLongitude());
        });
        log.info("Finish findAllGroupByCompanyCode companyCode:{}", companyCode);
        return applicantGroups;
    }

    public List<GroupNameLookupDto> findGroupsNameLookupByCompanyCode(String companyCode) {
        log.info("Start findGroupsNameLookupByCompanyCode companyCode:{}", companyCode);
        List<GroupNameLookupDto> groupNameLookup = mapList(applicantGroupRepository.findByCompanyRitualSeasonCompanyCode(companyCode)).stream().map(applicantGroupDto -> mapGroupName(applicantGroupDto)).collect(Collectors.toList());
        log.info("Finish findGroupsNameLookupByCompanyCode companyCode:{}", companyCode);
        return groupNameLookup;
    }

    private GroupNameLookupDto mapGroupName(ApplicantGroupDto applicantGroupDto) {
        log.info("Start mapGroupName");
        GroupNameLookupDto groupNameLookup = GroupNameLookupDto.builder()
                .code(applicantGroupDto.getReferenceNumber())
                .label(applicantGroupDto.getGroupName()).build();
        log.info("Finish mapGroupName");
        return groupNameLookup;
    }

    public String findGroupNumber(String uin) {
        log.info("Start findGroupNumber uin:{}", uin);
        String groupNumber = applicantGroupRepository.findReferenceNumberByUin(uin);
        if (groupNumber == null || groupNumber.equals("")) {
            log.info("Finish findGroupNumber not found with uin:{}", uin);
            return "";
        }
        log.info("Finish findGroupNumber uin:{}", uin);
        return groupNumber;
    }

    public ApplicantGroupDetailsVo findGroupDetailsByGroupId(long groupId, String companyRefCode, String companyTypeCode) {
        log.info("ApplicantGroupService ::: Start findGroupDetailsByGroupId {}", groupId);
        String multipleValue = "M";
        StringBuffer fullCompanyCode = new StringBuffer(companyRefCode).append("_").append(companyTypeCode);
        List<ApplicantGroupDetailsVo> groupDetailsByGroupId = applicantGroupRepository.findGroupDetailsByGroupId(groupId,fullCompanyCode.toString());
        ApplicantGroupDetailsVo result = new ApplicantGroupDetailsVo();
        long countCampInfo = groupDetailsByGroupId.stream().map(p -> p.getCampInfo()).distinct().count();
        long countBusInfo = groupDetailsByGroupId.stream().map(p -> p.getBusInfo()).distinct().count();
        if (groupDetailsByGroupId.isEmpty() == false) {
            log.debug("groupDetailsByGroupId is not empty {}",groupDetailsByGroupId.size());
            result = groupDetailsByGroupId.get(0);
        }

        if (countCampInfo > 1) {
            log.debug("countCampInfo > 1");
            result.setCampInfo(multipleValue);
        }
        if (countBusInfo > 1) {
            log.debug("countBusInfo > 1");
            result.setBusInfo(multipleValue);
        }
        log.info("ApplicantGroupService ::: Finish findGroupDetailsByGroupId {}", result.getGroupName());
        return result;
    }
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public boolean updateGroupLeader(long groupId, long staffId) {
        log.info("Start updateGroupLeader  groupId: {},    staffId: {}", groupId,    staffId);

        int affectedRows =  applicantGroupRepository.updateGroupLeader(groupId, staffId);

        log.info("Finish updateGroupLeader  affectedRows: {}", affectedRows);
        return affectedRows != 0;
    }

    public List<CompanyStaffVO> findGroupLeadersListByCompanyCode(String companyCode) {
        log.info("Start findGroupLeadersListByCompanyCode  companyCode: {}", companyCode);
        List<CompanyStaffVO> companyStaffVOList = applicantGroupRepository.findGroupLeadersListByCompanyCode(companyCode);
        log.info("Finish findGroupLeadersListByCompanyCode  companyCode: {}", companyCode);
        return companyStaffVOList;
    }


}
