/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the sha_language_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_language_lk")
@NamedQuery(name = "JpaLanguageLookup.findAll",  query = "SELECT j FROM JpaLanguageLookup j")
public class JpaLanguageLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -1406711894288460494L;
}
