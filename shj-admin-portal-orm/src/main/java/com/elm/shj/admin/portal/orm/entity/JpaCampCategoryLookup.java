/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_camp_category_lk database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_camp_category_lk")
@NamedQuery(name = "JpaCampCategoryLookup.findAll", query = "SELECT j FROM JpaCampCategoryLookup j")
public class JpaCampCategoryLookup extends JpaLocalizedLookup {


    private static final long serialVersionUID = -7713188758171174813L;
}
