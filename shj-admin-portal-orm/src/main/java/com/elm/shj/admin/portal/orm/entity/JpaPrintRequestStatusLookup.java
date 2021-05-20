/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the sha_print_request_status_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_print_request_status_lk")
@NamedQuery(name = "JpaPrintRequestStatusLookup.findAll", query = "SELECT j FROM JpaPrintRequestStatusLookup j")
public class JpaPrintRequestStatusLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = 5385841771862452757L;
}
