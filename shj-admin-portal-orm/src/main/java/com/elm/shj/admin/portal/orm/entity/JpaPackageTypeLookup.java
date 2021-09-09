/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_package_type_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_package_type_lk")
@NamedQuery(name = "JpaPackageTypeLookup.findAll", query = "SELECT j FROM JpaPackageTypeLookup j")
public class JpaPackageTypeLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = 7218313874312530826L;
}
