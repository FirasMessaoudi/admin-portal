/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantComplaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Repository for applicant complaint table.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
public interface ApplicantComplaintRepository extends JpaRepository<JpaApplicantComplaint, Long>, JpaSpecificationExecutor<JpaApplicantComplaint> {


    @Modifying
    @Query("update JpaApplicantComplaint complaint set complaint.statusCode = :status, " +
            "complaint.resolutionComment = :resolutionComment, complaint.updateDate = current_timestamp where complaint.id =:complaintId")
    void update(@Param("complaintId") long complaintId, @Param("resolutionComment") String resolutionComment, @Param("status") String status);

    @Transactional
    @Modifying
    @Query("update JpaApplicantComplaint complaint set complaint.crmTicketNumber = :crmTicketNumber, complaint.updateDate = current_timestamp where complaint.id =:complaintId")
    void updateCRMTicketNumber(@Param("complaintId") long complaintId, @Param("crmTicketNumber") String crmTicketNumber);

    @Query("SELECT new com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo(c.id,c.referenceNumber,c.creationDate,c.statusCode, c.typeCode,a.fullNameAr,a.fullNameEn, di.uin, COUNT(c)) " +
            "FROM JpaApplicantComplaint c JOIN c.applicantRitual ar JOIN ar.applicant a JOIN ar.applicant.digitalIds di where " +
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
            "((:serviceGroupRefCode = -1L or a.serviceGroupMakkahCode = :serviceGroupRefCode or a.serviceGroupMadinaCode = :serviceGroupRefCode)) " +
            "GROUP BY c.id,c.referenceNumber,c.creationDate,c.statusCode,c.typeCode,a.fullNameAr,a.fullNameEn, di.uin")
    Page<ApplicantComplaintVo> findApplicantComplaintFilter(@Param("referenceNumber") String referenceNumber, @Param("typeCode") String typeCode,
                                                            @Param("statusCode") String statusCode, @Param("applicantName") String applicantName,
                                                            @Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                                            @Param("applicantId") String applicantId, @Param("companyCode") String companyCode,
                                                            @Param("establishmentRefCode") long establishmentRefCode, @Param("missionRefCode") long missionRefCode,
                                                            @Param("serviceGroupRefCode") long serviceGroupRefCode, Pageable pageable);

    @Query("SELECT new com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo(c.id,c.referenceNumber,c.typeCode, c.city, c.description,c.locationLat, c.locationLng,c.mobileNumber,c.creationDate, att.id,a.fullNameAr,a.fullNameEn,a.fullNameOrigin, a.idNumber, a.passportNumber,a.dateOfBirthHijri, a.dateOfBirthGregorian,a.gender,a.nationalityCode,ac.email,ac.localMobileNumber,ac.intlMobileNumber,ac.countryCode, di.uin) " +
            "FROM JpaApplicantComplaint c JOIN c.applicantRitual ar JOIN ar.applicant a JOIN  a.digitalIds di JOIN a.contacts ac LEFT JOIN c.complaintAttachment att " +
            "WHERE (:statusCode is null OR c.statusCode = :statusCode) and " +
            "c.creationDate <= :creationDate AND c.crmTicketNumber is null")
    List<ApplicantComplaintVo> findByCreationDateLessThanEqualAndStatusCode(@Param("creationDate") Date creationDate, @Param("statusCode") String statusCode);


    List<JpaApplicantComplaint> findByApplicantRitualId(long applicantRitualId);

}