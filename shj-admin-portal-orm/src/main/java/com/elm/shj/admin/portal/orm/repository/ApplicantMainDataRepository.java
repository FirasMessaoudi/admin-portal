package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantMainData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for applicant main data.
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public interface ApplicantMainDataRepository extends JpaRepository<JpaApplicantMainData, Long> {

    @Query(value = "SELECT a FROM JpaApplicantMainData a INNER JOIN JpaApplicantDigitalId adi ON adi.applicantId = a.id " +
            "INNER JOIN JpaApplicantContact ac ON ac.applicant.id = a.id WHERE adi.uin = :uin")
    JpaApplicantMainData findByUin(@Param("uin") String uin);
}
