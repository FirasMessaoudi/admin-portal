/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the sha_country_lk database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_country_lk")
@NamedQuery(name = "JpaCountryLookup.findAll", query = "SELECT j FROM JpaCountryLookup j")
@Data
@NoArgsConstructor
public class JpaCountryLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = 722517589940056586L;
}
