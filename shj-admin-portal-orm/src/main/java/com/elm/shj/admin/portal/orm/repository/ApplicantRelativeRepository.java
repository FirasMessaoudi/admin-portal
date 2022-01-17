/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRelative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository for applicant relative table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public interface ApplicantRelativeRepository extends JpaRepository<JpaApplicantRelative, Long> {

    Optional<JpaApplicantRelative> findByApplicantIdAndRelativeApplicantId(long applicantId, long relativeApplicantId);

    @Modifying
    @Query("UPDATE JpaApplicantRelative ar SET ar.applicantRitual.id = :applicantRitualId " +
            "WHERE ar.applicant.id = :applicantId AND ar.packageReferenceNumber = :packageReferenceNumber")
    int updateApplicantRelativeApplicantRitual(@Param("applicantRitualId") long applicantRitualId, @Param("applicantId") long applicantId,
                                               @Param("packageReferenceNumber") String packageReferenceNumber);
}
