/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageHousing;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageHousingBasic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for applicant package housing
 *
 * @author rameez imtiaz
 * @since 1.2.0
 */

public interface ApplicantPackageHousingBasicRepository extends JpaRepository<JpaApplicantPackageHousingBasic, Long> {

}
