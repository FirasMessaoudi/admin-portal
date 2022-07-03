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
import java.util.*;
import java.util.stream.Collectors;

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
    List<String> cardStatus = Arrays.asList(ECardStatus.ACTIVE.name(), ECardStatus.READY_TO_PRINT.name(), ECardStatus.SENT_FOR_PRINT.name(), ECardStatus.PRINTED.name(), ECardStatus.DISTRIBUTED.name(), ECardStatus.SUSPENDED.name(), ECardStatus.CANCELLED.name());

    /**
     * find company staff cards by suin
     *
     * @param suin
     * @return
     */
    public List<CompanyStaffCardDto> findByDigitalId(String suin) {
        log.info("Start findByDigitalId with suin: {}", suin);
        List<CompanyStaffCardDto> companyStaffCardDtoList= mapList(companyStaffCardRepository.findAllByCompanyStaffDigitalIdSuin(suin));
        log.info("Finish findByDigitalId with suin: {}", suin);
        return companyStaffCardDtoList;
    }

    /**
     * find company staff cards by suin
     *
     * @param suin
     * @return
     */
    public CompanyStaffCardDto findByDigitalIdAndStatusCodeActive(String suin) {
        log.info("Start findByDigitalIdAndStatusCodeActive with suin: {}", suin);
        CompanyStaffCardDto companyStaffCardDto = getMapper().fromEntity(companyStaffCardRepository.findByCompanyStaffDigitalIdSuinAndStatusCode(suin, ECardStatus.ACTIVE.name()), mappingContext);
        log.info("Finish findByDigitalIdAndStatusCodeActive with suin: {}", suin);
        return companyStaffCardDto;
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
        log.info("Start findByDigitalIdCompanyCodeRitualType with suin: {}, companyCode: {}, ritualType: {} ", suin, companyCode, ritualType);
        List<CompanyStaffCardDto> companyStaffCardDtoList = mapList(companyStaffCardRepository.findAllByCompanyStaffSuinAndCompanyRitualSeasonCompanyCodeAndCompanyRitualSeasonRitualSeasonRitualTypeCode(suin, companyCode, ritualType, cardStatus));
        log.info("Finish findByDigitalIdCompanyCodeRitualType with suin: {}, companyCode: {}, ritualType: {} ", suin, companyCode, ritualType);
        return companyStaffCardDtoList;
    }

    /**
     * @param suin
     * @param companyCode
     * @param ritualType
     * @return
     */
    public List<CompanyStaffCardDto> findByDigitalIdAndDifferentCompanyOrRitual(String suin, String companyCode, String ritualType) {
        log.info("Start findByDigitalIdAndDifferentCompanyOrRitual with suin: {}, companyCode: {}, ritualType: {} ", suin, companyCode, ritualType);
        List<CompanyStaffCardDto> companyStaffCardDtoList= mapList(companyStaffCardRepository.findAllByCompanyStaffSuinAndCompanyRitualSeasonCompanyCodeNotOrCompanyRitualSeasonRitualSeasonRitualTypeCodeNot(suin, companyCode, ritualType, cardStatus));
        log.info("Finish findByDigitalIdAndDifferentCompanyOrRitual with suin: {}, companyCode: {}, ritualType: {} ", suin, companyCode, ritualType);
        return companyStaffCardDtoList;
    }

    @Transactional
    public Page<CompanyStaffCardDto> searchStaffCards(CompanyStaffCardFilterDto companyStaffCardFilter, Pageable pageable) {
        log.info("Start searchStaffCards with CompanyStaffCardFilterDto: {} ", companyStaffCardFilter);
        Page<CompanyStaffCardDto> companyStaffCardDtoPage = mapPage(companyStaffCardRepository.findAll(withStaffCardFilter(companyStaffCardFilter), pageable));
        log.info("Finish searchStaffCards with CompanyStaffCardFilterDto");
        return companyStaffCardDtoPage;
    }

    private Specification<JpaCompanyStaffCard> withStaffCardFilter(final CompanyStaffCardFilterDto criteria) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            //Create atomic predicates
            List<Predicate> predicates = new ArrayList<>();
            Join<JpaCompanyStaffCard, JpaCompanyRitualSeason> companyRitualSeason = root.join("companyRitualSeason");
            Join<JpaCompanyRitualSeason, JpaRitualSeason> ritualSeason = companyRitualSeason.join("ritualSeason");
            Join<JpaCompanyStaffCard, JpaCompanyStaffDigitalId> companyStaffDigitalId = root.join("companyStaffDigitalId");

            predicates.add(criteriaBuilder.notEqual(root.get("statusCode"), ECardStatus.EXPIRED.name()));
            predicates.add(criteriaBuilder.notEqual(root.get("statusCode"), ECardStatus.REISSUED.name()));

            predicates.add(criteriaBuilder.equal(companyStaffDigitalId.join("companyStaff").get("deleted"), false));

            if (criteria.getIdNumber() != null) {
                predicates.add(criteriaBuilder.equal(companyStaffDigitalId.join("companyStaff").get("idNumber"), criteria.getIdNumber()));
            }

            if (criteria.getPassportNumber() != null) {
                predicates.add(criteriaBuilder.equal(companyStaffDigitalId.join("companyStaff").get("passportNumber"), criteria.getPassportNumber()));
            }

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

            if (criteria.getJobTitle() != null) {
                predicates.add(criteriaBuilder.equal(companyStaffDigitalId.join("companyStaff").get("titleCode"), criteria.getJobTitle()));
            }

            if (criteria.getCardStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("statusCode"), criteria.getCardStatus()));
            }

            if (criteria.getSuin() != null && !criteria.getSuin().equals("")) {
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
        log.info("CompanyStaffCardService:: Start findAllPrintingCards");
        List<CompanyStaffCardDto> companyStaffCardDtoList = mapList(companyStaffCardRepository.findAllPrintingCards(ECardStatus.READY_TO_PRINT.name(), EPrintRequestStatus.NEW.name(), uin, companyCode, nationalityCode, seasonYear, ritualCode,
                excludedCardsIds.size() == 0 ? Arrays.asList(-1L) : excludedCardsIds));
        log.info("CompanyStaffCardService:: Finish findAllPrintingCards");
        return companyStaffCardDtoList;
    }

    public Page<CompanyStaffCardDto> findPrintingCards(String uin,
                                                       String companyCode, String nationalityCode, int seasonYear, String ritualCode, List<Long> excludedCardsIds, Pageable pageable) {
        log.info("CompanyStaffCardService:: Start findPrintingCards");
        Page<CompanyStaffCardDto> companyStaffCardDtoPage = mapPage(companyStaffCardRepository.findPrintingCards(ECardStatus.READY_TO_PRINT.name(), EPrintRequestStatus.NEW.name(), uin,
                companyCode, nationalityCode, seasonYear, ritualCode,
                excludedCardsIds.size() == 0 ? Arrays.asList(-1L) : excludedCardsIds, pageable));
        log.info("CompanyStaffCardService:: Finish findPrintingCards");
        return companyStaffCardDtoPage;
    }

    @Transactional
    public CompanyStaffCardDto changeCardStatus(CompanyStaffCardDto card, String actionCode, Optional<Long> userId) {
        log.info("CompanyStaffCardService:: Start changeCardStatus");
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
            log.info("CompanyStaffCardService:: Finish changeCardStatus");
            return savedCard;
        }
        //userCardStatusAuditService.saveUserCardStatusAudit(card, userId);
        log.info("CompanyStaffCardService:: Start changeCardStatus");
        return save(card);

    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public CompanyStaffCardDto findById(long cardId) {
        log.info("CompanyStaffCardService:: Start findById with id: {}", cardId );
        CompanyStaffCardDto companyStaffCardDto = findOne(cardId);
        log.info("CompanyStaffCardService:: Finish findById with id: {}", cardId );
        return companyStaffCardDto;
    }

    public List<CompanyStaffCardDto> findStaffCards(List<Long> cardIds) {
        log.info("Start findStaffCards with cardIds: {}", cardIds);
        List<CompanyStaffCardDto> companyStaffCardDtoList = mapList(companyStaffCardRepository.findStaffCards(cardIds));
        log.info("Finish findStaffCards with cardIds: {}", cardIds);
        return companyStaffCardDtoList;
    }

    public List<CompanyStaffCardDto> findStaffCardsByPrintRequestBatchIdAndDigitalIds(long batchId, Set<String> digitalIdSet) {
        log.info("Start findStaffCardsByPrintRequestBatchIdAndDigitalIds with batchId: {},  digitalIdSet: {}", batchId, digitalIdSet);
        List<CompanyStaffCardDto> companyStaffCardDtoList = mapList(companyStaffCardRepository.findStaffCardsByPrintRequestBatchIdAndDigitalIds(digitalIdSet.stream().collect(Collectors.toList()), batchId));
        log.info("Finish findStaffCardsByPrintRequestBatchIdAndDigitalIds with batchId: {},  digitalIdSet: {}", batchId, digitalIdSet);
        return companyStaffCardDtoList;
    }

    public void updateCardStatus(List<Long> cardsIds){
        log.info("CompanyStaffCardService :: Start updateCardStatus with cardsIds: {}",cardsIds);
        companyStaffCardRepository.updateCardStatus(cardsIds, ECardStatus.SENT_FOR_PRINT.name());
        log.info("CompanyStaffCardService :: Finish updateCardStatus with cardsIds: {}",cardsIds);
    }

    public List<ApplicantBasicInfoVo> findStaffBasicInfoByDigitalIds(List<String> digitalIds) {
        log.info("Start findStaffBasicInfoByDigitalIds with digitalIds: {}", digitalIds);
        List<ApplicantBasicInfoVo> applicantBasicInfoVoList = companyStaffCardRepository.findAllByStaffDigitalIds(digitalIds, Arrays.asList(ECardStatus.CANCELLED.name(),ECardStatus.EXPIRED.name(),ECardStatus.SUSPENDED.name()));
        log.info("Finish findStaffBasicInfoByDigitalIds with digitalIds: {}", digitalIds);
        return applicantBasicInfoVoList;
    }

    public void updateStaffCardStatusByStaffId(List<Long> staffCardIdList){
        log.info("Start updateStaffCardStatusByStaffId with staffCardIdList: {}", staffCardIdList);
        companyStaffCardRepository.updateCompanyStaffCardStatus(staffCardIdList);
        log.info("Finish updateStaffCardStatusByStaffId with staffCardIdList: {}", staffCardIdList);
    }

    public List<Long> findStaffCardByStaffId(Long staffId){
        log.info("Start findStaffCardByStaffId with staffId: {}", staffId);
        List<Long> staffCardIdList = companyStaffCardRepository.findStaffCard(staffId);
        log.info("Finish findStaffCardByStaffId with staffId: {}", staffId);
        return staffCardIdList;
    }
}
