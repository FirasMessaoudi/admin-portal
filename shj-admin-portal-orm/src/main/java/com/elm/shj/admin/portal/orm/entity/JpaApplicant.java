/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the sha_applicant database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_applicant")
@NamedQuery(name = "JpaApplicant.findAll", query = "SELECT j FROM JpaApplicant j")
@Data
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
    private long idNumber;

    @Column(name = "id_number_original")
    private String idNumberOriginal;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "date_of_birth_gregorian")
    private Date dateOfBirthGregorian;

    @Column(name = "date_of_birth_hijri")
    private long dateOfBirthHijri;

    @Column(name = "full_name_ar")
    private String fullNameAr;

    @Column(name = "full_name_en")
    private String fullNameEn;

    @Column(name = "full_name_origin")
    private String fullNameOrigin;

    @Column(name = "marital_status")
    private long maritalStatus;

    private String photo;

    @Column(name = "request_id")
    private long requestId;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicant")
    private List<JpaApplicantDigitalId> digitalIds;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicant")
    private List<JpaApplicantRelative> relatives;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicant")
    private List<JpaApplicantRitual> rituals;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicant")
    private List<JpaApplicantContact> contacts;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<JpaApplicantHealth> healths;

    private long status;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;
}
