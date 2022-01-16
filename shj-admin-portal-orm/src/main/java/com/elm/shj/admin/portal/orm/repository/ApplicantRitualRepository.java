/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for applicant ritual table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public interface ApplicantRitualRepository extends JpaRepository<JpaApplicantRitual, Long> {

    @Query("select ar from JpaApplicantRitual ar where ar.id not in (select ac.applicantRitual.id from JpaApplicantCard ac)")
    List<JpaApplicantRitual> findAllApplicantRitualsWithoutCard();


    @Query("select ar   from JpaApplicantRitual ar join ar.applicant a join a.digitalIds di where di.uin=:uin and ar.id=:rid")
    JpaApplicantRitual findCardDetailsByUinAndRitualId(@Param("uin") String uin, @Param("rid") long rid);

    Optional<JpaApplicantRitual> findByApplicantDigitalIdsUinAndApplicantPackageId(String uin, Long applicantPackageId);

    List<JpaApplicantRitual> findAllByApplicantId(Long id);

    JpaApplicantRitual findFirstByApplicantIdOrderByCreationDateDesc(long id);

    JpaApplicantRitual findByApplicantIdAndApplicantPackageRitualPackageReferenceNumber(long applicantId, String referenceNumber);

    JpaApplicantRitual findByApplicantIdAndPackageReferenceNumber(long applicantId, String referenceNumber);

}
