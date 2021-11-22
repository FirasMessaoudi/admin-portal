package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaRitualPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for Ritual Package
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface RitualPackageRepository extends JpaRepository<JpaRitualPackage, Long> {


    Optional<JpaRitualPackage> findByReferenceNumber(String referenceNumber);

    Optional<JpaRitualPackage> findByApplicantPackagesApplicantUinAndCompanyRitualSeasonId(long uin,long companyRitualSeasonId);

   /* @Query("SELECT ritualPackage FROM JpaRitualPackage ritualPackage " +
            "join ritualPackage.companyRitualSeason companyRitualSeason " +
            "JOIN ritualPackage.applicantPackages  applicantPackages " +
            "JOIN ritualPackage.packageHousings packageHousings " +
            "JOIN packageHousings.packageCatering packageCatering " +
            "LEFT JOIN packageCatering.applicantPackageCaterings applicantPackageCaterings " +
            "ON applicantPackageCaterings.packageCatering.id = packageCatering.id " +
            "AND applicantPackageCaterings.applicantPackage.id = applicantPackages.id " +
            "WHERE companyRitualSeason.id = :companyRitualSeasonId " +
            "AND applicantPackages.applicantUin = :uin")
    Optional<JpaRitualPackage> findByApplicantPackagesApplicantUinAndCompanyRitualSeasonIdNew(@Param("uin") long uin,@Param("companyRitualSeasonId") long companyRitualSeasonId);*/
}
