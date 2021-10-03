package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for applicant package data.
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public interface ApplicantPackageRepository extends JpaRepository<JpaApplicantPackage, Long> {

    Optional<JpaApplicantPackage> findByApplicantUinAndRitualPackageReferenceNumber(Long uin, String referenceNumber);

}
