/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualSeason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Company Ritual Season data.
 *
 * @author salzoubi
 * @since 1.1.0
 **/
public interface CompanyRitualSeasonRepository extends JpaRepository<JpaCompanyRitualSeason, Long> {

    Optional<JpaCompanyRitualSeason> findTopByCompanyCodeAndRitualSeasonRitualTypeCodeAndRitualSeasonSeasonYearAndRitualSeasonActiveTrueOrderBySeasonStartDesc(String companyCode, String ritualTypeCode, int seasonYear);

    Optional<JpaCompanyRitualSeason> findTopByCompanyStaffCardsCompanyStaffDigitalIdSuin(String suin);

    @Query("SELECT crs FROM JpaCompanyRitualSeason crs JOIN crs.company c JOIN crs.ritualSeason rs where " +
            "c.code = :companyCode AND rs.ritualTypeCode = :ritualTypeCode AND rs.seasonYear = :seasonYear AND rs.active=TRUE order by crs.seasonStart desc")
    Optional<JpaCompanyRitualSeason> findByCompanyCodeAndRitualTypeAndSeasonYear(@Param("companyCode") String companyCode, @Param("ritualTypeCode") String ritualTypeCode, @Param("seasonYear") int seasonYear);

    boolean existsByCompanyIdAndRitualSeasonId(Long companyId, Long ritualId);

    List<JpaCompanyRitualSeason> findByCompanyId(Long companyId);

}
