/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_country_lk database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_country_lk")
@NamedQuery(name = "JpaCountryLookup.findAll", query = "SELECT j FROM JpaCountryLookup j")
@Getter
@Setter
public class JpaCountryLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = 722517589940056586L;

    @Column(name = "country_phone_prefix")
    private String countryPhonePrefix;

    @Column(name = "country_name_prefix")
    private String countryNamePrefix;
}
