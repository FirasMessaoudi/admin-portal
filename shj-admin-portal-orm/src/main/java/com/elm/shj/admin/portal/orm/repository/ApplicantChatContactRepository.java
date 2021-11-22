/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantChatContact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Applicant Chat Contact Table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface ApplicantChatContactRepository extends JpaRepository<JpaApplicantChatContact, Long> {
}
