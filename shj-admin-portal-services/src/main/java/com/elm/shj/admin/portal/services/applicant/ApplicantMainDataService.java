package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantMainData;
import com.elm.shj.admin.portal.orm.repository.ApplicantMainDataRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantMainDataDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service handling main data version of applicant
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantMainDataService extends GenericService<JpaApplicantMainData, ApplicantMainDataDto, Long> {

    private final ApplicantMainDataRepository applicantMainDataRepository;

    /**
     * Finds an applicant by his uin
     *
     * @param uin the uin of applicant to find
     * @return the found applicant or empty structure
     */
    public Optional<ApplicantMainDataDto> findByUin(String uin) {
        JpaApplicantMainData applicant = applicantMainDataRepository.findByUin(uin);
        if (applicant != null) {
            ApplicantMainDataDto applicantMainDataDto = getMapper().fromEntity(applicant, mappingContext);
            return Optional.of(applicantMainDataDto);
        } else return Optional.empty();
    }

}
