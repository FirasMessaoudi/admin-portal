/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the shc_applicant database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_applicant")
@NamedQuery(name = "JpaApplicant.findAll", query = "SELECT j FROM JpaApplicant j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicant implements Serializable {

    private static final long serialVersionUID = -6527928280666512305L;

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

    private String photo;

    @Column(name = "biometric_data_finger")
    private String biometricDataFinger;

    @Column(name = "biometric_data_face")
    private String biometricDataFace;

    @Column(name = "education_level_code")
    private String educationLevelCode;

    @Column(name = "preferred_language")
    private String preferredLanguage;

    @ManyToOne
    @JoinColumn(name = "data_request_record_id")
    private JpaDataRequestRecord dataRequestRecord;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "applicant_id")
    private List<JpaApplicantDigitalId> digitalIds;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicant")
    private List<JpaApplicantRelative> relatives;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "applicant")
    private List<JpaApplicantRitual> rituals;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "applicant")
    private List<JpaApplicantContact> contacts;

    private boolean registered;

    @Column(name = "first_package_reference_number")
    private String packageReferenceNumber;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @Column(name = "mobile_logged_in")
    private Boolean mobileLoggedIn;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
        upperCase();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
        upperCase();
    }

    private void upperCase() {
        maritalStatusCode = StringUtils.upperCase(maritalStatusCode);
        gender = StringUtils.upperCase(gender);
        nationalityCode = StringUtils.upperCase(nationalityCode);
        educationLevelCode = StringUtils.upperCase(educationLevelCode);
        educationLevelCode = StringUtils.upperCase(educationLevelCode);
    }
}
