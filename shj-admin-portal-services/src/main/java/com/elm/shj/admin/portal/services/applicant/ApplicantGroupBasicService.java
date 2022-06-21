package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantGroupBasic;
import com.elm.shj.admin.portal.orm.repository.ApplicantGroupBasicRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantGroupBasicDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantGroupBasicService extends GenericService<JpaApplicantGroupBasic, ApplicantGroupBasicDto, Long> {

    private final ApplicantGroupBasicRepository applicantGroupBasicRepository;

    public Long findIdByReferenceNumberAndCompanyRitualSeason(String referenceNumber, long companyRitualSeasonId) {
        log.info("Start findIdByReferenceNumberAndCompanyRitualSeason for {} reference number and {} company ritual season.", referenceNumber, companyRitualSeasonId);
        Optional<Long> applicantGroupId = applicantGroupBasicRepository.findIdByReferenceNumberAndCompanyRitualSeasonId(referenceNumber, companyRitualSeasonId);
        if (applicantGroupId.isPresent()) {
            log.info("Found applicant group with {} id.", applicantGroupId.get());
            return applicantGroupId.get();
        }
        log.info("Finish findIdByReferenceNumberAndCompanyRitualSeason and no applicant group found.");
        return null;
    }


}
