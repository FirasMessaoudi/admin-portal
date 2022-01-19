/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantChatContactVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantChatContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Applicant Chat Contact Table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface ApplicantChatContactRepository extends JpaRepository<JpaApplicantChatContact, Long>, JpaSpecificationExecutor<JpaApplicantChatContact> {

    @Modifying
    @Query("UPDATE JpaApplicantChatContact contact SET contact.deleted = TRUE WHERE contact.applicantUin =:applicantUin AND" +
            " contact.contactUin=:contactUin AND contact.systemDefined = FALSE")
    int markDeleted(@Param("applicantUin") String applicantUin, @Param("contactUin") String contactUin);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantChatContactVo(j.id, j.applicantUin, j.contactUin, a.fullNameAr, a.fullNameEn, " +
            "j.type.id, j.alias, j.avatar, j.systemDefined, j.staffTitleCode, j.relationshipCode, j.mobileNumber, j.countryPhonePrefix, j.countryCode, " +
            "j.autoAdded, j.applicantRitualId, j.creationDate, j.updateDate) FROM JpaApplicantChatContact j " +
            "JOIN JpaApplicantDigitalId adi ON j.contactUin = adi.uin JOIN JpaApplicant a ON a.id = adi.applicantId WHERE j.applicantUin = :applicantUin " +
            "AND (j.systemDefined = FALSE OR (j.systemDefined = TRUE AND j.applicantRitualId = :ritualId)) AND j.deleted = FALSE")
    List<ApplicantChatContactVo> findContactApplicantList(@Param("applicantUin") String applicantUin, @Param("ritualId") Long ritualId);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantChatContactVo(j.id, j.applicantUin, j.contactUin, cs.fullNameAr, cs.fullNameEn, " +
            "j.type.id, j.alias, j.avatar, j.systemDefined, j.staffTitleCode, j.relationshipCode, j.mobileNumber, j.countryPhonePrefix, j.countryCode, " +
            "j.autoAdded, j.applicantRitualId, j.creationDate, j.updateDate) FROM JpaApplicantChatContact j " +
            "JOIN JpaCompanyStaffDigitalId csdi ON j.contactUin = csdi.suin JOIN csdi.companyStaff cs WHERE j.applicantUin = :applicantUin " +
            "AND (j.systemDefined = FALSE OR (j.systemDefined = TRUE AND j.applicantRitualId = :ritualId)) AND j.deleted = FALSE")
    List<ApplicantChatContactVo> findContactStaffList(@Param("applicantUin") String applicantUin, @Param("ritualId") Long ritualId);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantChatContactVo(j.id, j.applicantUin, j.contactUin, a.fullNameAr, a.fullNameEn, " +
            "j.type.id, j.alias, j.avatar, j.systemDefined, j.staffTitleCode, j.relationshipCode, j.mobileNumber, j.countryPhonePrefix, j.countryCode, " +
            "j.autoAdded, j.applicantRitualId, j.creationDate, j.updateDate) FROM JpaApplicantChatContact j " +
            "JOIN JpaApplicantDigitalId adi ON j.applicantUin = adi.uin JOIN JpaApplicant a ON a.id = adi.applicantId WHERE j.applicantUin = :applicantUin " +
            "AND j.systemDefined = TRUE AND j.applicantRitualId = :ritualId AND j.deleted = FALSE")
    List<ApplicantChatContactVo> findBySystemDefinedTrue(@Param("applicantUin") String applicantUin, @Param("ritualId") Long ritualId);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantChatContactVo(j.id, j.applicantUin, j.contactUin, a.fullNameAr, a.fullNameEn, " +
            "j.type.id, j.alias, j.avatar, j.systemDefined, j.staffTitleCode, j.relationshipCode, j.mobileNumber, j.countryPhonePrefix, j.countryCode, " +
            "j.autoAdded, j.applicantRitualId, j.creationDate, j.updateDate) FROM JpaApplicantChatContact j " +
            "JOIN JpaApplicantDigitalId adi ON j.applicantUin = adi.uin JOIN JpaApplicant a ON a.id = adi.applicantId WHERE j.applicantUin = :applicantUin " +
            "AND j.systemDefined = FALSE AND j.deleted = FALSE")
    List<ApplicantChatContactVo> findBySystemDefinedFalse(@Param("applicantUin") String applicantUin);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantChatContactVo(j.id, j.applicantUin, j.contactUin, a.fullNameAr, a.fullNameEn, " +
            "j.type.id, j.alias, j.avatar, j.systemDefined, j.staffTitleCode, j.relationshipCode, j.mobileNumber, j.countryPhonePrefix, j.countryCode, " +
            "j.autoAdded, j.applicantRitualId, j.creationDate, j.updateDate) FROM JpaApplicantChatContact j " +
            "JOIN JpaApplicantDigitalId adi ON j.applicantUin = adi.uin JOIN JpaApplicant a ON a.id = adi.applicantId WHERE j.id = :id")
    Optional<ApplicantChatContactVo> findApplicantContactVoById(@Param("id") Long id);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantChatContactVo(j.id, j.applicantUin, j.contactUin, cs.fullNameAr, cs.fullNameEn, " +
            "j.type.id, j.alias, j.avatar, j.systemDefined, j.staffTitleCode, j.relationshipCode, j.mobileNumber, j.countryPhonePrefix, j.countryCode, " +
            "j.autoAdded, j.applicantRitualId, j.creationDate, j.updateDate) FROM JpaApplicantChatContact j " +
            "JOIN JpaCompanyStaffDigitalId csdi ON j.contactUin = csdi.suin JOIN csdi.companyStaff cs WHERE cs.id = :id")
    Optional<ApplicantChatContactVo> findStaffContactVoById(@Param("id") Long id);

    Optional<JpaApplicantChatContact> findByApplicantUinAndContactUin(String applicantUin, String contactUin);

    Optional<JpaApplicantChatContact> findByApplicantUinAndContactUinAndDeletedFalse(String applicantUin, String contactUin);


}
