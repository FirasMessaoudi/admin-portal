/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_incident_status_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_incident_status_lk")
@NamedQuery(name = "JpaIncidentStatusLookup.findAll", query = "SELECT j FROM JpaIncidentStatusLookup j")
public class JpaIncidentStatusLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -1876540546830728632L;
}
