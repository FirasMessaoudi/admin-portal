/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicant;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaff;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface CompanyStaffRepository extends JpaRepository<JpaCompanyStaff, Long> {

    List<JpaCompanyStaff> findByApplicantGroupsGroupApplicantListsApplicantUinAndCompanyRitualSeasonId(String applicantUin, long sid);

    @Query(value = "SELECT CASE WHEN COUNT(a)> 0 THEN TRUE ELSE FALSE END " +
            "FROM JpaCompanyStaff a WHERE " +
            "(a.idNumber = :idNumber AND a.dateOfBirthHijri = :dateOfBirthHijri) OR " +
            "(a.passportNumber = :passportNumber AND a.dateOfBirthGregorian = :dateOfBirthGregorian)")
    boolean existsByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                              @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian);


    @Query("select s from JpaCompanyStaff s where s.id not in (select sdi.companyStaff.id from JpaCompanyStaffDigitalId sdi where sdi.seasonYear =:season)")
    List<JpaCompanyStaff> findAllWithoutSuin(@Param("season") int season );

    @Query(value = "SELECT a " +
            "FROM JpaCompanyStaff a WHERE " +
            "(a.idNumber = :idNumber AND a.dateOfBirthHijri = :dateOfBirthHijri) OR " +
            "(a.passportNumber = :passportNumber AND a.dateOfBirthGregorian = :dateOfBirthGregorian)")
    JpaCompanyStaff findByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                                    @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian);


    @Query(value = "SELECT s FROM JpaCompanyStaff s JOIN s.digitalIds sdi WHERE sdi.suin = :suin")
    JpaCompanyStaff findBySuin(@Param("suin") String suin);
}
