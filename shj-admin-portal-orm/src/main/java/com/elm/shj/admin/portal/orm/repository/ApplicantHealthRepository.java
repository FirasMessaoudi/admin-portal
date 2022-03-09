/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for applicant health table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
public interface ApplicantHealthRepository extends JpaRepository<JpaApplicantHealth, Long> {

    @Query(value = "SELECT ah FROM JpaApplicantHealth ah JOIN ah.applicant a JOIN a.digitalIds adi " +
            "WHERE adi.uin = :uin AND ah.applicantRitual.id = :ritualId")
    JpaApplicantHealth findByUinAndRitualId(@Param("uin") String uin, @Param("ritualId") Long ritualId);

    JpaApplicantHealth findByApplicantIdAndPackageReferenceNumber(long applicantId, String referenceNumber);

    @Query("SELECT ah.id FROM JpaApplicantHealth ah WHERE ah.applicant.id = :applicantId AND ah.packageReferenceNumber = :packageReferenceNumber")
    Long findIdByApplicantIdAndPackageReferenceNumber(@Param("applicantId") long applicantId, @Param("packageReferenceNumber") String packageReferenceNumber);

    @Modifying
    @Query("UPDATE JpaApplicantHealth ah SET ah.applicantRitual.id = :applicantRitualId, " +
            "ah.updateDate = CURRENT_TIMESTAMP WHERE ah.applicant.id = :applicantId AND ah.packageReferenceNumber = :packageReferenceNumber")
    int updateApplicantHealthApplicantRitual(@Param("applicantRitualId") long applicantRitualId, @Param("applicantId") long applicantId,
                                             @Param("packageReferenceNumber") String packageReferenceNumber);
}
