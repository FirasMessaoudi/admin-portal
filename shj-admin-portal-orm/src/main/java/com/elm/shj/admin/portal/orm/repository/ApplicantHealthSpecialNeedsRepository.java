/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealthSpecialNeeds;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for applicant health special needs table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
public interface ApplicantHealthSpecialNeedsRepository extends JpaRepository<JpaApplicantHealthSpecialNeeds, Long> {
}
