package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantPackageVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for applicant package data.
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public interface ApplicantPackageRepository extends JpaRepository<JpaApplicantPackage, Long> {

    Optional<JpaApplicantPackage> findByApplicantUinAndRitualPackageReferenceNumber(Long uin, String referenceNumber);

    @Query("select New com.elm.shj.admin.portal.orm.entity.ApplicantPackageVo(a.id, a.applicantUin, a.startDate, a.endDate," +
            " a.ritualPackage.companyRitualSeason.ritualSeason.ritualTypeCode," +
            "a.ritualPackage.companyRitualSeason.ritualSeason.seasonYear, a.ritualPackage.companyRitualSeason.id)" +
            " from JpaApplicantPackage a where a.applicantUin = :applicantUin")
    List<ApplicantPackageVo> findApplicantPackageAndRitualSeasonByUin(@Param("applicantUin") long applicantUin);

}
