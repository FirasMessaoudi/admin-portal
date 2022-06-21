/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantComplaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

/**
 * Repository for applicant complaint table.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
public interface ApplicantComplaintRepository extends JpaRepository<JpaApplicantComplaint, Long>, JpaSpecificationExecutor<JpaApplicantComplaint> {


    @Modifying
    @Query("update JpaApplicantComplaint incident set incident.statusCode = :status, " +
            "incident.resolutionComment = :resolutionComment, incident.updateDate = current_timestamp where incident.id =:incidentId")
    void update(@Param("incidentId") long incidentId, @Param("resolutionComment") String resolutionComment, @Param("status") int status);

    @Query("SELECT c FROM JpaApplicantComplaint c JOIN c.applicantRitual ar JOIN ar.applicant a JOIN ar.applicant.digitalIds di where " +
            "(:referenceNumber is null OR c.referenceNumber like '%'+:referenceNumber+'%') and " +
            "(:typeCode is null OR c.typeCode = :typeCode) and " +
            "(:statusCode is null OR c.statusCode = :statusCode) and " +
            "(:startDate is null OR c.creationDate >= :startDate) and " +
            "(:endDate is null OR c.creationDate <= :endDate) and " +
            "((:applicantName is null OR a.fullNameEn like '%'+:applicantName+'%' OR a.fullNameAr like '%'+:applicantName+'%')) and " +
            "(:applicantId is null OR di.uin like '%'+:applicantId+'%') and " +
            "(:companyCode is null or a.companyCode = :companyCode) and " +
            "(:establishmentRefCode = -1L or a.establishmentRefCode = :establishmentRefCode) and " +
            "(:missionRefCode = -1L or a.missionRefCode = :missionRefCode) and " +
            "((:serviceGroupRefCode = -1L or a.serviceGroupMakkahCode = :serviceGroupRefCode or a.serviceGroupMadinaCode = :serviceGroupRefCode)) ")
    Page<JpaApplicantComplaint> findApplicantComplaintFilter(@Param("referenceNumber") String referenceNumber, @Param("typeCode") Integer typeCode,
                                                             @Param("statusCode") Integer statusCode, @Param("applicantName") String applicantName,
                                                             @Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                                             @Param("applicantId") String applicantId, @Param("companyCode") String companyCode,
                                                             @Param("establishmentRefCode") long establishmentRefCode, @Param("missionRefCode") long missionRefCode,
                                                             @Param("serviceGroupRefCode") long serviceGroupRefCode, Pageable pageable);



}
