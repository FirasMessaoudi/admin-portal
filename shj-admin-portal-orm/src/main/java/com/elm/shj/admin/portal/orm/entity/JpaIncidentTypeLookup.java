/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_incident_type_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_incident_type_lk")
@NamedQuery(name = "JpaIncidentTypeLookup.findAll", query = "SELECT j FROM JpaIncidentTypeLookup j")
public class JpaIncidentTypeLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = 5051231818056187879L;
}
