/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the sha_marital_status_lk database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_marital_status_lk")
@NamedQuery(name = "JpaMaritalStatusLookup.findAll", query = "SELECT j FROM JpaMaritalStatusLookup j")
public class JpaMaritalStatusLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -5634654515494631579L;
}
