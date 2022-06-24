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
@NamedQuery(name = "JpaApplicantBasic.findAll", query = "SELECT j FROM JpaApplicantBasic j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantBasic implements Serializable {

    private static final long serialVersionUID = -6527928280666512305L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    private String gender;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "nationality_code")
    private String nationalityCode;

    @Column(name = "date_of_birth_gregorian")
    private Date dateOfBirthGregorian;

    @Column(name = "date_of_birth_hijri")
    private Long dateOfBirthHijri;

    @Column(name = "first_package_reference_number")
    private String packageReferenceNumber;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "applicant_id", updatable = false)
    private List<JpaApplicantDigitalId> digitalIds;

    @Column(name = "full_name_ar")
    private String fullNameAr;

    @Column(name = "full_name_en")
    private String fullNameEn;

    @Column(name = "full_name_origin")
    private String fullNameOrigin;

    @Column(name = "preferred_language")
    private String preferredLanguage;

    @Column(name = "company_code")
    private String companyCode;

    @Column(name = "establishment_ref_code")
    private Integer establishmentRefCode;

    @Column(name = "mission_ref_code")
    private Integer missionRefCode;

    @Column(name = "makkah_service_group_ref_code")
    private Long serviceGroupMakkahCode;

    @Column(name = "madina_service_group_ref_code")
    private Long serviceGroupMadinaCode;

    private String photo;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "applicant")
    private List<JpaApplicantContactBasic> contacts;

    private boolean deleted;
    @Column(name = "id_number_original")
    private String idNumberOriginal;
    @Column(name = "marital_status_code")
    private String maritalStatusCode;

    @Column(name = "biometric_data_finger")
    private String biometricDataFinger;

    @Column(name = "biometric_data_face")
    private String biometricDataFace;

    @Column(name = "education_level_code")
    private String educationLevelCode;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
        upperCase();
    }

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
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
