/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_meal_time_lk database table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_meal_time_lk")
@NamedQuery(name = "JpaMealTimeLookup.findAll", query = "SELECT j FROM JpaMealTimeLookup j")

public class JpaMealTimeLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -7567424201879477884L;
}
