package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantMainData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for applicant main data.
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public interface ApplicantMainDataRepository extends JpaRepository<JpaApplicantMainData, Long> {

//    /*From Applicant table*/
//    private long id;
//    private String gender;
//    private String nationalityCode;
//    private String idNumber;
//    private String idNumberOriginal;
//    private String passportNumber;
//    private Date dateOfBirthGregorian;
//    private Long dateOfBirthHijri;
//    private String fullNameAr;
//    private String fullNameEn;
//    private String fullNameOrigin;
//    private String maritalStatusCode;
//    private String photo;
//
//    /*From Applicant, Applicant Ritual and Applicant Relatives*/
//    private List<ApplicantRelativeDto> relatives;
//
//    /*From Applicant and Applicant Contacts*/
//    private List<ApplicantContactDto> contacts;
//
//    /*From Applicant Ritual, Applicant Package, Ritual Package, Company Ritual Season and Ritual Season*/
//    private String ritualTypeCode;
//
//    /*From Applicant Ritual and Applicant Card*/
//    private String cardReferenceNumber;
//    private String cardStatusCode;
//
//    /*From Applicant and Applicant Digital ID*/
//    private String statusCode;
//    private String uin;




    @Query(value = "SELECT a FROM JpaApplicantMainData a INNER JOIN JpaApplicantDigitalId adi ON adi.applicantId = a.id " +
            "INNER JOIN JpaApplicantContact ac ON ac.applicant.id = a.id WHERE adi.uin = :uin")
    JpaApplicantMainData findByUin(@Param("uin") String uin);
}
