/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.card;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffCard;
import com.elm.shj.admin.portal.orm.repository.CompanyStaffCardRepository;
import com.elm.shj.admin.portal.services.dto.CompanyStaffCardDto;
import com.elm.shj.admin.portal.services.dto.ECardStatus;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Service handling company staff card
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyStaffCardService extends GenericService<JpaCompanyStaffCard, CompanyStaffCardDto, Long> {
    private final CompanyStaffCardRepository companyStaffCardRepository;
    List<String> cardStatus = Arrays.asList(ECardStatus.ACTIVE.name(), ECardStatus.READY_TO_PRINT.name(), ECardStatus.SENT_FOR_PRINT.name(), ECardStatus.PRINTED.name(), ECardStatus.DISTRIBUTED.name(), ECardStatus.SUSPENDED.name());

    /**
     * find company staff cards by suin
     *
     * @param suin
     * @return
     */
    public List<CompanyStaffCardDto> findByDigitalId(String suin) {
        return mapList(companyStaffCardRepository.findAllByCompanyStaffDigitalIdSuin(suin));
    }

    /**
     * find company staff cards by suin
     *
     * @param suin
     * @return
     */
    public  CompanyStaffCardDto  findByDigitalIdAndStatusCodeActive(String suin) {
        return getMapper().fromEntity(companyStaffCardRepository.findByCompanyStaffDigitalIdSuinAndStatusCode(suin, ECardStatus.ACTIVE.name()), mappingContext);
     }
    /**
     * find company staff cards
     *
     * @param suin
     * @param companyCode
     * @param ritualType
     * @return
     */
    public List<CompanyStaffCardDto> findByDigitalIdCompanyCodeRitualType(String suin, String companyCode, String ritualType) {
        return mapList(companyStaffCardRepository.findAllByCompanyStaffSuinAndCompanyRitualSeasonCompanyCodeAndCompanyRitualSeasonRitualSeasonRitualTypeCode(suin, companyCode, ritualType, cardStatus));
    }

    /**
     * @param suin
     * @param companyCode
     * @param ritualType
     * @return
     */
    public List<CompanyStaffCardDto> findByDigitalIdAndDifferentCompanyOrRitual(String suin, String companyCode, String ritualType) {
        return mapList(companyStaffCardRepository.findAllByCompanyStaffSuinAndCompanyRitualSeasonCompanyCodeNotOrCompanyRitualSeasonRitualSeasonRitualTypeCodeNot(suin, companyCode, ritualType, cardStatus));
    }

    @Transactional
    public Page<CompanyStaffCardDto> searchStaffCards(String uin, String idNumber, String passportNumber, Pageable pageable) {
        return mapPage(companyStaffCardRepository.findStaffCards(ECardStatus.REISSUED.name(), pageable));
    }

    public List<CompanyStaffCardDto> findAllPrintingCards(String uin, String idNumber, String hamlahNumber, String motawefNumber,
                                                          String passportNumber, String nationalityCode, List<Long> excludedCardsIds) {
        log.debug("Find all printing cards...");
        return mapList(companyStaffCardRepository.findAllPrintingCards(ECardStatus.READY_TO_PRINT.name(), uin, idNumber, passportNumber, nationalityCode,
                excludedCardsIds.size() == 0 ? Arrays.asList(-1L) : excludedCardsIds));
    }

    public Page<CompanyStaffCardDto> findPrintingCards(String uin, String idNumber, String hamlahNumber, String motawefNumber,
                                                       String passportNumber, String nationalityCode, List<Long> excludedCardsIds, Pageable pageable) {
        log.debug("Find all printing cards...");
        return mapPage(companyStaffCardRepository.findPrintingCards(ECardStatus.READY_TO_PRINT.name(),
                uin, idNumber, passportNumber, nationalityCode,
                excludedCardsIds.size() == 0 ? Arrays.asList(-1L) : excludedCardsIds, pageable));
    }

}
