package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantGroupDetailsVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplicantGroupRepository extends JpaRepository<JpaApplicantGroup, Long> {

    Optional<JpaApplicantGroup> findByReferenceNumber(String referenceNumber);

    Optional<JpaApplicantGroup> getApplicantGroupByReferenceNumberAndCompanyRitualSeasonId(String referenceNumber, long companyRitualSeasonId);

    Page<JpaApplicantGroup> findByCompanyRitualSeasonCompanyCode(String companyCode, Pageable pageable);

    List<JpaApplicantGroup> findByCompanyRitualSeasonCompanyCode(String companyCode);

    @Query("SELECT ag.referenceNumber FROM JpaApplicantGroup ag JOIN ag.groupApplicantLists ga WHERE ga.applicantUin =  :uin ")
    String findReferenceNumberByUin(@Param("uin") String uin);

    @Query("SELECT new com.elm.shj.admin.portal.orm.entity.ApplicantGroupDetailsVo(ag.id,ag.localOfficeId,ag.groupName,gl.fullNameAr,gl.fullNameEn,gl.idNumber,gl.passportNumber,trans.vehicleInfo,housing.campInfo) " +
            "FROM JpaApplicantGroup ag " +
            "join ag.groupLeader gl " +
            "left join ag.groupApplicantLists al " +
            "left join JpaApplicantPackage package on CAST(package.applicantUin as text) = al.applicantUin " +
            "left join package.applicantPackageHousings housing " +
            "left join package.applicantPackageTransportations trans " +
            "where ag.referenceNumber = :referenceNumber ")
    List<ApplicantGroupDetailsVo> findGroupDetailsByGroupId(@Param("referenceNumber") String referenceNumber);
}
