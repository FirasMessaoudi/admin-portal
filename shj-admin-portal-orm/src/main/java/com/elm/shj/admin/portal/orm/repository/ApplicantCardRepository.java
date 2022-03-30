/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
/**
 * Repository for Applicant Card Table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public interface ApplicantCardRepository extends JpaRepository<JpaApplicantCard, Long>, JpaSpecificationExecutor<JpaApplicantCard> {

    @Query("SELECT card FROM JpaApplicantCard card LEFT JOIN card.applicantRitual ar LEFT JOIN ar.applicant a LEFT JOIN a.digitalIds adi WHERE card.id " +
            "NOT IN (SELECT card2.id FROM JpaApplicantCard card2 LEFT JOIN JpaPrintRequestCard prc  ON card2.id= prc.cardId  LEFT JOIN JpaPrintRequest pr ON prc.printRequest.id=pr.id " +
            "WHERE pr.statusCode <> :printRequestStatus or card2.statusCode <> :cardStatus) AND card.id NOT IN :excludedCardsIds " +
            "AND (adi.uin LIKE '%'+:uin+'%' OR :uin IS NULL) AND (a.idNumber LIKE '%'+:idNumber+'%' OR :idNumber IS NULL) " +
            "AND (a.passportNumber LIKE '%'+:passportNumber+'%' OR :passportNumber IS NULL) " +
            "AND (a.nationalityCode = :nationalityCode OR :nationalityCode IS NULL)")
    Page<JpaApplicantCard> findPrintingCards(@Param("cardStatus") String cardStatus, @Param("printRequestStatus") String printRequestStatus,
                                             @Param("uin") String uin, @Param("idNumber") String idNumber, @Param("passportNumber") String passportNumber,
                                             @Param("nationalityCode") String nationalityCode, @Param("excludedCardsIds") List<Long> excludedCardsIds,
                                             Pageable pageable);

    JpaApplicantCard findByIdAndStatusCodeNot(long id, String statusCode);

    @Query("SELECT card FROM JpaApplicantCard card LEFT JOIN card.applicantRitual ar LEFT JOIN ar.applicant a LEFT JOIN a.digitalIds adi WHERE card.id " +
            "NOT IN (SELECT card2.id FROM JpaApplicantCard card2 LEFT JOIN JpaPrintRequestCard prc ON card2.id= prc.cardId LEFT JOIN JpaPrintRequest pr ON prc.printRequest.id=pr.id " +
            "WHERE pr.statusCode <> :printRequestStatus OR card2.statusCode <> :cardStatus) AND card.id NOT IN :excludedCardsIds " +
            "AND (adi.uin LIKE '%'+:uin+'%' OR :uin IS NULL) AND (a.idNumber LIKE '%'+:idNumber+'%' OR :idNumber IS NULL) " +
            "AND (a.passportNumber LIKE '%'+:passportNumber+'%' OR :passportNumber IS NULL) " +
            "AND (a.nationalityCode = :nationalityCode OR :nationalityCode IS NULL)")
    List<JpaApplicantCard> findAllPrintingCards(@Param("cardStatus") String cardStatus, @Param("printRequestStatus") String printRequestStatus, @Param("uin") String uin, @Param("idNumber") String idNumber, @Param("passportNumber") String passportNumber, @Param("nationalityCode") String nationalityCode, @Param("excludedCardsIds") List<Long> excludedCardsIds);


    /*this method is used find all Applicant Cards with status Not Equals REISSUED */
    @Query("SELECT card FROM JpaApplicantCard card WHERE card.statusCode <> :reissuedStatusCode ")
    Page<JpaApplicantCard> findAllApplicantCards(@Param("reissuedStatusCode") String reissuedStatusCode, Pageable pageable);

    /*this method is used to filter Applicant Cards Based on Search Criteria */
    @Query("SELECT card FROM JpaApplicantCard card LEFT JOIN card.applicantRitual ar LEFT JOIN ar.applicant a LEFT JOIN a.digitalIds adi " +
            "WHERE (adi.uin LIKE '%'+:uin+'%' OR :uin IS NULL) AND (TRIM(a.idNumber) LIKE '%'+:idNumber+'%' OR :idNumber IS NULL) AND (a.passportNumber LIKE '%'+:passportNumber+'%' OR :passportNumber IS NULL) AND (card.statusCode <> :reissuedStatusCode ) ")
    Page<JpaApplicantCard> searchApplicantCards(@Param("uin") String uin, @Param("idNumber") String idNumber, @Param("passportNumber") String passportNumber, @Param("reissuedStatusCode") String reissuedStatusCode, Pageable pageable);

    JpaApplicantCard findByApplicantRitualIdAndStatusCodeNot(long id, String statusCode);


    @Modifying
    @Query("UPDATE JpaApplicantCard card SET card.statusCode = :statusCode WHERE card.id in :cardsIds ")
    int updateCardStatusesAsExpired(@Param("statusCode") String statusCode, @Param("cardsIds") List<Long> cardsIds);

    @Query("SELECT appCard FROM JpaApplicantCard appCard " +
            "INNER JOIN appCard.applicantRitual ritual " +
            "INNER JOIN ritual.applicantPackage package " +
            "WHERE :todayDate > package.endDate AND appCard.statusCode NOT IN :excludedCardsStatuses ")
    List<JpaApplicantCard> findApplicantCardsEligibleToExpire(@Param("todayDate") Date todayDate, @Param("excludedCardsStatuses") List<String> excludedCardsStatuses);

    @Query("SELECT appCard FROM JpaApplicantCard appCard WHERE   appCard.id IN :cardsIds ")
    List<JpaApplicantCard> findApplicantCards(@Param("cardsIds") List<Long> cardsIds);

    @Modifying
    @Query("UPDATE JpaApplicantCard card SET card.statusCode = :newStatusCode WHERE card.id in :cardIdsList AND card.statusCode = :oldStatusCode" )
    int updateCardStatuses(@Param("newStatusCode") String newStatusCode,@Param("oldStatusCode") String oldStatusCode, @Param("cardIdsList") List<Long> cardIdsList);

    @Query("SELECT applicantCard from JpaApplicantCard applicantCard " +
            "join applicantCard.applicantRitual applicantRitual " +
            "join applicantRitual.applicant applicant " +
            "join JpaApplicantDigitalId applicantDigitalId on applicantDigitalId.applicantId = applicant.id " +
            "where applicantCard.batchId = :batchId " +
            "and applicantDigitalId.uin in :digitalIdList ")
    List<JpaApplicantCard> findApplicantCardsByPrintRequestBatchIdAndDigitalIds(@Param("digitalIdList") List<String> digitalIdList , @Param("batchId") long batchId);
}