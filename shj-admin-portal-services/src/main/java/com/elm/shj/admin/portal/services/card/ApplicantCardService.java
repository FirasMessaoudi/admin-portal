/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.card;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantCard;
import com.elm.shj.admin.portal.orm.repository.ApplicantCardRepository;
import com.elm.shj.admin.portal.services.applicant.ApplicantPackageCateringService;
import com.elm.shj.admin.portal.services.applicant.ApplicantPackageHousingService;
import com.elm.shj.admin.portal.services.applicant.ApplicantPackageTransportationService;
import com.elm.shj.admin.portal.services.company.CompanyLiteService;
import com.elm.shj.admin.portal.services.company.CompanyRitualStepService;
import com.elm.shj.admin.portal.services.company.CompanyService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service handling applicant card
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantCardService extends GenericService<JpaApplicantCard, ApplicantCardDto, Long> {

    public static final Map<String, String[]> CARD_STATUS_ALLOWED_ACTION = new HashMap<>();

    private final ApplicantCardRepository applicantCardRepository;
    private final UserCardStatusAuditService userCardStatusAuditService;
    private final CompanyRitualStepService companyRitualStepService;
    private final ApplicantRitualService applicantRitualService;
    private final ApplicantPackageCateringService applicantPackageCateringService;
    private final ApplicantPackageHousingService applicantPackageHousingService;
    private final ApplicantPackageTransportationService applicantPackageTransportationService;
    private final CompanyStaffService companyStaffService;
    private final CompanyLiteService companyLiteService;
    private final CompanyService companyService;

    @PostConstruct
    private void postConstruct() {
        //build map of allowed actions per current status for the card
        CARD_STATUS_ALLOWED_ACTION.put(ECardStatus.ACTIVE.name(), new String[]{ECardStatusAction.SUSPEND_CARD.name(), ECardStatusAction.CANCEL_CARD.name()});
        CARD_STATUS_ALLOWED_ACTION.put(ECardStatus.CANCELLED.name(), new String[]{ECardStatusAction.REISSUE_CARD.name()});
        CARD_STATUS_ALLOWED_ACTION.put(ECardStatus.SUSPENDED.name(), new String[]{ECardStatusAction.ACTIVATE_CARD.name(), ECardStatusAction.CANCEL_CARD.name()});
        CARD_STATUS_ALLOWED_ACTION.put(ECardStatus.READY_TO_PRINT.name(), new String[]{ECardStatusAction.CANCEL_CARD.name()});
        CARD_STATUS_ALLOWED_ACTION.put(ECardStatus.SENT_FOR_PRINT.name(), new String[]{ECardStatusAction.CANCEL_CARD.name()});
        CARD_STATUS_ALLOWED_ACTION.put(ECardStatus.PRINTED.name(), new String[]{ECardStatusAction.CANCEL_CARD.name(), ECardStatusAction.ACTIVATE_CARD.name()});
        CARD_STATUS_ALLOWED_ACTION.put(ECardStatus.DISTRIBUTED.name(), new String[]{ECardStatusAction.CANCEL_CARD.name(), ECardStatusAction.ACTIVATE_CARD.name()});
        CARD_STATUS_ALLOWED_ACTION.put(ECardStatus.WAITING_TO_SEND.name(), new String[]{ECardStatusAction.CANCEL_CARD.name()});

    }

    /**
     * Find all applicants cards.
     *
     * @param pageable the current page information
     * @return the list of applicants' cards
     */
    public Page<ApplicantCardDto> findAll(Pageable pageable) {
        return mapPage(applicantCardRepository.findAllApplicantCards(ECardStatus.REISSUED.name(), pageable));
    }

    /**
     * Find printing cards with search parameters.
     *
     * @param uin
     * @param idNumber
     * @param hamlahNumber
     * @param motawefNumber
     * @param passportNumber
     * @param nationalityCode
     * @param excludedCardsIds
     * @param pageable         the current page information
     * @return the list of printing cards
     */
    public Page<ApplicantCardDto> findPrintingCards(String uin, String idNumber, String hamlahNumber, String motawefNumber,
                                                    String passportNumber, String nationalityCode, List<Long> excludedCardsIds,
                                                    Pageable pageable) {
        log.debug("Find printing cards...");
        return mapPage(applicantCardRepository.findPrintingCards(ECardStatus.READY_TO_PRINT.name(),
                EPrintRequestStatus.NEW.name(), uin, idNumber, passportNumber, nationalityCode,
                excludedCardsIds.size() == 0 ? Arrays.asList(-1L) : excludedCardsIds, pageable));
    }

    public List<ApplicantCardDto> findAllPrintingCards(String uin, String idNumber, String hamlahNumber, String motawefNumber,
                                                       String passportNumber, String nationalityCode, List<Long> excludedCardsIds) {
        log.debug("Find all printing cards...");
        return mapList(applicantCardRepository.findAllPrintingCards(ECardStatus.READY_TO_PRINT.name(),
                EPrintRequestStatus.NEW.name(), uin, idNumber, passportNumber, nationalityCode,
                excludedCardsIds.size() == 0 ? Arrays.asList(-1L) : excludedCardsIds));
    }

    /**
     * Find Applicant Cards based on search criteria.
     *
     * @param uin
     * @param idNumber
     * @param pageable the current page information
     * @return the list of applicant cards
     */
    public Page<ApplicantCardDto> searchApplicantCards(String uin, String idNumber, String passportNumber, Pageable pageable) {
        Page<ApplicantCardDto> applicantCards;
        if (uin == null && idNumber == null && passportNumber == null) {
            applicantCards = mapPage(applicantCardRepository.findAllApplicantCards(ECardStatus.REISSUED.name(), pageable));
        } else {
            applicantCards = mapPage(applicantCardRepository.searchApplicantCards(uin, idNumber, passportNumber, ECardStatus.REISSUED.name(), pageable));
        }
        applicantCards.forEach(card -> {
            ApplicantPackageDto applicantPackageDto = card.getApplicantRitual().getApplicantPackage();
            if (applicantPackageDto != null) {
                card.getApplicantRitual().setTypeCode(applicantPackageDto.getRitualPackage().getCompanyRitualSeason().getRitualSeason().getRitualTypeCode());
            }
        });
        return applicantCards;
    }

    /**
     * change card status
     *
     * @param card       to change its status
     * @param actionCode the new status Code
     * @param userId     the id of the logged-in user
     * @return card with  the status changed successfully
     **/
    @Transactional
    public ApplicantCardDto changeCardStatus(ApplicantCardDto card, String actionCode, Optional<Long> userId) {

        if (actionCode.equalsIgnoreCase(ECardStatusAction.CANCEL_CARD.name())) {
            card.setStatusCode(ECardStatus.CANCELLED.name());
        } else if (actionCode.equalsIgnoreCase(ECardStatusAction.ACTIVATE_CARD.name())) {
            card.setStatusCode(ECardStatus.ACTIVE.name());
        } else if (actionCode.equalsIgnoreCase(ECardStatusAction.SUSPEND_CARD.name())) {
            card.setStatusCode(ECardStatus.SUSPENDED.name());
        } else {
            card.setStatusCode(ECardStatus.REISSUED.name());
            save(card);
            log.debug("Generate new applicant card  after mark old one as reissued...");
            userCardStatusAuditService.saveUserCardStatusAudit(card, userId);
            ApplicantCardDto savedCard = save(ApplicantCardDto.builder().applicantRitual(card.getApplicantRitual()).statusCode(ECardStatus.READY_TO_PRINT.name()).build());
            userCardStatusAuditService.saveUserCardStatusAudit(savedCard, userId);
            return savedCard;
        }
        userCardStatusAuditService.saveUserCardStatusAudit(card, userId);
        return save(card);

    }


    /**
     * Build Applicant Card
     *
     * @param applicantCardDto the   applicant card to be populated
     * @return the   final applicant card to be returned
     */
    public ApplicantCardDto buildApplicantCard(ApplicantCardDto applicantCardDto) {

        ApplicantRitualDto applicantRitualDto = applicantRitualService.findApplicantRitualWithContactsAndRelatives(applicantCardDto.getApplicantRitual().getId());

        applicantCardDto.setApplicantRitual(applicantRitualDto);
        applicantCardDto.getApplicantRitual().getApplicant().setContacts(new ArrayList<>(applicantRitualDto.getContacts()));
        applicantCardDto.getApplicantRitual().getApplicant().setRelatives(new ArrayList<>(applicantRitualDto.getRelatives()));

        if (CollectionUtils.isNotEmpty(applicantRitualDto.getApplicantHealths())) {
            applicantCardDto.getApplicantRitual().getApplicant().setApplicantHealth(new ArrayList<>(applicantRitualDto.getApplicantHealths()).get(0));
        }
        List<ApplicantDigitalIdDto> digitalIds = applicantCardDto.getApplicantRitual().getApplicant().getDigitalIds();
        if (digitalIds.size() > 0) {

            String uin = digitalIds.get(0).getUin();
            ApplicantPackageDto applicantPackageDto = applicantRitualDto.getApplicantPackage();
            if (applicantPackageDto != null) {
                applicantCardDto.getApplicantRitual().setTypeCode(applicantPackageDto.getRitualPackage().getCompanyRitualSeason().getRitualSeason().getRitualTypeCode());

                long applicantPackageId = applicantPackageDto.getId();
                applicantCardDto.setApplicantPackageHousings(applicantPackageHousingService.findApplicantPackageHousingByUinAndApplicantPackageId(Long.parseLong(uin), applicantPackageId));
                applicantCardDto.setApplicantPackageCaterings(applicantPackageCateringService.findApplicantPackageCateringByUinAndApplicantPackageId(Long.parseLong(uin), applicantPackageId));
                applicantCardDto.setApplicantPackageTransportations(applicantPackageTransportationService.findApplicantPackageTransportationByUinAndApplicantPackageId(Long.parseLong(uin), applicantPackageId));
                applicantCardDto.setCompanyLite(companyService.findCompanyByCompanyRitualSeasonsIdAndApplicantUin(applicantPackageDto.getRitualPackage().getCompanyRitualSeason().getId(), Long.parseLong(uin)));
                List<CompanyRitualStepDto> companyRitualSteps = companyRitualStepService.findCompanyRitualStepsByApplicantUin(uin);
                applicantCardDto.setCompanyRitualSteps(companyRitualSteps);
                List<CompanyStaffDto> groupLeaders = companyStaffService.findRelatedEmployeesByApplicantUinAndSeasonId(uin, applicantPackageDto.getRitualPackage().getCompanyRitualSeason().getId());
                applicantCardDto.setGroupLeaders(groupLeaders);
            }
        }
        return applicantCardDto;
    }

    /**
     * Find Applicant Card
     *
     * @param cardId
     * @return the  applicant cards
     */
    public ApplicantCardDto findApplicantCard(long cardId) {
        return getMapper().fromEntity(applicantCardRepository.findByIdAndStatusCodeNot(cardId, ECardStatus.REISSUED.name()), mappingContext);
    }

    public List<ApplicantCardDto> findApplicantCardsEligibleToExpire() {
        List<String> excludedCardsStatuses = Stream.of(ECardStatus.EXPIRED.name(), ECardStatus.CANCELLED.name(), ECardStatus.REISSUED.name()).collect(Collectors.toList());
        List<JpaApplicantCard> applicantCardsList = applicantCardRepository.findApplicantCardsEligibleToExpire(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()), excludedCardsStatuses);
        return getMapper().fromEntityList(applicantCardsList, mappingContext);
    }

    @Transactional
    public void updateCardStatusesAsExpired(List<Long> cardsIds) {
        applicantCardRepository.updateCardStatusesAsExpired(ECardStatus.EXPIRED.name(), cardsIds);
    }

}
