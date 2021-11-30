/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantIncident;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantIncidentLite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for applicant incident Lite Entity.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface ApplicantIncidentLiteRepository extends JpaRepository<JpaApplicantIncidentLite, Long>, JpaSpecificationExecutor<JpaApplicantIncidentLite> {
    List<JpaApplicantIncidentLite> findByApplicantRitualId(long applicantRitualId);

}
