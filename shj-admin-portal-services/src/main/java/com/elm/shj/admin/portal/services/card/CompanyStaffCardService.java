/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.card;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffCard;
import com.elm.shj.admin.portal.orm.repository.CompanyStaffCardRepository;
import com.elm.shj.admin.portal.services.dto.CompanyStaffCardDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    List<String> cardStatus = Arrays.asList("ACTIVE", "READY_TO_PRINT", "SENT_FOR_PRINT", "PRINTED", "DISTRIBUTED", "SUSPENDED");

    /**
     * find company staff cards by digital id
     *
     * @param id
     * @return
     */
    public List<CompanyStaffCardDto> findByDigitalId(long id) {
        return mapList(companyStaffCardRepository.findAllByCompanyStaffDigitalIdId(id));
    }

    /**
     * find company staff cards
     *
     * @param digitalId
     * @param companyCode
     * @param ritualType
     * @return
     */
    public List<CompanyStaffCardDto> findByDigitalIdCompanyCodeRitualType(long digitalId, String companyCode, String ritualType) {
        return mapList(companyStaffCardRepository.findAllByCompanyStaffDigitalIdIdAndStatusCodeInAndCompanyRitualSeasonCompanyCodeAndCompanyRitualSeasonRitualSeasonRitualTypeCode(digitalId, cardStatus, companyCode, ritualType));
    }

    /**
     * @param digitalId
     * @param companyCode
     * @param ritualType
     * @return
     */
    public List<CompanyStaffCardDto> findByDigitalIdAndDifferentCompanyOrRitual(long digitalId, String companyCode, String ritualType) {
        return mapList(companyStaffCardRepository.findAllByCompanyStaffDigitalIdIdAndStatusCodeInAndCompanyRitualSeasonCompanyCodeNotOrCompanyRitualSeasonRitualSeasonRitualTypeCodeNot(digitalId, cardStatus, companyCode, ritualType));
    }
}
