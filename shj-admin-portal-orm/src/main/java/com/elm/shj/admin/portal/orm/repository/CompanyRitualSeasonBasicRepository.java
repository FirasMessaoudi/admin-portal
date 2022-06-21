package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualSeasonBasic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for Company Ritual Season Basic data.
 *
 * @author ahmad flaifel
 * @since 1.2.5
 **/
public interface CompanyRitualSeasonBasicRepository extends JpaRepository<JpaCompanyRitualSeasonBasic, Long> {

    Optional<JpaCompanyRitualSeasonBasic> findTopByCompanyCodeAndRitualSeasonRitualTypeCodeAndRitualSeasonSeasonYearAndRitualSeasonActiveTrueOrderBySeasonStartDesc(String companyCode, String ritualTypeCode, int seasonYear);
}