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
 * The persistent class for the shc_company_staff database table.
 * @author salzoubi
 * @since 1.1.0
 * **/
@Entity
@Table(name = "shc_company_staff")
@NamedQuery(name = "JpaCompanyStaff.findAll", query = "SELECT j FROM JpaCompanyStaff j")
@Getter
@Setter
@NoArgsConstructor
public class JpaCompanyStaff implements Serializable {

    private static final long serialVersionUID = -8603753627078284482L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "full_name_ar")
    private String fullNameAr;

    @Column(name = "full_name_en")
    private String fullNameEn;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "title_code")
    private String titleCode;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @Column(name = "mobile_number_intl", nullable = false)
    private String mobileNumberIntl;

    @Column(name = "email")
    private String email;

    private String photo;

    @Column(name = "id_number_original")
    private String idNumberOriginal;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "date_of_birth_gregorian")
    private Date dateOfBirthGregorian;

    @Column(name = "date_of_birth_hijri")
    private Long dateOfBirthHijri;

    private String gender;

    @Column(name = "nationality_code")
    private String nationalityCode;

    @Column(name = "full_name_origin")
    private String fullNameOrigin;

    @Column(name = "custom_job_title")
    private String customJobTitle;

    @Column(name = "creation_date",nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "data_request_record_id")
    private Long dataRequestRecordId;


    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "companyStaff",fetch = FetchType.EAGER)
    private List<JpaCompanyStaffDigitalId> digitalIds;

    @OneToMany(mappedBy = "groupLeader")
    private List<JpaApplicantGroup> applicantGroups;

    private boolean registered;

    @Column(name = "preferred_language")
    private String preferredLanguage;

    @Column(name = "country_code")
    private String countryCode;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
        upperCase();
    }

    private void upperCase() {
        gender = StringUtils.upperCase(gender);
        nationalityCode = StringUtils.upperCase(nationalityCode);
    }
}
