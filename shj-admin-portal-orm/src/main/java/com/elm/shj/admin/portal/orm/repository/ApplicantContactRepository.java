/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantCard;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantContact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Query("update JpaApplicantContact contact set contact.countryCode = :countryCode, contact.email = :email, " +
            "contact.intlMobileNumber =:intlMobileNumber where contact.applicant.id =:applicantId and contact.applicantRitual.id=:ritualId")
    void updateContactIntlNumber(@Param("email") String email, @Param("countryCode") String countryCode, @Param("intlMobileNumber") String intlMobileNumber, @Param("applicantId") long applicantId, @Param("ritualId") long ritualId);


    @Modifying
    @Query("update JpaApplicantContact contact set contact.countryCode = :countryCode, contact.email = :email, " +
            "contact.localMobileNumber =:localMobileNumber where contact.applicant.id =:applicantId and contact.applicantRitual.id=:ritualId")
    void updateContactLocalNumber(@Param("email") String email, @Param("countryCode") String countryCode, @Param("localMobileNumber") String localMobileNumber, @Param("applicantId") long applicantId, @Param("ritualId") long ritualId);

}
