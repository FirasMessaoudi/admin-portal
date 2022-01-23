/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantEmergencyDataUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Repository for Applicant Emergency Data Upload Table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
public interface ApplicantEmergencyDataUploadRepository extends JpaRepository<JpaApplicantEmergencyDataUpload, Long> {

    @Query(value = "select aedu from JpaApplicantEmergencyDataUpload aedu where " +
            "(aedu.idNumber = :idNumber and aedu.dateOfBirthHijri = :dateOfBirthHijri) or " +
            "(aedu.passportNumber = :passportNumber and aedu.dateOfBirthGregorian = :dateOfBirthGregorian) and aedu.packageReferenceNumber = :packageCode")
    JpaApplicantEmergencyDataUpload findByBasicInfo(@Param("idNumber") String idNumber, @Param("dateOfBirthHijri") Long dateOfBirthHijri,
                                 @Param("passportNumber") String passportNumber, @Param("dateOfBirthGregorian") Date dateOfBirthGregorian, @Param("packageCode") String packageCode);
}
