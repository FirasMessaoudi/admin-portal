/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantBasicInfoVo;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for Company Staff Card Table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public interface CompanyStaffCardRepository extends JpaRepository<JpaCompanyStaffCard, Long>, JpaSpecificationExecutor<JpaCompanyStaffCard> {
    List<JpaCompanyStaffCard> findAllByCompanyStaffDigitalIdSuin(String suin);

    JpaCompanyStaffCard findByCompanyStaffDigitalIdSuinAndStatusCode(String suin, String statusCode);

    @Query("select c from JpaCompanyStaffCard c where c.companyStaffDigitalId.suin = :suin and c.companyRitualSeason.company.code = :companyCode and c.companyRitualSeason.ritualSeason.ritualTypeCode = :ritualCode and c.statusCode in :cardStatus")
    List<JpaCompanyStaffCard> findAllByCompanyStaffSuinAndCompanyRitualSeasonCompanyCodeAndCompanyRitualSeasonRitualSeasonRitualTypeCode(
            @Param("suin") String suin, @Param("companyCode") String companyCode, @Param("ritualCode") String ritualTypeCode,
            @Param("cardStatus") List<String> cardStatus);

    @Query("select c from JpaCompanyStaffCard c where " +
            "(c.companyStaffDigitalId.suin = :suin) and " +
            "(c.companyRitualSeason.company.code != :companyCode or c.companyRitualSeason.ritualSeason.ritualTypeCode != :ritualCode) and c.statusCode in :cardStatus")
    List<JpaCompanyStaffCard> findAllByCompanyStaffSuinAndCompanyRitualSeasonCompanyCodeNotOrCompanyRitualSeasonRitualSeasonRitualTypeCodeNot(
            @Param("suin") String suin, @Param("companyCode") String companyCode, @Param("ritualCode") String ritualTypeCode,
            @Param("cardStatus") List<String> cardStatus);

    @Query("select c from JpaCompanyStaffCard c where c.statusCode = :cardStatus and c.id NOT IN :excludedCardsIds " +
            "and (c.id NOT IN(select p.cardId from JpaPrintRequestCard p where (p.printRequest.statusCode <> :printRequestStatus or c.statusCode <> :cardStatus) and p.printRequest.target='STAFF')) " +
            "and (c.companyStaffDigitalId.suin LIKE '%'+:uin+'%' OR :uin IS NULL)" +
            "and (c.companyRitualSeason.company.code = :companyCode OR :companyCode IS NULL)" +
            "and (c.companyStaffDigitalId.companyStaff.nationalityCode = :nationalityCode OR :nationalityCode IS NULL)" +
            "and (c.companyRitualSeason.ritualSeason.seasonYear = :seasonYear OR :seasonYear IS NULL)" +
            "and (c.companyRitualSeason.ritualSeason.ritualTypeCode = :ritualCode OR :ritualCode IS NULL)")
    List<JpaCompanyStaffCard> findAllPrintingCards(@Param("cardStatus") String cardStatus, @Param("printRequestStatus") String printRequestStatus,
                                                   @Param("uin") String uin, @Param("companyCode") String companyCode,
                                                   @Param("nationalityCode") String nationalityCode, @Param("seasonYear") int seasonYear,
                                                   @Param("ritualCode") String ritualCode, @Param("excludedCardsIds") List<Long> excludedCardsIds);

    @Query("select c from JpaCompanyStaffCard c where c.statusCode = :cardStatus and c.id NOT IN :excludedCardsIds " +
            "and (c.id NOT IN(select p.cardId from JpaPrintRequestCard p where (p.printRequest.statusCode <> :printRequestStatus or c.statusCode <> :cardStatus) and p.printRequest.target='STAFF')) " +
            "and (c.companyStaffDigitalId.suin LIKE '%'+:uin+'%' OR :uin IS NULL)" +
            "and (c.companyRitualSeason.company.code = :companyCode OR :companyCode IS NULL)" +
            "and (c.companyStaffDigitalId.companyStaff.nationalityCode = :nationalityCode OR :nationalityCode IS NULL)" +
            "and (c.companyRitualSeason.ritualSeason.seasonYear = :seasonYear OR :seasonYear IS NULL)" +
            "and (c.companyRitualSeason.ritualSeason.ritualTypeCode = :ritualCode OR :ritualCode IS NULL)")
    Page<JpaCompanyStaffCard> findPrintingCards(@Param("cardStatus") String cardStatus, @Param("printRequestStatus") String printRequestStatus,
                                                @Param("uin") String uin, @Param("companyCode") String companyCode,
                                                @Param("nationalityCode") String nationalityCode, @Param("seasonYear") int seasonYear,
                                                @Param("ritualCode") String ritualCode, @Param("excludedCardsIds") List<Long> excludedCardsIds,
                                                Pageable pageable);

    @Query("SELECT staffCard FROM JpaCompanyStaffCard staffCard WHERE   staffCard.id IN :cardsIds ")
    List<JpaCompanyStaffCard> findStaffCards(@Param("cardsIds") List<Long> cardsIds);


    @Query("SELECT staffCard from JpaCompanyStaffCard staffCard " +
            "join staffCard.companyStaffDigitalId companyStaffDigitalId " +
            "join JpaPrintRequestBatchCard printRequestBatchCard on printRequestBatchCard.cardId = staffCard.id " +
            "join printRequestBatchCard.printRequestBatch printRequestBatch " +
            "where printRequestBatch.id = :batchId " +
            "And companyStaffDigitalId.suin in :digitalIdList ")
    List<JpaCompanyStaffCard> findStaffCardsByPrintRequestBatchIdAndDigitalIds(@Param("digitalIdList") List<String> digitalIdList, @Param("batchId") long batchId);

    @Modifying
    @Query("UPDATE JpaCompanyStaffCard csc SET csc.statusCode=:status, csc.updateDate = CURRENT_TIMESTAMP WHERE csc.id IN :cardsIds")
    void updateCardStatus(@Param("cardsIds") List<Long> cardsIds, @Param("status") String status);

    @Query("select new com.elm.shj.admin.portal.orm.entity.ApplicantBasicInfoVo(digitalId.suin, staff.fullNameAr, staff.fullNameEn,card.referenceNumber) from JpaCompanyStaffCard card  " +
            " join card.companyStaffDigitalId digitalId " +
            " join digitalId.companyStaff staff " +
            "where card.statusCode not in :cardStatusCodeList " +
            "and digitalId.suin in :digitalIdList ")
    List<ApplicantBasicInfoVo> findAllByStaffDigitalIds(@Param("digitalIdList") List<String> digitalIdList,@Param("cardStatusCodeList") List<String> cardStatusCodeList);

    @Query("SELECT staffCard FROM JpaCompanyStaffCard staffCard join staffCard.companyStaffDigitalId csd join csd.companyStaff cs WHERE cs.id = :staffId and staffCard.statusCode NOT IN ('CANCELLED', 'EXPIRED', 'SUSPENDED') ")
    JpaCompanyStaffCard findStaffCard(@Param("staffId") Long staffId);

    @Modifying
    @Query("UPDATE JpaCompanyStaffCard csc SET csc.statusCode= 'CANCELLED', csc.updateDate = CURRENT_TIMESTAMP WHERE csc.id = :staffCardId")
    void updateCompanyStaffCardStatus(@Param("staffCardId") long staffCardId);
}
