/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantCardBasic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Applicant Card Table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public interface ApplicantCardBasicRepository extends JpaRepository<JpaApplicantCardBasic, Long> {

}
