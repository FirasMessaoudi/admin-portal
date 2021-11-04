/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for Applicant Card Table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public interface ApplicantCardRepository extends JpaRepository<JpaApplicantCard, Long>   {

    @Query("SELECT card FROM JpaApplicantCard card LEFT JOIN card.applicantRitual ar LEFT JOIN ar.applicant a LEFT JOIN a.digitalIds adi WHERE card.id " +
            "NOT IN (SELECT card2.id FROM JpaApplicantCard card2 LEFT JOIN card2.printRequestCards prc LEFT JOIN prc.printRequest pr " +
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
            "NOT IN (SELECT card2.id FROM JpaApplicantCard card2 LEFT JOIN card2.printRequestCards prc LEFT JOIN prc.printRequest pr " +
            "WHERE pr.statusCode <> :printRequestStatus OR card2.statusCode <> :cardStatus) AND card.id NOT IN :excludedCardsIds " +
            "AND (adi.uin LIKE '%'+:uin+'%' OR :uin IS NULL) AND (a.idNumber LIKE '%'+:idNumber+'%' OR :idNumber IS NULL) " +
            "AND (a.passportNumber LIKE '%'+:passportNumber+'%' OR :passportNumber IS NULL) " +
            "AND (a.nationalityCode = :nationalityCode OR :nationalityCode IS NULL)")
    List<JpaApplicantCard> findAllPrintingCards(@Param("cardStatus") String cardStatus, @Param("printRequestStatus") String printRequestStatus,
                                                @Param("uin") String uin, @Param("idNumber") String idNumber, @Param("passportNumber") String passportNumber,
                                                @Param("nationalityCode") String nationalityCode, @Param("excludedCardsIds") List<Long> excludedCardsIds);


    /*this method is used find all Applicant Cards with status Not Equals  REISSUED */
    @Query("SELECT card FROM JpaApplicantCard card    WHERE  card.statusCode <> :reissuedStatusCode   ")
    Page<JpaApplicantCard> findAllApplicantCards(@Param("reissuedStatusCode") String reissuedStatusCode, Pageable pageable);

    /*this method is used to filter Applicant Cards Based on Search Criteria */
    @Query("SELECT card FROM JpaApplicantCard card LEFT JOIN card.applicantRitual ar LEFT JOIN ar.applicant a LEFT JOIN a.digitalIds adi " +
            "WHERE (adi.uin LIKE '%'+:uin+'%' OR :uin IS NULL) AND (TRIM(a.idNumber) LIKE '%'+:idNumber+'%' OR :idNumber IS NULL) AND (a.passportNumber LIKE '%'+:passportNumber+'%' OR :passportNumber IS NULL)  AND (card.statusCode <> :reissuedStatusCode ) ")
    Page<JpaApplicantCard> searchApplicantCards(@Param("uin") String uin, @Param("idNumber") String idNumber, @Param("passportNumber") String passportNumber, @Param("reissuedStatusCode") String reissuedStatusCode, Pageable pageable);

    JpaApplicantCard findByApplicantRitualId(long id);


}
