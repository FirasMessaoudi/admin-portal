/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantRitualVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitualBasic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for applicant ritual table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public interface ApplicantRitualBasicRepository extends JpaRepository<JpaApplicantRitualBasic, Long> {

    @Query("select ar from JpaApplicantRitualBasic ar left join JpaApplicantCardBasic ac on ar.id = ac.applicantRitual.id " +
            "inner join JpaApplicantDigitalId adi on ar.applicant.id = adi.applicantId where ac.id is null and ar.applicant.deleted = false")
    Page<JpaApplicantRitualBasic> findWithExistingDigitalIdAndWithoutCard(Pageable pageable);

    @Query("SELECT new com.elm.shj.admin.portal.orm.entity.ApplicantRitualVo(a.fullNameAr,a.fullNameEn,a.fullNameOrigin, a.idNumber, a.passportNumber,a.dateOfBirthHijri, a.dateOfBirthGregorian,a.gender,a.nationalityCode,ac.email,ac.localMobileNumber,ac.intlMobileNumber,ac.countryCode, di.uin) " +
            "FROM JpaApplicantRitualBasic ar JOIN ar.applicant a JOIN  a.digitalIds di JOIN a.contacts ac " +
            "WHERE ar.id = :id and ar.applicant.deleted = false")
    ApplicantRitualVo findByIdForCrm(@Param("id") long id);
}
