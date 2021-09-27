/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualSeason;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for Company Ritual Season data.
 *
 * @author salzoubi
 * @since 1.1.0
 **/
public interface CompanyRitualSeasonRepository extends JpaRepository<JpaCompanyRitualSeason, Long> {

    JpaCompanyRitualSeason findTopByRitualPackagesApplicantPackagesApplicantUinOrderBySeasonStartDesc(long applicantUin);

    List<JpaCompanyRitualSeason> findAllByRitualPackagesApplicantPackagesApplicantUinOrderBySeasonStartDesc(long applicantUin);

}
