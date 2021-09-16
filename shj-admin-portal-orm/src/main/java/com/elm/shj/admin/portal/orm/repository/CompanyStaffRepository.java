/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for Company Staff data.
 *
 * @author salzoubi
 * @since 1.1.0
 **/
public interface CompanyStaffRepository extends JpaRepository<JpaCompanyStaff, Long> {

    @Query("select cs from JpaCompanyStaff cs join cs.companyRitualSeason cr"+
            " join cs.applicantGroups ag join cr.ritualSeason rs join ag.groupApplicantLists ga"+
            " where ga.applicantUin = :uin and rs.id = :sid and ag.id = ga.applicantGroup.id")
    List<JpaCompanyStaff> findRelatedEmployeesByUinAndSeasonId(@Param("uin") String uin, @Param("sid") long sid);

}
