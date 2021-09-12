/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageHousing;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for applicant package housing
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
public interface ApplicantPackageHousingRepository extends JpaRepository<JpaApplicantPackageHousing, Long> {
}
