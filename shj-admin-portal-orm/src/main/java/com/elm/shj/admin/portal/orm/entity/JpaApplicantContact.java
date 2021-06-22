/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_applicant_contact database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_applicant_contact")
@NamedQuery(name = "JpaApplicantContact.findAll", query = "SELECT j FROM JpaApplicantContact j")
@Data
@NoArgsConstructor
public class JpaApplicantContact implements Serializable {

    private static final long serialVersionUID = 7220456892811254255L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    private JpaApplicant applicant;

    @Column(name = "language_list")
    private String languageList;

    private String email;

    @Column(name = "local_mobile_number")
    private String localMobileNumber;

    @Column(name = "intl_mobile_number")
    private String intlMobileNumber;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "building_number")
    private String buildingNumber;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

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
        countryCode = StringUtils.upperCase(countryCode);
        languageList = StringUtils.upperCase(languageList);
    }
}
