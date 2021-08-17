package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantCard;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantMainData;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.repository.ApplicantCardRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantMainDataRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantContactDtoMapper;
import com.elm.shj.admin.portal.services.dto.ApplicantMainDataDto;
import com.elm.shj.admin.portal.services.dto.ApplicantMainDataDtoMapper;
import com.elm.shj.admin.portal.services.dto.ApplicantRelativeDtoMapper;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final ApplicantCardRepository applicantCardRepository;
    private final ApplicantRelativeDtoMapper applicantRelativeDtoMapper;
    private final ApplicantContactDtoMapper applicantContactDtoMapper;
    private final ApplicantMainDataDtoMapper applicantMainDataDtoMapper;

    /**
     * Finds an applicant by his uin
     *
     * @param uin the uin of applicant to find
     * @return the found applicant or empty structure
     */
    @Transactional
    public Optional<ApplicantMainDataDto> findByUin(String uin, long ritualId) {
        JpaApplicantMainData applicant = applicantMainDataRepository.findByUin(uin);
        if (applicant != null) {
            ApplicantMainDataDto applicantMainDataDto = applicantMainDataDtoMapper.fromEntity(applicant, mappingContext);

            applicantMainDataDto.setUin(uin);

            if (applicant.getRituals() != null && !applicant.getRituals().isEmpty()) {
                Optional<JpaApplicantRitual> ritualOptional = applicant.getRituals().stream().filter(ritual -> ritual.getId() == ritualId).findFirst();

                if (ritualOptional.isPresent()) {
                    JpaApplicantRitual applicantRitual = ritualOptional.get();
                    applicantMainDataDto.setRitualTypeCode(applicantRitual.getTypeCode());
                    applicantMainDataDto.setRelatives(applicantRelativeDtoMapper.fromEntityList(applicantRitual.getRelatives(), mappingContext));
                    applicantMainDataDto.setContacts(applicantContactDtoMapper.fromEntityList(applicantRitual.getContacts(), mappingContext));
                }

                JpaApplicantCard jpaApplicantCard = applicantCardRepository.findByApplicantRitualId(ritualId);
                if (jpaApplicantCard != null) {
                    applicantMainDataDto.setCardReferenceNumber(jpaApplicantCard.getReferenceNumber());
                    applicantMainDataDto.setCardStatusCode(jpaApplicantCard.getStatusCode());
                }
            }
            return Optional.of(applicantMainDataDto);
        } else return Optional.empty();
    }

}
