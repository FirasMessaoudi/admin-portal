/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the sha_applicant_contact database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_applicant_contact")
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
    private int localMobileNumber;

    @Column(name = "intl_mobile_number")
    private int intlMobileNumber;

    @Column(name = "street_name")
    private String street_name;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "building_number")
    private int buildingNumber;

    @Column(name = "postal_code")
    private int postal_code;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;
}
