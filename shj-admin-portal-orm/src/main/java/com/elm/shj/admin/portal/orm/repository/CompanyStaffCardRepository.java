/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for Company Staff Card Table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public interface CompanyStaffCardRepository extends JpaRepository<JpaCompanyStaffCard, Long> , JpaSpecificationExecutor<JpaCompanyStaffCard> {
    List<JpaCompanyStaffCard> findAllByCompanyStaffDigitalIdSuin(String suin);

    JpaCompanyStaffCard findByCompanyStaffDigitalIdSuinAndStatusCode(String suin, String statusCode);

    @Query("select c from JpaCompanyStaffCard c where c.companyStaffDigitalId.suin = :suin and c.companyRitualSeason.company.code = :companyCode and c.companyRitualSeason.ritualSeason.ritualTypeCode = :ritualCode and c.statusCode in :cardStatus")
    List<JpaCompanyStaffCard> findAllByCompanyStaffSuinAndCompanyRitualSeasonCompanyCodeAndCompanyRitualSeasonRitualSeasonRitualTypeCode(@Param("suin") String suin, @Param("companyCode") String companyCode, @Param("ritualCode") String ritualTypeCode, @Param("cardStatus") List<String> cardStatus);

    @Query("select c from JpaCompanyStaffCard c where" +
            " (c.companyStaffDigitalId.suin = :suin) and" +
            "(c.companyRitualSeason.company.code != :companyCode or c.companyRitualSeason.ritualSeason.ritualTypeCode != :ritualCode) and c.statusCode in :cardStatus")
    List<JpaCompanyStaffCard> findAllByCompanyStaffSuinAndCompanyRitualSeasonCompanyCodeNotOrCompanyRitualSeasonRitualSeasonRitualTypeCodeNot(@Param("suin") String suin, @Param("companyCode") String companyCode, @Param("ritualCode") String ritualTypeCode, @Param("cardStatus") List<String> cardStatus);

    @Query("SELECT c FROM JpaCompanyStaffCard c WHERE  c.statusCode <> :reissuedStatusCode ")
    Page<JpaCompanyStaffCard> findStaffCards(@Param("reissuedStatusCode") String reissuedStatusCode, Pageable pageable);

    @Query("select c from JpaCompanyStaffCard c where c.statusCode = :cardStatus and c.id NOT IN :excludedCardsIds " +
            "and (c.companyStaffDigitalId.suin LIKE '%'+:uin+'%' OR :uin IS NULL) AND (c.companyStaffDigitalId.companyStaff.idNumber LIKE '%'+:idNumber+'%' OR :idNumber IS NULL)" +
            "and (c.companyStaffDigitalId.companyStaff.passportNumber LIKE '%'+:passportNumber+'%' OR :passportNumber IS NULL)" +
            "and (c.companyStaffDigitalId.companyStaff.nationalityCode = :nationalityCode OR :nationalityCode IS NULL)")
    List<JpaCompanyStaffCard> findAllPrintingCards(@Param("cardStatus") String cardStatus, @Param("uin") String uin, @Param("idNumber") String idNumber, @Param("passportNumber") String passportNumber, @Param("nationalityCode") String nationalityCode, @Param("excludedCardsIds") List<Long> excludedCardsIds);

    @Query("select c from JpaCompanyStaffCard c where c.statusCode = :cardStatus and c.id NOT IN :excludedCardsIds " +
            "and (c.companyStaffDigitalId.suin LIKE '%'+:uin+'%' OR :uin IS NULL) AND (c.companyStaffDigitalId.companyStaff.idNumber LIKE '%'+:idNumber+'%' OR :idNumber IS NULL)" +
            "and (c.companyStaffDigitalId.companyStaff.passportNumber LIKE '%'+:passportNumber+'%' OR :passportNumber IS NULL)" +
            "and (c.companyStaffDigitalId.companyStaff.nationalityCode = :nationalityCode OR :nationalityCode IS NULL)")
    Page<JpaCompanyStaffCard> findPrintingCards(@Param("cardStatus") String cardStatus, @Param("uin") String uin, @Param("idNumber") String idNumber, @Param("passportNumber") String passportNumber, @Param("nationalityCode") String nationalityCode, @Param("excludedCardsIds") List<Long> excludedCardsIds, Pageable pageable);
}
