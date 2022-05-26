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

import java.util.List;
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
public class ApplicantRelativeService extends GenericService<JpaApplicantRelative, ApplicantRelativeDto, Long> {
    private final ApplicantRelativeRepository applicantRelativeRepository;

    //TODO not used
    public ApplicantRelativeDto findByApplicantIdAndRelativeApplicantId(long applicantId, long relativeApplicantId) {
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
        log.info("Start updateApplicantRelativeApplicantRitual ::: applicantRitualId: {},  applicantId: {},  packageReferenceNumber: {}", applicantRitualId, applicantId, packageReferenceNumber);
        int numberOfAffectedRows = applicantRelativeRepository.updateApplicantRelativeApplicantRitual(applicantRitualId, applicantId, packageReferenceNumber);
        log.info("Finish updateApplicantRelativeApplicantRitual ::: numberOfAffectedRows: {}", numberOfAffectedRows);
        return numberOfAffectedRows;
    }


    /**
     * Finds an applicant relatives in last ritual
     *
     * @param applicantUin           the uin of applicant to find by
     * @param packageReferenceNumber to find by
     * @return the list of relatives to return
     */
    @Transactional
    public List<ApplicantRelativeDto> findApplicantRelativesInLastRitual(String applicantUin, String packageReferenceNumber) {
        log.info("Start findApplicantRelativesInLastRitual ::: applicantUin: {}, packageReferenceNumber: {}", applicantUin, packageReferenceNumber);
        List<ApplicantRelativeDto> applicantRelativeDtos = mapList(((ApplicantRelativeRepository) getRepository()).findByApplicantUinAndPackageReferenceNumber(applicantUin, packageReferenceNumber));
        log.info("Finish findApplicantRelativesInLastRitual ::: applicantRelativeDtosListSize: {}", applicantRelativeDtos.size());
        return applicantRelativeDtos;
    }
}
