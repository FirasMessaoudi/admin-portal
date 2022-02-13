/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.CountVo;
import com.elm.shj.admin.portal.orm.entity.JpaCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for company data.
 *
 * @author salzoubi
 * @since 1.1.0
 **/
public interface CompanyRepository extends JpaRepository<JpaCompany, Long> {
    JpaCompany findByCompanyRitualSeasonsIdAndCompanyRitualSeasonsRitualPackagesApplicantPackagesApplicantUin(long companyRitualSeasonsId, long applicantUin);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.CountVo(c.labelAr, 0, COUNT(c.labelAr), '') " +
            "FROM JpaApplicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs JOIN crs.company c JOIN crs.ritualSeason rs " +
            "WHERE rs.seasonYear = :seasonYear AND rs.ritualTypeCode IN (:ritualTypeCodeList) " +
            "GROUP BY c.labelAr ORDER BY COUNT(c.labelAr) DESC")
    Page<CountVo> findCompaniesWithMaxApplicantsByHijriSeason(@Param("seasonYear") int seasonYear,
                                                              @Param("ritualTypeCodeList") List<String> ritualTypeCodeList, Pageable pageable);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.CountVo(c.labelAr, 0, COUNT(c.labelAr), '') " +
            "FROM JpaApplicantPackage ap JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs JOIN crs.company c JOIN crs.ritualSeason rs " +
            "WHERE rs.seasonYear = :seasonYear AND rs.ritualTypeCode IN (:ritualTypeCodeList) " +
            "GROUP BY c.labelAr ORDER BY COUNT(c.labelAr)")
    Page<CountVo> findCompaniesWithMinApplicantsByHijriSeason(@Param("seasonYear") int seasonYear,
                                                              @Param("ritualTypeCodeList") List<String> ritualTypeCodeList, Pageable pageable);
}
