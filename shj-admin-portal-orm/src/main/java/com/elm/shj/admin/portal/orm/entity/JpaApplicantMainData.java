package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The main applicant data entity version for the shc_applicant database table.
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant")
@NamedQuery(name = "JpaApplicantMainData.findAll", query = "SELECT j FROM JpaApplicantMainData j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantMainData implements Serializable {

    private static final long serialVersionUID = 5603087008428077661L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    private String gender;

    @Column(name = "nationality_code")
    private String nationalityCode;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "id_number_original")
    private String idNumberOriginal;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "date_of_birth_gregorian")
    private Date dateOfBirthGregorian;

    @Column(name = "date_of_birth_hijri")
    private Long dateOfBirthHijri;

    @Column(name = "full_name_ar")
    private String fullNameAr;

    @Column(name = "full_name_en")
    private String fullNameEn;

    @Column(name = "full_name_origin")
    private String fullNameOrigin;

    @Column(name = "marital_status_code")
    private String maritalStatusCode;

    @Column(name = "emergency_contact_name")
    private String emergencyContactName;

    @Column(name = "emergency_contact_mobile_number")
    private String emergencyContactMobileNumber;

    private String photo;

    private boolean deleted;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    @JoinColumn(name = "applicant_id", updatable = false)
    private List<JpaApplicantDigitalId> digitalIds;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "applicant")
    private List<JpaApplicantContact> contacts;
}
