/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_housing_category_lk database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_housing_category_lk")
@NamedQuery(name = "JpaHousingCategoryLookup.findAll", query = "SELECT j FROM JpaHousingCategoryLookup j")
public class JpaHousingCategoryLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = 6982496247922594913L;
}
