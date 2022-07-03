/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_country_lk database table.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_country_lk")
@NamedQuery(name = "JpaCountryLookup.findAll", query = "SELECT j FROM JpaCountryLookup j")
@Getter
@Setter
public class JpaCountryLookup extends JpaLocalizedLookup {

}
