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

    @Query("select ar from JpaApplicantRitual ar where ar.id not in (select ac.applicantRitual.id from JpaApplicantCard ac) " +
            "and ar.applicant.id in (select adi.applicantId from JpaApplicantDigitalId adi)")
    List<JpaApplicantRitual> findWithExistingDigitalIdAndWithoutCard();

    @Query("select ar from JpaApplicantRitual ar join ar.applicant a join a.digitalIds di where di.uin=:uin and ar.id=:rid")
    JpaApplicantRitual findCardDetailsByUinAndRitualId(@Param("uin") String uin, @Param("rid") long rid);

    Optional<JpaApplicantRitual> findByApplicantDigitalIdsUinAndApplicantPackageId(String uin, Long applicantPackageId);

    List<JpaApplicantRitual> findAllByApplicantId(Long id);

    JpaApplicantRitual findFirstByApplicantDigitalIdsUinOrderByCreationDateDesc(String uin);

    JpaApplicantRitual findByApplicantIdAndPackageReferenceNumber(long applicantId, String referenceNumber);

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
            "where card.statusCode = :cardStatusCode " +
            "and digitalId.uin in :digitalIdList ")
    List<ApplicantBasicInfoVo> findAllByApplicantDigitalIds(@Param("digitalIdList") List<String> digitalIdList,@Param("cardStatusCode") String cardStatusCode);
}
