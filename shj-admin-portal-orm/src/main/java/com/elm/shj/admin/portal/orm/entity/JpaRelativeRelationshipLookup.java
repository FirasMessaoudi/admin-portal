/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the sha_relative_relationship_lk database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_relative_relationship_lk")
@NamedQuery(name = "JpaRelativeRelationshipLookup.findAll", query = "SELECT j FROM JpaRelativeRelationshipLookup j")
public class JpaRelativeRelationshipLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -1388049731108498608L;
}
