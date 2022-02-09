/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualSeason;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
