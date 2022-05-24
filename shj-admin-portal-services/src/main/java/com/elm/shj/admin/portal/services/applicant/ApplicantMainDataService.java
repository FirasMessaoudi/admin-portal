/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantCard;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantMainData;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.repository.ApplicantCardRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantDigitalIdRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantMainDataRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
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
    private final ApplicantRitualRepository applicantRitualRepository;
    private final ApplicantDigitalIdRepository applicantDigitalIdRepository;

    private final ApplicantRelativeDtoMapper applicantRelativeDtoMapper;
    private final ApplicantContactDtoMapper applicantContactDtoMapper;

    private final ApplicantPackageService applicantPackageService;

    /**
     * Finds an applicant by his uin
     *
     * @param uin the uin of applicant to find
     * @return the found applicant or empty structure
     */
    @Transactional
    public Optional<ApplicantMainDataDto> findByUin(String uin, long applicantPackageId) {
        log.debug("Start findByUin uin:{} , applicantPackageId:{}", uin, applicantPackageId);
        JpaApplicantMainData applicant = applicantMainDataRepository.findByDigitalIdsUin(uin);

        if (applicant == null) {
            log.debug("Finish findByUin not found with uin:{} , applicantPackageId:{}", uin, applicantPackageId);
            return Optional.empty();
        }

        String statusCode = CollectionUtils.isEmpty(applicant.getDigitalIds()) ? "" : applicant.getDigitalIds().get(0).getStatusCode();
        ApplicantMainDataDto applicantMainDataDto = getMapper().fromEntity(applicant, mappingContext);
        applicantMainDataDto.setStatusCode(statusCode);
        applicantMainDataDto.setUin(uin);

        ApplicantPackageDto applicantPackageDto = applicantPackageService.findByIdAndApplicantUin(applicantPackageId,Long.parseLong(uin));

        if (applicantPackageDto != null) {
            applicantMainDataDto.setRitualTypeCode(applicantPackageDto.getRitualPackage().getCompanyRitualSeason().getRitualSeason().getRitualTypeCode());
            Optional<JpaApplicantRitual> applicantRitual = applicantRitualRepository.findByApplicantDigitalIdsUinAndApplicantPackageId(uin, applicantPackageDto.getId());
            if (applicantRitual.isPresent()) {
                applicantRitual.get().getRelatives().size();

                applicantMainDataDto.setRelatives(applicantRelativeDtoMapper.fromEntityList(new ArrayList<>(applicantRitual.get().getRelatives()), mappingContext));
                applicantMainDataDto.setContacts(applicantContactDtoMapper.fromEntityList(new ArrayList<>(applicantRitual.get().getApplicant().getContacts()), mappingContext));

                JpaApplicantCard jpaApplicantCard = applicantCardRepository.findByApplicantRitualIdAndStatusCodeNot(applicantRitual.get().getId(), ECardStatus.REISSUED.name());
                if (jpaApplicantCard != null) {
                    applicantMainDataDto.setCardReferenceNumber(jpaApplicantCard.getReferenceNumber());
                    applicantMainDataDto.setCardStatusCode(jpaApplicantCard.getStatusCode());
                }
            }
        }
        log.debug("Finish findByUin found with uin:{}", applicantMainDataDto.getUin());
        return Optional.of(applicantMainDataDto);
    }
}
