package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantGroup;
import com.elm.shj.admin.portal.orm.repository.ApplicantGroupRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantGroupDto;
import com.elm.shj.admin.portal.services.dto.GroupNameLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantGroupService extends GenericService<JpaApplicantGroup, ApplicantGroupDto, Long> {

    private final ApplicantGroupRepository applicantGroupRepository;

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

    public List<ApplicantGroupDto> findGroupsByCompanyCode(String companyCode) {
        log.info("Start findGroupsByCompanyCode companyCode:{}", companyCode);
        List<ApplicantGroupDto> applicantGroups = mapList(applicantGroupRepository.findByCompanyRitualSeasonCompanyCode(companyCode));
        applicantGroups.forEach(applicantGroupDto -> applicantGroupDto.setReferenceNumber(applicantGroupDto.getReferenceNumber().indexOf("_") != -1 ? applicantGroupDto.getReferenceNumber().substring(0, applicantGroupDto.getReferenceNumber().indexOf("_")) : applicantGroupDto.getReferenceNumber()));
        return applicantGroups;
    }

    public List<GroupNameLookupDto> findGroupsNameLookupByCompanyCode(String companyCode) {
        log.info("Start findGroupsNameLookupByCompanyCode companyCode:{}", companyCode);
        List<GroupNameLookupDto> groupNameLookup = mapList(applicantGroupRepository.findByCompanyRitualSeasonCompanyCode(companyCode)).stream().map(applicantGroupDto -> mapGroupName(applicantGroupDto)).collect(Collectors.toList());
        return groupNameLookup;
    }

    private GroupNameLookupDto mapGroupName(ApplicantGroupDto applicantGroupDto){
        GroupNameLookupDto groupNameLookup = GroupNameLookupDto.builder()
                .code(applicantGroupDto.getReferenceNumber().indexOf("_") != -1 ? applicantGroupDto.getReferenceNumber().substring(0, applicantGroupDto.getReferenceNumber().indexOf("_")) : applicantGroupDto.getReferenceNumber())
                .label(applicantGroupDto.getGroupName()).build();
        return groupNameLookup;
    }

    public String findGroupNumber(String uin) {
        log.info("Start findGroupNumber uin:{}", uin);
        String groupNumber = applicantGroupRepository.findReferenceNumberByUin(uin);
        if(groupNumber == null || groupNumber.equals("")) return "";
        groupNumber = groupNumber.indexOf("_") != -1 ? groupNumber.substring(0, groupNumber.indexOf("_")) : groupNumber;
        return groupNumber;
    }
}
