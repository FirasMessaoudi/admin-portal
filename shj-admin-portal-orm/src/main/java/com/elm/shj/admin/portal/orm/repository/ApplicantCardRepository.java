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
            "WHERE (pr.statusCode <> :printRequestStatus or card2.statusCode <> :cardStatus) and pr.target='APPLICANT') AND card.id NOT IN :excludedCardsIds " +
            "AND (adi.uin LIKE '%'+:uin+'%' OR :uin IS NULL) AND (a.idNumber LIKE '%'+:idNumber+'%' OR :idNumber IS NULL) " +
            "AND (a.passportNumber LIKE '%'+:passportNumber+'%' OR :passportNumber IS NULL) " +
            "AND (a.nationalityCode = :nationalityCode OR :nationalityCode IS NULL) and a.deleted = false")
    Page<JpaApplicantCard> findPrintingCards(@Param("cardStatus") String cardStatus, @Param("printRequestStatus") String printRequestStatus,
                                             @Param("uin") String uin, @Param("idNumber") String idNumber, @Param("passportNumber") String passportNumber,
                                             @Param("nationalityCode") String nationalityCode, @Param("excludedCardsIds") List<Long> excludedCardsIds,
                                             Pageable pageable);

    JpaApplicantCard findByIdAndStatusCodeNot(long id, String statusCode);

    @Query("SELECT card FROM JpaApplicantCard card LEFT JOIN card.applicantRitual ar LEFT JOIN ar.applicant a LEFT JOIN a.digitalIds adi WHERE card.id " +
            "NOT IN (SELECT card2.id FROM JpaApplicantCard card2 LEFT JOIN JpaPrintRequestCard prc ON card2.id= prc.cardId LEFT JOIN JpaPrintRequest pr ON prc.printRequest.id=pr.id " +
            "WHERE (pr.statusCode <> :printRequestStatus OR card2.statusCode <> :cardStatus)  and pr.target='APPLICANT') AND card.id NOT IN :excludedCardsIds " +
            "AND (adi.uin LIKE '%'+:uin+'%' OR :uin IS NULL) AND (a.idNumber LIKE '%'+:idNumber+'%' OR :idNumber IS NULL) " +
            "AND (a.passportNumber LIKE '%'+:passportNumber+'%' OR :passportNumber IS NULL) " +
            "AND (a.nationalityCode = :nationalityCode OR :nationalityCode IS NULL) and a.deleted = false")
    List<JpaApplicantCard> findAllPrintingCards(@Param("cardStatus") String cardStatus, @Param("printRequestStatus") String printRequestStatus, @Param("uin") String uin, @Param("idNumber") String idNumber, @Param("passportNumber") String passportNumber, @Param("nationalityCode") String nationalityCode, @Param("excludedCardsIds") List<Long> excludedCardsIds);

    /*this method is used find all Applicant Cards with status Not Equals REISSUED */
    @Query("SELECT card FROM JpaApplicantCard card WHERE card.statusCode <> :reissuedStatusCode ")
    Page<JpaApplicantCard> findAllApplicantCards(@Param("reissuedStatusCode") String reissuedStatusCode, Pageable pageable);

    /*this method is used to filter Applicant Cards Based on Search Criteria */
    @Query("SELECT card FROM JpaApplicantCard card LEFT JOIN card.applicantRitual ar LEFT JOIN ar.applicant a LEFT JOIN a.digitalIds adi " +
            "WHERE (adi.uin LIKE '%'+:uin+'%' OR :uin IS NULL) AND (TRIM(a.idNumber) LIKE '%'+:idNumber+'%' OR :idNumber IS NULL) AND (a.passportNumber LIKE '%'+:passportNumber+'%' OR :passportNumber IS NULL) AND (card.statusCode <> :reissuedStatusCode ) ")
    Page<JpaApplicantCard> searchApplicantCards(@Param("uin") String uin, @Param("idNumber") String idNumber, @Param("passportNumber") String passportNumber, @Param("reissuedStatusCode") String reissuedStatusCode, Pageable pageable);

    JpaApplicantCard findByApplicantRitualIdAndStatusCodeNot(long id, String statusCode);

    @Query("SELECT ac FROM JpaApplicantCard ac WHERE :todayDate > ac.applicantRitual.applicantPackage.endDate AND ac.statusCode NOT IN :excludedCardsStatuses")
    List<JpaApplicantCard> findCardsToExpire(@Param("todayDate") Date todayDate, @Param("excludedCardsStatuses") List<String> excludedCardsStatuses);

    //TODO: this may cause an issue with SQL server due to the IN parameters length; max is 2100 so find another solution
    @Query("SELECT appCard FROM JpaApplicantCard appCard WHERE appCard.id IN :cardsIds ")
    List<JpaApplicantCard> findApplicantCards(@Param("cardsIds") List<Long> cardsIds);

    @Query("SELECT applicantCard from JpaApplicantCard applicantCard " +
            "join applicantCard.applicantRitual applicantRitual " +
            "join applicantRitual.applicant applicant " +
            "join JpaApplicantDigitalId applicantDigitalId on applicantDigitalId.applicantId = applicant.id " +
            "join JpaPrintRequestBatchCard printRequestBatchCard on printRequestBatchCard.cardId = applicantCard.id " +
            "join printRequestBatchCard.printRequestBatch printRequestBatch " +
            "where printRequestBatch.id = :batchId " +
            "and applicantDigitalId.uin in :digitalIdList and applicant.deleted = false ")
    List<JpaApplicantCard> findApplicantCardsByPrintRequestBatchIdAndDigitalIds(@Param("digitalIdList") List<String> digitalIdList , @Param("batchId") long batchId);

    JpaApplicantCard findByApplicantRitualIdAndStatusCodeNotIn(long applicantRitualId, List<String> cardsStatus);

    @Modifying
    @Query("UPDATE JpaApplicantCard appCard SET appCard.statusCode=:status, appCard.updateDate = CURRENT_TIMESTAMP WHERE appCard.id IN :cardsIds")
    void updateCardStatus(@Param("cardsIds") List<Long> cardsIds, @Param("status") String status);
}
