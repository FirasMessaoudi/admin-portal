/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for applicant health table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
public interface ApplicantHealthRepository extends JpaRepository<JpaApplicantHealth, Long> {

    @Query(value = "SELECT ah FROM JpaApplicantHealth ah JOIN ah.applicant a JOIN a.digitalIds adi " +
            "WHERE adi.uin = :uin AND ah.applicantRitual.id = :ritualId")
    JpaApplicantHealth findByUinAndRitualId(@Param("uin") String uin, @Param("ritualId") Long ritualId);

}
