/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * the persistent class for the shc_applicant_package_catering table
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant_package_catering")
@NamedQuery(name = "JpaApplicantPackageCatering.findAll", query = "SELECT j FROM JpaApplicantPackageCatering j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantPackageCatering implements Serializable {

    private static final long serialVersionUID = 3356506323628579362L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "option_ar")
    private String optionAr;

    @Column(name = "option_en")
    private String optionEn;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_package_id")
    private JpaApplicantPackage applicantPackage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ritual_package_catering_id")
    private JpaPackageCatering packageCatering;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }

}
