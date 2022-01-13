/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.card;

import com.elm.shj.admin.portal.orm.entity.*;
import com.elm.shj.admin.portal.orm.repository.CompanyStaffCardRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    public CompanyStaffCardDto findByDigitalIdAndStatusCodeActive(String suin) {
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
    public Page<CompanyStaffCardDto> searchStaffCards(CompanyStaffCardFilterDto companyStaffCardFilter, Pageable pageable) {
        return mapPage(companyStaffCardRepository.findAll(withStaffCardFilter(companyStaffCardFilter), pageable));
    }

    private Specification<JpaCompanyStaffCard> withStaffCardFilter(final CompanyStaffCardFilterDto criteria) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            //Create atomic predicates
            List<Predicate> predicates = new ArrayList<>();
            Join<JpaCompanyStaffCard, JpaCompanyRitualSeason> companyRitualSeason = root.join("companyRitualSeason");
            Join<JpaCompanyRitualSeason, JpaRitualSeason> ritualSeason = companyRitualSeason.join("ritualSeason");
            if (criteria.getRitualType() != null) {
                Path<String> ritualTypeCode = ritualSeason.get("ritualTypeCode");
                predicates.add(criteriaBuilder.equal(ritualTypeCode, criteria.getRitualType()));
            }

            if (criteria.getRitualSeason() != null) {
                Path<Long> seasonYear = ritualSeason.get("seasonYear");
                predicates.add(criteriaBuilder.equal(seasonYear, criteria.getRitualSeason()));
            }

            if (criteria.getCompanyCode() != null) {
                Join<JpaCompanyRitualSeason, JpaCompany> company = companyRitualSeason.join("company");
                Path<String> companyCode = company.get("code");
                predicates.add(criteriaBuilder.equal(companyCode, criteria.getCompanyCode()));
            }

            if (criteria.getCardStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("statusCode"), criteria.getCardStatus()));
            }

            if (criteria.getSuin() != null && !criteria.getSuin().equals("")) {
                Join<JpaCompanyStaffCard, JpaCompanyStaffDigitalId> companyStaffDigitalId = root.join("companyStaffDigitalId");
                predicates.add(criteriaBuilder.equal(companyStaffDigitalId.get("suin"), criteria.getSuin()));
            }

            if (criteria.getCardNumber() != null && !criteria.getCardNumber().equals("")) {
                predicates.add(criteriaBuilder.equal(root.get("referenceNumber"), criteria.getCardNumber()));
            }

            if (criteria.getBatchNumber() != null) {
                predicates.add(criteriaBuilder.equal(root.get("batchNumber"), criteria.getBatchNumber()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public List<CompanyStaffCardDto> findAllPrintingCards(String uin,
                                                          String companyCode, String nationalityCode, int seasonYear, String ritualCode, List<Long> excludedCardsIds) {
        log.debug("Find all printing cards...");
        return mapList(companyStaffCardRepository.findAllPrintingCards(ECardStatus.READY_TO_PRINT.name(), EPrintRequestStatus.NEW.name(), uin, companyCode, nationalityCode, seasonYear, ritualCode,
                excludedCardsIds.size() == 0 ? Arrays.asList(-1L) : excludedCardsIds));
    }

    public Page<CompanyStaffCardDto> findPrintingCards(String uin,
                                                       String companyCode, String nationalityCode, int seasonYear, String ritualCode, List<Long> excludedCardsIds, Pageable pageable) {
        log.debug("search printing cards...");
        return mapPage(companyStaffCardRepository.findPrintingCards(ECardStatus.READY_TO_PRINT.name(), EPrintRequestStatus.NEW.name(), uin,
                companyCode, nationalityCode, seasonYear, ritualCode,
                excludedCardsIds.size() == 0 ? Arrays.asList(-1L) : excludedCardsIds, pageable));
    }

    public CompanyStaffCardDto findStaffCardById(long cardId) {
        return getMapper().fromEntity(companyStaffCardRepository.findByIdAndStatusCodeNot(cardId, ECardStatus.REISSUED.name()), mappingContext);
    }

    @Transactional
    public CompanyStaffCardDto changeCardStatus(CompanyStaffCardDto card, String actionCode, Optional<Long> userId) {
        if (actionCode.equalsIgnoreCase(ECardStatusAction.CANCEL_CARD.name())) {
            card.setStatusCode(ECardStatus.CANCELLED.name());
        } else if (actionCode.equalsIgnoreCase(ECardStatusAction.ACTIVATE_CARD.name())) {
            card.setStatusCode(ECardStatus.ACTIVE.name());
        } else if (actionCode.equalsIgnoreCase(ECardStatusAction.SUSPEND_CARD.name())) {
            card.setStatusCode(ECardStatus.SUSPENDED.name());
        } else {
            card.setStatusCode(ECardStatus.REISSUED.name());
            save(card);
            log.debug("Generate new staff card after marking the old one as reissued...");
            //TODO Audit Staff Card Logs
            //userCardStatusAuditService.saveUserCardStatusAudit(card, userId);
            CompanyStaffCardDto savedCard = save(CompanyStaffCardDto
                    .builder()
                    .referenceNumber(card.getReferenceNumber())
                    .companyRitualSeason(card.getCompanyRitualSeason())
                    .companyStaffDigitalId(card.getCompanyStaffDigitalId())
                    .statusCode(ECardStatus.READY_TO_PRINT.name())
                    .build());
            //userCardStatusAuditService.saveUserCardStatusAudit(savedCard, userId);
            return savedCard;
        }
        //userCardStatusAuditService.saveUserCardStatusAudit(card, userId);
        return save(card);

    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public CompanyStaffCardDto findById(long cardId) {
        return findOne(cardId);
    }

}
