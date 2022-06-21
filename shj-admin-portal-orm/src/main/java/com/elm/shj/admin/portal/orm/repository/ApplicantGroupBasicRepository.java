package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantGroupBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ApplicantGroupBasicRepository extends JpaRepository<JpaApplicantGroupBasic, Long> {

    @Query("SELECT ag.id FROM JpaApplicantGroupBasic ag WHERE ag.referenceNumber = :referenceNumber and ag.companyRitualSeasonId = :companyRitualSeasonId")
    Optional<Long> findIdByReferenceNumberAndCompanyRitualSeasonId(@Param("referenceNumber") String referenceNumber, @Param("companyRitualSeasonId") long companyRitualSeasonId);
}
