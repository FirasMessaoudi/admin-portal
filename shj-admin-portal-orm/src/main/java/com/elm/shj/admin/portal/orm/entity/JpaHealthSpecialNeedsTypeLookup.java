/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_health_special_needs_type_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_health_special_needs_type_lk")
@NamedQuery(name = "JpaHealthSpecialNeedsTypeLookup.findAll", query = "SELECT j FROM JpaHealthSpecialNeedsTypeLookup j")
public class JpaHealthSpecialNeedsTypeLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -3234084494886375689L;
}
