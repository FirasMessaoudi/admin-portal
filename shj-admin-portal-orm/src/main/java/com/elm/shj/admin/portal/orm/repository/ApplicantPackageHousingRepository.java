/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageHousing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for applicant package housing
 *
 * @author firas messaoudi
 * @since 1.1.0
 */

public interface ApplicantPackageHousingRepository extends JpaRepository<JpaApplicantPackageHousing, Long> {

    List<JpaApplicantPackageHousing> findAllByApplicantPackageApplicantUinAndApplicantPackageId(long applicantUin, long applicantPackageId);

    Optional<JpaApplicantPackageHousing> findTopByApplicantPackageApplicantUinAndApplicantPackageIdOrderByCreationDateDesc(long applicantUin, long applicantPackageId);

    Optional<JpaApplicantPackageHousing> findTopByApplicantPackageIdAndPackageHousingId(long applicantPackageId, long packageHousingId);
}
