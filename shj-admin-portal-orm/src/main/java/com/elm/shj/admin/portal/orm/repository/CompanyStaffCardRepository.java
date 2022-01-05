/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for Company Staff Card Table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public interface CompanyStaffCardRepository extends JpaRepository<JpaCompanyStaffCard, Long> {
    List<JpaCompanyStaffCard> findAllByCompanyStaffSuin(String suin);

    JpaCompanyStaffCard findByCompanyStaffSuinAndStatusCode(String suin, String statusCode);
    //check those two
    @Query("select c from JpaCompanyStaffCard c where c.companyStaffSuin = :suin and c.companyRitualSeason.company.code = :companyCode and c.companyRitualSeason.ritualSeason.ritualTypeCode = :ritualCode and c.statusCode in :cardStatus")
    List<JpaCompanyStaffCard> findAllByCompanyStaffSuinAndCompanyRitualSeasonCompanyCodeAndCompanyRitualSeasonRitualSeasonRitualTypeCode(@Param("suin") String suin, @Param("companyCode") String companyCode, @Param("ritualCode") String ritualTypeCode, @Param("cardStatus") List<String> cardStatus);

    @Query("select c from JpaCompanyStaffCard c where" +
            " (c.companyStaffSuin = :suin) and" +
            "(c.companyRitualSeason.company.code != :companyCode or c.companyRitualSeason.ritualSeason.ritualTypeCode != :ritualCode) and c.statusCode in :cardStatus")
    List<JpaCompanyStaffCard> findAllByCompanyStaffSuinAndCompanyRitualSeasonCompanyCodeNotOrCompanyRitualSeasonRitualSeasonRitualTypeCodeNot(@Param("suin") String suin, @Param("companyCode") String companyCode, @Param("ritualCode") String ritualTypeCode, @Param("cardStatus") List<String> cardStatus);

    @Query("SELECT c FROM JpaCompanyStaffCard c WHERE  c.statusCode <> :reissuedStatusCode ")
    Page<JpaCompanyStaffCard> findStaffCards(@Param("reissuedStatusCode") String reissuedStatusCode, Pageable pageable);
}
