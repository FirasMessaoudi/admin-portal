/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
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
 * The light entity version for the shc_applicant database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant")
@NamedQuery(name = "JpaApplicantLite.findAll", query = "SELECT j FROM JpaApplicantLite j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantLite implements Serializable {

    private static final long serialVersionUID = -2370960446369887033L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "nationality_code")
    private String nationalityCode;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "first_package_reference_number")
    private String packageReferenceNumber;

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

    //TODO(aflaifel): Remove this or user another version of JpaApplicant without photo
    private String photo;

    private String gender;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicant")
    private List<JpaApplicantContact> contacts;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "applicant_id")
    private List<JpaApplicantDigitalId> digitalIds;

    private boolean deleted;
}
