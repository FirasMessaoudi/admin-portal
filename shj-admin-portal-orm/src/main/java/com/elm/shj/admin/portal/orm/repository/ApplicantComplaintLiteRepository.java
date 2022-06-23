/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantComplaintLite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Repository for applicant complaint Lite Entity.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface ApplicantComplaintLiteRepository extends JpaRepository<JpaApplicantComplaintLite, Long>, JpaSpecificationExecutor<JpaApplicantComplaintLite> {
    JpaApplicantComplaintLite findByCrmTicketNumber(String crmTicketNumber);

    @Query("select substring(j.referenceNumber,5, 8) from JpaApplicantComplaintLite j where j.referenceNumber like :referenceNum% order by substring(j.referenceNumber, 5, 8) desc")
    List<String> fetchReferenceNumByReferenceNumLike(@Param("referenceNum") String referenceNum);

    @Query("SELECT new com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo(c.id,c.referenceNumber,c.creationDate,c.statusCode, c.typeCode,c.city, c.description, c.locationLat, c.locationLng, att.id, att.filePath, c.resolutionComment, c.updateDate,a.fullNameAr,a.fullNameEn, di.uin, a.preferredLanguage) " +
            "FROM JpaApplicantComplaintLite c JOIN JpaApplicantRitualBasic ar ON c.applicantRitualId = ar.id JOIN JpaApplicant a ON a.id = ar.applicant.id JOIN JpaApplicantDigitalId di ON di.applicantId = a.id LEFT JOIN JpaComplaintAttachment att ON att.applicantComplaint.id = c.id " +
            "WHERE c.id = :id")
    ApplicantComplaintVo findOneLite(@Param("id") Long id);





}
