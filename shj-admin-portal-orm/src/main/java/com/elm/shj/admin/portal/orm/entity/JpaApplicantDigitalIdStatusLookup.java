/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_applicant_digital_id_status_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant_digital_id_status_lk")
@NamedQuery(name = "JpaApplicantDigitalIdStatusLookup.findAll", query = "SELECT j FROM JpaApplicantDigitalIdStatusLookup j")
public class JpaApplicantDigitalIdStatusLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = 7841949657705295859L;
}