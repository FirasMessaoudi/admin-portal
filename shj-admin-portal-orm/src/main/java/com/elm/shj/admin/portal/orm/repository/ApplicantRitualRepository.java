/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantBasicInfoVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for applicant ritual table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public interface ApplicantRitualRepository extends JpaRepository<JpaApplicantRitual, Long> {

    @Query("select ar from JpaApplicantRitual ar join ar.applicant a join a.digitalIds di where di.uin=:uin and ar.id=:rid and a.deleted = false")
    JpaApplicantRitual findCardDetailsByUinAndRitualId(@Param("uin") String uin, @Param("rid") long rid);

    Optional<JpaApplicantRitual> findByApplicantDigitalIdsUinAndApplicantPackageId(String uin, Long applicantPackageId);

    @Query("SELECT a.id FROM JpaApplicantRitual a JOIN a.applicant.digitalIds di WHERE di.uin = :uin AND a.applicantPackage.id = :applicantPackageId and a.applicant.deleted = false")
    Long findIdByApplicantDigitalIdsUinAndApplicantPackageId(@Param("uin") String uin, @Param("applicantPackageId") Long applicantPackageId);

    List<JpaApplicantRitual> findAllByApplicantId(Long id);

    JpaApplicantRitual findFirstByApplicantDigitalIdsUinOrderByCreationDateDesc(String uin);

    @Modifying
    @Query("UPDATE JpaApplicantRitual ar SET ar.applicantPackage.id = :applicantPackageId, ar.updateDate = CURRENT_TIMESTAMP WHERE ar.id = :applicantRitualId")
    void updateApplicantRitualApplicantPackage(@Param("applicantPackageId") long applicantPackageId, @Param("applicantRitualId") long applicantRitualId);

    @Query("SELECT ar.id FROM JpaApplicantRitual ar WHERE ar.applicant.id = :applicantId AND ar.packageReferenceNumber = :packageReferenceNumber")
    Long findIdByApplicantIdAndPackageReferenceNumber(@Param("applicantId") long applicantId, @Param("packageReferenceNumber") String packageReferenceNumber);

    @Modifying
    @Query("UPDATE JpaApplicantRitual ar SET ar.dataRequestRecordId = :dataRequestRecordId, ar.updateDate = CURRENT_TIMESTAMP WHERE ar.id = :applicantRitualId")
    void updateDataRequestRecordId(@Param("dataRequestRecordId") long dataRequestRecordId, @Param("applicantRitualId") long applicantRitualId);

    @Query("select new com.elm.shj.admin.portal.orm.entity.ApplicantBasicInfoVo(digitalId.uin, applicant.fullNameAr, applicant.fullNameEn,card.referenceNumber) from JpaApplicantCard card  " +
            " join card.applicantRitual applicantRitual " +
            " join applicantRitual.applicant applicant " +
            " join JpaApplicantDigitalId digitalId  on digitalId.applicantId = applicant.id " +
            "where card.statusCode not in :cardStatusCodeList " +
            "and digitalId.uin in :digitalIdList and applicant.deleted = false ")
    List<ApplicantBasicInfoVo> findAllByApplicantDigitalIds(@Param("digitalIdList") List<String> digitalIdList,@Param("cardStatusCodeList") List<String> cardStatusCodeList);

    @Query("SELECT ar.packageReferenceNumber FROM JpaApplicantRitual ar WHERE ar.applicant.id = :applicantId and ar.applicant.deleted = false ORDER BY ar.creationDate desc")
    List<String> findPackageReferenceNumberByApplicantId(@Param("applicantId") Long applicantId);
}
