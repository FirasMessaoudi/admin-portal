/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Company Staff data.
 *
 * @author salzoubi
 * @since 1.1.0
 **/
public interface CompanyStaffRepository extends JpaRepository<JpaCompanyStaff, Long> {

    List<JpaCompanyStaff> findByApplicantGroupsGroupApplicantListsApplicantUinAndApplicantGroupsCompanyRitualSeasonId(String applicantUin, long sid);

    @Query("SELECT CASE WHEN COUNT(a)> 0 THEN TRUE ELSE FALSE END " +
            "FROM JpaCompanyStaff a WHERE " +
            "(a.idNumber = :idNumber AND a.dateOfBirthHijri = :dateOfBirthHijri) OR " +
            "(a.passportNumber = :passportNumber AND a.dateOfBirthGregorian = :dateOfBirthGregorian)")
    boolean existsByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                              @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian);

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
}
