/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ChatContactVo;
import com.elm.shj.admin.portal.orm.entity.JpaChatContact;
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
public interface ChatContactRepository extends JpaRepository<JpaChatContact, Long>, JpaSpecificationExecutor<JpaChatContact> {

    @Modifying
    @Query("UPDATE JpaChatContact contact SET contact.deleted = TRUE WHERE contact.digitalId =:digitalId AND" +
            " contact.contactDigitalId=:contactDigitalId AND contact.systemDefined = FALSE")
    int markDeleted(@Param("digitalId") String digitalId, @Param("contactDigitalId") String contactDigitalId);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ChatContactVo(j.id, j.digitalId, j.contactDigitalId, a.fullNameAr, a.fullNameEn, " +
            "j.type.id, j.alias, j.avatar, j.systemDefined, j.staffTitleCode, j.relationshipCode, j.mobileNumber, j.countryPhonePrefix, j.countryCode, " +
            "j.autoAdded, j.applicantRitualId, j.creationDate, j.updateDate, adi.statusCode, j.deleted ) FROM JpaChatContact j " +
            "JOIN JpaApplicantDigitalId adi ON j.contactDigitalId = adi.uin JOIN JpaApplicant a ON a.id = adi.applicantId WHERE j.digitalId = :digitalId " +
            "AND (j.systemDefined = FALSE OR (j.systemDefined = TRUE AND j.applicantRitualId = :ritualId) ) and j.deleted = FALSE")
    List<ChatContactVo> findContactApplicantList(@Param("digitalId") String digitalId, @Param("ritualId") Long ritualId);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ChatContactVo(j.id, j.digitalId, j.contactDigitalId, cs.fullNameAr, cs.fullNameEn, " +
            "j.type.id, j.alias, j.avatar, j.systemDefined, j.staffTitleCode, j.relationshipCode, j.mobileNumber, j.countryPhonePrefix, j.countryCode, " +
            "j.autoAdded, j.applicantRitualId, j.creationDate, j.updateDate, csdi.statusCode, j.deleted ) FROM JpaChatContact j " +
            "JOIN JpaCompanyStaffDigitalId csdi ON j.contactDigitalId = csdi.suin JOIN csdi.companyStaff cs WHERE j.digitalId = :digitalId " +
            "AND (j.systemDefined = FALSE OR (j.systemDefined = TRUE AND j.applicantRitualId = :ritualId))  and j.deleted = FALSE  ")
    List<ChatContactVo> findContactStaffList(@Param("digitalId") String digitalId, @Param("ritualId") Long ritualId);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ChatContactVo(j.id, j.digitalId, j.contactDigitalId, a.fullNameAr, a.fullNameEn, " +
            "j.type.id, j.alias, j.avatar, j.systemDefined, j.staffTitleCode, j.relationshipCode, j.mobileNumber, j.countryPhonePrefix, j.countryCode, " +
            "j.autoAdded, j.applicantRitualId, j.creationDate, j.updateDate, adi.statusCode, j.deleted ) FROM JpaChatContact j " +
            "JOIN JpaApplicantDigitalId adi ON j.digitalId = adi.uin JOIN JpaApplicant a ON a.id = adi.applicantId WHERE j.digitalId = :digitalId " +
            "AND j.systemDefined = TRUE AND j.applicantRitualId = :ritualId ")
    List<ChatContactVo> findBySystemDefinedTrue(@Param("digitalId") String digitalId, @Param("ritualId") Long ritualId);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ChatContactVo(j.id, j.digitalId, j.contactDigitalId, a.fullNameAr, a.fullNameEn, " +
            "j.type.id, j.alias, j.avatar, j.systemDefined, j.staffTitleCode, j.relationshipCode, j.mobileNumber, j.countryPhonePrefix, j.countryCode, " +
            "j.autoAdded, j.applicantRitualId, j.creationDate, j.updateDate, adi.statusCode, j.deleted ) FROM JpaChatContact j " +
            "JOIN JpaApplicantDigitalId adi ON j.digitalId = adi.uin JOIN JpaApplicant a ON a.id = adi.applicantId WHERE j.digitalId = :digitalId " +
            "AND j.systemDefined = FALSE and j.deleted = FALSE ")
    List<ChatContactVo> findBySystemDefinedFalse(@Param("digitalId") String digitalId);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ChatContactVo(j.id, j.digitalId, j.contactDigitalId, a.fullNameAr, a.fullNameEn, " +
            "j.type.id, j.alias, j.avatar, j.systemDefined, j.staffTitleCode, j.relationshipCode, j.mobileNumber, j.countryPhonePrefix, j.countryCode, " +
            "j.autoAdded, j.applicantRitualId, j.creationDate, j.updateDate, adi.statusCode, j.deleted) FROM JpaChatContact j " +
            "JOIN JpaApplicantDigitalId adi ON j.contactDigitalId = adi.uin JOIN JpaApplicant a ON a.id = adi.applicantId WHERE j.id = :id")
    Optional<ChatContactVo> findApplicantContactVoById(@Param("id") Long id);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ChatContactVo(j.id, j.digitalId, j.contactDigitalId, cs.fullNameAr, cs.fullNameEn, " +
            "j.type.id, j.alias, j.avatar, j.systemDefined, j.staffTitleCode, j.relationshipCode, j.mobileNumber, j.countryPhonePrefix, j.countryCode, " +
            "j.autoAdded, j.applicantRitualId, j.creationDate, j.updateDate, csdi.statusCode, j.deleted) FROM JpaChatContact j " +
            "JOIN JpaCompanyStaffDigitalId csdi ON j.contactDigitalId = csdi.suin JOIN csdi.companyStaff cs WHERE cs.id = :id ")
    Optional<ChatContactVo> findStaffContactVoById(@Param("id") Long id);

    Optional<JpaChatContact> findByDigitalIdAndContactDigitalId(String digitalId, String contactDigitalId);

    Optional<JpaChatContact> findByDigitalIdAndContactDigitalIdAndDeletedFalse(String digitalId, String contactDigitalId);

    @Modifying
    @Query("UPDATE JpaChatContact contact SET contact.deleted = TRUE WHERE" +
            " contact.contactDigitalId=:StaffUin")
    int markStaffDeleted(@Param("StaffUin") String suin);


}
