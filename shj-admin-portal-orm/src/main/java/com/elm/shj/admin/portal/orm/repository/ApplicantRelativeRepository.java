/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRelative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
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
    @Query("UPDATE JpaApplicantRelative ar SET ar.applicantRitual.id = :applicantRitualId, " +
            "ar.updateDate = CURRENT_TIMESTAMP WHERE ar.applicant.id = :applicantId AND ar.packageReferenceNumber = :packageReferenceNumber")
    int updateApplicantRelativeApplicantRitual(@Param("applicantRitualId") long applicantRitualId, @Param("applicantId") long applicantId,
                                               @Param("packageReferenceNumber") String packageReferenceNumber);

    @Query(value = "SELECT ar FROM JpaApplicantRelative ar JOIN ar.applicant.digitalIds adi WHERE adi.uin = :applicantUin AND ar.packageReferenceNumber=:packageReferenceNumber")
    List<JpaApplicantRelative> findByApplicantUinAndPackageReferenceNumber(@Param("applicantUin") String applicantUin, @Param("packageReferenceNumber") String packageReferenceNumber);
}
