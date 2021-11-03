/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_meal_type_lk database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_meal_type_lk")
@NamedQuery(name = "JpaMealTypeLookup.findAll", query = "SELECT j FROM JpaMealTypeLookup j")

public class JpaMealTypeLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = 2945086896840028098L;
}
