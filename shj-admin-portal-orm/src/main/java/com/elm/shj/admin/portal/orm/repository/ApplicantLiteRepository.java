/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantLite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for applicant lite.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface ApplicantLiteRepository extends JpaRepository<JpaApplicantLite, Long> {
    @Query(value = "SELECT a FROM JpaApplicantLite a JOIN a.digitalIds adi WHERE adi.uin = :uin")
    JpaApplicantLite findByUin(@Param("uin") String uin);
}
