/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantIncidentLite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repository for applicant incident Lite Entity.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface ApplicantIncidentLiteRepository extends JpaRepository<JpaApplicantIncidentLite, Long>, JpaSpecificationExecutor<JpaApplicantIncidentLite> {

    JpaApplicantIncidentLite findByCrmTicketNumberOrReferenceNumber(String crmTicketNumber, String referenceNumber);
    List<JpaApplicantIncidentLite> findByApplicantRitualId(long applicantRitualId);

    @Query("select substring(j.referenceNumber,5, 8) from JpaApplicantIncidentLite j where j.referenceNumber like :referenceNum% order by substring(j.referenceNumber, 5, 8) desc")
    List<String> fetchReferenceNumByReferenceNumLike(@Param("referenceNum") String referenceNum);

    @Transactional
    @Modifying
    @Query("update JpaApplicantIncidentLite i set i.crmTicketNumber = :crmTicketNumber, i.crmStatusUpdated = false, i.updateDate = current_timestamp where i.id =:incidentId")
    void updateCRMTicketNumber(@Param("incidentId") long incidentId, @Param("crmTicketNumber") String crmTicketNumber);

    @Transactional
    @Modifying
    @Query("update JpaApplicantIncidentLite i set i.crmStatusUpdated = true, i.updateDate = current_timestamp where i.id =:incidentId")
    void updateCRMUpdateStatus(@Param("incidentId") long incidentId);

    @Query("SELECT new com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo(c.id,c.referenceNumber,c.creationDate,c.statusCode, c.typeCode,c.city, c.description, c.locationLat, c.locationLng,c.campNumber, att.id, att.filePath, c.resolutionComment, c.updateDate,a.fullNameAr,a.fullNameEn, di.uin, a.preferredLanguage) " +
            "FROM JpaApplicantIncidentLite c JOIN JpaApplicantRitualBasic ar ON c.applicantRitualId = ar.id JOIN JpaApplicant a ON a.id = ar.applicant.id JOIN JpaApplicantDigitalId di ON di.applicantId = a.id LEFT JOIN JpaComplaintAttachment att ON att.applicantComplaint.id = c.id " +
            "WHERE c.id = :id")
    ApplicantComplaintVo findOneLite(@Param("id") Long id);

}
