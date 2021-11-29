/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantIncident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for applicant incident table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface ApplicantIncidentRepository extends JpaRepository<JpaApplicantIncident, Long>, JpaSpecificationExecutor<JpaApplicantIncident> {
    List<JpaApplicantIncident> findByApplicantRitualId(long applicantRitualId);

    @Modifying
    @Query("update JpaApplicantIncident incident set incident.statusCode = :status, " +
            "incident.resolutionComment = :resolutionComment, incident.updateDate = current_timestamp where incident.id =:incidentId")
    void update(@Param("incidentId") long incidentId, @Param("resolutionComment") String resolutionComment,@Param("status") String status);
}
