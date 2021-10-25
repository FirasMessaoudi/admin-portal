package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantCard;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantMainData;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.repository.ApplicantCardRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantMainDataRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private final CompanyRitualSeasonLiteService companyRitualSeasonLiteService;
    private final ApplicantRitualRepository applicantRitualRepository;


    /**
     * Finds an applicant by his uin
     *
     * @param uin the uin of applicant to find
     * @return the found applicant or empty structure
     */
    @Transactional
    public Optional<ApplicantMainDataDto> findByUin(String uin, long companyRitualSeasonId) {
        JpaApplicantMainData applicant = applicantMainDataRepository.findByUin(uin);
        if (applicant != null) {
            ApplicantMainDataDto applicantMainDataDto = applicantMainDataDtoMapper.fromEntity(applicant, mappingContext);
            String statusCode = applicant.getDigitalIds().get(0).getStatusCode();
            applicantMainDataDto.setStatusCode(statusCode);

            applicantMainDataDto.setUin(uin);

            CompanyRitualSeasonLiteDto companyRitualSeasonLiteDto = companyRitualSeasonLiteService.findOne(companyRitualSeasonId);

            if (companyRitualSeasonLiteDto != null) {
                applicantMainDataDto.setRitualTypeCode(companyRitualSeasonLiteDto.getRitualSeason().getRitualTypeCode());

                JpaApplicantRitual applicantRitual = applicantRitualRepository.findByApplicantDigitalIdsUinAndApplicantPackageRitualPackageCompanyRitualSeasonId(uin, companyRitualSeasonLiteDto.getId());
                if (applicantRitual != null) {
                    applicantRitual.getRelatives().size();
                    applicantRitual.getContacts().size();

                    applicantMainDataDto.setRelatives(applicantRelativeDtoMapper.fromEntityList(new ArrayList<>(applicantRitual.getRelatives()), mappingContext));
                    applicantMainDataDto.setContacts(applicantContactDtoMapper.fromEntityList(new ArrayList<>(applicantRitual.getContacts()), mappingContext));


                    JpaApplicantCard jpaApplicantCard = applicantCardRepository.findByApplicantRitualId(applicantRitual.getId());
                    if (jpaApplicantCard != null) {
                        applicantMainDataDto.setCardReferenceNumber(jpaApplicantCard.getReferenceNumber());
                        applicantMainDataDto.setCardStatusCode(jpaApplicantCard.getStatusCode());
                    }

                }

            }

            return Optional.of(applicantMainDataDto);
        } else return Optional.empty();
    }

}
