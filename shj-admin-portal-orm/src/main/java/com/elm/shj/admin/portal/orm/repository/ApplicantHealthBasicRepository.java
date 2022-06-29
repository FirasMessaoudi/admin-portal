/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealthBasic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Lite repository for applicant health table.
 *
 * @author f.messaoudi
 * @since 1.3.0
 */
public interface ApplicantHealthBasicRepository extends JpaRepository<JpaApplicantHealthBasic, Long> {

}
