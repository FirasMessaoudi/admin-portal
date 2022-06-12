package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplicantGroupRepository extends JpaRepository<JpaApplicantGroup, Long> {

    Optional<JpaApplicantGroup> findByReferenceNumber(String referenceNumber);

    Optional<JpaApplicantGroup> getApplicantGroupByReferenceNumberAndCompanyRitualSeasonId(String referenceNumber, long companyRitualSeasonId);

    List<JpaApplicantGroup> findByCompanyRitualSeasonCompanyCode(String companyCode);

    @Query("SELECT ag.referenceNumber FROM JpaApplicantGroup ag JOIN ag.groupApplicantLists ga WHERE ga.applicantUin =  :uin ")
    String findReferenceNumberByUin(@Param("uin") String uin);
}
