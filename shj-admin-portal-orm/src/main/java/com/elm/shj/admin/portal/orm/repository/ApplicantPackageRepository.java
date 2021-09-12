package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for applicant package data.
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public interface ApplicantPackageRepository extends JpaRepository<JpaApplicantPackage, Long> {
}
