/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.GroupRitualStepVo;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStepLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for company ritual step lookup table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface CompanyRitualStepLookupRepository extends JpaRepository<JpaCompanyRitualStepLookup, Long> {


    @Query(value = "select j from JpaCompanyRitualStepLookup j")
    List<JpaCompanyRitualStepLookup> findAllWithDescription();

    List<JpaCompanyRitualStepLookup> findAllByLang(String lang);


    @Query("Select distinct new com.elm.shj.admin.portal.orm.entity.GroupRitualStepVo(s.id,s.applicantGroup.id,s.stepCode,slk.stepIndex,slk.editMode,s.time) " +
            "from JpaCompanyRitualStep s " +
            "join JpaCompanyRitualStepLookup slk on slk.code = s.stepCode " +
            "where s.applicantGroup.id = :groupId")
    List<GroupRitualStepVo> findCompanyRitualStepsByGroupId(@Param("groupId") long groupId);


    Optional<JpaCompanyRitualStepLookup> findTopByCodeAndLang(String code, String lang);
}
