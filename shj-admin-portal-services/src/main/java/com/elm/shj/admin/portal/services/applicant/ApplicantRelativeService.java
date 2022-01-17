package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRelative;
import com.elm.shj.admin.portal.orm.repository.ApplicantRelativeRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantRelativeDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service handling Relative Relationship
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantRelativeService  extends GenericService<JpaApplicantRelative, ApplicantRelativeDto, Long> {
    private final ApplicantRelativeRepository applicantRelativeRepository;

    public ApplicantRelativeDto findByApplicantIdAndRelativeApplicantId(long applicantId, long relativeApplicantId){
        Optional<JpaApplicantRelative> applicantRelative = applicantRelativeRepository.findByApplicantIdAndRelativeApplicantId(applicantId, relativeApplicantId);
        return applicantRelative.map(r -> getMapper().fromEntity(r, mappingContext)).orElse(null);
    }

    /**
     * Set applicant ritual id for the applicant relative.
     *
     * @param applicantRitualId
     * @param applicantId
     * @param packageReferenceNumber
     * @return
     */
    @Transactional
    public int updateApplicantRelativeApplicantRitual(long applicantRitualId, long applicantId, String packageReferenceNumber) {
        return applicantRelativeRepository.updateApplicantRelativeApplicantRitual(applicantRitualId, applicantId, packageReferenceNumber);
    }

}
