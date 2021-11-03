/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.card;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantCard;
import com.elm.shj.admin.portal.orm.repository.ApplicantCardRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantCardDto;
import com.elm.shj.admin.portal.services.dto.ECardStatus;
import com.elm.shj.admin.portal.services.dto.ECardStatusAction;
import com.elm.shj.admin.portal.services.dto.EPrintRequestStatus;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

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

    private static Map<String, String[]> CARD_STATUS_ALLOWED_ACTION = new HashMap<>();

    private final ApplicantCardRepository applicantCardRepository;

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
        return mapPage(getRepository().findAll(pageable));
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
        if (uin == null && idNumber == null && passportNumber == null) {
            return mapPage(getRepository().findAll(pageable));
        } else {

            return mapPage(applicantCardRepository.searchApplicantCards(uin, idNumber, passportNumber, pageable));

        }
    }

    /**
     * change card status
     *
     * @param card            to   change it's status
     * @param actionCode      the new status Code
     * @param userAuthorities set of user authorities the new status Code
     * @return String represent if the change done successfully or an issue happened
     **/
    @Transactional
    public String changeCardStatus(ApplicantCardDto card, String actionCode, Set<GrantedAuthority> userAuthorities) {
        boolean isUserAllowed = userAuthorities.parallelStream().anyMatch(auth -> auth.getAuthority().equalsIgnoreCase(actionCode));
        if (!isUserAllowed)
            return "user not allowed";

        String[] allowedActions = CARD_STATUS_ALLOWED_ACTION.get(card.getStatusCode().toUpperCase());
        boolean isActionAllowed = Arrays.stream(allowedActions).anyMatch(action -> action.equalsIgnoreCase(actionCode));

        if (!isActionAllowed)
            return "action not allowed";

        if (actionCode.equalsIgnoreCase(ECardStatusAction.CANCEL_CARD.name())) {
            applicantCardRepository.changeCardStatus(card.getId(), ECardStatus.CANCELLED.name());
        } else if (actionCode.equalsIgnoreCase(ECardStatusAction.ACTIVATE_CARD.name())) {
            applicantCardRepository.changeCardStatus(card.getId(), ECardStatus.ACTIVE.name());
        } else if (actionCode.equalsIgnoreCase(ECardStatusAction.SUSPEND_CARD.name())) {
            applicantCardRepository.changeCardStatus(card.getId(), ECardStatus.SUSPENDED.name());
        } else {
            applicantCardRepository.changeCardStatus(card.getId(), ECardStatus.REISSUED.name());
            log.debug("Generate new applicant card  after mark old one as reissued...");
            save(ApplicantCardDto.builder().applicantRitual(card.getApplicantRitual()).statusCode(ECardStatus.READY_TO_PRINT.name()).build());
        }
        return "Status Changed Successfully";
    }


}
