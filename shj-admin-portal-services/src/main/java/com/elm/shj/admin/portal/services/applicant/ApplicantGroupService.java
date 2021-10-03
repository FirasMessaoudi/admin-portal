package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantGroup;
import com.elm.shj.admin.portal.orm.repository.ApplicantGroupRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantGroupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantGroupService extends GenericService<JpaApplicantGroup, ApplicantGroupDto, Long> {

    private final ApplicantGroupRepository applicantGroupRepository;

    public ApplicantGroupDto getApplicantGroupByReferenceNumber(String referenceNumber) {
        Optional<JpaApplicantGroup> applicantGroupOptional = applicantGroupRepository.findByReferenceNumber(referenceNumber);
        if (applicantGroupOptional.isPresent()) {
            return getMapper().fromEntity(applicantGroupOptional.get(), mappingContext);
        }
        return null;
    }

}
