/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantStaffVO;
import com.elm.shj.admin.portal.orm.entity.CompanyStaffFullVO;
import com.elm.shj.admin.portal.orm.entity.CompanyStaffVO;
import com.elm.shj.admin.portal.orm.entity.JpaApplicant;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Repository for Company Staff data.
 *
 * @author salzoubi
 * @since 1.1.0
 **/
public interface CompanyStaffRepository extends JpaRepository<JpaCompanyStaff, Long>, JpaSpecificationExecutor<JpaCompanyStaff> {

    List<JpaCompanyStaff> findByApplicantGroupsGroupApplicantListsApplicantUinAndApplicantGroupsCompanyRitualSeasonId(String applicantUin, long sid);

    @Query("SELECT CASE WHEN COUNT(a)> 0 THEN TRUE ELSE FALSE END " +
            "FROM JpaCompanyStaff a WHERE " +
            "(a.idNumber = :idNumber AND a.dateOfBirthHijri = :dateOfBirthHijri) OR " +
            "(a.passportNumber = :passportNumber AND a.dateOfBirthGregorian = :dateOfBirthGregorian)")
    boolean existsByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                              @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian);

    @Query("SELECT CASE WHEN COUNT(cs)> 0 THEN TRUE ELSE FALSE END " +
            "FROM JpaCompanyStaff cs WHERE " +
            "(cs.idNumber = :idNumber) OR " +
            "(cs.passportNumber = :passportNumber AND cs.nationalityCode = :nationalityCode)")
    boolean existsByBasicInfo(@Param("idNumber") String idNumber,
                              @Param("passportNumber") String passportNumber, @Param("nationalityCode") String nationalityCode);

    @Query("SELECT CASE WHEN COUNT(a)> 0 THEN TRUE ELSE FALSE END " +
            "FROM JpaCompanyStaff a WHERE " +
            "((a.idNumber = :idNumber AND a.dateOfBirthHijri = :dateOfBirthHijri) OR " +
            "(a.passportNumber = :passportNumber AND a.dateOfBirthGregorian = :dateOfBirthGregorian)) AND a.titleCode=:titleCode")
    boolean existsByBasicInfoAndTitleIsGroupLeader(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                                                   @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian, @Param("titleCode") String titleCode);

    @Query("select s from JpaCompanyStaff s where s.id not in (select sdi.companyStaff.id from JpaCompanyStaffDigitalId sdi where sdi.seasonYear =:season)")
    List<JpaCompanyStaff> findAllWithoutSuin(@Param("season") int season);

    @Query("SELECT a " +
            "FROM JpaCompanyStaff a WHERE " +
            "(a.idNumber = :idNumber AND a.dateOfBirthHijri = :dateOfBirthHijri) OR " +
            "(a.passportNumber = :passportNumber AND a.dateOfBirthGregorian = :dateOfBirthGregorian)")
    JpaCompanyStaff findByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                                    @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian);


    @Query("SELECT a " +
            "FROM JpaCompanyStaff a WHERE " +
            "((a.idNumber = :idNumber AND a.dateOfBirthHijri = :dateOfBirthHijri) OR " +
            "(a.passportNumber = :passportNumber AND a.dateOfBirthGregorian = :dateOfBirthGregorian)) AND a.titleCode=:titleCode")
    JpaCompanyStaff findGroupLeaderByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                                               @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian, @Param("titleCode") String titleCode);


    @Query("SELECT s FROM JpaCompanyStaff s JOIN s.digitalIds sdi WHERE sdi.suin = :suin AND sdi.statusCode=:statusCode")
    JpaCompanyStaff findBySuin(@Param("suin") String suin, @Param("statusCode") String statusCode);

    JpaCompanyStaff findByApplicantGroupsGroupApplicantListsApplicantUinAndApplicantGroupsCompanyRitualSeasonIdAndTitleCode(String applicantUin, long companyRitualSeason, String titleCode);

    @Modifying
    @Query("update JpaCompanyStaff staff set staff.countryCode = :countryCode, staff.email = :email, " +
            "staff.mobileNumberIntl =:intlMobileNumber, staff.registered = TRUE, staff.updateDate = CURRENT_TIMESTAMP where staff.id =:staffId")
    int updateCompanyStaffIntlNumber(@Param("email") String email, @Param("countryCode") String countryCode, @Param("intlMobileNumber") String intlMobileNumber, @Param("staffId") long staffId);


    @Modifying
    @Query("update JpaCompanyStaff staff set staff.countryCode = :countryCode, staff.email = :email, " +
            "staff.mobileNumber =:localMobileNumber, staff.registered = TRUE, staff.updateDate = CURRENT_TIMESTAMP where staff.id =:staffId")
    int updateCompanyStaffLocalNumber(@Param("email") String email, @Param("countryCode") String countryCode, @Param("localMobileNumber") String localMobileNumber, @Param("staffId") long staffId);

    JpaCompanyStaff findByIdAndRegisteredTrue(long id);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.CompanyStaffVO(" +
            " digitalId.suin, staff.fullNameEn,staff.fullNameAr, staff.titleCode,staff.photo, " +
            " cards.referenceNumber,cards.statusCode,ritualSeason.ritualTypeCode,ritualSeason.seasonYear, company.labelEn, company.labelAr,company.code,staff.idNumber,staff.passportNumber,staff.fullNameOrigin,staff.dateOfBirthGregorian,staff.dateOfBirthHijri,staff.gender,staff.nationalityCode,cards.referenceNumber,cards.id ) " +
            "from JpaCompanyStaff staff " +
            "join staff.digitalIds digitalId " +
            "join digitalId.companyStaffCards cards " +
            "join cards.companyRitualSeason companyRitualSeason " +
            "join companyRitualSeason.ritualSeason ritualSeason " +
            "join companyRitualSeason.company company " +
            "where digitalId.suin = :suin ")
    CompanyStaffVO findStaffMainData(@Param("suin") String suin);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantStaffVO(" +
            " digitalId.suin, staff.fullNameEn,staff.fullNameAr, ritualSeason.ritualTypeCode,card.statusCode ,staff.photo, " +
            " company.labelEn, company.labelAr ) " +
            "from JpaCompanyStaff staff " +
            "join staff.digitalIds digitalId " +
            "join digitalId.companyStaffCards card " +
            "join card.companyRitualSeason companyRitualSeason " +
            "join companyRitualSeason.ritualSeason ritualSeason " +
            "join companyRitualSeason.company company " +
            "where (staff.idNumber =:idNumber " +
            "or staff.idNumberOriginal =:idNumber " +
            "or staff.passportNumber =:idNumber )" +
            "AND digitalId.statusCode=:digitalIdStatus " +
            "AND card.statusCode <> :canceledCardStatus "+
            "AND card.statusCode <> :suspendedCardStatus "
    )
    ApplicantStaffVO findStaffByIdNumber(@Param("idNumber") String idNumber, @Param("digitalIdStatus") String digitalIdStatus, @Param("canceledCardStatus") String canceledCardStatus, @Param("suspendedCardStatus") String suspendedCardStatus);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantStaffVO(" +
            " digitalId.suin, staff.fullNameEn,staff.fullNameAr, ritualSeason.ritualTypeCode,card.statusCode ,staff.photo, " +
            "  company.labelEn, company.labelAr ) " +
            "from JpaCompanyStaff staff " +
            "join staff.digitalIds digitalId " +
            "join digitalId.companyStaffCards card " +
            "join card.companyRitualSeason companyRitualSeason " +
            "join companyRitualSeason.ritualSeason ritualSeason " +
            "join companyRitualSeason.company company " +
            "where digitalId.suin =:suin "+
            "AND digitalId.statusCode=:digitalIdStatus " +
            "AND card.statusCode <> :canceledCardStatus "+
            "AND card.statusCode <> :suspendedCardStatus ")
    ApplicantStaffVO findStaffBySuin(@Param("suin") String suin, @Param("digitalIdStatus") String digitalIdStatus, @Param("canceledCardStatus") String canceledCardStatus, @Param("suspendedCardStatus") String suspendedCardStatus);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantStaffVO(" +
            " digitalId.suin, staff.fullNameEn,staff.fullNameAr, ritualSeason.ritualTypeCode,card.statusCode ,staff.photo, " +
            "  company.labelEn, company.labelAr ) " +
            "from JpaCompanyStaff staff " +
            "join staff.digitalIds digitalId " +
            "join digitalId.companyStaffCards card " +
            "join card.companyRitualSeason companyRitualSeason " +
            "join companyRitualSeason.ritualSeason ritualSeason " +
            "join companyRitualSeason.company company " +
            "where digitalId.suin =:suin " +
            "AND card.id = :cardId ")
    ApplicantStaffVO findStaffBySuinAndCardId(@Param("suin") String suin, @Param("cardId") long cardId);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.CompanyStaffVO(" +
            " digitalId.suin, staff.fullNameEn,staff.fullNameAr, staff.titleCode,staff.photo, " +
            " cards.referenceNumber,cards.statusCode,ritualSeason.ritualTypeCode,ritualSeason.seasonYear, company.labelEn, company.labelAr,company.code,staff.idNumber,staff.passportNumber,staff.fullNameOrigin,staff.dateOfBirthGregorian,staff.dateOfBirthHijri,staff.gender,staff.nationalityCode,cards.referenceNumber,cards.id ) " +
            "from JpaCompanyStaff staff " +
            "join staff.digitalIds digitalId " +
            "join digitalId.companyStaffCards cards " +
            "join cards.companyRitualSeason companyRitualSeason " +
            "join companyRitualSeason.ritualSeason ritualSeason " +
            "join companyRitualSeason.company company " +
            "where staff.id = :staffId ")
    CompanyStaffVO findStaffById(@Param("staffId") long staffId);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.CompanyStaffFullVO(" +
            " digitalId.suin, staff.fullNameEn,staff.fullNameAr, staff.titleCode, staff.customJobTitle, staff.photo, " +
            " cards.referenceNumber,cards.statusCode,ritualSeason.ritualTypeCode,ritualSeason.seasonYear, company.labelEn, company.labelAr,company.code,staff.idNumber,staff.passportNumber,staff.fullNameOrigin,staff.dateOfBirthGregorian,staff.dateOfBirthHijri,staff.gender,staff.nationalityCode,cards.referenceNumber,cards.id ) " +
            "from JpaCompanyStaff staff " +
            "join staff.digitalIds digitalId " +
            "join digitalId.companyStaffCards cards " +
            "join cards.companyRitualSeason companyRitualSeason " +
            "join companyRitualSeason.ritualSeason ritualSeason " +
            "join companyRitualSeason.company company " +
            "where staff.id = :staffId ")
    CompanyStaffFullVO findOrganizerStaffById(@Param("staffId") long staffId);

    @Modifying
    @Query("update JpaCompanyStaff staff set staff.titleCode = :jobTitle, staff.customJobTitle = :customJobTitle, staff.updateDate = CURRENT_TIMESTAMP where staff.id =:staffId")
    int updateCompanyStaffJobTitle(@Param("jobTitle") String jobTitle, @Param("customJobTitle") String customJobTitle, @Param("staffId") long staffId);



    @Query("SELECT cs FROM JpaCompanyStaff cs JOIN cs.digitalIds di JOIN di.companyStaffCards csc JOIN " +
            "csc.companyRitualSeason cr JOIN cr.company c WHERE c.code=:code AND  c.typeCode =:typeCode")
    List<JpaCompanyStaff> findStaffByCompanyCodeAndCompanyTypeCode(@Param("code") String code, @Param("typeCode") long typeCode);

    @Query("SELECT a FROM JpaCompanyStaff a WHERE a.id IN :selectedStaff")
    List<JpaCompanyStaff> findAllByIds(@Param("selectedStaff") List<Long> selectedStaff);

   List<JpaCompanyStaff> findAllByRegisteredTrue();

    long countJpaCompanyStaffByRegisteredTrue();

    @Query("SELECT a FROM JpaCompanyStaff a WHERE a.id IN :selectedStaffs")
    Page<JpaCompanyStaff> findByIds(@Param("selectedStaffs") List<Long> selectedStaffs, Pageable pageable);


}
