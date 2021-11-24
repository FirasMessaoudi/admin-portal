/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantChatContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for Applicant Chat Contact Table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface ApplicantChatContactRepository extends JpaRepository<JpaApplicantChatContact, Long> {

    List<JpaApplicantChatContact> findAllByApplicantUinAndDeletedFalse(String applicantUin);

    List<JpaApplicantChatContact> findAllByApplicantUinAndSystemDefinedAndDeletedFalse(String applicantUin, Boolean systemDefined);
    @Modifying
    @Query("update JpaApplicantChatContact contact set contact.deleted = true where contact.applicantUin =:applicantUin and" +
            " contact.contactUin=:contactUin and contact.systemDefined=false")
    int markDeleted(@Param("applicantUin") String applicantUin,@Param("contactUin") String contactUin );

}
