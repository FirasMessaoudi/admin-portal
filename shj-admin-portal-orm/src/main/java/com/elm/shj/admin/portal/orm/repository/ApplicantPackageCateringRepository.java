/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageCatering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for applicant package catering
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
public interface ApplicantPackageCateringRepository extends JpaRepository<JpaApplicantPackageCatering, Long> {

    List<JpaApplicantPackageCatering> findAllByApplicantPackageApplicantUinAndApplicantPackageRitualPackageCompanySeasonPackagesCompanyRitualSeasonId(long applicantUin, long companyRitualSeasonId);

}
