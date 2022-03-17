package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantMainData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for applicant main data.
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public interface ApplicantMainDataRepository extends JpaRepository<JpaApplicantMainData, Long> {

    JpaApplicantMainData findByDigitalIdsUin(String uin);
}
