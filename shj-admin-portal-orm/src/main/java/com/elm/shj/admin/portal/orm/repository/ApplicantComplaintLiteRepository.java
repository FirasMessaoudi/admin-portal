/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantComplaintLite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * Repository for applicant complaint Lite Entity.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface ApplicantComplaintLiteRepository extends JpaRepository<JpaApplicantComplaintLite, Long>, JpaSpecificationExecutor<JpaApplicantComplaintLite> {
    JpaApplicantComplaintLite findByCrmTicketNumber(String crmTicketNumber);

}
