/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

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


}
