/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_ritual_package database table.
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@Entity
@Table(name = "shc_ritual_package")
@NamedQuery(name = "JpaRitualPackageBasic.findAll", query = "SELECT j FROM JpaRitualPackageBasic j")
@Getter
@Setter
@NoArgsConstructor
public class JpaRitualPackageBasic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "package_type_code", nullable = false)
    private String packageTypeCode;

    private Float price;

    @Column(name = "departure_city")
    private String departureCity;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "hajj_office_makkah")
    private String hajjOfficeMakkah;

    @Column(name = "hajj_office_madina")
    private String hajjOfficeMadina;

    @Column(name = "package_name_ar")
    private String packageNameAr;

    @Column(name = "package_name_en")
    private String packageNameEn;

    @ManyToOne
    @JoinColumn(name = "company_ritual_season_id")
    private JpaCompanyRitualSeasonBasic companyRitualSeason;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }

}