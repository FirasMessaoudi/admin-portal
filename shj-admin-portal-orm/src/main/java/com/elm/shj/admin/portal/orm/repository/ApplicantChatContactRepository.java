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

    @Query("SELECT j FROM JpaApplicantChatContact j WHERE j.deleted = FALSE AND j.applicantUin = :applicantUin AND j.isAutomatically = FALSE " +
            "AND (j.systemDefined = FALSE OR (j.systemDefined = TRUE AND j.applicantRitual.id = :applicantRitualId))")
    List<JpaApplicantChatContact> findAllByUinAndRitualIdAndSystemDefined(@Param("applicantUin") String applicantUin,
                                                                          @Param("applicantRitualId") Long applicantRitualId);

    @Query("SELECT j FROM JpaApplicantChatContact j WHERE j.deleted = FALSE AND j.applicantUin = :applicantUin AND j.isAutomatically = FALSE " +
            "AND j.systemDefined = FALSE")
    List<JpaApplicantChatContact> findUserDefined(@Param("applicantUin") String applicantUin);

    @Query("SELECT j FROM JpaApplicantChatContact j WHERE j.deleted = FALSE AND j.applicantUin = :applicantUin AND j.isAutomatically = FALSE " +
            "AND j.systemDefined = TRUE AND j.applicantRitual.id = :applicantRitualId")
    List<JpaApplicantChatContact> findSystemDefined(@Param("applicantUin") String applicantUin,
                                                    @Param("applicantRitualId") Long applicantRitualId);

    @Modifying
    @Query("update JpaApplicantChatContact contact set contact.deleted = true where contact.applicantUin =:applicantUin and" +
            " contact.contactUin=:contactUin   and contact.systemDefined=false")
    int markDeleted(@Param("applicantUin") String applicantUin,@Param("contactUin") String contactUin  );

    @Modifying
    @Query("update JpaApplicantChatContact j set j.avatar = :avatar, j.updateDate = CURRENT_TIMESTAMP where j.contactUin =:contactUin")
    void updateAvatar(@Param("contactUin") String contactUin, @Param("avatar") String avatar);

 }
