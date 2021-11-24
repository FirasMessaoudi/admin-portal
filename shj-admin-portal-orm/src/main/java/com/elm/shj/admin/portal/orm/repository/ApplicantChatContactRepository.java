/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantChatContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for Applicant Chat Contact Table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface ApplicantChatContactRepository extends JpaRepository<JpaApplicantChatContact, Long> {

    List<JpaApplicantChatContact> findAllByApplicantUin(String applicantUin);

    List<JpaApplicantChatContact> findAllByApplicantUinAndSystemDefined(String applicantUin, Boolean systemDefined);

    long deleteByApplicantUinAndSystemDefinedFalse(String applicantUin);
}
