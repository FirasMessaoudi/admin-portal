/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the shc_area_layers_lk database table.
 *
 * @author Jaafer Jarray
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_area_layers_lk")
@NamedQuery(name = "JpaAreaLayerLookup.findAll", query = "SELECT j FROM JpaAreaLayerLookup j")
public class JpaAreaLayerLookup extends JpaLocalizedLookup {

    private static final long serialVersionUID = -913161328642286375L;

}
