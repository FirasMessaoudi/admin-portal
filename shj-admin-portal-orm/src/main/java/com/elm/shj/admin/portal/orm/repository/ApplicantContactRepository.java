/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for Applicant Contact Table.
 *
 * @author ahmad Ali
 * @since 1.1.0
 */
public interface ApplicantContactRepository extends JpaRepository<JpaApplicantContact, Long> {
    @Modifying
    @Query("update JpaApplicantContact contact set  contact.email = :email, " +
            "contact.intlMobileNumber =:intlMobileNumber, contact.updateDate = CURRENT_TIMESTAMP where contact.applicant.id =:applicantId")
    int updateContactIntlNumber(@Param("email") String email,
                                @Param("intlMobileNumber") String intlMobileNumber, @Param("applicantId") long applicantId);


    @Modifying
    @Query("update JpaApplicantContact contact set  contact.email = :email, " +
            "contact.localMobileNumber =:localMobileNumber, contact.updateDate = CURRENT_TIMESTAMP where contact.applicant.id =:applicantId")
    int updateContactLocalNumber(@Param("email") String email,
                                 @Param("localMobileNumber") String localMobileNumber, @Param("applicantId") long applicantId);
    List<JpaApplicantContact> findAllByApplicantId(Long id);
}
