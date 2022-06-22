/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitualBasic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository for applicant ritual table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public interface ApplicantRitualBasicRepository extends JpaRepository<JpaApplicantRitualBasic, Long> {

    @Query("select ar from JpaApplicantRitualBasic ar left join JpaApplicantCardBasic ac on ar.id = ac.applicantRitual.id " +
            "left join JpaApplicantDigitalId adi on ar.applicant.id = adi.applicantId where ac.id is null")
    Page<JpaApplicantRitualBasic> findWithExistingDigitalIdAndWithoutCard(Pageable pageable);
}
