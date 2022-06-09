package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicantGroupRepository extends JpaRepository<JpaApplicantGroup, Long> {

    Optional<JpaApplicantGroup> findByReferenceNumber(String referenceNumber);

    Optional<JpaApplicantGroup> getApplicantGroupByReferenceNumberAndCompanyRitualSeasonId(String referenceNumber, long companyRitualSeasonId);

    List<JpaApplicantGroup> findByCompanyRitualSeasonCompanyCode(String companyCode);
}
