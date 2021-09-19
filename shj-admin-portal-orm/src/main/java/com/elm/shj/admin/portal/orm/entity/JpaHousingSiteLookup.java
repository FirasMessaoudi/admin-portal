/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_housing_site_lk database table.
 *
 * @author Ahmed elsayed
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_housing_site_lk")
@NamedQuery(name = "JpaHousingSiteLookup.findAll", query = "SELECT j FROM JpaHousingSiteLookup j")
public class JpaHousingSiteLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -8390783034766649764L;
}
